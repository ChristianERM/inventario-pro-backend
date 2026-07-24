package com.portafolio.inventariopro.scheduler;

import com.portafolio.inventariopro.entity.Product;
import com.portafolio.inventariopro.entity.StockAlert;
import com.portafolio.inventariopro.enums.AlertStatus;
import com.portafolio.inventariopro.enums.AlertType;
import com.portafolio.inventariopro.repository.ProductRepository;
import com.portafolio.inventariopro.repository.StockAlertRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class LowStockScheduler {

    private final ProductRepository productRepository;
    private final StockAlertRepository stockAlertRepository;

    @Scheduled(fixedRate = 30000)
    public void checkLowStockProducts() {
        List<Product> lowStockProducts = productRepository.findLowStockProducts();

        if (lowStockProducts.isEmpty()) {
            log.info("Automatización de stock: no hay productos con bajo stock.");
            return;
        }

        log.warn("Automatización de stock: se encontraron {} producto(s) con bajo stock.", lowStockProducts.size());

        for (Product product : lowStockProducts) {
            log.warn(
                    "Producto con bajo stock: {} | SKU: {} | Stock actual: {} {} | Stock mínimo: {}",
                    product.getName(),
                    product.getSku(),
                    product.getCurrentStock(),
                    product.getUnit(),
                    product.getMinimumStock()
            );

            boolean hasActiveAlert = stockAlertRepository.existsByProductAndTypeAndStatusIn(
                    product,
                    AlertType.LOW_STOCK,
                    List.of(AlertStatus.PENDING, AlertStatus.NOTIFIED)
            );

            if (hasActiveAlert) {
                log.info("Ya existe una alerta activa para el producto: {}", product.getName());
                continue;
            }

            StockAlert alert = StockAlert.builder()
                    .product(product)
                    .type(AlertType.LOW_STOCK)
                    .status(AlertStatus.PENDING)
                    .message("Producto con bajo stock: " + product.getName())
                    .currentStock(product.getCurrentStock())
                    .minimumStock(product.getMinimumStock())
                    .build();

            stockAlertRepository.save(alert);

            log.warn("Nueva alerta de bajo stock guardada para el producto: {}", product.getName());
        }
    }
}
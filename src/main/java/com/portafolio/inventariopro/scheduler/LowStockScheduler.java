package com.portafolio.inventariopro.scheduler;

import com.portafolio.inventariopro.entity.Product;
import com.portafolio.inventariopro.repository.ProductRepository;
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
        }
    }
}
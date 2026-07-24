package com.portafolio.inventariopro.repository;

import com.portafolio.inventariopro.entity.Product;
import com.portafolio.inventariopro.entity.StockAlert;
import com.portafolio.inventariopro.enums.AlertStatus;
import com.portafolio.inventariopro.enums.AlertType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockAlertRepository extends JpaRepository<StockAlert, Long> {

    boolean existsByProductAndTypeAndStatus(
            Product product,
            AlertType type,
            AlertStatus status
    );
}
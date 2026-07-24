package com.portafolio.inventariopro.repository;

import com.portafolio.inventariopro.entity.Product;
import com.portafolio.inventariopro.entity.StockAlert;
import com.portafolio.inventariopro.enums.AlertStatus;
import com.portafolio.inventariopro.enums.AlertType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface StockAlertRepository extends JpaRepository<StockAlert, Long> {

    boolean existsByProductAndTypeAndStatus(
            Product product,
            AlertType type,
            AlertStatus status
    );

    boolean existsByProductAndTypeAndStatusIn(
            Product product,
            AlertType type,
            Collection<AlertStatus> statuses
    );

    @Query("""
            SELECT sa
            FROM StockAlert sa
            JOIN FETCH sa.product
            WHERE sa.status = :status
            ORDER BY sa.createdAt DESC
            """)
    List<StockAlert> findByStatusWithProductOrderByCreatedAtDesc(@Param("status") AlertStatus status);

    @Query("""
            SELECT sa
            FROM StockAlert sa
            JOIN FETCH sa.product
            WHERE sa.id = :id
            """)
    Optional<StockAlert> findByIdWithProduct(@Param("id") Long id);
}
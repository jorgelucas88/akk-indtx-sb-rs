package com.inditex.core.prices.repository;

import com.inditex.core.prices.entity.PricesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;

public interface PricesRepository extends JpaRepository<PricesEntity, Long> {
    @Query("SELECT p FROM PricesEntity p " +
            "JOIN BrandsEntity b on p.brand.id = b.id " +
            "JOIN ProductsEntity pr on p.product.id = pr.id " +
            "where 1=1 " +
            "AND b.id = :brandId " +
            "AND pr.id = :productId " +
            "and :date between p.startDate and p.endDate " +
            "ORDER BY p.priority " +
            "DESC LIMIT 1")
    PricesEntity getPrice(Timestamp date, Integer productId, Integer brandId);
}

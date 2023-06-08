package com.uygunaldim.repository;

import com.uygunaldim.data.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findById(Long id);
    Boolean existsByNameAndMarketName(String name, String market);
    Page<Product> findAllByCategoryAndPriceGreaterThanAndPriceLessThanOrderByPriceAsc(Pageable pageable, String category, BigDecimal minPrice, BigDecimal maxPrice);
    Page<Product> findAllByPriceGreaterThanAndPriceLessThanOrderByPriceAsc(Pageable pageable, BigDecimal minPrice, BigDecimal maxPrice);
    Page<Product> findAllByNameContainsIgnoreCaseAndPriceGreaterThanAndPriceLessThanOrderByPriceAsc(Pageable pageable, String name, BigDecimal minPrice, BigDecimal maxPrice);
    @Query("SELECT p.category FROM Product p GROUP BY p.category")
    List<String> findAllCategories();
}

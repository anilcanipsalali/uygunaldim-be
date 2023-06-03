package com.uygunaldim.repository;

import com.uygunaldim.data.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findById(Long id);
    Boolean existsByNameAndMarketName(String name, String market);
    Page<Product> findAllByCategoryOrderByPriceAsc(String category, Pageable pageable);
    Page<Product> findAllByOrderByPriceAsc(Pageable pageable);
}

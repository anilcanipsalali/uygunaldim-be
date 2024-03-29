package com.uygunaldim.repository;

import com.uygunaldim.data.entity.ProductLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductLogRepository extends JpaRepository<ProductLog, Long> {
    List<ProductLog> findAllByProductId(Long id);
}
package com.uygunaldim.service;

import com.uygunaldim.dto.ProductLogDto;
import com.uygunaldim.entity.ProductLog;
import com.uygunaldim.repository.ProductLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductLogService {

    private final ProductLogRepository productLogRepository;

    public List<ProductLogDto> getAllProductsLog() {
        return productLogRepository.findAll().stream().map(ProductLogDto::of).toList();
    }

    public List<ProductLogDto> getAllProductLogById(Long id) {
        return findAllProductLogById(id).stream().map(ProductLogDto::of).toList();
    }

    protected List<ProductLog> findAllProductLogById(Long id) {
        return productLogRepository.findAllByProductId(id);
    }
}

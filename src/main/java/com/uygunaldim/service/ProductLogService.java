package com.uygunaldim.service;

import com.uygunaldim.entity.Product;
import com.uygunaldim.entity.ProductLog;
import com.uygunaldim.entity.enums.OperationEnum;
import com.uygunaldim.repository.ProductLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductLogService {

    private final ProductLogRepository productLogRepository;

    public List<ProductLog> getAllProductsLog() {
        return productLogRepository.findAll();
    }

    public List<ProductLog> getAllProductLogById(Long id) {
        return findAllProductLogById(id);
    }

    public Product log(Product product, OperationEnum operation) {
        productLogRepository.save(ProductLog.builder()
                        .productId(product.getId())
                        .vendor(product.getVendor())
                        .name(product.getName())
                        .category(product.getCategory())
                        .weight(product.getWeight())
                        .price(product.getPrice())
                        .logo(product.getLogo())
                        .market(product.getMarket().getName())
                        .createdAt(LocalDateTime.now())
                        .operation(operation)
                        .build());
        return product;
    }

    protected List<ProductLog> findAllProductLogById(Long id) {
        return productLogRepository.findAllByProductId(id);
    }
}

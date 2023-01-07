package com.uygunaldim.dto;

import com.uygunaldim.entity.ProductLog;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ProductLogDto {
    private Long id;
    private ProductDto product;
    private LocalDateTime createdAt;

    public static ProductLogDto of(ProductLog productLog) {
        return ProductLogDto.builder()
                .id(productLog.getId())
                .product(ProductDto.of(productLog.getProduct()))
                .createdAt(productLog.getCreatedAt())
                .build();
    }
}

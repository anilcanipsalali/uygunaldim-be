package com.uygunaldim.dto;

import com.uygunaldim.entity.ProductLog;
import com.uygunaldim.entity.enums.OperationEnum;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ProductLogDto {
    private Long id;
    private ProductDto product;
    private LocalDateTime createdAt;
    private OperationEnum operation;

    public static ProductLogDto of(ProductLog productLog) {
        return ProductLogDto.builder()
                .id(productLog.getId())
                .product(ProductDto.of(productLog.getProduct()))
                .createdAt(productLog.getCreatedAt())
                .operation(productLog.getOperation())
                .build();
    }
}

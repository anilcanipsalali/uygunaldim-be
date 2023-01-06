package com.uygunaldim.dto;

import com.uygunaldim.entity.Product;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class ProductDto {
    private Long id;
    private Integer quantity;
    private String name;
    private BigDecimal weight;
    private BigDecimal price;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private MarketDto market;

    public static ProductDto of(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .quantity(product.getQuantity())
                .name(product.getName())
                .weight(product.getWeight())
                .price(product.getPrice())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .market(MarketDto.of(product.getMarket()))
                .build();
    }
}

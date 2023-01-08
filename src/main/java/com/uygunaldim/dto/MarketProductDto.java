package com.uygunaldim.dto;

import com.uygunaldim.entity.Product;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
@Builder
public class MarketProductDto {
    private Long id;
    private Integer quantity;
    private String vendor;
    private String name;
    private String type;
    private BigDecimal weight;
    private BigDecimal price;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static MarketProductDto of(Product product) {
        return MarketProductDto.builder()
                .id(product.getId())
                .quantity(product.getQuantity())
                .vendor(product.getVendor())
                .name(product.getName())
                .type(product.getType())
                .weight(product.getWeight())
                .price(product.getPrice())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }
}

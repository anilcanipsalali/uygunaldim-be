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
    private String vendor;
    private String name;
    private String category;
    private BigDecimal weight;
    private BigDecimal price;
    private String logo;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private ProductMarketDto market;

    public static ProductDto of(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .vendor(product.getVendor())
                .name(product.getName())
                .category(product.getCategory())
                .weight(product.getWeight())
                .price(product.getPrice())
                .logo(product.getLogo())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .market(ProductMarketDto.of(product.getMarket()))
                .build();
    }
}

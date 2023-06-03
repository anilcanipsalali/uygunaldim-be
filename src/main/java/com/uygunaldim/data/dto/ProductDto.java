package com.uygunaldim.data.dto;

import com.uygunaldim.data.entity.Product;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class ProductDto {
    private Long id;
    private String vendor;
    private String name;
    private String category;
    private BigDecimal price;
    private String logo;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private ProductMarketDto market;
    private List<ProductReviewDto> reviews;

    public static ProductDto of(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .vendor(product.getVendor())
                .name(product.getName())
                .category(product.getCategory())
                .price(product.getPrice())
                .logo(product.getLogo())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .market(ProductMarketDto.of(product.getMarket()))
                .reviews(product.getReviews().stream().map(ProductReviewDto::of).toList())
                .build();
    }
}

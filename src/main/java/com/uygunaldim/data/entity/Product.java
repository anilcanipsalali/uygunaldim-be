package com.uygunaldim.data.entity;

import com.uygunaldim.data.dto.MarketProductDto;
import com.uygunaldim.data.dto.ProductDto;
import com.uygunaldim.data.dto.ReviewProductDto;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "PRODUCT")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class Product {
    @Id
    @SequenceGenerator(name = "seqProductId", sequenceName = "seq_product_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqProductId")
    private Long id;
    @Column(name = "VENDOR")
    private String vendor;
    @Column(name = "NAME")
    private String name;
    @Column(name = "CATEGORY")
    private String category;
    @Column(name = "PRICE")
    private BigDecimal price;
    @Column(name = "LOGO")
    private String logo;
    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;
    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "market_id", referencedColumnName = "id")
    private Market market;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Review> reviews;

    public static Product of(ProductDto productDto) {
        return Product.builder()
                .id(productDto.getId())
                .vendor(productDto.getVendor())
                .name(productDto.getName())
                .category(productDto.getCategory())
                .price(productDto.getPrice())
                .logo(productDto.getLogo())
                .createdAt(productDto.getCreatedAt())
                .updatedAt(productDto.getUpdatedAt())
                .market(Market.of(productDto.getMarket()))
                .reviews(productDto.getReviews().stream().map(Review::of).toList())
                .build();
    }

    public static Product of(MarketProductDto marketProductDto) {
        return Product.builder()
                .id(marketProductDto.getId())
                .vendor(marketProductDto.getVendor())
                .name(marketProductDto.getName())
                .category(marketProductDto.getCategory())
                .price(marketProductDto.getPrice())
                .logo(marketProductDto.getLogo())
                .createdAt(marketProductDto.getCreatedAt())
                .updatedAt(marketProductDto.getUpdatedAt())
                .build();
    }

    public static Product of(ReviewProductDto reviewProductDto) {
        return Product.builder()
                .id(reviewProductDto.getId())
                .build();
    }
}

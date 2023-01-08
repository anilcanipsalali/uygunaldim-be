package com.uygunaldim.entity;

import com.uygunaldim.dto.MarketProductDto;
import com.uygunaldim.dto.ProductDto;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

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
    @Column(name = "WEIGHT")
    private BigDecimal weight;
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

    public static Product of(ProductDto productDto) {
        return Product.builder()
                .id(productDto.getId())
                .vendor(productDto.getVendor())
                .name(productDto.getName())
                .category(productDto.getCategory())
                .weight(productDto.getWeight())
                .price(productDto.getPrice())
                .logo(productDto.getLogo())
                .createdAt(productDto.getCreatedAt())
                .updatedAt(productDto.getUpdatedAt())
                .market(Market.of(productDto.getMarket()))
                .build();
    }

    public static Product of(MarketProductDto marketProductDto) {
        return Product.builder()
                .id(marketProductDto.getId())
                .vendor(marketProductDto.getVendor())
                .name(marketProductDto.getName())
                .category(marketProductDto.getCategory())
                .weight(marketProductDto.getWeight())
                .price(marketProductDto.getPrice())
                .logo(marketProductDto.getLogo())
                .createdAt(marketProductDto.getCreatedAt())
                .updatedAt(marketProductDto.getUpdatedAt())
                .build();
    }
}

package com.uygunaldim.entity;

import com.uygunaldim.dto.MarketProductDto;
import com.uygunaldim.dto.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class Product {
    @Id
    @SequenceGenerator(name = "seqProductId", sequenceName = "seq_product_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqProductId")
    private Long id;
    @Column(name = "QUANTITY")
    private Integer quantity;
    @Column(name = "NAME")
    private String name;
    @Column(name = "WEIGHT")
    private BigDecimal weight;
    @Column(name = "PRICE")
    private BigDecimal price;
    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;
    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "market_id", referencedColumnName = "id")
    private Market market;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductLog> productLogs;

    public static Product of(ProductDto productDto) {
        return Product.builder()
                .id(productDto.getId())
                .quantity(productDto.getQuantity())
                .name(productDto.getName())
                .weight(productDto.getWeight())
                .price(productDto.getPrice())
                .createdAt(productDto.getCreatedAt())
                .updatedAt(productDto.getUpdatedAt())
                .market(Market.of(productDto.getMarket()))
                .build();
    }

    public static Product of(MarketProductDto marketProductDto) {
        return Product.builder()
                .id(marketProductDto.getId())
                .quantity(marketProductDto.getQuantity())
                .name(marketProductDto.getName())
                .weight(marketProductDto.getWeight())
                .price(marketProductDto.getPrice())
                .createdAt(marketProductDto.getCreatedAt())
                .updatedAt(marketProductDto.getUpdatedAt())
                .build();
    }
}

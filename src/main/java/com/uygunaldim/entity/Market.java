package com.uygunaldim.entity;

import com.uygunaldim.dto.MarketDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "MARKET")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Market {
    @Id
    @SequenceGenerator(name = "seqMarketId", sequenceName = "seq_market_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqMarketId")
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;
    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;
    @OneToMany(mappedBy = "market", cascade = CascadeType.ALL)
    private List<Product> products;

    public static Market of(MarketDto marketDto) {
        return Market.builder()
                .id(marketDto.getId())
                .name(marketDto.getName())
                .createdAt(marketDto.getCreatedAt())
                .updatedAt(marketDto.getUpdatedAt())
                .products(marketDto.getProducts().stream().map(Product::of).toList())
                .build();
    }
}

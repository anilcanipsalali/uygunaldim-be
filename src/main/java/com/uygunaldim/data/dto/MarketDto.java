package com.uygunaldim.data.dto;

import com.uygunaldim.data.entity.Market;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class MarketDto {
    private Long id;
    private String name;
    private String logo;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<MarketProductDto> products;

    public static MarketDto of(Market market) {
        return MarketDto.builder()
                .id(market.getId())
                .name(market.getName())
                .logo(market.getLogo())
                .createdAt(market.getCreatedAt())
                .updatedAt(market.getUpdatedAt())
                .products(market.getProducts().stream().map(MarketProductDto::of).toList())
                .build();
    }
}

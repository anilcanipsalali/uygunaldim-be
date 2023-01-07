package com.uygunaldim.dto;

import com.uygunaldim.entity.Market;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ProductMarketDto {
    private Long id;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static ProductMarketDto of(Market market) {
        return ProductMarketDto.builder()
                .id(market.getId())
                .name(market.getName())
                .createdAt(market.getCreatedAt())
                .updatedAt(market.getUpdatedAt())
                .build();
    }
}

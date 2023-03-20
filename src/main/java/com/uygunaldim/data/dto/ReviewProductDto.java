package com.uygunaldim.data.dto;

import com.uygunaldim.data.entity.Product;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class ReviewProductDto {
    private Long id;

    public static ReviewProductDto of(Product product) {
        return ReviewProductDto.builder()
                .id(product.getId())
                .build();
    }
}

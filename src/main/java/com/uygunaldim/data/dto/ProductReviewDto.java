package com.uygunaldim.data.dto;

import com.uygunaldim.data.entity.Review;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductReviewDto {
    private Long id;
    private String username;
    private String comment;
    private int star;

    public static ProductReviewDto of(Review review) {
        return ProductReviewDto.builder()
                .id(review.getId())
                .comment(review.getComment())
                .star(review.getStar())
                .username(review.getUsername())
                .build();
    }
}

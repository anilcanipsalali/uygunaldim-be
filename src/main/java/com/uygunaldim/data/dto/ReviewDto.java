package com.uygunaldim.data.dto;

import com.uygunaldim.data.entity.Review;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewDto {
    private Long id;
    private String username;
    private String comment;
    private int star;
    private ReviewProductDto product;

    public static ReviewDto of(Review review) {
        return ReviewDto.builder()
                .id(review.getId())
                .comment(review.getComment())
                .star(review.getStar())
                .username(review.getUsername())
                .product(ReviewProductDto.of(review.getProduct()))
                .build();
    }
}

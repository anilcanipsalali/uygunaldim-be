package com.uygunaldim.data.entity;

import com.uygunaldim.data.dto.ProductReviewDto;
import com.uygunaldim.data.dto.ReviewDto;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "REVIEW")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class Review {
    @Id
    @SequenceGenerator(name = "seqReviewId", sequenceName = "seq_review_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqReviewId")
    private Long id;
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "COMMENT")
    private String comment;
    @Column(name = "STAR")
    private int star;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    public static Review of(ReviewDto reviewDto) {
        return Review.builder()
                .id(reviewDto.getId())
                .username(reviewDto.getUsername())
                .comment(reviewDto.getComment())
                .star(reviewDto.getStar())
                .product(Product.of(reviewDto.getProduct()))
                .build();
    }

    public static Review of(ProductReviewDto productReviewDto) {
        return Review.builder()
                .id(productReviewDto.getId())
                .username(productReviewDto.getUsername())
                .comment(productReviewDto.getComment())
                .star(productReviewDto.getStar())
                .build();
    }
}

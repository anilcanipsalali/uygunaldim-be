package com.uygunaldim.service;

import com.uygunaldim.data.dto.ReviewDto;
import com.uygunaldim.data.dto.request.ReviewRequest;
import com.uygunaldim.data.entity.Product;
import com.uygunaldim.data.entity.Review;
import com.uygunaldim.exception.NotFoundException;
import com.uygunaldim.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.uygunaldim.util.ApplicationConstants.REVIEW_NOT_FOUND;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ProductService productService;

    public List<ReviewDto> getAllReviews() {
        return reviewRepository.findAll().stream().map(ReviewDto::of).toList();
    }

    public List<ReviewDto> getAllReviewsByProductId(Long id) {
        return reviewRepository.findAllByProductId(id).stream().map(ReviewDto::of).toList();
    }

    public ReviewDto getReviewById(Long id) {
        return ReviewDto.of(findReviewById(id));
    }

    protected Review findReviewById(Long id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(REVIEW_NOT_FOUND, "Review could not found by id: " + id));
    }

    public ReviewDto createReview(ReviewRequest request) {
        return ReviewDto.of(reviewRepository.save(Review.builder()
                .comment(request.getComment())
                .username(request.getUsername())
                .star(request.getStar())
                .product(Product.of(productService.getProductById(request.getProductId())))
                .build()));
    }

    public ReviewDto updateReview(ReviewRequest request) {
        Review review = findReviewById(request.getId());
        review.setComment(request.getComment());
        review.setUsername(request.getUsername());
        review.setStar(request.getStar());
        review.setProduct(Product.of(productService.getProductById(request.getProductId())));
        return ReviewDto.of(review);
    }

    public String deleteReview(Long id) {
        Long reviewId = findReviewById(id).getId();
        reviewRepository.deleteById(id);
        return "Review with id: " + reviewId + " is deleted!";
    }
}

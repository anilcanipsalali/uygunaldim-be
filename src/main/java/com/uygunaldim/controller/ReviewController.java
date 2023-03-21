package com.uygunaldim.controller;

import com.uygunaldim.data.dto.ReviewDto;
import com.uygunaldim.data.dto.request.ReviewRequest;
import com.uygunaldim.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/review")
@CrossOrigin(allowedHeaders = "*", origins = "*")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping
    public ResponseEntity<List<ReviewDto>> getAllReviews() {
        return ResponseEntity.ok(reviewService.getAllReviews());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewDto> getReviewById(@PathVariable Long id) {
        return ResponseEntity.ok(reviewService.getReviewById(id));
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<List<ReviewDto>> getAllReviewsByProductId(@PathVariable Long id) {
        return ResponseEntity.ok(reviewService.getAllReviewsByProductId(id));
    }

    @PostMapping("/create/{productId}")
    public ResponseEntity<ReviewDto> createReview(@Valid @RequestBody ReviewRequest request) {
        return ResponseEntity.ok(reviewService.createReview(request));
    }

    @PostMapping("/update/{productId}")
    public ResponseEntity<ReviewDto> updateReview(@Valid @RequestBody ReviewRequest request) {
        return ResponseEntity.ok(reviewService.updateReview(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable Long id) {
        return ResponseEntity.ok(reviewService.deleteReview(id));
    }
}

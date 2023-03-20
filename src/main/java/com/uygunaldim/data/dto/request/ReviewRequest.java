package com.uygunaldim.data.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewRequest {
    private Long id;
    private Long productId;
    private String username;
    private String comment;
    private int star;
}

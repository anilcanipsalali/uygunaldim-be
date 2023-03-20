package com.uygunaldim.data.dto.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
public class MarketRequest {
    private Long id;
    @NotEmpty(message = "Market name cannot be empty!")
    private String name;
    @NotEmpty(message = "Market logo cannot be empty!")
    private String logo;
}

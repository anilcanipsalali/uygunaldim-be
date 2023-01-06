package com.uygunaldim.dto.request;

import com.uygunaldim.dto.ProductDto;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
public class MarketRequest {
    private Long id;
    @NotNull(message = "Market name cannot be null!")
    @NotBlank(message = "Market name cannot be blank!")
    private String name;
    private List<ProductDto> products;
}

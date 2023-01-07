package com.uygunaldim.dto.request;

import com.uygunaldim.dto.MarketProductDto;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@Builder
public class MarketRequest {
    private Long id;
    @NotEmpty(message = "Market name cannot be empty!")
    private String name;
    private List<MarketProductDto> products;
}

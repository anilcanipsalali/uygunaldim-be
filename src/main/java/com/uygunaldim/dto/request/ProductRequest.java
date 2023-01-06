package com.uygunaldim.dto.request;

import com.uygunaldim.dto.MarketDto;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
public class ProductRequest {
    private Long id;
    @NotNull(message = "Product quantity cannot be null!")
    @NotBlank(message = "Product quantity cannot be blank!")
    private Integer quantity;
    @NotNull(message = "Product name cannot be null!")
    @NotBlank(message = "Product name cannot be blank!")
    private String name;
    @NotNull(message = "Product weight cannot be null!")
    @NotBlank(message = "Product weight cannot be blank!")
    private BigDecimal weight;
    @NotNull(message = "Product price cannot be null!")
    @NotBlank(message = "Product price cannot be blank!")
    private BigDecimal price;
    @NotNull(message = "Product market cannot be null!")
    @NotBlank(message = "Product market cannot be blank!")
    private MarketDto market;
}

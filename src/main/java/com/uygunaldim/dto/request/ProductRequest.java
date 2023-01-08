package com.uygunaldim.dto.request;

import com.uygunaldim.dto.ProductMarketDto;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
public class ProductRequest {
    private Long id;
    @NotEmpty(message = "Product vendor cannot be blank!")
    private String vendor;
    @NotEmpty(message = "Product name cannot be blank!")
    private String name;
    @NotEmpty(message = "Product category cannot be blank!")
    private String category;
    @NotNull(message = "Product weight cannot be blank!")
    private BigDecimal weight;
    @NotNull(message = "Product price cannot be blank!")
    private BigDecimal price;
    @NotEmpty(message = "Product logo cannot be blank!")
    private String logo;
    @NotNull(message = "Product market cannot be blank!")
    private ProductMarketDto market;
}

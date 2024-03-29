package com.uygunaldim.service;

import com.uygunaldim.data.dto.ProductDto;
import com.uygunaldim.data.dto.request.ProductRequest;
import com.uygunaldim.data.entity.Product;
import com.uygunaldim.data.entity.enums.OperationEnum;
import com.uygunaldim.exception.AlreadyExistsException;
import com.uygunaldim.exception.NotFoundException;
import com.uygunaldim.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static com.uygunaldim.util.ApplicationConstants.PRODUCT_BAD_REQUEST;
import static com.uygunaldim.util.ApplicationConstants.PRODUCT_NOT_FOUND;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductLogService productLogService;
    private final MarketService marketService;

    public Page<ProductDto> getAllProducts(int offset, int pageSize, BigDecimal minPrice, BigDecimal maxPrice) {
        return productRepository.findAllByPriceGreaterThanAndPriceLessThanOrderByPriceAsc(
                PageRequest.of(offset, pageSize), minPrice, maxPrice).map(ProductDto::of);
    }

    public Page<ProductDto> getAllProductsByCategory(int offset, int pageSize, String category, BigDecimal minPrice, BigDecimal maxPrice) {
        return productRepository.findAllByCategoryAndPriceGreaterThanAndPriceLessThanOrderByPriceAsc(
                PageRequest.of(offset, pageSize), category, minPrice, maxPrice).map(ProductDto::of);
    }

    public Page<ProductDto> getAllProductsByName(int offset, int pageSize, String name, BigDecimal minPrice, BigDecimal maxPrice) {
        return productRepository.findAllByNameContainsIgnoreCaseAndPriceGreaterThanAndPriceLessThanOrderByPriceAsc(
                PageRequest.of(offset, pageSize), name, minPrice, maxPrice).map(ProductDto::of);
    }

    public ProductDto getProductById(Long id) {
        return ProductDto.of(findProductById(id));
    }

    public List<String> getAllCategories() {
        return productRepository.findAllCategories();
    }

    protected Product findProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(PRODUCT_NOT_FOUND, "Product could not found by id: " + id));
    }

    public ProductDto updateProduct(ProductRequest request) {
        Product product = findProductById(request.getId());
        product.setVendor(request.getVendor());
        product.setName(request.getName());
        product.setCategory(request.getCategory());
        product.setPrice(request.getPrice());
        product.setLogo(request.getLogo());
        product.setMarket(marketService.getMarketIfExistsOrCreate(request.getMarket().getName(), request.getMarket().getLogo()));
        product.setUpdatedAt(LocalDateTime.now());

        return ProductDto.of(productLogService.log(productRepository.save(product), OperationEnum.UPDATE));
    }

    public ProductDto createProduct(ProductRequest request) {
        if (isProductExistsByNameAndMarket(request)) {
            throw new AlreadyExistsException(PRODUCT_BAD_REQUEST, "Product already exists with name: "
                    + request.getName() + " and market: " + request.getMarket().getName());
        }

        Product product = productLogService.log(
                productRepository.save(Product.builder()
                    .vendor(request.getVendor())
                    .name(request.getName())
                    .category(request.getCategory())
                    .price(request.getPrice())
                    .logo(request.getLogo())
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .market(marketService.getMarketIfExistsOrCreate(request.getMarket().getName(),
                            request.getMarket().getLogo()))
                    .reviews(Collections.emptyList())
                    .build()), OperationEnum.CREATE
                );

        return ProductDto.of(product);
    }

    public String deleteProduct(Long id) {
        Product product = findProductById(id);
        productRepository.deleteById(id);
        productLogService.log(product, OperationEnum.DELETE);
        return "Product with name: " + product.getName() + " is deleted!";
    }

    private boolean isProductExistsByNameAndMarket(ProductRequest request) {
        return productRepository.existsByNameAndMarketName(request.getName(), request.getMarket().getName());
    }
}

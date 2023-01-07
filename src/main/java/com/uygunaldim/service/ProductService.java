package com.uygunaldim.service;

import com.uygunaldim.dto.ProductDto;
import com.uygunaldim.dto.request.ProductRequest;
import com.uygunaldim.entity.Product;
import com.uygunaldim.exception.AlreadyExistsException;
import com.uygunaldim.exception.NotFoundException;
import com.uygunaldim.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final MarketService marketService;

    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream().map(ProductDto::of).toList();
    }

    public ProductDto getProductById(Long id) {
        return ProductDto.of(findProductById(id));
    }

    protected Product findProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("UYGNALDM-PRODUCT-404", "Product could not found by id: " + id));
    }

    public ProductDto updateProduct(ProductRequest request) {
        Product product = findProductById(request.getId());
        product.setId(request.getId());
        product.setQuantity(request.getQuantity());
        product.setName(request.getName());
        product.setWeight(request.getWeight());
        product.setPrice(request.getPrice());
        product.setMarket(marketService.getMarketIfExistsOrCreate(request));
        product.setUpdatedAt(LocalDateTime.now());
        return ProductDto.of(productRepository.save(product));
    }

    public ProductDto createProduct(ProductRequest request) {
        if (isProductExistsByNameAndMarket(request)) {
            throw new AlreadyExistsException("UYGNALDM-PRODUCT-400", "Product already exists with name: "
                    + request.getName() + " and market: " + request.getMarket().getName());
        }

        return ProductDto.of(
                productRepository.save(Product.builder()
                        .quantity(request.getQuantity())
                        .name(request.getName())
                        .weight(request.getWeight())
                        .price(request.getPrice())
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .market(marketService.getMarketIfExistsOrCreate(request))
                        .build())
        );
    }

    public String deleteProduct(Long id) {
        Product product = findProductById(id);
        productRepository.deleteById(id);
        return "Product with name: " + product.getName() + " is deleted!";
    }

    private boolean isProductExistsByNameAndMarket(ProductRequest request) {
        return productRepository.existsByNameAndMarketName(request.getName(), request.getMarket().getName());
    }
}

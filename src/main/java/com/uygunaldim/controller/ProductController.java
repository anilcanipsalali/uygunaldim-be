package com.uygunaldim.controller;

import com.uygunaldim.data.dto.ProductDto;
import com.uygunaldim.data.dto.request.ProductRequest;
import com.uygunaldim.service.ProductService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/product")
@CrossOrigin(allowedHeaders = "*", origins = "*")
@SecurityRequirement(name = "Bearer Authentication")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<Page<ProductDto>> getAllProducts(@RequestParam int offset, @RequestParam int pageSize) {
        return ResponseEntity.ok(productService.getAllProducts(offset, pageSize));
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<Page<ProductDto>> getAllProductsByCategory(@PathVariable String category, @RequestParam int offset, @RequestParam int pageSize) {
        return ResponseEntity.ok(productService.getAllProductsByCategory(category, offset, pageSize));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductRequest request) {
        return ResponseEntity.ok(productService.createProduct(request));
    }

    @PostMapping("/update")
    public ResponseEntity<ProductDto> updateProduct(@Valid @RequestBody ProductRequest request) {
        return ResponseEntity.ok(productService.updateProduct(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        return ResponseEntity.ok(productService.deleteProduct(id));
    }
}

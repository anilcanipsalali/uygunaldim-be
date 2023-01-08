package com.uygunaldim.controller;

import com.uygunaldim.entity.ProductLog;
import com.uygunaldim.service.ProductLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/log")
@RequiredArgsConstructor
public class LogController {
    private final ProductLogService productLogService;

    @GetMapping("/product")
    public ResponseEntity<List<ProductLog>> getAllProductsLog() {
        return ResponseEntity.ok(productLogService.getAllProductsLog());
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<List<ProductLog>> getProductLogById(@PathVariable Long id) {
        return ResponseEntity.ok(productLogService.getAllProductLogById(id));
    }
}
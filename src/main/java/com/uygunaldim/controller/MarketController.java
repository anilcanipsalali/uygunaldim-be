package com.uygunaldim.controller;

import com.uygunaldim.data.dto.MarketDto;
import com.uygunaldim.data.dto.request.MarketRequest;
import com.uygunaldim.service.MarketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/market")
@RequiredArgsConstructor
public class MarketController {
    private final MarketService marketService;

    @GetMapping
    public ResponseEntity<List<MarketDto>> getAllMarkets() {
        return ResponseEntity.ok(marketService.getAllMarkets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MarketDto> getMarketById(@PathVariable Long id) {
        return ResponseEntity.ok(marketService.getMarketById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<MarketDto> createMarket(@Valid @RequestBody MarketRequest request) {
        return ResponseEntity.ok(marketService.createMarket(request));
    }

    @PostMapping("/update")
    public ResponseEntity<MarketDto> updateMarket(@Valid @RequestBody MarketRequest request) {
        return ResponseEntity.ok(marketService.updateMarket(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMarket(@PathVariable Long id) {
        return ResponseEntity.ok(marketService.deleteMarket(id));
    }
}

package com.uygunaldim.service;

import com.uygunaldim.dto.MarketDto;
import com.uygunaldim.dto.request.MarketRequest;
import com.uygunaldim.entity.Market;
import com.uygunaldim.entity.Product;
import com.uygunaldim.exception.AlreadyExistsException;
import com.uygunaldim.exception.NotFoundException;
import com.uygunaldim.repository.MarketRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class MarketService {

    private final MarketRepository marketRepository;

    public MarketService(MarketRepository marketRepository) {
        this.marketRepository = marketRepository;
    }

    public List<MarketDto> getAllMarkets() {
        return marketRepository.findAll().stream().map(MarketDto::of).toList();
    }

    public MarketDto getMarketById(Long id) {
        return MarketDto.of(findMarketById(id));
    }

    protected Market findMarketById(Long id) {
        return marketRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("UYGNALDM-MARKET-404", "Market could not found by id: " + id));
    }

    public MarketDto updateMarket(MarketRequest request) {
        Market market = findMarketById(request.getId());
        market.setName(request.getName());
        market.setUpdatedAt(LocalDateTime.now());
        market.setProducts(request.getProducts().stream().map(Product::of).toList());
        return MarketDto.of(marketRepository.save(market));
    }

    public MarketDto createMarket(MarketRequest request) {
        if (isMarketExists(request)) {
           throw new AlreadyExistsException("UYGNALDM-MARKET-400", "Market already exists with name: " + request.getName());
        }

        return MarketDto.of(
            marketRepository.save(Market.builder()
                    .name(request.getName())
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .products(request.getProducts().stream().map(Product::of).toList())
                    .build())
        );
    }

    public String deleteMarket(Long id) {
        Market market = findMarketById(id);
        marketRepository.deleteById(id);
        return "Market with name: " + market.getName() + " is deleted!";
    }

    private boolean isMarketExists(MarketRequest request) {
        return marketRepository.existsByName(request.getName());
    }
}

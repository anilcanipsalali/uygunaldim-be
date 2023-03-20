package com.uygunaldim.service;

import com.uygunaldim.data.dto.MarketDto;
import com.uygunaldim.data.dto.request.MarketRequest;
import com.uygunaldim.data.entity.Market;
import com.uygunaldim.exception.AlreadyExistsException;
import com.uygunaldim.exception.NotFoundException;
import com.uygunaldim.repository.MarketRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MarketService {

    private final MarketRepository marketRepository;

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

    protected Market findMarketByName(String name) {
        return marketRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException("UYGNALDM-MARKET-404", "Market could not found by name: " + name));
    }

    public MarketDto updateMarket(MarketRequest request) {
        if (isMarketExistsByName(request.getName())) {
            throw new AlreadyExistsException("UYGNALDM-MARKET-400", "Market already exists with name: " + request.getName());
        }

        Market market = findMarketById(request.getId());
        market.setId(request.getId());
        market.setName(request.getName());
        market.setLogo(request.getLogo());
        market.setUpdatedAt(LocalDateTime.now());
        return MarketDto.of(marketRepository.save(market));
    }

    public MarketDto createMarket(MarketRequest request) {
        if (isMarketExistsByName(request.getName())) {
           throw new AlreadyExistsException("UYGNALDM-MARKET-400", "Market already exists with name: " + request.getName());
        }

        return MarketDto.of(marketRepository.save(Market.builder()
                .name(request.getName())
                .logo(request.getLogo())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .products(Collections.emptyList())
                .build()));
    }

    public Market getMarketIfExistsOrCreate(String name) {
        if (isMarketExistsByName(name)) {
            return findMarketByName(name);
        } else {
            return Market.of(createMarket(MarketRequest.builder()
                    .name(name)
                    .build()));
        }
    }

    public String deleteMarket(Long id) {
        String name = findMarketById(id).getName();
        marketRepository.deleteById(id);
        return "Market with name: " + name + " is deleted!";
    }

    private boolean isMarketExistsByName(String name) {
        return marketRepository.existsByName(name);
    }
}

package com.uygunaldim.repository;

import com.uygunaldim.data.entity.Market;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MarketRepository extends JpaRepository<Market, Long> {
    Optional<Market> findById(Long id);
    Optional<Market> findByName(String name);
    List<Market> findAll();
    Boolean existsByName(String name);
}

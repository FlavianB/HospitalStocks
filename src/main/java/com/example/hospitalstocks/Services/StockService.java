package com.example.hospitalstocks.Services;

import com.example.hospitalstocks.Entities.Stock;
import com.example.hospitalstocks.Repositories.StockRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class StockService {
    private final StockRepository stockRepository;
    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public Page<Stock> getAllStocks(Pageable pageable) {
        return stockRepository.findAll(pageable);
    }

    public Stock saveStock(Stock stock) {
        return stockRepository.save(stock);
    }

    public Page<Stock> getAllStocksByDateRange(LocalDate start, LocalDate end, Pageable pageable) {
        return stockRepository.findAllByDateBetween(start, end, pageable);
    }

    public Stock getStockById(UUID id) {
        return stockRepository.findById(id).orElse(null);
    }
    // Other CRUD operations
}

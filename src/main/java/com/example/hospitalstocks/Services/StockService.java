package com.example.hospitalstocks.Services;

import com.example.hospitalstocks.Entities.Stock;
import com.example.hospitalstocks.Repositories.StockRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class StockService {
    private final StockRepository stockRepository;
    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public Page<Stock> getAllStocks(Pageable pageable) {
        stockRepository.updateStock();
        return stockRepository.findAll(pageable);
    }

    public Stock saveStock(Stock stock) {
        return stockRepository.save(stock);
    }

    public Stock getStockById(UUID id) {
        return stockRepository.findById(id).orElse(null);
    }

    public void updateReorderLevel(UUID id, int newReorderLevel) {
        Stock stock = stockRepository.findById(id).orElse(null);
        if (stock != null) {
            stock.setReorderLevel(newReorderLevel);
            stockRepository.save(stock);
        }
    }
    // Other CRUD operations
}

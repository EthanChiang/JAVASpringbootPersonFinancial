package com.first.demoapp.service;

import com.first.demoapp.dto.StockBalanceDto;
import com.first.demoapp.entity.Stock;
import org.springframework.stereotype.Service;

import java.util.List;

public interface StockService {
    Stock save(Stock stock);
    List<StockBalanceDto> getStockBalance();
}

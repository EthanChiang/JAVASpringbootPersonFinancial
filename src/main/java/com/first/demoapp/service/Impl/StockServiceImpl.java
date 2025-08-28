package com.first.demoapp.service.Impl;

import com.first.demoapp.Repository.StockRepository;
import com.first.demoapp.dto.StockBalanceDto;
import com.first.demoapp.entity.Stock;
import com.first.demoapp.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StockServiceImpl implements StockService {

    private StockRepository stockRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public StockServiceImpl(StockRepository stockRepository, RestTemplate restTemplate) {
        this.stockRepository = stockRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public Stock save(Stock stock) {
        return stockRepository.save(stock);
    }


    @Override
    public List<StockBalanceDto> getStockBalance() {
        List<Stock> stockList = stockRepository.findAll();
        if (stockList.isEmpty()) {
            return new ArrayList<>();
        }

        Map<String, List<Stock>> stocksByStockId = stockList.stream()
                .collect(Collectors.groupingBy(Stock::getStockId));

        List<StockBalanceDto> stockBalanceDtos = new ArrayList<>();
        double totalStockValue = 0.0;
        Map<String, Double> stockValueMap = new HashMap<>();

        // 先計算每支股票的市值
        for (Map.Entry<String, List<Stock>> entry : stocksByStockId.entrySet()) {
            String stockId = entry.getKey();
            List<Stock> transactions = entry.getValue();

            int shares = 0;
            double stockCost = 0.0;
            for (Stock s : transactions) {
                shares += s.getStockNum();
                stockCost += s.getStockNum() * s.getStockPrice() + s.getProcessingFee() + s.getTax();
            }

            // 取得目前股價
            String url = "https://www.twse.com.tw/exchangeReport/STOCK_DAY?response=json&stockNo=" + stockId;
            double currentPrice = 0.0;
            try {
                ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
                List<List<String>> priceArray = (List<List<String>>) response.getBody().get("data");
                if (priceArray != null && !priceArray.isEmpty()) {
                    List<String> lastDay = priceArray.get(priceArray.size() - 1);
                    currentPrice = Double.parseDouble(lastDay.get(6).replace(",", ""));
                }
            } catch (Exception e) {
                currentPrice = 0.0;
            }

            double totalValue = Math.round(currentPrice * shares);
            stockValueMap.put(stockId, totalValue);
            totalStockValue += totalValue;
        }

        // 再組裝 DTO
        for (Map.Entry<String, List<Stock>> entry : stocksByStockId.entrySet()) {
            String stockId = entry.getKey();
            List<Stock> transactions = entry.getValue();

            int shares = 0;
            double stockCost = 0.0;
            for (Stock s : transactions) {
                shares += s.getStockNum();
                stockCost += s.getStockNum() * s.getStockPrice() + s.getProcessingFee() + s.getTax();
            }

            // 取得目前股價
            String url = "https://www.twse.com.tw/exchangeReport/STOCK_DAY?response=json&stockNo=" + stockId;
            double currentPrice = 0.0;
            try {
                ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
                List<List<String>> priceArray = (List<List<String>>) response.getBody().get("data");
                if (priceArray != null && !priceArray.isEmpty()) {
                    List<String> lastDay = priceArray.get(priceArray.size() - 1);
                    currentPrice = Double.parseDouble(lastDay.get(6).replace(",", ""));
                }
            } catch (Exception e) {
                currentPrice = 0.0;
            }

            double totalValue = stockValueMap.get(stockId);
            double averageCost = shares > 0 ? Math.round(stockCost / shares * 100.0) / 100.0 : 0.0;
            double rateOfReturn = stockCost > 0 ? Math.round((totalValue - stockCost) * 10000.0 / stockCost) / 100.0 : 0.0;
            double valuePercentage = totalStockValue > 0 ? Math.round(totalValue * 10000.0 / totalStockValue) / 100.0 : 0.0;

            StockBalanceDto dto = StockBalanceDto.builder()
                    .stockId(stockId)
                    .shares(shares)
                    .currentPrice(currentPrice)
                    .totalValue(totalValue)
                    .valuePercentage(valuePercentage)
                    .stockCost(stockCost)
                    .averageCost(averageCost)
                    .rateOfReturn(rateOfReturn)
                    .build();

            stockBalanceDtos.add(dto);
        }

        return stockBalanceDtos;
    }
}

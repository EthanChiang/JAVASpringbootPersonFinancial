package com.first.demoapp.service.Impl;

import com.first.demoapp.Repository.StockRepository;
import com.first.demoapp.dto.StockBalanceDto;
import com.first.demoapp.entity.Stock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
//
//class StockServiceImplTest {
//    @Mock
//    private StockRepository stockRepository;
//
//    @Mock
//    private RestTemplate restTemplate;
//
//    private StockServiceImpl stockService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        stockService = new StockServiceImpl(stockRepository, restTemplate);
//    }
//
//    @Test
//    void testGetStockBalance() {
//        // 準備模擬資料
//        Stock stock1 = new Stock();
//        stock1.setStockId("2330");
//        stock1.setStockNum(10);
//        stock1.setStockPrice(500.0);
//        stock1.setProcessingFee(10);
//        stock1.setTax(5);
//
//        Stock stock2 = new Stock();
//        stock2.setStockId("2330");
//        stock2.setStockNum(5);
//        stock2.setStockPrice(520.0);
//        stock2.setProcessingFee(8);
//        stock2.setTax(4);
//
//        List<Stock> stockList = Arrays.asList(stock1, stock2);
//        when(stockRepository.findAll()).thenReturn(stockList);
//
//        // Mock RestTemplate
//        Map<String, Object> responseMap = new HashMap<>();
//        List<List<String>> data = new ArrayList<>();
//        data.add(Arrays.asList("2025/08/01", "", "", "", "", "", "600")); // 假設收盤價在第7欄
//        responseMap.put("data", data);
//        ResponseEntity<Map> responseEntity = ResponseEntity.ok(responseMap);
//        when(restTemplate.getForEntity(anyString(), eq(Map.class))).thenReturn(responseEntity);
//
//        // 執行測試
//        List<StockBalanceDto> result = stockService.getStockBalance();
//
//        // 驗證結果
//        assertNotNull(result);
//        assertFalse(result.isEmpty());
//        StockBalanceDto dto = result.get(0);
//        assertEquals("2330", dto.getStockId());
//        assertEquals(15, dto.getShares());
//        assertEquals(600.0, dto.getCurrentPrice());
//        assertTrue(dto.getTotalValue() > 0);
//        assertTrue(dto.getStockCost() > 0);
//    }
//}

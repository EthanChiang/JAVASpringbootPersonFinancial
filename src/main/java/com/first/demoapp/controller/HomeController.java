package com.first.demoapp.controller;

import com.first.demoapp.dto.CashBalanceDto;
import com.first.demoapp.dto.StockBalanceDto;
import com.first.demoapp.entity.Cash;
import com.first.demoapp.service.CashService;
import com.first.demoapp.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    private CashService cashService;
    private StockService stockService;

    @Autowired
    public HomeController(CashService cashService, StockService stockService) {
        this.cashService = cashService;
        this.stockService = stockService;
    }

    @GetMapping("/")
    public String home(Model model) {

    CashBalanceDto cashBalanceDto = cashService.getCashBalance();
    List<Cash> cashList = cashService.findAll();
    List<StockBalanceDto> stockBalanceDtoList = stockService.getStockBalance(); // 取得股票庫存資訊

        Map<String,String> cashData = new HashMap<>();
        cashData.put("td", cashBalanceDto.getTaiwaneseDollars().toString()); // 台幣總額
        cashData.put("ud", cashBalanceDto.getUsDollars().toString());  // 美金總額
        cashData.put("currency", cashBalanceDto.getCurrency().toString()); // 今日匯率
        cashData.put("total", cashBalanceDto.getTotalBalance().toString()); // 現金總額 (50000 + 1500 * 31.25)

        List<Map<String, String>> stockInfo = new ArrayList<>();
        for (StockBalanceDto stock : stockBalanceDtoList) {
            Map<String, String> stockMap = new HashMap<>();
            stockMap.put("stockId", stock.getStockId());
            stockMap.put("shares", String.valueOf(stock.getShares()));
            stockMap.put("currentPrice", String.valueOf(stock.getCurrentPrice()));
            stockMap.put("totalValue", String.valueOf(stock.getTotalValue()));
            stockMap.put("valuePercentage", String.valueOf(stock.getValuePercentage()));
            stockMap.put("stockCost", String.valueOf(stock.getStockCost()));
            stockMap.put("averageCost", String.valueOf(stock.getAverageCost()));
            stockMap.put("rateOfReturn", String.valueOf(stock.getRateOfReturn()));
            stockInfo.add(stockMap);
        }
        
        // 將所有資料放入一個大的 Map 物件中，以便於前端統一取用
         Map<String, Object> data = new HashMap<>();
         data.put("td", cashData.get("td"));
         data.put("ud", cashData.get("ud"));
         data.put("currency", cashData.get("currency"));
         data.put("total", cashData.get("total"));
         data.put("cashList", cashList);
         data.put("stockInfo", stockInfo);

        // 將 data 物件添加到 Model 中，名稱為 "data"，前端將透過 `${data}` 存取
        model.addAttribute("data", data);
        return "index";
    }

}

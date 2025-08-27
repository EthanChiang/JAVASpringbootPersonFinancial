package com.first.demoapp.controller;


import com.first.demoapp.entity.Stock;
import com.first.demoapp.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/stock")
public class StockController {

    private StockService stockService;

    @Autowired
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping
    public String stockHome() {
        return "stock"; // 返回股票管理頁面
    }

    @PostMapping("/create")
    public String createStock(Stock stock) {
        System.out.println("Creating stock: " + stock);
        stockService.save(stock);
        return "redirect:/";
    }
}

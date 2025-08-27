package com.first.demoapp.controller;

import com.first.demoapp.entity.Cash;
import com.first.demoapp.service.CashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cash")
public class CashController {
   private CashService cashService;

   @Autowired
   public CashController(CashService cashService) {
         this.cashService = cashService;
    }

   @GetMapping
    public String cashHome() {
         return "cash"; // 返回現金管理頁面
    }

   @PostMapping("/create")
   public String createCash(Cash cash) {
       System.out.println("Creating cash: " + cash);
       cashService.save(cash);
       return "redirect:/";
   }

   @PostMapping("delete/{id}")
   public String deleteCash(@PathVariable("id") Integer id) {
       cashService.deleteById(id);
       return "redirect:/";
   }


}

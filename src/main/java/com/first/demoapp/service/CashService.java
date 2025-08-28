package com.first.demoapp.service;

import com.first.demoapp.dto.CashBalanceDto;
import com.first.demoapp.entity.Cash;

import java.util.List;

public interface CashService {
    Cash save(Cash cash);
    List<Cash> findAll();
    CashBalanceDto getCashBalance();
    void deleteById(int id);
}

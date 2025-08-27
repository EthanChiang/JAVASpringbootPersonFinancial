package com.first.demoapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StockBalanceDto {
    //股票代號
    private String stockId;
    //持有股數
    private Integer shares;
    //目前股價
    private Double currentPrice;
    //目前市值
    private Double totalValue;
    //股票資產占比
    private Double valuePercentage;
    //購買總成本
    private Double stockCost;
    //平均成本
    private Double averageCost;
    //報酬率(%)
    private Double rateOfReturn;
}

package com.first.demoapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CashBalanceDto {
    private Integer taiwaneseDollars;
    private Double usDollars;
    private Double currency;
    private Integer totalBalance;
}

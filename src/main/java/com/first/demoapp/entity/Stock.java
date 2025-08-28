package com.first.demoapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "stock")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Integer transactionId;

    @Column(name = "stock_id", length = 10)
    private String stockId;

    @Column(name = "stock_num")
    private Integer stockNum;

    @Column(name = "stock_price")
    private Double stockPrice; // 使用 Double 或 BigDecimal 處理浮點數更精確

    @Column(name = "processing_fee")
    private Integer processingFee;

    @Column(name = "tax")
    private Integer tax;

    @Column(name = "date_info")
    private LocalDate dateInfo;
}

package com.first.demoapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "cash")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cash {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Integer transactionId;

    @Column(name = "taiwanese_dollars")
    private Integer taiwaneseDollars;

    @Column(name = "us_dollars")
    private Double usDollars;

    @Column(length = 30)
    private String note;

    @Column(name = "date_info")
    private LocalDate dateInfo;

}

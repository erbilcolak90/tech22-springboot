package com.tech22test.tech22test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRateResponse {

    private boolean success;
    private Long timestamp;
    private String base;
    private Date date;
    private Map<String,Double> rates;
    private double amount;
}

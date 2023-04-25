package com.tech22test.tech22test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRate {

    private String baseCurrency;
    private String targetCurrency;
    private double amount;

}



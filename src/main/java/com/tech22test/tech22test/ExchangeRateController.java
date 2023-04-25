package com.tech22test.tech22test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exchange")
public class ExchangeRateController {

    @Autowired
    private ExchangeRateService exchangeRateService;

    @GetMapping("/exchange-rate")
    public ResponseEntity<ExchangeRateResponse> getExchangeRate(@RequestBody ExchangeRate exchangeRateInput) {

        ExchangeRateResponse exchangeRate = exchangeRateService.getExchangeRate(exchangeRateInput);
        return ResponseEntity.ok(exchangeRate);

    }
}


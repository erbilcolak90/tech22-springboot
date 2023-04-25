package com.tech22test.tech22test;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ExchangeRateService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${fixer.accessKey}")
    private String apiKey;

    @Value("${fixer.baseUrl}")
    private String baseUrl;

    public ExchangeRateResponse getExchangeRate(ExchangeRate exchangeRateInput) {
        try{
            String url = baseUrl+"symbols="+exchangeRateInput.getTargetCurrency()+"&base="+exchangeRateInput.getBaseCurrency();
            HttpHeaders headers = new HttpHeaders();
            headers.add("apikey",apiKey);

            HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
            ResponseEntity<ExchangeRateResponse> customRest = restTemplate.exchange(url, HttpMethod.GET, entity, ExchangeRateResponse.class);

            if(customRest.getBody() != null){
                double totalAmount = customRest.getBody().getRates().get(exchangeRateInput.getTargetCurrency()) * exchangeRateInput.getAmount();
                return new ExchangeRateResponse(
                        customRest.getBody().isSuccess(),
                        customRest.getBody().getTimestamp(),
                        customRest.getBody().getBase(),
                        customRest.getBody().getDate(),
                        customRest.getBody().getRates(),
                        totalAmount);

            }else{
                throw new RestTemplateExchangeException("Response is null");
            }

        }catch (RestTemplateExchangeException exchangeException){
            exchangeException.getMessage();
        }
        return null;
    }
}

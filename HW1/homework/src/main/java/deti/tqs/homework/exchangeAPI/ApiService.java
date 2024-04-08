package deti.tqs.homework.exchangeAPI;

import deti.tqs.homework.exchangeAPI.ExchangeRatesResponse;
import jakarta.persistence.Cacheable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiService {

    /*
    private final RestTemplate restTemplate;

    public ApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Cacheable("exchangeRates")
    public ExchangeRatesResponse getExchangeRates() {
        ResponseEntity<ExchangeRatesResponse> responseEntity = restTemplate.getForEntity("https://api.frankfurter.app/latest", ExchangeRatesResponse.class);
        return responseEntity.getBody();
    }
    */
}

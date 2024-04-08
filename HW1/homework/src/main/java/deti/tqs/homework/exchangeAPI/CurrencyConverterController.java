package deti.tqs.homework.exchangeAPI;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/converter")
public class CurrencyConverterController {

    @Autowired
    private ApiService apiService;

    @GetMapping("/exchange")
    public ExchangeRatesResponse exchange() {
        //return apiService.getExchangeRates();
        return null;
    }
}

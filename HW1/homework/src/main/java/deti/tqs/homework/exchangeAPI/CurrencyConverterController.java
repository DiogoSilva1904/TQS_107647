package deti.tqs.homework.exchangeAPI;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/converter")
public class CurrencyConverterController {

    private final ApiService apiService;

    public CurrencyConverterController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping("/exchange")
    public Map<String,Object> exchange() {
        return apiService.getExchangeRates();
    }
}

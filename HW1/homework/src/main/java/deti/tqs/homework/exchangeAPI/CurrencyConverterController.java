package deti.tqs.homework.exchangeAPI;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/converter")
@Tag(name = "Currency Converter API", description = "Endpoints for currency conversion")
public class CurrencyConverterController {

    private final ApiService apiService;

    public CurrencyConverterController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping("/exchange")
    @Operation(summary = "Get exchange rates", description = "Get exchange rates")
    public Map<String,Object> exchange() {
        return apiService.getExchangeRates();
    }
}

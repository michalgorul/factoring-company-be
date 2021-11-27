package com.polsl.factoringcompany.currency;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping(path = "/api/currency")
public class CurrencyController {

    private final CurrencyService currencyService;

    @GetMapping
    public List<CurrencyEntity> getCurrencies() {
        return currencyService.getCurrencies();
    }

    @GetMapping(path = "/{id}")
    public CurrencyEntity getCurrency(@PathVariable Long id) {
        return this.currencyService.getCurrency(id);
    }

    @PostMapping
    public CurrencyEntity addCurrency(@RequestParam() String name, @RequestParam() String code) {
        return this.currencyService.addCurrency(name, code);
    }

    @PutMapping("/{id}")
    public CurrencyEntity updateCurrency(@PathVariable Long id, @RequestBody CurrencyRequestDto currencyRequestDto) {
        return this.currencyService.updateCurrency(id, currencyRequestDto);
    }

    @DeleteMapping("/{id}")
    public void deleteCurrency(@PathVariable Long id) {
        this.currencyService.deleteCurrency(id);
    }
}


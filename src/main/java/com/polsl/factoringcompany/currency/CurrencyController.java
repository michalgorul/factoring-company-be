package com.polsl.factoringcompany.currency;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type Currency controller. Class for creating endpoints.
 *
 * @author Michal Goral
 * @version 1.0
 */
@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping(path = "/api/currency")
public class CurrencyController {

    /**
     * the currency service bean
     */
    private final CurrencyService currencyService;

    /**
     * Gets all currencies from database.
     *
     * @return the currencies
     */
    @GetMapping
    public List<CurrencyEntity> getCurrencies() {
        return currencyService.getCurrencies();
    }

    /**
     * Gets currency specified by id.
     *
     * @param id the id
     * @return the currency
     */
    @GetMapping(path = "/{id}")
    public CurrencyEntity getCurrency(@PathVariable Long id) {
        return this.currencyService.getCurrency(id);
    }

    /**
     * Creates currency entity and saves it to database.
     *
     * @param name the name
     * @param code the code
     * @return the currency entity
     */
    @PostMapping
    public CurrencyEntity addCurrency(@RequestParam() String name, @RequestParam() String code) {
        return this.currencyService.addCurrency(name, code);
    }

    /**
     * Updates currency entity and saves it to database.
     *
     * @param id                 the id
     * @param currencyRequestDto the currency request dto
     * @return the currency entity
     */
    @PutMapping("/{id}")
    public CurrencyEntity updateCurrency(@PathVariable Long id, @RequestBody CurrencyRequestDto currencyRequestDto) {
        return this.currencyService.updateCurrency(id, currencyRequestDto);
    }

    /**
     * Deletes currency specified by id from database.
     *
     * @param id the id
     */
    @DeleteMapping("/{id}")
    public void deleteCurrency(@PathVariable Long id) {
        this.currencyService.deleteCurrency(id);
    }
}


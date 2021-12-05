package com.polsl.factoringcompany.currency;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The type currency request dto.
 * @author Michal Goral
 * @version 1.0
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyRequestDto {

    /**
     * the code
     */
    private String code;

    /**
     * the name
     */
    private String name;
}

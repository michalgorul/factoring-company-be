package com.polsl.factoringcompany.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The type Product request used in REST API.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductRequest {
    /**
     * the name
     */
    private String name;

    /**
     * the pkwiu
     */
    private String pkwiu;

    /**
     * the measure unit
     */
    private String measureUnit;

}

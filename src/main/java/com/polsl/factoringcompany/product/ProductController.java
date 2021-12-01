package com.polsl.factoringcompany.product;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type Product controller. Class for creating endpoints.
 * @author Michal Goral
 * @version 1.0
 */
@AllArgsConstructor
@RestController
@RequestMapping(path = "/api/product")
public class ProductController {

    /**
     * the bean of product service
     */
    private final ProductService productService;

    /**
     * Gets all products in database.
     *
     * @return the products
     */
    @GetMapping
    public List<ProductEntity> getProducts() {
        return productService.getProducts();
    }

    /**
     * Gets specific product.
     *
     * @param id the id
     * @return the product
     */
    @GetMapping(path = "/{id}")
    public ProductEntity getProduct(@PathVariable Long id) {
        return this.productService.getProduct(id);
    }

    /**
     * Gets product specified by name.
     *
     * @param name the name
     * @return the product by name
     */
    @GetMapping(path = "/name/{name}")
    public ProductEntity getProductByName(@PathVariable String name) {
        return this.productService.getProductByName(name);
    }

    /**
     * Creates new product entity.
     *
     * @param productRequest the product request
     * @return the created product entity
     */
    @PostMapping
    public ProductEntity addProduct(@RequestBody ProductRequest productRequest) {
        return this.productService.addProduct(productRequest);
    }

    /**
     * Updates product entity specified by id.
     *
     * @param id             the id
     * @param productRequest the product request
     * @return the updated product entity
     */
    @PutMapping("/{id}")
    public ProductEntity updateProduct(@PathVariable Long id, @RequestBody ProductRequest productRequest) {
        return this.productService.updateProduct(id, productRequest);
    }

    /**
     * Deletes product specified by id.
     *
     * @param id the id
     */
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        this.productService.deleteProduct(id);
    }
}

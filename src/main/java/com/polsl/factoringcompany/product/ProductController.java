package com.polsl.factoringcompany.product;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/api/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<ProductEntity> getProducts() {
        return productService.getProducts();
    }

    @GetMapping(path = "/{id}")
    public ProductEntity getProduct(@PathVariable Long id) {
        return this.productService.getProduct(id);
    }

    @GetMapping(path = "/name/{name}")
    public ProductEntity getProductByName(@PathVariable String name) {
        return this.productService.getProductByName(name);
    }

    @PostMapping
    public ProductEntity addProduct(@RequestBody ProductRequest productRequest) {
        return this.productService.addProduct(productRequest);
    }

    @PutMapping("/{id}")
    public ProductEntity updateProduct(@PathVariable Long id, @RequestBody ProductRequest productRequest) {
        return this.productService.updateProduct(id, productRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        this.productService.deleteProduct(id);
    }
}

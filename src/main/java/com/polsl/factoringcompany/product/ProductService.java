package com.polsl.factoringcompany.product;

import com.polsl.factoringcompany.exceptions.IdNotFoundInDatabaseException;
import com.polsl.factoringcompany.exceptions.NotUniqueException;
import com.polsl.factoringcompany.exceptions.ValueImproperException;
import com.polsl.factoringcompany.stringvalidation.StringValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * The type Product service. Used to connect controller with Data access object
* @author Michal Goral
* @version 1.0
 */
@Service
@AllArgsConstructor
public class ProductService {

    /**
     * the product repository bean
     */
    private final ProductRepository productRepository;


    /**
     * Gets all products from database.
     *
     * @return the products
     */
    public List<ProductEntity> getProducts() {
        return productRepository.findAll();
    }


    /**
     * Gets product specified by id. If product is not found it throws IdNotFoundInDatabaseException
     *
     * @param id the id
     * @return the product
     */
    public ProductEntity getProduct(Long id) {
        return this.productRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundInDatabaseException("Product", id));
    }


    /**
     * Creates new product entity and saves it to database.
     *
     * @param productRequest the product request
     * @return the product entity
     */
    public ProductEntity addProduct(ProductRequest productRequest) {

        validating(productRequest.getName(), productRequest.getPkwiu(), productRequest.getMeasureUnit());

        try {
            return productRepository.save(new ProductEntity(
                    StringUtils.capitalize(productRequest.getName()),
                    productRequest.getPkwiu(),
                    productRequest.getMeasureUnit()));
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Deletes product specified by id.
     *
     * @param id the id
     */
    public void deleteProduct(Long id) {
        try {
            this.productRepository.deleteById(id);
        } catch (RuntimeException ignored) {
            throw new IdNotFoundInDatabaseException("Product", id);
        }
    }


    /**
     * Updates product entity and saves it to database.
     *
     * @param id             the id
     * @param productRequest the product request
     * @return the updated product entity
     */
    @Transactional
    public ProductEntity updateProduct(Long id, ProductRequest productRequest) {

        Optional<ProductEntity> productEntity = productRepository.findById(id);

        if (productEntity.isEmpty())
            throw new IdNotFoundInDatabaseException("Product", id);

        validatingUpdate(productRequest.getName(), productRequest.getPkwiu(), productRequest.getMeasureUnit(), id);

        try {
            productEntity.get().setName(StringUtils.capitalize(productRequest.getName()));
            productEntity.get().setMeasureUnit(productRequest.getMeasureUnit());
            productEntity.get().setPkwiu(productRequest.getPkwiu());
            return this.productRepository.save(productEntity.get());
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Validates if specific fields are in proper form
     * @param name the name
     * @param pkwiu the pkwiu
     * @param measureUnit the measure unit
     */
    private void validating(String name, String pkwiu, String measureUnit) {

        if (StringValidator.stringWithSpacesImproper(name, 50))
            throw new ValueImproperException(name);

        if (pkwiuImproper(pkwiu))
            throw new ValueImproperException(pkwiu, "PKWIU");

        if (StringValidator.stringWithSpacesImproper(measureUnit, 8))
            throw new ValueImproperException(measureUnit, "measure unit");

        if (ifNameTaken(name))
            throw new NotUniqueException("Product", "name", name);
    }

    /**
     * Validates if specific fields are in proper form while updating existing product entity. If one of teh
     * is not correct it throws ValueImproperException
     * @param name the name
     * @param pkwiu the pkwiu
     * @param measureUnit the measure unit
     */
    private void validatingUpdate(String name, String pkwiu, String measureUnit, Long id) {

        if (StringValidator.stringWithSpacesImproper(name, 50))
            throw new ValueImproperException(name);

        if (pkwiuImproper(pkwiu))
            throw new ValueImproperException(pkwiu, "PKWIU");

        if (StringValidator.stringWithSpacesImproper(measureUnit, 8))
            throw new ValueImproperException(measureUnit, "measure unit");

        if (ifNameTakenUpdate(id, name))
            throw new NotUniqueException("Product", "name", name);
    }


    /**
     * Checks if name taken.
     *
     * @param name the name
     * @return true if name is already in use
     */
    public boolean ifNameTaken(String name) {
        Optional<ProductEntity> foundByName = productRepository.findProductEntityByName(
                StringUtils.capitalize(name));
        return foundByName.isPresent();
    }

    /**
     * Checks if name taken while updating existing entity.
     *
     * @param id   the id
     * @param name the name
     * @return true if name is already in use
     */
    public boolean ifNameTakenUpdate(Long id, String name) {
        Optional<ProductEntity> foundByName = productRepository.findProductEntityByName(
                StringUtils.capitalize(name));
        Optional<ProductEntity> foundById = productRepository.findById(id);

        if (foundById.isEmpty())
            throw new IdNotFoundInDatabaseException("Product", id);
        if (foundByName.isEmpty())
            return false;

        return !foundByName.get().getId().equals(foundById.get().getId());
    }


    /**
     * Checks if pkwiu is in proper form.
     *
     * @param pkwiu the pkwiu
     * @return true if pkwiu is improper
     */
    public boolean pkwiuImproper(String pkwiu) {
        String patterns = "\\d\\d[.]\\d\\d[.]\\d\\d[.]\\d";
        Pattern pattern = Pattern.compile(patterns);
        return !pattern.matcher(pkwiu).matches();
    }

    /**
     * Gets product specified by name. If it is not found it throws IdNotFoundInDatabaseException
     *
     * @param name the name
     * @return the product entity
     */
    public ProductEntity getProductByName(String name) {
        return this.productRepository.findProductEntityByName(name)
                .orElseThrow(() -> new IdNotFoundInDatabaseException("Product", 0L));
    }
}

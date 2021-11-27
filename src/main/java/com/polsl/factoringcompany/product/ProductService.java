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

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;


    public List<ProductEntity> getProducts() {
        return productRepository.findAll();
    }


    public ProductEntity getProduct(Long id) {
        return this.productRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundInDatabaseException("Product", id));
    }


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


    public void deleteProduct(Long id) {
        try {
            this.productRepository.deleteById(id);
        } catch (RuntimeException ignored) {
            throw new IdNotFoundInDatabaseException("Product", id);
        }
    }


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


    public boolean ifNameTaken(String name) {
        Optional<ProductEntity> foundByName = productRepository.findProductEntityByName(
                StringUtils.capitalize(name));
        return foundByName.isPresent();
    }

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


    public boolean pkwiuImproper(String pkwiu) {
        String patterns = "\\d\\d[.]\\d\\d[.]\\d\\d[.]\\d";
        Pattern pattern = Pattern.compile(patterns);
        return !pattern.matcher(pkwiu).matches();
    }

    public ProductEntity getProductByName(String name) {
        return this.productRepository.findProductEntityByName(name)
                .orElseThrow(() -> new IdNotFoundInDatabaseException("Product", 0L));
    }
}

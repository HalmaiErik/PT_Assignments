package businessLogicLayer.validators;

import model.Product;

public class ProductQuantityValidator implements Validator<Product> {
    /**
     * Checks the parameter product's quantity. If it is below 0, it throw an IllegalArgumentException.
     * @param product Product
     */
    public void validate(Product product) {
        if(product.getQuantity() < 0) {
            throw new IllegalArgumentException("A product's quantity needs to be a positive number");
        }
    }
}

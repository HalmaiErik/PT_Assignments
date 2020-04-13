package businessLogicLayer.validators;

import model.Product;

public class ProductNameValidator implements Validator<Product> {
    private static final int MIN_LENGTH = 2;
    private static final int MAX_LENGTH = 60;

    /**
     * Checks the length of the parameter product's name string. If it is not between the MIN and MAX lengths, it throws
     * an IllegalArgumentException.
     * @param product Product
     */
    public void validate(Product product) {
        if(product.getName().length() < MIN_LENGTH || product.getName().length() > MAX_LENGTH) {
            throw new IllegalArgumentException("The product's name needs to between 2 and 60 characters");
        }
    }
}

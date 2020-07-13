package businessLogicLayer;

import businessLogicLayer.validators.ProductNameValidator;
import businessLogicLayer.validators.ProductQuantityValidator;
import businessLogicLayer.validators.Validator;
import databaseAccessLayer.ProductDAO;
import model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductBLL {
    private List<Validator<Product>> validators;
    private ProductDAO productDAO;

    /**
     * Constructor method. It initializes the productDAO and the validator list by adding all the validators for the Product objects.
     */
    public ProductBLL() {
        validators = new ArrayList<Validator<Product>>();
        validators.add(new ProductNameValidator());
        validators.add(new ProductQuantityValidator());
        productDAO = new ProductDAO();
    }

    /**
     * Truncates the product table from the database.
     */
    public void truncateTable() {
        productDAO.truncateTable();
    }

    /**
     * Validates and inserts the product given as parameter into the database.
     * @param product Product
     */
    public void insertProduct(Product product) {
        for (Validator<Product> validator : validators) {
            validator.validate(product);
        }

        productDAO.insert(product);
    }

    /**
     * Updates the parameter product's quantity to the given value.
     * @param newQuantity Integer
     * @param productId Integer
     */
    public void updateProductQuantity(int newQuantity, int productId) {
        productDAO.update(newQuantity, "quantity", productId, "id");
    }

    /**
     * Removes the product having the name given as parameter from the database.
     * @param name String
     */
    public void removeProduct(String name) {
        productDAO.delete(name, "name");
    }

    /**
     * Returns a list with all the products in the product database table.
     * @return List<Product>
     */
    public List<Product> listProducts() {
        return productDAO.findAll();
    }

    /**
     * Finds the product with the name given as parameter.
     * @param name String
     * @return Product
     */
    public Product findProductByName(String name) {
        Product product = productDAO.find(name, "name");

        return product;
    }
}

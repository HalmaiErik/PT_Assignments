package businessLogic;

import java.util.ArrayList;
import java.util.List;

public class CompositeProduct extends MenuItem {
    private String name;
    private List<MenuItem> products;

    /**
     * Constructor method. Sets the CompositeProduct object's name to the String given as parameter and initializes the
     * list of products in the object.
     * @param name String
     */
    public CompositeProduct(String name) {
        this.name = name;
        this.products = new ArrayList<MenuItem>();
    }

    /**
     * Adds a MenuItem object to the list of products of the CompositeProduct object.
     * @param item MenuItem
     */
    public void addProduct(MenuItem item) {
        products.add(item);
    }

    /**
     * Removes a MenuItem object from the list of products of the CompositeProduct object.
     * @param item MenuItem
     */
    public void removeProduct(MenuItem item) {
        products.remove(item);
    }

    /**
     * Implemented computePrice method of MenuItem abstract class. In this case, of the CompositeProduct, it calculates
     * the sum of the prices in the list of products.
     * @return Integer
     */
    public float computePrice() {
        float totalPrice = 0;

        for (MenuItem item : products) {
            totalPrice += item.computePrice();
        }

        return totalPrice;
    }

    /**
     * Overwritten toString method for the bill creation.
     * @return String
     */
    @Override
    public String toString() {
        return name + " - " + computePrice() + " lei";
    }

    /**
     * Returns a string containing the products inside the composite object.
     * @return String
     */
    public String toStringProducts() {
        StringBuilder sb = new StringBuilder();
        for(MenuItem item : products) {
            sb.append(item.getName() + ", ");
        }
        return sb.toString();
    }

    /**
     * Generates a matrix of objects containing the names of the products in the composite object. Used to populate the
     * JTables.
     * @return Object[][]
     */
    public Object[][] generateNames() {
        Object[][] objects = new Object[products.size()][1];

        int row = 0;
        for (MenuItem item : products) {
            objects[row][0] = item.getName();
            row++;
        }

        return objects;
    }

    public String getName() {
        return name;
    }

    public List<MenuItem> getProducts() {
        return products;
    }
}

package businessLogic;

public class BaseProduct extends MenuItem {
    private String name;
    private float price;

    /**
     * Constructor method. Sets the BaseProduct object's name and price to the values given as parameters.
     * @param name String
     * @param price Float
     */
    public BaseProduct(String name, float price) {
        this.name = name;
        this.price = price;
    }

    /**
     * Overwritten toString method for the bill creation.
     * @return String
     */
    @Override
    public String toString() {
        return name + " - " + price + " lei";
    }

    /**
     * Implemented computePrice method of MenuItem abstract class. In this case, of the BaseProducts, it returns the
     * price of the product.
     * @return Integer
     */
    public float computePrice() {
        return price;
    }

    public String getName() {
        return name;
    }
}

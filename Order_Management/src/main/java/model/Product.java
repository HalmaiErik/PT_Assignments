package model;

public class Product {
    private int id;
    private String name;
    private int quantity;
    private float price;

    /**
     * Parameterless constructor.
     */
    public Product() {}

    /**
     * Constructor method, that sets the object's id, name, quantity and price to the values given as parameters.
     * @param id Integer
     * @param name String
     * @param quantity Integer
     * @param price Integer
     */
    public Product(int id, String name, int quantity, float price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    /**
     * Constructor method, that sets the object's id, name, quantity and price to the values given as parameters.
     * @param name String
     * @param quantity Integer
     * @param price Float
     */
    public Product(String name, int quantity, float price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public float getPrice() {
        return price;
    }
}

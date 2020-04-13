package model;

public class OrderItem {
    private int id;
    private int idOrder;
    private int idProduct;
    private int amount;
    private float price;

    /**
     * Parameterless constructor.
     */
    public OrderItem() {}

    /**
     * Constructor method that sets the object's id, orderId, productId, amount and price to the values given as parameters.
     * @param id Integer
     * @param orderId Integer
     * @param idProduct Integer
     * @param amount Integer
     * @param price Float
     */
    public OrderItem(int id, int orderId, int idProduct, int amount, float price) {
        this.id = id;
        this.idOrder = orderId;
        this.idProduct = idProduct;
        this.amount = amount;
        this.price = price;
    }

    /**
     * Constructor method that sets the object's orderId, productId, amount and price to the values given as parameters.
     * @param orderId Integer
     * @param idProduct Integer
     * @param amount Integer
     * @param price Float
     */
    public OrderItem(int orderId, int idProduct, int amount, float price) {
        this.idOrder = orderId;
        this.idProduct = idProduct;
        this.amount = amount;
        this.price = price;
    }



    public void setId(int id) {
        this.id = id;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public void setIdProduct(int productId) {
        this.idProduct = productId;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public int getAmount() {
        return amount;
    }

    public float getPrice() {
        return price;
    }
}

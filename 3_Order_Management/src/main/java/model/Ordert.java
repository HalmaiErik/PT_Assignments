package model;

public class Ordert {
    private int id;
    private int idClient;
    private float totalPrice;

    /**
     * Parameterless constructor.
     */
    public Ordert() {}

    /**
     * Constructor method that sets the object's id, clientId and totalPrice to the values given as parameters.
     * @param id Integer
     * @param idClient Integer
     * @param totalPrice Float
     */
    public Ordert(int id, int idClient, float totalPrice) {
        this.id = id;
        this.idClient = idClient;
        this.totalPrice = totalPrice;
    }

    /**
     * Constructor method that sets the object's clientId and totalPrice to the values given as parameters.
     * @param idClient Integer
     * @param totalPrice Float
     */
    public Ordert(int idClient, float totalPrice) {
        this.idClient = idClient;
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Ordert{" +
                "id=" + id +
                ", clientId=" + idClient +
                ", totalPrice=" + totalPrice +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdClient(int clientId) {
        this.idClient = clientId;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getId() {
        return id;
    }

    public int getIdClient() {
        return idClient;
    }

    public float getTotalPrice() {
        return totalPrice;
    }
}

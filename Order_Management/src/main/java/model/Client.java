package model;

public class Client {
    private int id;
    private String name;
    private String address;

    /**
     * Parameterless constructor.
     */
    public Client() {}

    /**
     * Constructor method that sets the object's id, name and address to the values given as parameters.
     * @param id Integer
     * @param name String
     * @param address String
     */
    public Client(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    /**
     * Constructor method that sets the object's name and address to the values given as parameters.
     * @param name String
     * @param address String
     */
    public Client(String name, String address) {
        this.name = name;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }
}

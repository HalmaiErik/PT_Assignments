package businessLogicLayer;

import databaseAccessLayer.ClientDAO;
import databaseAccessLayer.OrderDAO;
import model.Client;
import model.Ordert;

import java.util.List;

public class OrderBLL {
    private OrderDAO orderDAO;

    /**
     * Constructor method. It initializes the orderDAO.
     */
    public OrderBLL() {
        orderDAO = new OrderDAO();
    }

    /**
     * Truncates the ordert table from the database.
     */
    public void truncateTable() {
        orderDAO.truncateTable();
    }

    /**
     * Drops the idclient foreign key from the ordert table. The foreign key references the id from the client table.
     */
    public void dropFK() {
        orderDAO.dropFK();
    }

    /**
     * Adds the foreign key idclient referencing the id from the client table.
     */
    public void addFK() {
        orderDAO.addFK();
    }

    /**
     * Inserts the ordert given as parameter into the database.
     * @param ordert Ordert
     */
    public void insertOrder(Ordert ordert) {
        ClientDAO clientDAO = new ClientDAO();
        Client client = clientDAO.find(ordert.getIdClient(), "id");

        if(client != null) {
            orderDAO.insert(ordert);
        }
    }

    /**
     * Updates the totalPrice to the newTotal for the order with orderId given as parameter.
     * @param newTotal Float
     * @param orderId Integer
     */
    public void updateTotalForId(float newTotal, int orderId) {
        orderDAO.update(newTotal, "totalprice", orderId, "id");
    }

    /**
     * Finds the order for the client with id given as parameter
     * @param clientId Integer
     * @return Ordert
     */
    public Ordert findOrderByClient(int clientId) {
        return orderDAO.find(clientId, "idclient");
    }

    /**
     * Returns a list with all the orders in the order database table.
     * @return List<Ordert>
     */
    public List<Ordert> listOrders() {
        return orderDAO.findAll();
    }
}

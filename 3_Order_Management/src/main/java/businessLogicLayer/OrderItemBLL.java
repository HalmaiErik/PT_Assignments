package businessLogicLayer;

import businessLogicLayer.validators.OrderAmountValidator;
import businessLogicLayer.validators.Validator;
import databaseAccessLayer.OrderItemDAO;
import model.OrderItem;
import model.Product;

import java.util.ArrayList;
import java.util.List;

// TODO: 10-Apr-20 Add comments

public class OrderItemBLL {
    private List<Validator<OrderItem>> validators;
    private OrderItemDAO orderItemDAO;

    /**
     * Constructor method. It initializes the orderItemDAO and the validator list by adding all the validators for the OrderItem objects.
     */
    public OrderItemBLL() {
        validators = new ArrayList<Validator<OrderItem>>();
        validators.add(new OrderAmountValidator());
        orderItemDAO = new OrderItemDAO();
    }

    /**
     * Drops the foreign keys idorder and idproduct from the orderitem database. The two foreign keys reference the id from the
     * ordert table and the id from the product table.
     */
    public void dropFK() {
        orderItemDAO.dropFK();
    }

    /**
     * Adds the idorder and idproduct foreign keys referencing the ids from the ordert and product tables.
     */
    public void addFK() {
        orderItemDAO.addFK();
    }

    /**
     * Truncates the orderItem table from the database.
     */
    public void truncateTable() {
        orderItemDAO.truncateTable();
    }

    /**
     * Inserts the OrderItem given as parameter into the orderitem table.
     * @param orderItem OrderItem
     */
    public void insertOrderItem(OrderItem orderItem) {
        orderItemDAO.insert(orderItem);
    }
}

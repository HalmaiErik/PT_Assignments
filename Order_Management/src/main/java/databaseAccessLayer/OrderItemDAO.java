package databaseAccessLayer;

import connection.ConnectionFactory;
import model.OrderItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;

public class OrderItemDAO extends AbstractDAO<OrderItem> {
    public OrderItemDAO() {
        super();
    }

    /**
     * Creates drop foreign key query.
     * @return String
     */
    private String createDropFKQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("ALTER TABLE orderitem DROP FOREIGN KEY idproduct , DROP FOREIGN KEY idorder");

        return sb.toString();
    }

    /**
     * Creates add foreign key query.
     * @return String
     */
    private String createAddFKQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("ALTER TABLE orderitem ");
        sb.append("ADD CONSTRAINT idorder FOREIGN KEY (idorder) REFERENCES ordert (id) ");
        sb.append("ON DELETE CASCADE ON UPDATE CASCADE, ");
        sb.append("ADD CONSTRAINT idproduct FOREIGN KEY (idproduct) REFERENCES product (id) ");
        sb.append("ON DELETE CASCADE ON UPDATE CASCADE");

        return sb.toString();
    }

    /**
     * Drops the foreign keys idorder and idproduct, referencing the ids from the ordert and product tables.
     */
    public void dropFK() {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createDropFKQuery();

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "OrderItemDAO: dropFK " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * Adds the foreign keys idorder and idproduct, referencing the ids from the ordert and product tables.
     */
    public void addFK() {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createAddFKQuery();

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "OrderItemDAO: addFK " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }
}

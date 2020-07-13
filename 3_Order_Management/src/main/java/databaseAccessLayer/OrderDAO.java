package databaseAccessLayer;

import connection.ConnectionFactory;
import model.Ordert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;

public class OrderDAO extends AbstractDAO<Ordert> {
    public OrderDAO() {
        super();
    }

    /**
     * Creates drop foreign key query.
     * @return String
     */
    private String createDropFKQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("ALTER TABLE ordert DROP FOREIGN KEY idclient");

        return sb.toString();
    }

    /**
     * Creates add foreign key query.
     * @return String
     */
    private String createAddFKQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("ALTER TABLE ordert ");
        sb.append("ADD CONSTRAINT idclient FOREIGN KEY (idclient) REFERENCES client (id) ");
        sb.append("ON DELETE CASCADE ON UPDATE CASCADE");

        return sb.toString();
    }

    /**
     * Drops the foreign key idclient, referencing the client table's id.
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
            LOGGER.log(Level.WARNING, "OrderDAO: dropFK " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * Adds the foreign key idclient, referencing the client table's id.
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
            LOGGER.log(Level.WARNING, "OrderDAO: addFK " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }
}

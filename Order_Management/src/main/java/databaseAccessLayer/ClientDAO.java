package databaseAccessLayer;

import connection.ConnectionFactory;
import model.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

public class ClientDAO extends AbstractDAO<Client> {
    public ClientDAO() {
        super();
    }

    /**
     * Creates select where query. It searches for the client with name and address given as parameters.
     * @return String
     */
    private String createSelectWithNameAndAddress() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM client");
        sb.append(" WHERE name =?");
        sb.append(" AND address =?");

        return sb.toString();
    }

    /**
     * Finds the client with name and address given as parameters.
     * @param name String
     * @param address String
     * @return Client or null
     */
    public Client findClientByNameAndAddress(String name, String address) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectWithNameAndAddress();

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setObject(1, name);
            statement.setObject(2, address);
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ClientDAO: findTwoFields " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }
}

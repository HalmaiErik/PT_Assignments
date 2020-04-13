package connection;

import java.sql.*;
import java.util.logging.Logger;

public class ConnectionFactory {
    private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String DBURL = "jdbc:mysql://localhost:3306/shopdb";
    private static final String USER = "root";
    private static final String PASS = "root123";

    private static ConnectionFactory singleInstance = new ConnectionFactory();

    /**
     * Private constructor method, that initializes the singleton connection object.
     */
    private ConnectionFactory() {
        try {
            Class.forName(DRIVER);
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates the connection to the database and returns it.
     * @return Connection
     */
    private Connection createConnection() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DBURL, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

    /**
     * Gets the connection in the singleton object.
     * @return Connection
     */
    public static Connection getConnection() {
        return singleInstance.createConnection();
    }

    /**
     * Closes the Connection object given as parameter.
     * @param connection Connection
     */
    public static void close(Connection connection) {
        try {
            connection.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Closes the Statement object given as parameter.
     * @param statement Statement
     */
    public static void close(Statement statement) {
        try {
            statement.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Closes the ResultSet object given as parameter.
     * @param resultSet ResultSet
     */
    public static void close(ResultSet resultSet) {
        try {
            resultSet.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}

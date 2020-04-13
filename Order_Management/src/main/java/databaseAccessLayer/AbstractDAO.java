package databaseAccessLayer;

import connection.ConnectionFactory;
import model.Client;
import model.Ordert;
import model.OrderItem;
import model.Product;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    private final Class<T> type;

    /**
     * Constructor method, that initializes the class type of the AbstractDAO.
     */
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * Creates truncate table query.
     * @return String
     */
    private String createTruncateQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("TRUNCATE TABLE " + type.getSimpleName());

        return sb.toString();
    }

    /**
     * Creates select all query.
     * @return String
     */
    private String createSelectAllQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM ");
        sb.append(type.getSimpleName());

        return sb.toString();
    }

    /**
     * Creates select where query. The field parameter is used to find the specific element to select
     * @param field String
     * @return String
     */
    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + " = ?");

        return sb.toString();
    }

    /**
     * Creates insert query. The t parameter is used to find the number of columns in the specific table.
     * @param t Object
     * @return String
     */
    private String createInsertQuery(T t) {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ");
        sb.append(type.getSimpleName());

        // Client
        if(t instanceof Client) {
            sb.append(" (name, address) VALUES (?,?)");
        }
        // Product
        else if(t instanceof Product) {
            sb.append(" (name, quantity, price) VALUES (?,?,?)");
        }
        // Ordert
        else if(t instanceof Ordert) {
            sb.append(" (idclient, totalprice) VALUES (?,?)");
        }
        //OrderItem
        else if(t instanceof OrderItem) {
            sb.append(" (idorder, idproduct, amount, price) VALUES (?,?,?,?)");
        }

        return sb.toString();
    }

    /**
     * Creates update query. The fieldToUpdate is updated for the element with fieldToFind.
     * @param fieldToUpdate String
     * @param fieldToFind String
     * @return String
     */
    private String createUpdateQuery(String fieldToUpdate, String fieldToFind) {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ");
        sb.append(type.getSimpleName());
        sb.append(" SET " + fieldToUpdate + "=?");
        sb.append(" WHERE " + fieldToFind + "=?");

        return sb.toString();
    }

    /**
     * Creates delete query. The field parameter is used to find the element to delete.
     * @param field String
     * @return String
     */
    private String createDeleteQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + " =?");

        return sb.toString();
    }

    /**
     * Creates objects of the specific class type from a resultSet.
     * @param resultSet ResultSet
     * @return List<T>
     */
    List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();

        try {
            while(resultSet.next()) {
                T instance = type.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    Object value = resultSet.getObject(field.getName());
                    PropertyDescriptor descriptor = new PropertyDescriptor(field.getName(), type);
                    Method method = descriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException | IllegalAccessException | SecurityException | IllegalArgumentException |
                InvocationTargetException | SQLException | IntrospectionException e) {
            e.printStackTrace();
        }

        return list;
    }

    /**
     * Truncates the table specific to the class type.
     */
    public void truncateTable() {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createTruncateQuery();

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO: truncateTable " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * Inserts an object of type T into the database.
     * @param t T
     */
    public void insert(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createInsertQuery(t);

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);

            Field[] declaredFields = type.getDeclaredFields();
            // Skip id
            for(int i = 1; i < declaredFields.length; i++) {
                declaredFields[i].setAccessible(true);
                Object field = declaredFields[i].get(t);
                statement.setObject(i, field);
            }

            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO: insert " + e.getMessage());
        } catch (IllegalAccessException | IllegalArgumentException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * Updates the value of the fieldToUpdate from the database table, having the fieldToFind value equal to findValue.
     * @param updateValue Object
     * @param fieldToUpdate String
     * @param findValue Object
     * @param fieldToFind String
     */
    public void update(Object updateValue, String fieldToUpdate, Object findValue, String fieldToFind) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createUpdateQuery(fieldToUpdate, fieldToFind);

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setObject(1, updateValue);
            statement.setObject(2, findValue);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO: update " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * Deletes an element from the database, having the given field equal to the given value.
     * @param value Object
     * @param field String
     */
    public void delete(Object value, String field) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createDeleteQuery(field);

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setObject(1, value);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO: delete " + e.getMessage());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * Returns the selection of all the elements in the given database table.
     * @return List<T>
     */
    public List<T> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectAllQuery();

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            ArrayList<T> result = (ArrayList<T>) createObjects(resultSet);

            return result;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO: findAll " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return null;
    }

    /**
     * Returns the element in the database having the given field equal to the given value.
     * @param value Object
     * @param field String
     * @return T
     */
    public T find(Object value, String field) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery(field);

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setObject(1, value);
            resultSet = statement.executeQuery();

            if(resultSet.isBeforeFirst()) {
                return createObjects(resultSet).get(0);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO: find " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return null;
    }
}

package businessLogic;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Order {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    private int orderID;
    private int table;
    private String dateTime;

    /**
     * Constructor method. Sets the orderID and table number to the arguments given as parameters and sets the dateTime
     * String by generating the current LocalDateTime and formatting it to the pattern "dd-MM-yyyy HH:mm:ss".
     * @param orderID Integer
     * @param table Integer
     */
    public Order(int orderID, int table) {
        this.orderID = orderID;
        this.table = table;

        LocalDateTime localDateTime = LocalDateTime.now();
        this.dateTime = DATE_TIME_FORMATTER.format(localDateTime);
    }

    /**
     * Overwritten equals method for the hashCode method.
     * @param o Object
     * @return True if equals, False otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return getTable() == order.getTable();
    }

    /**
     * Overwritten hashCode method. Generates the hash code for the Order object.
     * @return Integer
     */
    @Override
    public int hashCode() {
        return Objects.hash(getTable());
    }

    public int getOrderID() {
        return orderID;
    }

    public int getTable() {
        return table;
    }

    public String getDateTime() {
        return dateTime;
    }
}

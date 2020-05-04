package businessLogic;

import java.util.*;

public class Restaurant extends Observable implements IRestaurantProcessing {
    private static final int TABLE_NO = 15;

    private int orderID;
    private static List<MenuItem> restaurantMenu;
    private Map<Order, ArrayList<MenuItem>> orders;

    /**
     * Constructor method. Initializes the orderID variable, restaurantMenu list and orders HashMap.
     */
    public Restaurant() {
        orderID = 0;
        restaurantMenu = new ArrayList<MenuItem>();
        orders = new HashMap<Order, ArrayList<MenuItem>>();
    }

    /**
     * Well Formed type method. Verifies the variables of the Restaurant object.
     * @return true if well formed, false otherwise
     */
    private boolean wellFormed() {
        if(orderID == Integer.MAX_VALUE) return false;

        for (MenuItem item : restaurantMenu) {
            if(item.getName() == null || item.computePrice() <= 0)
                return false;
        }

        for (Order order : orders.keySet()) {
            if (order.getOrderID() <= 0 || order.getTable() <= 0 || order.getTable() > TABLE_NO ||
                order.getDateTime() == null || calcOrderPrice(order) < 0)
                return false;
        }

        return true;
    }

    /**
     * Finds the MenuItem in the restaurant's menu that has the name equal to the String given as parameter.
     * @param name String
     * @return MenuItem or null.
     */
    public MenuItem findItemWithName(String name) {
        for(MenuItem item : restaurantMenu) {
            if(item.getName().equals(name))
                return item;
        }
        return null;
    }

    /**
     * Overwritten newMenuItem method of IRestaurantProcess interface. Adds the new menuItem object to the list containing
     * the restaurant menu. If the menuItem is already in the list, it doesn't do nothing. Checks if wellFormed.
     * @param menuItem MenuItem
     */
    @Override
    public void newMenuItem(MenuItem menuItem) {
        if(restaurantMenu.contains(menuItem))
            return;

        restaurantMenu.add(menuItem);
        assert wellFormed() : "Bad input";
    }

    /**
     * Overwritten deleteMenuItem method of IRestaurantProcess interface. Deletes the given menuItem from the restaurant's
     * menu. It also deletes the items containing the given menuItem from the restaurant's menu.
     * @param menuItem MenuItem
     */
    @Override
    public void deleteMenuItem(MenuItem menuItem) {
        restaurantMenu.remove(menuItem);

        for (MenuItem item : restaurantMenu) {
            if(item instanceof CompositeProduct) {
                if(((CompositeProduct) item).getProducts().contains(menuItem))
                    ((CompositeProduct) item).getProducts().remove(menuItem);
            }
        }
    }

    /**
     * Overwritten editMenuItem method of IRestaurantProcess interface. Replaces the first menuItem with the second.
     * @param menuItem Replaced MenuItem
     * @param edited Replacement MenuItem
     */
    @Override
    public void editMenuItem(MenuItem menuItem, MenuItem edited) {
        deleteMenuItem(menuItem);
        newMenuItem(edited);

        assert wellFormed() : "Bad edited input";
    }

    /**
     * Overwritten newOrder method of IRestaurantProcess interface. Creates a new order with tableId and list of ordered
     * items given as parameters. If the table already has an order, it adds the menuItems to that order.
     * If the order contains composite products, it notifies the Chef. Checks if wellFormed.
     * @param tableId Integer
     * @param menuItems List<MenuItem>
     */
    @Override
    public void newOrder(int tableId, List<MenuItem> menuItems) {
        orderID++;
        Order order = new Order(orderID, tableId);

        if (orders.containsKey(order)) {
            for (MenuItem item : menuItems) {
                orders.get(order).add(item);
            }
        }
        else {
            orders.put(order, (ArrayList<MenuItem>) menuItems);
        }

        for (MenuItem item : menuItems) {
            if(item instanceof CompositeProduct) {
                setChanged();
                notifyObservers(item);
            }
        }

        assert wellFormed() : "Bad order details";
    }

    /**
     * Overwritten calcOrderPrice method of IRestaurantProcess interface. Calculates the price of the order given
     * as parameter.
     * @param order Order
     * @return Float
     */
    @Override
    public float calcOrderPrice(Order order) {
        float sum = 0;

        for(MenuItem item : orders.get(order)) {
            sum += item.computePrice();
        }

        return sum;
    }

    /**
     * Overwritten generateBill method of IRestaurantProcess interface. Generates the bill for the order (table) given
     * as parameter.
     * @param order Order
     * @return String
     */
    @Override
    public String generateBill(Order order) {
        StringBuilder sb = new StringBuilder();

        sb.append("Ordered items:\n");
        for (MenuItem item : orders.get(order)) {
            sb.append(item.toString() + "\n");
        }
        sb.append("Total price: " + calcOrderPrice(order) + " lei\n");
        sb.append("Table: " + order.getTable() + "\n");
        sb.append("Date: "  + order.getDateTime());
        orders.remove(order);

        return sb.toString();
    }

    /**
     * Generates a matrix of objects containing the names of the items in the restaurant's menu. Used to populate the
     * JTables.
     * @return Object[][]
     */
    public static Object[][] generateNames() {
        Object[][] objects = new Object[restaurantMenu.size()][1];

        int row = 0;
        for (MenuItem item : restaurantMenu) {
            objects[row][0] = item.getName();
            row++;
        }

        return objects;
    }

    /**
     * Generates a matrix of objects containing the names and prices of the items in the restaurant's menu. Used to populate
     * the JTables.
     * @return Object[][]
     */
    public static Object[][] generateObjects() {
        Object[][] objects = new Object[restaurantMenu.size()][2];

        int row = 0;
        for (MenuItem item : restaurantMenu) {
            objects[row][0] = item.getName();
            objects[row][1] = item.computePrice();
            row++;
        }

        return objects;
    }

    /**
     * Generates a matrix of objects containing the names of the items in the restaurant's menu, except the name and the
     * name of items in the CompositeProduct given as parameter. Used to populate the JTables.
     * @param prod CompositeProduct
     * @return Object[][]
     */
    public static Object[][] generateObjectsExcept(CompositeProduct prod) {
        Object[][] comp = prod.generateNames();
        Object[][] objects = new Object[restaurantMenu.size()][1];

        int row = 0;
        boolean inProd = false;
        for (MenuItem item : restaurantMenu) {
            for (int i = 0; i < comp.length; i++) {
                if(item.getName().equals(comp[i][0].toString()) || prod.getName().equals(item.getName())) {
                    inProd = true;
                    break;
                }
            }
            if(!inProd) {
                objects[row][0] = item.getName();
                row++;
            }
            else inProd = false;
        }

        return objects;
    }

    public static void setRestaurantMenu(List<MenuItem> restaurantMenu) {
        Restaurant.restaurantMenu = restaurantMenu;
    }

    public static List<MenuItem> getRestaurantMenu() {
        return restaurantMenu;
    }

    public static int getTableNo() {
        return TABLE_NO;
    }
}

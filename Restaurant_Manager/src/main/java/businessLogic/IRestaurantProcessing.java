package businessLogic;

import java.util.List;

public interface IRestaurantProcessing {
    /**
     * Implementable method to add a new menu item in the restaurant's menu.
     * @param menuItem MenuItem
     */
    public void newMenuItem(MenuItem menuItem);

    /**
     * Implementable method to remove a menu item from a restaurant's menu.
     * @param menuItem MenuItem
     */
    public void deleteMenuItem(MenuItem menuItem);

    /**
     * Implementable method to edit a menu item in the restaurant's menu and replace it with a new one.
     * @param menuItem MenuItem
     * @param edited MenuItem
     */
    public void editMenuItem(MenuItem menuItem, MenuItem edited);

    /**
     * Implementable method to create a new order for a table.
     * @param tableId Integer
     * @param items List<MenuItem>
     */
    public void newOrder(int tableId, List<MenuItem> items);

    /**
     * Implementable method to calculate the price of orders from a table.
     * @param order Order
     * @return Float
     */
    public float calcOrderPrice(Order order);

    /**
     * Implementable method to generate the bill's text for an order.
     * @param order Order
     * @return String
     */
    public String generateBill(Order order);
}
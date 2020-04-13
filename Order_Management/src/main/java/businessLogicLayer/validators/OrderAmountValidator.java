package businessLogicLayer.validators;

import model.OrderItem;

public class OrderAmountValidator implements Validator<OrderItem> {
    /**
     * Checks the parameter OrderItem's quantity. If it is smaller or equal to zero it throw an IllegalArgumentException.
     * @param orderItem OrderItem
     */
    public void validate(OrderItem orderItem) {
        if(orderItem.getAmount() <= 0) {
            throw new IllegalArgumentException("An order's amount needs to be bigger than 0");
        }
    }
}

package presentationLayer.Waiter;

import businessLogic.MenuItem;
import businessLogic.Restaurant;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class NewOrderController {
    private Restaurant model;
    private NewOrderFrame orderFrame;

    public NewOrderController(Restaurant model, NewOrderFrame view) {
        this.model = model;
        orderFrame = view;

        orderFrame.addAddListener(new AddListener());
        orderFrame.addRemoveListener(new RemoveListener());
        orderFrame.addEnterListener(new EnterListener());
    }

    class AddListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                DefaultTableModel menuModel = (DefaultTableModel) orderFrame.getMenuTable().getModel();
                DefaultTableModel orderModel = (DefaultTableModel) orderFrame.getOrderTable().getModel();
                int row = orderFrame.getMenuTable().getSelectedRow();

                Object[] object = new Object[1];
                object[0] = menuModel.getValueAt(row, 0);
                orderModel.addRow(object);
            } catch (ArrayIndexOutOfBoundsException ex) {
                orderFrame.showError(new ArrayIndexOutOfBoundsException("Please select an item"));
            }
        }
    }

    class RemoveListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                DefaultTableModel menuModel = (DefaultTableModel) orderFrame.getMenuTable().getModel();
                DefaultTableModel orderModel = (DefaultTableModel) orderFrame.getOrderTable().getModel();
                int row = orderFrame.getOrderTable().getSelectedRow();

                Object[] object = new Object[1];
                object[0] = orderModel.getValueAt(row, 0);
                menuModel.addRow(object);

                orderModel.removeRow(row);
            } catch (ArrayIndexOutOfBoundsException ex) {
                orderFrame.showError(new ArrayIndexOutOfBoundsException("Please select an item"));
            }
        }
    }

    class EnterListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<MenuItem> order = new ArrayList<MenuItem>();
            for (int i = 0; i < orderFrame.getOrderTable().getRowCount(); i++) {
                MenuItem item = model.findItemWithName(String.valueOf(orderFrame.getOrderTable().getValueAt(i, 0)));
                order.add(item);
            }
            model.newOrder(orderFrame.getTableID(), order);
            orderFrame.dispose();
        }
    }
}

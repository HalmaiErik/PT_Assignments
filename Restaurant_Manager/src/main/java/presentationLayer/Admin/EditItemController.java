package presentationLayer.Admin;

import businessLogic.BaseProduct;
import businessLogic.CompositeProduct;
import businessLogic.MenuItem;
import businessLogic.Restaurant;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditItemController {
    private Restaurant model;
    private EditItemFrame editFrame;

    public EditItemController(Restaurant model, EditItemFrame view) {
        this.model = model;
        this.editFrame = view;

        editFrame.addAddListener(new AddListener());
        editFrame.addRemoveListener(new RemoveListener());
        editFrame.addEnterListener(new EnterListener());
    }

    class AddListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                DefaultTableModel prodModel = (DefaultTableModel) editFrame.getProdTable().getModel();
                DefaultTableModel menuModel = (DefaultTableModel) editFrame.getMenuTable().getModel();
                int row = editFrame.getProdTable().getSelectedRow();

                Object[] object = new Object[1];
                object[0] = prodModel.getValueAt(row, 0);
                menuModel.addRow(object);

                prodModel.removeRow(row);
            } catch (ArrayIndexOutOfBoundsException ex) {
                editFrame.showError(new ArrayIndexOutOfBoundsException("Please select an item"));
            }
        }
    }

    class RemoveListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                DefaultTableModel prodModel = (DefaultTableModel) editFrame.getProdTable().getModel();
                DefaultTableModel menuModel = (DefaultTableModel) editFrame.getMenuTable().getModel();
                int row = editFrame.getMenuTable().getSelectedRow();

                Object[] object = new Object[1];
                object[0] = menuModel.getValueAt(row, 0);
                prodModel.addRow(object);

                menuModel.removeRow(row);
            } catch (ArrayIndexOutOfBoundsException ex) {
                editFrame.showError(new ArrayIndexOutOfBoundsException("Please select an item"));
            }
        }
    }

    class EnterListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            MenuItem toEdit = editFrame.getItem();

            if (toEdit instanceof BaseProduct) {
                BaseProduct edited = new BaseProduct(editFrame.getNameString(), editFrame.getPriceInt());
                model.editMenuItem(toEdit, edited);
            } else if (toEdit instanceof CompositeProduct) {
                CompositeProduct edited = new CompositeProduct(editFrame.getNameString());

                for (int i = 0; i < editFrame.getProdTable().getRowCount(); i++) {
                    MenuItem itemToAdd = model.findItemWithName(String.valueOf(editFrame.getProdTable().getValueAt(i, 0)));
                    edited.addProduct(itemToAdd);
                }

                model.editMenuItem(toEdit, edited);
            }
            editFrame.dispose();
        }
    }
}

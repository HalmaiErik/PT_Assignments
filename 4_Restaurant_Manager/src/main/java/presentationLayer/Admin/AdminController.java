package presentationLayer.Admin;

import businessLogic.MenuItem;
import businessLogic.Restaurant;
import dataLayer.RestaurantSerializator;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AdminController {
    private Restaurant model;
    private AdminFrame adminFrame;

    public AdminController(Restaurant model, AdminFrame view) {
        this.model = model;
        adminFrame = view;

        adminFrame.addAddListener(new AddListener());
        adminFrame.addEditListener(new EditListener());
        adminFrame.addDeleteListener(new DeleteListener());
        adminFrame.addRefreshListener(new RefreshListener());
        adminFrame.addSaveListener(new SaveListener());
    }

    class AddListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            AddItemFrame addFrame = new AddItemFrame();
            AddItemController addController = new AddItemController(model, addFrame);
            addFrame.setVisible(true);
        }
    }

    class EditListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                MenuItem selectedItem = model.findItemWithName(adminFrame.getSelectedItem());
                EditItemFrame editFrame = new EditItemFrame(selectedItem);
                EditItemController editController = new EditItemController(model, editFrame);
                editFrame.setVisible(true);
            } catch (ArrayIndexOutOfBoundsException ex) {
                adminFrame.showError(new ArrayIndexOutOfBoundsException("Please select an item"));
            }
        }
    }

    class DeleteListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                MenuItem selectedItem = model.findItemWithName(adminFrame.getSelectedItem());
                model.deleteMenuItem(selectedItem);

                DefaultTableModel tableModel = (DefaultTableModel) adminFrame.getMenuItemTable().getModel();
                tableModel.removeRow(adminFrame.getMenuItemTable().getSelectedRow());
            } catch (ArrayIndexOutOfBoundsException ex) {
                adminFrame.showError(new ArrayIndexOutOfBoundsException("Please select an item"));
            }
        }
    }

    class RefreshListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            adminFrame.initTable();
        }
    }

    class SaveListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            RestaurantSerializator serializator = new RestaurantSerializator(model);
            try {
                serializator.write();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}

package presentationLayer.Admin;

import businessLogic.BaseProduct;
import businessLogic.CompositeProduct;
import businessLogic.MenuItem;
import businessLogic.Restaurant;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddItemController {
    private Restaurant model;
    private AddItemFrame addFrame;

    public AddItemController(Restaurant model, AddItemFrame view) {
        this.model = model;
        this.addFrame = view;

        addFrame.addTypeListener(new TypeListener());
        addFrame.addAddListener(new AddListener());
        addFrame.addRemoveListener(new RemoveListener());
        addFrame.addEnterListener(new EnterListener());
    }

    class TypeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JComboBox typeCB = addFrame.getItemTypeCB();
            String selected = String.valueOf(typeCB.getSelectedItem());

            if(selected.equals("Composite product")) {
                // Init tables
                addFrame.initTables();
                // Enable table buttons
                for(JButton button : addFrame.getCompositeButtons()) {
                    button.setEnabled(true);
                }
                // Disable price TF
                addFrame.getPriceTF().setEnabled(false);
            }
            else if(selected.equals("Base product")) {
                // Init tables
                addFrame.initTables();
                // Disable table buttons
                for(JButton button : addFrame.getCompositeButtons()) {
                    button.setEnabled(false);
                }
                // Enable price TF
                addFrame.getPriceTF().setEnabled(true);
            }
        }
    }

    class AddListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                DefaultTableModel compModel = (DefaultTableModel) addFrame.getCompositeTable().getModel();
                DefaultTableModel prodModel = (DefaultTableModel) addFrame.getNewProdTable().getModel();
                int row = addFrame.getCompositeTable().getSelectedRow();

                Object[] object = new Object[1];
                object[0] = compModel.getValueAt(row, 0);
                prodModel.addRow(object);

                compModel.removeRow(row);
            } catch(ArrayIndexOutOfBoundsException ex) {
                addFrame.showError(new ArrayIndexOutOfBoundsException("Please select an item"));
            }
        }
    }

    class RemoveListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                DefaultTableModel compModel = (DefaultTableModel) addFrame.getCompositeTable().getModel();
                DefaultTableModel prodModel = (DefaultTableModel) addFrame.getNewProdTable().getModel();
                int row = addFrame.getNewProdTable().getSelectedRow();

                Object[] object = new Object[1];
                object[0] = prodModel.getValueAt(row, 0);
                compModel.addRow(object);

                prodModel.removeRow(row);
            } catch (ArrayIndexOutOfBoundsException ex) {
                addFrame.showError(new ArrayIndexOutOfBoundsException("Please select an item"));
            }
        }
    }

    class EnterListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JComboBox typeCB = addFrame.getItemTypeCB();
            String selected = String.valueOf(typeCB.getSelectedItem());

            if (selected.equals("Base product")) {
                BaseProduct newProd = new BaseProduct(addFrame.getNameText(), addFrame.getPriceInt());
                model.newMenuItem(newProd);
            } else if (selected.equals("Composite product")) {
                CompositeProduct newProd = new CompositeProduct(addFrame.getNameText());

                for (int i = 0; i < addFrame.getNewProdTable().getRowCount(); i++) {
                    MenuItem itemToAdd = model.findItemWithName(String.valueOf(addFrame.getNewProdTable().getValueAt(i, 0)));
                    newProd.addProduct(itemToAdd);
                }

                model.newMenuItem(newProd);
            }
            addFrame.initTables();
            addFrame.resetTFs();
        }
    }
}

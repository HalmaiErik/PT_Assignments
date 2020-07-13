package presentationLayer.Admin;

import businessLogic.BaseProduct;
import businessLogic.CompositeProduct;
import businessLogic.MenuItem;
import businessLogic.Restaurant;

import javax.swing.*;
import java.awt.event.ActionListener;

public class EditItemFrame extends JFrame {
    private javax.swing.JButton addToProdBtn;
    private javax.swing.JButton enterBtn;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel menuLab;
    private javax.swing.JTable menuTable;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField nameTF;
    private javax.swing.JLabel priceLabel;
    private javax.swing.JTextField priceTF;
    private javax.swing.JLabel prodLab;
    private javax.swing.JTable prodTable;
    private javax.swing.JButton removeFromProdTable;

    private MenuItem item;

    public EditItemFrame(MenuItem selected) {
        initComponents();
        item = selected;

        nameTF.setText(item.getName());
        priceTF.setText(String.valueOf(item.computePrice()));

        if(item instanceof BaseProduct) {
            addToProdBtn.setEnabled(false);
            removeFromProdTable.setEnabled(false);
        }
        else if(item instanceof CompositeProduct) {
            Object[][] generatedObjs = ((CompositeProduct) item).generateNames();
            prodTable.setModel(new javax.swing.table.DefaultTableModel(
                    generatedObjs,
                    new String[]{
                            "Name"
                    }
            ));

            menuTable.setModel(new javax.swing.table.DefaultTableModel(
                    Restaurant.generateObjectsExcept((CompositeProduct) item),
                    new String [] {
                            "Name"
                    }
            ));
        }
    }

    public void addAddListener(ActionListener aal) {
        addToProdBtn.addActionListener(aal);
    }

    public void addRemoveListener(ActionListener ral) {
        removeFromProdTable.addActionListener(ral);
    }

    public void addEnterListener(ActionListener eal) {
        enterBtn.addActionListener(eal);
    }

    public void showError(Exception e) {
        JOptionPane.showMessageDialog(this, e.getMessage(), e.getClass().toString(), JOptionPane.ERROR_MESSAGE);
    }

    public JTable getProdTable() {
        return prodTable;
    }

    public JTable getMenuTable() {
        return menuTable;
    }

    public String getNameString() {
        return nameTF.getText();
    }

    public int getPriceInt() {
        return Integer.parseInt(priceTF.getText());
    }

    public MenuItem getItem() {
        return item;
    }

    private void initComponents() {
        nameLabel = new javax.swing.JLabel();
        priceLabel = new javax.swing.JLabel();
        nameTF = new javax.swing.JTextField();
        priceTF = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        prodTable = new javax.swing.JTable();
        addToProdBtn = new javax.swing.JButton();
        removeFromProdTable = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        menuTable = new javax.swing.JTable();
        prodLab = new javax.swing.JLabel();
        menuLab = new javax.swing.JLabel();
        enterBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Edit item");
        setResizable(false);

        nameLabel.setText("Name:");

        priceLabel.setText("Price:");

        prodTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String[]{
                        "Name"
                }
                ));
        jScrollPane1.setViewportView(prodTable);

        addToProdBtn.setText(">>");

        removeFromProdTable.setText("<<");

        menuTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                        "Name"
                }
        ));
        jScrollPane2.setViewportView(menuTable);

        prodLab.setText("Product");

        menuLab.setText("Menu");

        enterBtn.setText("Enter");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(nameLabel)
                                                        .addComponent(priceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(nameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(priceTF, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(32, 32, 32))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(enterBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(35, 35, 35)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(12, 12, 12)
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(15, 15, 15)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(addToProdBtn)
                                                        .addComponent(removeFromProdTable))
                                                .addGap(15, 15, 15)
                                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(95, 95, 95)
                                                .addComponent(prodLab)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(menuLab)
                                                .addGap(119, 119, 119))))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(78, 78, 78)
                                .addComponent(addToProdBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(removeFromProdTable)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(57, 57, 57)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(nameLabel)
                                                        .addComponent(nameTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(25, 25, 25)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(priceLabel)
                                                        .addComponent(priceTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                                                .addComponent(enterBtn)
                                                .addGap(52, 52, 52))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(prodLab)
                                                        .addComponent(menuLab))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                                .addContainerGap())
        );

        pack();
    }
}

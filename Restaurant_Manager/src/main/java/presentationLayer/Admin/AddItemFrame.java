package presentationLayer.Admin;

import businessLogic.Restaurant;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AddItemFrame extends JFrame {
    private javax.swing.JButton addToProdBtn;
    private javax.swing.JTable compositeTable;
    private javax.swing.JButton enterBtn;
    private javax.swing.JComboBox itemTypeCB;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel menuLabel;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField nameTF;
    private javax.swing.JTable newProdTable;
    private javax.swing.JLabel priceLabel;
    private javax.swing.JTextField priceTF;
    private javax.swing.JLabel prodLabel;
    private javax.swing.JButton removeFromProdTable;
    private javax.swing.JLabel typeLabel;

    public AddItemFrame() {
        initComponents();
    }

    public void addTypeListener(ActionListener tal) {
        itemTypeCB.addActionListener(tal);
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

    public JComboBox getItemTypeCB() {
        return itemTypeCB;
    }

    public JTextField getPriceTF() {
        return priceTF;
    }

    public JTable getCompositeTable() {
        return compositeTable;
    }

    public JTable getNewProdTable() {
        return newProdTable;
    }

    public String getNameText() {
        return nameTF.getText();
    }

    public int getPriceInt() {
        return Integer.parseInt(priceTF.getText());
    }

    public void resetTFs() {
        nameTF.setText("");
        priceTF.setText("");
    }

    public List<JButton> getCompositeButtons() {
        List<JButton> buttons = new ArrayList<JButton>();
        buttons.add(addToProdBtn);
        buttons.add(removeFromProdTable);

        return buttons;
    }

    public void initTables() {
        compositeTable.setModel(new javax.swing.table.DefaultTableModel(
                Restaurant.generateNames(),
                new String [] {
                        "Name"
                }
        ){
            boolean[] canEdit = new boolean [] {
                    false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });

        newProdTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                        "Name"
                }
        ){
            boolean[] canEdit = new boolean [] {
                    false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
    }

    private void initComponents() {
        itemTypeCB = new javax.swing.JComboBox();
        typeLabel = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        priceLabel = new javax.swing.JLabel();
        nameTF = new javax.swing.JTextField();
        priceTF = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        compositeTable = new javax.swing.JTable();
        addToProdBtn = new javax.swing.JButton();
        removeFromProdTable = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        newProdTable = new javax.swing.JTable();
        menuLabel = new javax.swing.JLabel();
        prodLabel = new javax.swing.JLabel();
        enterBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Add new item");
        setResizable(false);

        itemTypeCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Composite product", "Base product" }));

        typeLabel.setText("Product type:");

        nameLabel.setText("Name:");

        priceLabel.setText("Price:");

        compositeTable.setModel(new javax.swing.table.DefaultTableModel(
                Restaurant.generateObjects(),
                new String [] {
                        "Name"
                }
        ){
            boolean[] canEdit = new boolean [] {
                    false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(compositeTable);

        addToProdBtn.setText(">>");

        removeFromProdTable.setText("<<");

        newProdTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                        "Name"
                }
        ){
            boolean[] canEdit = new boolean [] {
                    false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(newProdTable);

        menuLabel.setText("Menu");

        prodLabel.setText("Product");

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
                                                        .addComponent(typeLabel)
                                                        .addComponent(priceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(itemTypeCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(nameTF)
                                                        .addComponent(priceTF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(enterBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(35, 35, 35)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(15, 15, 15)
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
                                                .addComponent(menuLabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(prodLabel)
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
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(itemTypeCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(typeLabel))
                                                .addGap(25, 25, 25)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(nameLabel)
                                                        .addComponent(nameTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(25, 25, 25)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(priceLabel)
                                                        .addComponent(priceTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(enterBtn)
                                                .addGap(52, 52, 52))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(menuLabel)
                                                        .addComponent(prodLabel))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                                .addContainerGap())
        );

        pack();
    }
}

package presentationLayer.Waiter;

import businessLogic.Restaurant;

import javax.swing.*;
import java.awt.event.ActionListener;

public class NewOrderFrame extends JFrame {
    private javax.swing.JButton addToOrderBtn;
    private javax.swing.JButton enterBtn;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel menuLabel;
    private javax.swing.JTable menuTable;
    private javax.swing.JLabel orderLabel;
    private javax.swing.JTable orderTable;
    private javax.swing.JButton removeFromOrdBtn;

    private int tableID;

    public NewOrderFrame(int tableID) {
        this.tableID = tableID;
        initComponents();
    }

    public void addAddListener(ActionListener aal) {
        addToOrderBtn.addActionListener(aal);
    }

    public void addRemoveListener(ActionListener ral) {
        removeFromOrdBtn.addActionListener(ral);
    }

    public void addEnterListener(ActionListener eal) {
        enterBtn.addActionListener(eal);
    }

    public void showError(Exception e) {
        JOptionPane.showMessageDialog(this, e.getMessage(), e.getClass().toString(), JOptionPane.ERROR_MESSAGE);
    }

    public JTable getMenuTable() {
        return menuTable;
    }

    public JTable getOrderTable() {
        return orderTable;
    }

    public int getTableID() {
        return tableID;
    }

    private void initComponents() {
        jScrollPane1 = new javax.swing.JScrollPane();
        menuTable = new javax.swing.JTable();
        addToOrderBtn = new javax.swing.JButton();
        removeFromOrdBtn = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        orderTable = new javax.swing.JTable();
        menuLabel = new javax.swing.JLabel();
        orderLabel = new javax.swing.JLabel();
        enterBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Create new order");

        menuTable.setModel(new javax.swing.table.DefaultTableModel(
                Restaurant.generateNames(),
                new String [] {
                        "Name"
                }
        ));
        jScrollPane1.setViewportView(menuTable);

        addToOrderBtn.setText(">>");

        removeFromOrdBtn.setText("<<");

        orderTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                        "Name"
                }
        ));
        jScrollPane2.setViewportView(orderTable);

        menuLabel.setText("Menu");

        orderLabel.setText("Order");

        enterBtn.setText("Enter");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(addToOrderBtn)
                                                        .addComponent(removeFromOrdBtn))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                                                .addComponent(enterBtn)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                        .addGroup(layout.createSequentialGroup()
                                .addGap(71, 71, 71)
                                .addComponent(menuLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(orderLabel)
                                .addGap(79, 79, 79))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addComponent(addToOrderBtn)
                                .addGap(18, 18, 18)
                                .addComponent(removeFromOrdBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                                .addComponent(enterBtn)
                                .addGap(38, 38, 38))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(menuLabel)
                                        .addComponent(orderLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addContainerGap())
        );

        pack();
    }
}

package presentationLayer.Waiter;

import businessLogic.Restaurant;

import javax.swing.*;
import java.awt.event.ActionListener;

public class WaiterFrame extends JFrame {
    private javax.swing.JButton billBtn;
    private javax.swing.JButton newOrderBtn;
    private javax.swing.JLabel tableLabel;
    private javax.swing.JComboBox tablesCB;

    public WaiterFrame() {
        initComponents();
    }

    public void addOrderListener(ActionListener oal) {
        newOrderBtn.addActionListener(oal);
    }

    public void addBillListener(ActionListener bal) {
        billBtn.addActionListener(bal);
    }

    public JComboBox getTablesCB() {
        return tablesCB;
    }

    public void showError(Exception e) {
        JOptionPane.showMessageDialog(this, e.getMessage(), e.getClass().toString(), JOptionPane.ERROR_MESSAGE);
    }

    private void initComponents() {
        tableLabel = new javax.swing.JLabel();
        tablesCB = new javax.swing.JComboBox();
        newOrderBtn = new javax.swing.JButton();
        billBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Waiter Manager");

        tableLabel.setText("Tables:");

        DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel();
        for (int i = 1; i <= Restaurant.getTableNo(); i++) {
            comboBoxModel.addElement(String.valueOf(i));
        }
        tablesCB.setModel(comboBoxModel);

        newOrderBtn.setText("New order");

        billBtn.setText("Generate bill");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(tableLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tablesCB, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(billBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(newOrderBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(35, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(tableLabel)
                                        .addComponent(tablesCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(newOrderBtn))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(billBtn)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }
}

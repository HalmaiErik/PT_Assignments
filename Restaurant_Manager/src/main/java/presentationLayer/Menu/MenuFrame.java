package presentationLayer.Menu;

import javax.swing.*;
import java.awt.event.ActionListener;

public class MenuFrame extends JFrame {
    private javax.swing.JButton adminButton;
    private javax.swing.JButton chefButton;
    private javax.swing.JTextField titleTF;
    private javax.swing.JButton waiterButton;

    public MenuFrame() {
        initComponents();
    }

    public void addAdminListener(ActionListener aal) {
        adminButton.addActionListener(aal);
    }

    public void addChefListener(ActionListener cal) {
        chefButton.addActionListener(cal);
    }

    public void addWaiterListener(ActionListener wal) {
        waiterButton.addActionListener(wal);
    }

    public void showInfo(Exception e) {
        JOptionPane.showMessageDialog(this, e.getMessage(), e.getClass().toString(), JOptionPane.INFORMATION_MESSAGE);
    }

    private void initComponents() {
        chefButton = new javax.swing.JButton();
        adminButton = new javax.swing.JButton();
        waiterButton = new javax.swing.JButton();
        titleTF = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Restaurant Manager");
        setResizable(false);

        chefButton.setText("Chef");

        adminButton.setText("Administrator");

        waiterButton.setText("Waiter");

        titleTF.setEditable(false);
        titleTF.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        titleTF.setText("Restaurant Manager");
        titleTF.setBorder(null);
        titleTF.setFocusable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(100, 100, 100)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(chefButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(adminButton, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                                        .addComponent(waiterButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(125, Short.MAX_VALUE)
                                .addComponent(titleTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(122, 122, 122))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(titleTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)
                                .addComponent(adminButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25)
                                .addComponent(waiterButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25)
                                .addComponent(chefButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }
}

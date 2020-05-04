package presentationLayer.Admin;

import businessLogic.Restaurant;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.event.ActionListener;

public class AdminFrame extends JFrame {
    private javax.swing.JButton addBtn;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JButton editBtn;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable menuItemTable;
    private javax.swing.JButton refreshBtn;
    private javax.swing.JButton saveBtn;

    public AdminFrame() {
        initComponents();
    }

    public void addAddListener(ActionListener aal) {
        addBtn.addActionListener(aal);
    }

    public void addDeleteListener(ActionListener dal) {
        deleteBtn.addActionListener(dal);
    }

    public void addEditListener(ActionListener eal) {
        editBtn.addActionListener(eal);
    }

    public void addRefreshListener(ActionListener ral) {
        refreshBtn.addActionListener(ral);
    }

    public void addSaveListener(ActionListener sal) {
        saveBtn.addActionListener(sal);
    }



    public String getSelectedItem() {
        TableModel model = menuItemTable.getModel();
        int row = menuItemTable.getSelectedRow();
        String name = model.getValueAt(row, 0).toString();

        return name;
    }

    public void showError(Exception e) {
        JOptionPane.showMessageDialog(this, e.getMessage(), e.getClass().toString(), JOptionPane.ERROR_MESSAGE);
    }

    public JTable getMenuItemTable() {
        return menuItemTable;
    }

    public void initTable() {
        menuItemTable.setModel(new javax.swing.table.DefaultTableModel(
                Restaurant.generateObjects(),
                new String [] {
                        "Name", "Price"
                }
        ) {
            boolean[] canEdit = new boolean [] {
                    false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
    }

    private void initComponents() {
        jScrollPane1 = new javax.swing.JScrollPane();
        menuItemTable = new javax.swing.JTable();
        addBtn = new javax.swing.JButton();
        editBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        refreshBtn = new javax.swing.JButton();
        saveBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Administrator Manager");

        menuItemTable.setModel(new javax.swing.table.DefaultTableModel(
                Restaurant.generateObjects(),
                new String [] {
                        "Name", "Price"
                }
        ) {
            boolean[] canEdit = new boolean [] {
                    false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(menuItemTable);
        if (menuItemTable.getColumnModel().getColumnCount() > 0) {
            menuItemTable.getColumnModel().getColumn(0).setResizable(false);
            menuItemTable.getColumnModel().getColumn(1).setResizable(false);
        }

        addBtn.setText("ADD");

        editBtn.setText("EDIT");

        deleteBtn.setText("DELETE");

        refreshBtn.setText("Refresh");

        saveBtn.setText("Save");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(deleteBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(editBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(addBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addComponent(refreshBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(saveBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(24, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(addBtn)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(editBtn)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(deleteBtn)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(refreshBtn)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(saveBtn)))
                                .addContainerGap(35, Short.MAX_VALUE))
        );

        pack();
    }
}

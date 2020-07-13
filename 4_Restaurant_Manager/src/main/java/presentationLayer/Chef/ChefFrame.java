package presentationLayer.Chef;

import businessLogic.CompositeProduct;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class ChefFrame extends JFrame implements Observer {
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    private List<CompositeProduct> orders;

    public ChefFrame() {
        initComponents();
        orders = new ArrayList<CompositeProduct>();
    }

    @Override
    public void update(Observable o, Object arg) {
        CompositeProduct order = (CompositeProduct) arg;
        orders.add(order);

        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        Object[] object = new Object[2];
        object[0] = order.getName();
        object[1] = order.toStringProducts();
        tableModel.addRow(object);
    }

    private void initComponents() {
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Chef Manager");

        table.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                        "Name", "Products"
                }
        ));
        jScrollPane1.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setMinWidth(100);
            table.getColumnModel().getColumn(0).setPreferredWidth(100);
            table.getColumnModel().getColumn(0).setMaxWidth(100);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 495, Short.MAX_VALUE)
                                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
                                .addContainerGap())
        );

        pack();
    }
}

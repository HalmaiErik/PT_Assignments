package gui;

import javax.swing.*;
import java.awt.event.ActionListener;

public class View extends JFrame {
    private static final String INIT_VALUE = "0";

    private javax.swing.JButton addBtn;
    private javax.swing.JButton derivateBtn1;
    private javax.swing.JButton derivateBtn2;
    private javax.swing.JButton divideBtn;
    private javax.swing.JButton integrateBtn1;
    private javax.swing.JButton integrateBtn2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton multiplyBtn;
    private javax.swing.JTextField polyOneTF;
    private javax.swing.JTextField polyResultTF;
    private javax.swing.JTextField polyTwoTF;
    private javax.swing.JButton subtractBtn;
    private javax.swing.JButton clearBtn;

    public View() {
        initComponents();

        resetAll();
    }

    public void resetAll() {
        polyOneTF.setText(INIT_VALUE);
        polyTwoTF.setText(INIT_VALUE);
        polyResultTF.setText(INIT_VALUE);
    }

    public void showError(Exception e) {
        JOptionPane.showMessageDialog(this, e.getMessage(), e.toString(), JOptionPane.ERROR_MESSAGE);
    }

    public void setResult(String str) { polyResultTF.setText(str); }

    public void addDerivateListener(ActionListener dal) {
        derivateBtn1.addActionListener(dal);
        derivateBtn2.addActionListener(dal);
    }

    public void addIntegrateListener(ActionListener ial) {
        integrateBtn1.addActionListener(ial);
        integrateBtn2.addActionListener(ial);
    }

    public void addAddListener(ActionListener aal) {
        addBtn.addActionListener(aal);
    }

    public void addSubtractListener(ActionListener sal) {
        subtractBtn.addActionListener(sal);
    }

    public void addMultiplyListener(ActionListener mal) {
        multiplyBtn.addActionListener(mal);
    }

    public void addDivideListener(ActionListener dal) {
        divideBtn.addActionListener(dal);
    }

    public void addClearListener(ActionListener cal) { clearBtn.addActionListener(cal); }

    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        polyOneTF = new javax.swing.JTextField();
        derivateBtn1 = new javax.swing.JButton();
        integrateBtn1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        polyTwoTF = new javax.swing.JTextField();
        derivateBtn2 = new javax.swing.JButton();
        integrateBtn2 = new javax.swing.JButton();
        addBtn = new javax.swing.JButton();
        subtractBtn = new javax.swing.JButton();
        multiplyBtn = new javax.swing.JButton();
        divideBtn = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        polyResultTF = new javax.swing.JTextField();
        clearBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Polynom Calculator");
        setPreferredSize(new java.awt.Dimension(530, 240));
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setText("P1:");

        polyOneTF.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        derivateBtn1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        derivateBtn1.setText("Derivate");

        integrateBtn1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        integrateBtn1.setText("Integrate");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setText("P2:");

        polyTwoTF.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        derivateBtn2.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        derivateBtn2.setText("Derivate");

        integrateBtn2.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        integrateBtn2.setText("Integrate");

        addBtn.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        addBtn.setText("+");

        subtractBtn.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        subtractBtn.setText("-");

        multiplyBtn.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        multiplyBtn.setText("*");

        divideBtn.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        divideBtn.setText("/");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setText("R:");

        polyResultTF.setEditable(false);
        polyResultTF.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        polyResultTF.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));


        clearBtn.setText("Clear");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(polyOneTF, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(derivateBtn1)
                                                .addGap(18, 18, 18)
                                                .addComponent(integrateBtn1))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(subtractBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(multiplyBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(divideBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(polyTwoTF, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(derivateBtn2)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(integrateBtn2))))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(jLabel3)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(polyResultTF, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(77, 77, 77)
                                                .addComponent(clearBtn)
                                                .addGap(58, 58, 58)))
                                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(polyOneTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(derivateBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(integrateBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(polyTwoTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(derivateBtn2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(integrateBtn2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(multiplyBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(divideBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(subtractBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3)
                                        .addComponent(polyResultTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(clearBtn))
                                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }

    public String getInputOne() {
        return polyOneTF.getText();
    }

    public String getInputTwo() { return polyTwoTF.getText(); }

    public JButton getDerivateBtn1() {
        return derivateBtn1;
    }

    public JButton getDerivateBtn2() {
        return derivateBtn2;
    }

    public JButton getIntegrateBtn1() {
        return integrateBtn1;
    }

    public JButton getIntegrateBtn2() {
        return integrateBtn2;
    }
}

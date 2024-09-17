/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui.invoice;

import java.awt.Frame;
import java.io.InputStream;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.BookingItems;
import model.MySQL2;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class InvoicePanel extends javax.swing.JPanel {

    HashMap<String, BookingItems> bookingItemMap = new HashMap<>();
    HashMap<String, String> paymentMethodMap = new HashMap<>();

    public InvoicePanel() {
        initComponents();
        loadPaymentMethods();
    }

    public JLabel getjLabel18() {
        return jLabel18;
    }

    public JLabel getjLabel9() {
        return jLabel9;
    }

    public JTextField getjTextField2() {
        return jTextField2;
    }

    public JTextField getjTextField1() {
        return jTextField1;
    }

    private double total = 0;
    private double discount = 0;
    private double payment = 0;
    private double balance = 0;
    private String paymentMethod = "Select";

    private void calculate() {

        if (discountField.getText().isEmpty()) {
            discount = 0;
        } else {
            try {
                discount = Double.parseDouble(discountField.getText());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number for discount.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                discountField.setText("0");
            }
        }

        if (paymentField.getText().isEmpty()) {
            payment = 0;
        } else {
            try {
                payment = Double.parseDouble(paymentField.getText());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number for payment.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                paymentField.setText("0");
            }
        }

        total = Double.parseDouble(jFormattedTextField2.getText());

        paymentMethod = String.valueOf(jComboBox1.getSelectedItem());

        total -= discount;

        if (paymentMethod.equals("Cash")) {
            //cash
            paymentField.setEditable(true);
            balance = payment - total;

            if (balance < 0) {
                jButton6.setEnabled(false);
            } else {
                jButton6.setEnabled(true);
            }

        } else {
            //card
            payment = total;
            balance = 0;
            paymentField.setText(String.valueOf(payment));
            paymentField.setEditable(false);
            jButton6.setEnabled(true);
        }

        jFormattedTextField5.setText(String.valueOf(balance));

    }

    private void loadPaymentMethods() {

        try {

            ResultSet resultSet = MySQL2.executeSearch("SELECT * FROM `payment_method`");

            Vector<String> vector = new Vector<>();

            while (resultSet.next()) {
                vector.add(resultSet.getString("name"));
                paymentMethodMap.put(resultSet.getString("name"), resultSet.getString("id"));
            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox1.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void loadBookingItems() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);

        total = 0;

        try {
            String bid = jLabel18.getText();
            if (bid.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Select Booking.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                return;
            } else {
                try {
                    int intBID = Integer.parseInt(bid);
                } catch (NumberFormatException e) {
                    return;
                }
            }
            ResultSet resultSet = MySQL2.executeSearch("SELECT * FROM `booking_item` INNER JOIN `activity` ON `activity`.`id`=`booking_item`.`activity_id` WHERE `bookings_id`='" + bid + "' ");

            while (resultSet.next()) {
                String acName = resultSet.getString("activity.name");
                String price = resultSet.getString("activity.price");
                String qty = resultSet.getString("qty");
                String aID = resultSet.getString("id");

                Vector<String> vector = new Vector<>();
                vector.add(aID);
                vector.add(acName);
                vector.add(qty);
                vector.add(price);

                double itemTotal = Double.parseDouble(qty) * Double.parseDouble(price);
                total += itemTotal;
                vector.add(String.valueOf(itemTotal));
                model.addRow(vector);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        jFormattedTextField2.setText(String.valueOf(total));
        calculate();
    }

//    private void putItem() {
//        String bid = jLabel18.getText();
//        String email = jTextField1.getText();
//
//        if (bid.isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Please Select Booking.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
//            return;
//        } else {
//            try {
//                int intBID = Integer.parseInt(bid);
//            } catch (NumberFormatException e) {
//                JOptionPane.showMessageDialog(this, "Please Select Booking.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
//                return;
//            }
//        }
//
//        try {
//            ResultSet resultSet = MySQL2.executeSearch("SELECT * FROM `booking_item` INNER JOIN `activity` ON `activity`.`id`=`booking_item`.`activity_id` WHERE `bookings_id`='" + bid + "' ");
//            while (resultSet.next()) {
//                String acName = resultSet.getString("activity.name");
//                String price = resultSet.getString("activity.name");
//                String qty = resultSet.getString("activity.name");
//                String aID = resultSet.getString("activity.name");
//
//                BookingItems bookingItems = new BookingItems();
//                bookingItems.setActivityName(acName);
//                bookingItems.setPrice(price);
//                bookingItems.setQty(qty);
//                bookingItems.setAcID(aID);
//
//                loadBookingItems();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(this, "Something Wrong.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
//            return;
//        }
//
//    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jButton10 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jFormattedTextField2 = new javax.swing.JFormattedTextField();
        jLabel20 = new javax.swing.JLabel();
        discountField = new javax.swing.JFormattedTextField();
        jLabel21 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel22 = new javax.swing.JLabel();
        paymentField = new javax.swing.JFormattedTextField();
        jLabel23 = new javax.swing.JLabel();
        jFormattedTextField5 = new javax.swing.JFormattedTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();

        jLabel2.setFont(new java.awt.Font("Poppins", 1, 16)); // NOI18N
        jLabel2.setText("Invoice");

        jLabel7.setText("Booking");

        jButton4.setText("Select Booking");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel3.setText("Customer email");

        jTextField2.setEditable(false);

        jLabel4.setText("Activity");

        jButton5.setText("Select an Activity");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel5.setText("Activity Name");

        jTextField1.setEditable(false);

        jLabel1.setText("Qty");

        jButton2.setBackground(new java.awt.Color(0, 153, 0));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton2.setText("Add to Invoice");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(0, 153, 0));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton3.setText("Reset");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("BID");

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("ID");

        jTextField4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField4.setText("1");

        jButton10.setText("RemoveQty");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton9.setText("Add Qty");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField2)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField1)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton9)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel18))
                .addGap(5, 5, 5)
                .addComponent(jButton4)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(5, 5, 5)
                .addComponent(jButton5)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton10)
                    .addComponent(jButton9))
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addContainerGap(63, Short.MAX_VALUE))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item ID", "Activity Name", "Qty", "Price", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(10);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(10);
        }

        jButton6.setBackground(new java.awt.Color(51, 153, 0));
        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton6.setText("Save Invoice");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setBackground(new java.awt.Color(204, 0, 51));
        jButton7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton7.setText("Cancle");

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel19.setText("Total");

        jFormattedTextField2.setEditable(false);
        jFormattedTextField2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jFormattedTextField2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField2.setText("0");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel20.setText("Discount");

        discountField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        discountField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        discountField.setText("0");
        discountField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                discountFieldActionPerformed(evt);
            }
        });
        discountField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                discountFieldKeyReleased(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel21.setText("Payment Method");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel22.setText("Payment");

        paymentField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        paymentField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        paymentField.setText("0");
        paymentField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                paymentFieldKeyReleased(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel23.setText("Balance");

        jFormattedTextField5.setEditable(false);
        jFormattedTextField5.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jFormattedTextField5.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextField5.setText("0");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jFormattedTextField2)
                            .addComponent(discountField)
                            .addComponent(paymentField)
                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jFormattedTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(discountField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(paymentField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22))
                .addGap(7, 7, 7)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(jFormattedTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6)
                    .addComponent(jButton7))
                .addContainerGap())
        );

        jLabel6.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabel6.setText("Double click for remove items");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 533, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(8, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addContainerGap())
        );

        jButton8.setText("Reset All");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton8)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        SelectBooking sb = new SelectBooking((Frame) javax.swing.SwingUtilities.getWindowAncestor(this), true, this);
        sb.setVisible(true);
        loadBookingItems();
//        putItem();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void discountFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_discountFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_discountFieldActionPerformed

    private void discountFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_discountFieldKeyReleased
        calculate();
    }//GEN-LAST:event_discountFieldKeyReleased

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        calculate();
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void paymentFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_paymentFieldKeyReleased
        calculate();
    }//GEN-LAST:event_paymentFieldKeyReleased

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        int row = jTable1.getSelectedRow();
        String acName = jTextField1.getText();
        String id = jLabel9.getText();
        String bid = jLabel18.getText();
        String qty = jTextField4.getText();

        if (row != -1) {
            if (acName.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Activity Name cannot be empty.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            } else if (id.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Activity ID is missing.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    int activityId = Integer.parseInt(id);

                    if (activityId <= 0) {
                        JOptionPane.showMessageDialog(this, "Activity ID must be a positive integer.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if (bid.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Booking ID is missing.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                    } else if (qty.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Quantity cannot be empty.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        // Validate quantity as an integer
                        try {
                            int quantity = Integer.parseInt(qty);
                            if (quantity <= 0) {
                                JOptionPane.showMessageDialog(this, "Quantity must be a positive number.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                            ResultSet resultSet = MySQL2.executeSearch("SELECT * FROM `booking_item` WHERE `booking_item`.`bookings_id` = '" + bid + "' AND `activity_id`='" + id + "' ");
                            if (resultSet.next()) {
                                String existingQty = resultSet.getString("qty");
                                int updatedQty = Integer.parseInt(existingQty) + quantity;
                                MySQL2.executeIUD("UPDATE `booking_item` SET `qty`='" + updatedQty + "' WHERE `bookings_id` = '" + bid + "' AND `activity_id`='" + id + "'");
                                JOptionPane.showMessageDialog(this, "Quantity updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(this, "Activity Not Found to update!", "Success", JOptionPane.ERROR_MESSAGE);
                            }
                            loadBookingItems();
                            reset();

                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(this, "Quantity must be a valid number.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                        } catch (Exception e) {
                            e.printStackTrace();
                            JOptionPane.showMessageDialog(this, "An error occurred while processing the booking.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Please select an activity row. Activity ID must be a valid integer.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select an Activity row to update.", "Invalid Input", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        int row = jTable1.getSelectedRow();
        String acName = jTextField1.getText();
        String id = jLabel9.getText();
        String bid = jLabel18.getText();
        String qty = jTextField4.getText();

        if (row != -1) {
            if (acName.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Activity Name cannot be empty.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            } else if (id.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Activity ID is missing.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    int activityId = Integer.parseInt(id);
                    if (activityId <= 0) {
                        JOptionPane.showMessageDialog(this, "Activity ID must be a positive integer.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (bid.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Booking ID is missing.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                    } else if (qty.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Quantity cannot be empty.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        try {
                            int quantity = Integer.parseInt(qty);
                            if (quantity <= 0) {
                                JOptionPane.showMessageDialog(this, "Quantity to subtract must be a positive number.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                                return;
                            }

                            ResultSet resultSet = MySQL2.executeSearch("SELECT * FROM `booking_item` WHERE `booking_item`.`bookings_id` = '" + bid + "' AND `activity_id`='" + id + "' ");
                            if (resultSet.next()) {
                                String existingQty = resultSet.getString("qty");
                                int currentQty = Integer.parseInt(existingQty);

                                int updatedQty = currentQty - quantity;
                                if (updatedQty <= 0) {
                                    JOptionPane.showMessageDialog(this, "Cannot reduce quantity below 1.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                                } else {
                                    MySQL2.executeIUD("UPDATE `booking_item` SET `qty`='" + updatedQty + "' WHERE `bookings_id` = '" + bid + "' AND `activity_id`='" + id + "'");
                                    JOptionPane.showMessageDialog(this, "Quantity updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                                    loadBookingItems();
                                    reset();
                                }
                            } else {
                                JOptionPane.showMessageDialog(this, "Activity Item Not Found.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(this, "Quantity must be a valid number.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                        } catch (Exception e) {
                            e.printStackTrace();
                            JOptionPane.showMessageDialog(this, "An error occurred while processing the booking.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Please select an activity row. Activity ID must be a valid integer.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select an Activity row to update.", "Invalid Input", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int row = jTable1.getSelectedRow();
        jTextField1.setText(String.valueOf(jTable1.getValueAt(row, 1)));
        String itemId = String.valueOf(jTable1.getValueAt(row, 0));
        try {
            ResultSet resultSet = MySQL2.executeSearch("SELECT * FROM `booking_item` WHERE `booking_item`.`id` = '" + itemId + "' ");
            if (resultSet.next()) {
                jLabel9.setText(resultSet.getString("activity_id"));

                if (evt.getClickCount() == 2) {
                    int response = JOptionPane.showConfirmDialog(this, "Do you need to remove this Activity?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                    if (response == JOptionPane.YES_OPTION) {
                        MySQL2.executeIUD("DELETE FROM `booking_item` WHERE `id` ='" + itemId + "' ");
                        JOptionPane.showMessageDialog(this, "Remove Activity successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        loadBookingItems();
                        reset();
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        reset();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        resetAll();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        String email = jTextField2.getText();
        if (email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Select Booking First ", "WARNING", JOptionPane.WARNING_MESSAGE);
        } else {
            SelectInActivity sia = new SelectInActivity((Frame) javax.swing.SwingUtilities.getWindowAncestor(this), true, this);
            sia.setVisible(true);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String acName = jTextField1.getText();
        String id = jLabel9.getText();
        String bid = jLabel18.getText();
        String qty = jTextField4.getText();
        if (acName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Plese Select Booking.", "Validation Error", JOptionPane.WARNING_MESSAGE);
        } else if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Activity ID is missing.", "Validation Error", JOptionPane.WARNING_MESSAGE);
        } else {
            try {
                int activityId = Integer.parseInt(id);

                if (activityId <= 0) {
                    JOptionPane.showMessageDialog(this, "Activity ID must be a positive integer.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (bid.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Booking ID is missing.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                } else if (qty.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Quantity cannot be empty.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        int quantity = Integer.parseInt(qty);
                        if (quantity <= 0) {
                            JOptionPane.showMessageDialog(this, "Quantity must be a positive number.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        ResultSet resultSet = MySQL2.executeSearch("SELECT * FROM `booking_item` WHERE `booking_item`.`bookings_id` = '" + bid + "' AND `activity_id`='" + id + "' ");
                        if (resultSet.next()) {
                            int response = JOptionPane.showConfirmDialog(this, "Activity already added! Want to update qty?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                            if (response == JOptionPane.YES_OPTION) {
                                String existingQty = resultSet.getString("qty");
                                int updatedQty = Integer.parseInt(existingQty) + quantity;
                                MySQL2.executeIUD("UPDATE `booking_item` SET `qty`='" + updatedQty + "' WHERE `bookings_id` = '" + bid + "' AND `activity_id`='" + id + "'");
                                JOptionPane.showMessageDialog(this, "Quantity updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                            }
                        } else {
                            MySQL2.executeIUD("INSERT INTO `booking_item`(`qty`,`bookings_id`,`activity_id`) "
                                    + "VALUES('" + qty + "', '" + bid + "', '" + id + "') ");
                            JOptionPane.showMessageDialog(this, "Activity Added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        }
                        loadBookingItems();
                        reset();

                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(this, "Quantity must be a valid number.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                    } catch (Exception e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(this, "An error occurred while processing the booking.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please select an activity.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        try {
            String bid = jLabel18.getText();
            String email = jTextField2.getText();
            String dateTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            String paidAmound = paymentField.getText();
            String paymentMethodID = paymentMethodMap.get(String.valueOf(jComboBox1.getSelectedItem()));
            String discount = String.valueOf(discountField.getText());
            String invoiceID = null ;

            if (email.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please select Booking.", "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            } else {
                try {
                    MySQL2.executeIUD("UPDATE `bookings` SET `b_status_id`='1' WHERE `id` = '" + bid + "' ");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Update Error.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            
            if(discount.isEmpty()){
                discount = "0";
            }
            
            //insert to invoice
            MySQL2.executeIUD("INSERT INTO `invoice` (`date`, `paid_amount`, `payment_method_id`, `discount`, `bookings_id` ) VALUES ('"+dateTime+"', '"+paidAmound+"', '"+paymentMethodID+"', '"+discount+"', '"+bid+"' )");
            //insert to invoice
            ResultSet rs = MySQL2.executeSearch("SELECT `id` FROM `invoice` WHERE `bookings_id`= '"+bid+"'");
            if(rs.next()){
                invoiceID = rs.getString("id");
            }else{
                JOptionPane.showMessageDialog(this, "Update Error.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            }
            
            HashMap<String, Object> params = new HashMap<>();
            params.put("Parameter1", jFormattedTextField2.getText());
            params.put("Parameter2", discountField.getText());
            params.put("Parameter3", String.valueOf(jComboBox1.getSelectedItem()));
            params.put("Parameter4", paymentField.getText());
            params.put("Parameter5", jFormattedTextField5.getText());
            
            params.put("Parameter6", invoiceID);
            params.put("Parameter7", email);
            params.put("Parameter8", dateTime);
            
            
            //View or print report
            InputStream inputSteam = this.getClass().getResourceAsStream("/reports/asuraInvoice.jasper");
            
            JRTableModelDataSource dataSource = new JRTableModelDataSource(jTable1.getModel());
            
            JasperPrint jasperPrint = JasperFillManager.fillReport(inputSteam, params, dataSource);
            
            JasperViewer.viewReport(jasperPrint, false);
            resetAll();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_jButton6ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField discountField;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JFormattedTextField jFormattedTextField2;
    private javax.swing.JFormattedTextField jFormattedTextField5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JFormattedTextField paymentField;
    // End of variables declaration//GEN-END:variables

    private void reset() {
        jTextField1.setText("");
        jLabel9.setText("ID");
    }

    private void resetAll() {
        jTextField2.setText("");
        jLabel18.setText("BID");
        jTable1.clearSelection();
        jFormattedTextField2.setText("0");
        paymentField.setText("0");
        calculate();
        loadBookingItems();
        reset();
    }

}

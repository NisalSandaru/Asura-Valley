/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui.booking;

import gui.admin.Dashboard;
import gui.booking.TestD;
import java.awt.Frame;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.MySQL2;


public class ManageBooking extends javax.swing.JPanel {

    public ManageBooking() {
        initComponents();
        loadPenOrders();
        jDateChooser1.setEnabled(false);
    }

    public JTextField getjTextField1() {
        return jTextField1;
    }

    public JLabel getjLabel10() {
        return jLabel10;
    }

    public void loadPenOrders() {
        try {
            ResultSet resultSet = MySQL2.executeSearch("SELECT * FROM `bookings` INNER JOIN `customer` ON `customer`.`mobile` = `bookings`.`customer_mobile` WHERE `b_status_id` = '2' ");

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);
            while (resultSet.next()) {
                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("id"));
                vector.add(resultSet.getString("customer.email"));
                vector.add(resultSet.getString("mobile"));
                vector.add(resultSet.getString("ar_date"));

                model.addRow(vector);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadBookDetails() {
        try {
            int row = jTable1.getSelectedRow();
            if (row != -1) {
                String pid = String.valueOf(jTable1.getValueAt(row, 0));
                ResultSet resultSet = MySQL2.executeSearch("SELECT * FROM `booking_item` INNER JOIN `activity` ON `activity`.`id` = `booking_item`.`activity_id` WHERE `bookings_id` = '" + pid + "' ");

                DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
                model.setRowCount(0);

                while (resultSet.next()) {
                    Vector<String> vector = new Vector<>();
                    int qty = resultSet.getInt("qty");
                    double price = resultSet.getDouble("activity.price");
                    double total = price * qty;
                    vector.add(resultSet.getString("id"));
                    vector.add(resultSet.getString("activity.name"));
                    vector.add(resultSet.getString("qty"));
                    vector.add(String.format("%.2f", total));
                    model.addRow(vector);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("Poppins", 1, 16)); // NOI18N
        jLabel1.setText("Manage Booking");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Edit Order");

        jDateChooser1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jLabel3.setText("Arrival Date");

        jLabel4.setText("Activity");

        jButton1.setText("Select Activity");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel5.setText("Activity Name");

        jTextField1.setEditable(false);

        jLabel6.setText("Qty :");

        jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField2.setText("1");

        jButton4.setText("Add Activity");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton6.setText("Add New Booking");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("Remove Booking");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("Reset");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setText("Add Qty");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("ID");

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("BID");

        jButton10.setText("Remove  Qty");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField1)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton9)
                    .addComponent(jButton10))
                .addGap(18, 18, 18)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton8)
                .addGap(27, 27, 27)
                .addComponent(jButton6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton7)
                .addContainerGap(276, Short.MAX_VALUE))
        );

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Pending Orders");

        jLabel7.setText("You can view order details by clicking pending order");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Cus email", "mobile", "arrival Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
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

        jButton2.setText("Reset All");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 767, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE))
        );

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Order Details");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Activity Name", "Qty", "Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.getTableHeader().setReorderingAllowed(false);
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jLabel12.setText("you can delete the activity on double click ");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 767, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        AddBooking addBooking = new AddBooking((Frame) javax.swing.SwingUtilities.getWindowAncestor(this), true);
        addBooking.setVisible(true);
        loadPenOrders();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        reset();
        int row = jTable1.getSelectedRow();
        String dateString = String.valueOf(jTable1.getValueAt(row, 3));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
        try {
            Date date = sdf.parse(dateString);
            jDateChooser1.setDate(date);
            jLabel11.setText(String.valueOf(jTable1.getValueAt(row, 0)));
        } catch (ParseException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error parsing date: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        loadBookDetails();
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        int row = jTable2.getSelectedRow();
        jTextField1.setText(String.valueOf(jTable2.getValueAt(row, 1)));
        String itemId = String.valueOf(jTable2.getValueAt(row, 0));
        try {
            ResultSet resultSet = MySQL2.executeSearch("SELECT * FROM `booking_item` WHERE `booking_item`.`id` = '" + itemId + "' ");
            if (resultSet.next()) {
                jLabel10.setText(resultSet.getString("activity_id"));

                if (evt.getClickCount() == 2) {
                    int response = JOptionPane.showConfirmDialog(this, "Do you need to remove this Activity?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                    if (response == JOptionPane.YES_OPTION) {
                        MySQL2.executeIUD("DELETE FROM `booking_item` WHERE `id` ='" + itemId + "' ");
                        JOptionPane.showMessageDialog(this, "Remove Activity successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        loadBookDetails();
                        reset();
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jTable2MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int row = jTable1.getSelectedRow();
        if (row != -1) {
            SelectAnActivity sa = new SelectAnActivity((Frame) javax.swing.SwingUtilities.getWindowAncestor(this), true, this);
            sa.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Please Select a Customer.", "Invalid Input", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        int row = jTable1.getSelectedRow();
        String acName = jTextField1.getText();
        String id = jLabel10.getText();
        String bid = jLabel11.getText();
        String qty = jTextField2.getText();
        if (row != -1) {
            if (acName.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Select Activity.", "Validation Error", JOptionPane.WARNING_MESSAGE);
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
                            loadBookDetails();
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
            JOptionPane.showMessageDialog(this, "Please select a Booking.", "Invalid Input", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        resetAll();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        int row = jTable2.getSelectedRow();
        String acName = jTextField1.getText();
        String id = jLabel10.getText();
        String bid = jLabel11.getText();
        String qty = jTextField2.getText();

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
                            loadBookDetails();
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
        int row = jTable2.getSelectedRow();
        String acName = jTextField1.getText();
        String id = jLabel10.getText();
        String bid = jLabel11.getText();
        String qty = jTextField2.getText();

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
                                    loadBookDetails();
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

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        reset();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        int row = jTable1.getSelectedRow();
        if (row != -1) {
            String bid = String.valueOf(jTable1.getValueAt(row, 0));
            if (!bid.isEmpty()) {
                int response = JOptionPane.showConfirmDialog(this, "Do you need to remove this Order? This can affect your reports", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                if (response == JOptionPane.YES_OPTION) {
                    try {
                        MySQL2.executeIUD("DELETE FROM `booking_item` WHERE `bookings_id` ='" + bid + "' ");
                        MySQL2.executeIUD("DELETE FROM `bookings` WHERE `id` = '" + bid + "' ");
                        JOptionPane.showMessageDialog(this, "Remove Booking successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        loadBookDetails();
                        loadPenOrders();
                        resetAll();
                    } catch (Exception e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(this, "An error occurred while removing the activity.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Booking ID is missing.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a Booking.", "Invalid Input", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton7ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables

    private void resetAll() {
        jDateChooser1.setDate(null);
        jLabel11.setText("BID");
        jTable1.clearSelection();
        jTable2.clearSelection();
        reset();
    }

    private void reset() {
        jTextField1.setText("");
        jTextField2.setText("1");
        jLabel10.setText("ID");
    }
}

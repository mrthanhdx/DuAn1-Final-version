/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import interfacee.hoaDonInterface;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import service.HoaDonServiceImpl;
import service.KhachHangServiceImpl;

/**
 *
 * @author trant
 */
public class khachhangdialog extends javax.swing.JFrame {

    private KhachHangServiceImpl khachHangServiceImpl = new KhachHangServiceImpl();
    hoaDonInterface hoadonService = new HoaDonServiceImpl();
    private Runnable callback;
    private DefaultTableModel model = new DefaultTableModel();
    SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
    private static int maHD;

    public khachhangdialog(int idHD, Runnable callback) {
        initComponents();

        maHD = idHD;
        this.callback = callback;
        filltable();
    }

    void filltable() {
        model = (DefaultTableModel) tblThongtinkhachhang.getModel();
        model.setRowCount(0);
        for (model.KhachHang kh : khachHangServiceImpl.getAll()) {
            Object rowData[] = new Object[6];
            rowData[0] = kh.getId();
            rowData[1] = kh.getTen();
            rowData[2] = date.format(kh.getNgaysinh());
            rowData[3] = kh.isGioitinh() ? "Nam" : "Nữ";
            rowData[4] = kh.getSdt();
            rowData[5] = kh.getEmail();
            model.addRow(rowData);
        }
    }

    void showDT(int index) {
        List<model.KhachHang> List = khachHangServiceImpl.getAll();
        model.KhachHang kh = List.get(index);
        txtTen1.setText(kh.getTen());
        txtNgaySinh.setText(date.format(kh.getNgaysinh()));
        rdoNam.setSelected(kh.isGioitinh());
        rdoNu.setSelected(!kh.isGioitinh());
        txtSdt.setText(kh.getSdt());
        txtEmail.setText(kh.getEmail());
    }

    private void addKhachHang() {
        model.KhachHang kh = new model.KhachHang();

        String tenKh = txtTen1.getText().trim();
        if (tenKh.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập Tên khách hàng.");
            return;
        }

        String ngaySinhKh = txtNgaySinh.getText().trim();
        if (ngaySinhKh.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập Ngày sinh khách hàng.");
            return;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date ngaySinh;
        try {
            ngaySinh = sdf.parse(ngaySinhKh);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "Ngày sinh không đúng định dạng dd/MM/yyyy.");
            return;
        }

        String sdt = txtSdt.getText().trim();
        if (sdt.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập Số điện thoại khách hàng.");
            return;
        }

        String email = txtEmail.getText().trim();
        if (email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập Email khách hàng.");
            return;
        }

        boolean gt = rdoNam.isSelected();

        kh.setTen(tenKh);
        kh.setNgaysinh(ngaySinh);
        kh.setGioitinh(gt);
        kh.setSdt(sdt);
        kh.setEmail(email);

        boolean addResult = khachHangServiceImpl.add(kh);
        if (addResult) {
            JOptionPane.showMessageDialog(this, "Add khách hàng thành công.");
        } else {
            JOptionPane.showMessageDialog(this, "Add khách hàng không thành công.");
        }
    }

    void LamMoi() {
        txtTen1.setText("");
        txtNgaySinh.setText("");
        txtEmail.setText("");
        txtSdt.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRadioButton1 = new javax.swing.JRadioButton();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblThongtinkhachhang = new javax.swing.JTable();
        txtTen1 = new javax.swing.JTextField();
        txtSdt = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtNgaySinh = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnThem = new javax.swing.JButton();
        btnLammoi = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();
        btnChon = new javax.swing.JButton();
        txtSearchKhachHang = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        jRadioButton1.setText("jRadioButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        tblThongtinkhachhang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Họ Tên", "Ngày sinh", "Giới tính", "Số điện thoại", "Email"
            }
        ));
        tblThongtinkhachhang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblThongtinkhachhangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblThongtinkhachhang);

        txtTen1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTen1ActionPerformed(evt);
            }
        });

        jLabel4.setText("Ngày sinh");

        jLabel6.setText("Email");

        jLabel5.setText("Số điện thoại");

        jLabel3.setText("Tên");

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnLammoi.setText("Làm mới");

        jLabel7.setText("Giới Tính");

        buttonGroup1.add(rdoNam);
        rdoNam.setSelected(true);
        rdoNam.setText("Nam");

        buttonGroup1.add(rdoNu);
        rdoNu.setText("Nữ");
        rdoNu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoNuActionPerformed(evt);
            }
        });

        jButton1.setText("Thoát");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnChon.setText("Chọn");
        btnChon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonActionPerformed(evt);
            }
        });

        txtSearchKhachHang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKhachHangKeyReleased(evt);
            }
        });

        jLabel1.setText("Nhập SDT");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 622, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTen1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel7)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(rdoNam)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rdoNu)
                        .addGap(28, 28, 28))
                    .addComponent(jLabel4)
                    .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtEmail)
                                .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnLammoi)
                            .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtSearchKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(85, 85, 85)
                        .addComponent(btnChon)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTen1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addComponent(jLabel7))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnLammoi, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnChon, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSearchKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdoNam)
                            .addComponent(rdoNu)
                            .addComponent(jLabel1))
                        .addGap(31, 31, 31)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tblThongtinkhachhangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblThongtinkhachhangMouseClicked
        // TODO add your handling code here:
        int selectedIndex = tblThongtinkhachhang.getSelectedRow();
        if (selectedIndex >= 0) {
            this.showDT(selectedIndex);
        }
    }//GEN-LAST:event_tblThongtinkhachhangMouseClicked

    private void txtTen1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTen1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTen1ActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        addKhachHang();
        filltable();
    }//GEN-LAST:event_btnThemActionPerformed

    private void rdoNuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoNuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoNuActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnChonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonActionPerformed
        int idKH = Integer.parseInt(tblThongtinkhachhang.getValueAt(tblThongtinkhachhang.getSelectedRow(), 0) + "");
        if (hoadonService.updateMaKHforHoaDon(idKH, maHD)) {
            JOptionPane.showMessageDialog(this, "Chon thanh cong khach hang ID = " + idKH);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Chon khach hang that bai");
        }
        if (callback != null) {
            callback.run();
        }
    }//GEN-LAST:event_btnChonActionPerformed

    private void txtSearchKhachHangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKhachHangKeyReleased
        String txtSearchKH = txtSearchKhachHang.getText();
        model = (DefaultTableModel) tblThongtinkhachhang.getModel();
        model.setRowCount(0);
        for (model.KhachHang kh : khachHangServiceImpl.getAll()) {
            if (kh.getSdt().contains(txtSearchKH)) {
                Object rowData[] = new Object[6];
                rowData[0] = kh.getId();
                rowData[1] = kh.getTen();
                rowData[2] = date.format(kh.getNgaysinh());
                rowData[3] = kh.isGioitinh() ? "Nam" : "Nữ";
                rowData[4] = kh.getSdt();
                rowData[5] = kh.getEmail();
                model.addRow(rowData);
            }
        }
    }//GEN-LAST:event_txtSearchKhachHangKeyReleased

//    /**
////     * @param args the command line arguments
////     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(khachhangdialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(khachhangdialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(khachhangdialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(khachhangdialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new khachhangdialog().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChon;
    private javax.swing.JButton btnLammoi;
    private javax.swing.JButton btnThem;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTable tblThongtinkhachhang;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNgaySinh;
    private javax.swing.JTextField txtSdt;
    private javax.swing.JTextField txtSearchKhachHang;
    private javax.swing.JTextField txtTen1;
    // End of variables declaration//GEN-END:variables
}

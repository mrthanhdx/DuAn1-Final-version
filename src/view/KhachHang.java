/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import service.KhachHangServiceImpl;

/**
 *
 * @author Dung
 */
public class KhachHang extends javax.swing.JPanel {

    private KhachHangServiceImpl khachHangServiceImpl = new KhachHangServiceImpl();
    private DefaultTableModel model = new DefaultTableModel();
    SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");

    /**
     * Creates new form KhachHang
     */
    public KhachHang() {
        initComponents();
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
    String tenKh = txtTen1.getText();
    String ngaySinhkh = txtNgaySinh.getText();
    String Sdt = txtSdt.getText();
    String Email = txtEmail.getText();

    // Kiểm tra các trường thông tin
    if (tenKh.isEmpty() || ngaySinhkh.isEmpty() || Sdt.isEmpty() || Email.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin.");
        return;
    }

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    Date ngaySinh;
    try {
        ngaySinh = sdf.parse(ngaySinhkh);
    } catch (ParseException ex) {
        JOptionPane.showMessageDialog(this, "Ngày sinh không đúng định dạng dd/MM/yyyy.");
        return;
    }
    
    // Kiểm tra định dạng SĐT
    if (!Sdt.matches("\\d{10,11}")) {
        JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ.");
        return;
    }
    
    // Kiểm tra định dạng Email
    if (!Email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
        JOptionPane.showMessageDialog(this, "Email không hợp lệ.");
        return;
    }

    model.KhachHang kh = new model.KhachHang();
    boolean gt = rdoNam.isSelected(); // Nếu rdoNam được chọn, giới tính là true, ngược lại là false
    
    kh.setTen(tenKh);
    kh.setNgaysinh(ngaySinh);
    kh.setGioitinh(gt);
    kh.setSdt(Sdt);
    kh.setEmail(Email);
    
    boolean addResult = khachHangServiceImpl.add(kh);
    if (addResult) {
        JOptionPane.showMessageDialog(this, "Thêm khách hàng thành công.");
    } else {
        JOptionPane.showMessageDialog(this, "Thêm khách hàng không thành công.");
    }
}



   private void updateKhachHang() {
    // Kiểm tra xem người dùng đã chọn dòng nào trong bảng chưa
    int selectedRow = tblThongtinkhachhang.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Vui lòng chọn một khách hàng để cập nhật.");
        return;
    }
    
    model.KhachHang kh = new model.KhachHang();
    String tenKh = txtTen1.getText();
    String ngaySinhkh = txtNgaySinh.getText();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    Date ngaySinh;
    try {
        ngaySinh = sdf.parse(ngaySinhkh);
    } catch (ParseException ex) {
        JOptionPane.showMessageDialog(this, "Ngày sinh không đúng định dạng dd/MM/yyyy.");
        return; 
    }
    boolean gt = rdoNam.isSelected(); // Nếu rdoNam được chọn, giới tính là true, ngược lại là false
    String Sdt = txtSdt.getText();
    String Email = txtEmail.getText();

    // Kiểm tra các trường thông tin
    if (tenKh.isEmpty() || ngaySinhkh.isEmpty() || Sdt.isEmpty() || Email.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin.");
        return;
    }

    // Kiểm tra định dạng SĐT
    if (!Sdt.matches("\\d{10,11}")) {
        JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ.");
        return;
    }

    // Kiểm tra định dạng Email
    if (!Email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
        JOptionPane.showMessageDialog(this, "Email không hợp lệ.");
        return;
    }

    String id = tblThongtinkhachhang.getValueAt(selectedRow, 0).toString();

    kh.setTen(tenKh);
    kh.setNgaysinh(ngaySinh);
    kh.setGioitinh(gt);
    kh.setSdt(Sdt);
    kh.setEmail(Email);

    // Thực hiện cập nhật và kiểm tra kết quả
    khachHangServiceImpl.update(kh, Integer.valueOf(id));
    if (checkValidateKhachHang()) {
        JOptionPane.showMessageDialog(this, "Cập nhật thông tin khách hàng thành công");
        this.filltable();
    } else {
        JOptionPane.showMessageDialog(this, "Cập nhật thông tin khách hàng thất bại");
    }
}



    boolean checkValidateKhachHang() {
        if (txtTen1.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên khách hàng đang trống !");
            return false;
        } else if (txtNgaySinh.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ngày sinh khách hàng đang trống !");
            return false;
        } else if (txtSdt.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "SDT khách hàng đang để trống !");
            return false;
        } else if (txtEmail.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Email khách hàng đang trống !");

            return false;
        }
        return true;
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        txtEmail = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtTen1 = new javax.swing.JTextField();
        txtSdt = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnThem = new javax.swing.JButton();
        btnCapnhat = new javax.swing.JButton();
        btnLammoi = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblThongtinkhachhang = new javax.swing.JTable();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        txtNgaySinh = new javax.swing.JTextField();

        setBackground(new java.awt.Color(204, 255, 255));

        jLabel3.setText("Tên");

        jLabel4.setText("Ngày sinh");

        txtTen1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTen1ActionPerformed(evt);
            }
        });

        jLabel5.setText("Số điện thoại");

        jLabel6.setText("Email");

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnCapnhat.setText("Cập nhật");
        btnCapnhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapnhatActionPerformed(evt);
            }
        });

        btnLammoi.setText("Làm mới");
        btnLammoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLammoiActionPerformed(evt);
            }
        });

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

        jTabbedPane1.addTab("Thông tin khách hàng", jScrollPane1);

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

        jLabel7.setText("Giới Tính");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTen1, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel7)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(rdoNam)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rdoNu)
                        .addGap(28, 28, 28))
                    .addComponent(jLabel4)
                    .addComponent(txtNgaySinh))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                            .addComponent(txtSdt))
                        .addGap(92, 92, 92)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCapnhat, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLammoi, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(66, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 706, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTen1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdoNam)
                            .addComponent(rdoNu)
                            .addComponent(btnLammoi, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCapnhat, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtTen1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTen1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTen1ActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        addKhachHang();
        filltable();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnCapnhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapnhatActionPerformed
        // TODO add your handling code here:
        updateKhachHang();
        filltable();
    }//GEN-LAST:event_btnCapnhatActionPerformed

    private void rdoNuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoNuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoNuActionPerformed

    private void btnLammoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLammoiActionPerformed
        // TODO add your handling code here:
        LamMoi();
        filltable();
    }//GEN-LAST:event_btnLammoiActionPerformed

    private void tblThongtinkhachhangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblThongtinkhachhangMouseClicked
        // TODO add your handling code here:
        int selectedIndex = tblThongtinkhachhang.getSelectedRow();
        if (selectedIndex >= 0) {
            this.showDT(selectedIndex);
        }
    }//GEN-LAST:event_tblThongtinkhachhangMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapnhat;
    private javax.swing.JButton btnLammoi;
    private javax.swing.JButton btnThem;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTable tblThongtinkhachhang;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNgaySinh;
    private javax.swing.JTextField txtSdt;
    private javax.swing.JTextField txtTen1;
    // End of variables declaration//GEN-END:variables
}

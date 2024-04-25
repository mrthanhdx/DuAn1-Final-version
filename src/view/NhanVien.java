/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import java.util.ArrayList;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import lombok.Builder;
import model.User;
import org.bridj.cpp.mfc.StandardAfxCommands;
import service.UserService;
import com.toedter.calendar.JDateChooser;
import java.awt.Choice;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class NhanVien extends javax.swing.JPanel {

    ArrayList<String> ls = new ArrayList<>();
    UserService us = new UserService();

    /**
     * Creates new form NhanVien
     */
    public NhanVien() {
        initComponents();
        showData();
        ls.add("Nhân Viên");
        ls.add("Quản Lý");
        DefaultComboBoxModel dcb = (DefaultComboBoxModel) cbbChucVu.getModel();
        dcb.removeAllElements();
        dcb.addAll(ls);
        dcb.setSelectedItem("Nhân Viên");
    }

    private String fomatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String newDate = sdf.format(date);
        return newDate;
    }

    private void showData() {
        DefaultTableModel dtm = (DefaultTableModel) tbl.getModel();
        dtm.setRowCount(0);
        for (User u : us.getAll()) {
            Object[] rowData = {
                u.getTen(),
                fomatDate(u.getNgaySinh()),
                u.getGioTinh() == true ? "Nam" : "Nữ",
                u.getSdt(),
                u.getTaiKhoan(),
                u.getIdCV() == 1 ? "Quản Lý" : "Nhân Viên",
                u.getEmail(),
                u.isTrangThai() == false ? "Làm Việc" : "Không làm việc",
                u.getNgayTao(),
                u.getNgaySua()
            };
            dtm.addRow(rowData);
        }
    }

    private void fillData(int row) {
        User u = us.getAll().get(row);
        txtTen.setText(u.getTen());
        txtNgaySinh.setText(fomatDate(u.getNgaySinh()));
        TxtSDT.setText(u.getSdt());
        txtEmail.setText(u.getEmail());
        txtMatKhau.setText(u.getMatKhau());
        txtTaiKhoan.setText(u.getTaiKhoan());
        if (u.getGioTinh()) {
            rdoNam.setSelected(true);
        } else {
            rdoNu.setSelected(true);
        }

        if (u.isTrangThai() == false) {
            ckbTrangThai.setSelected(true);
        } else {
            ckbTrangThai.setSelected(false);
        }
        if (u.getIdCV() == 1) {
            cbbChucVu.setSelectedItem("Quản Lý");
        } else {
            cbbChucVu.setSelectedItem("Nhân Viên");
        }
    }

    private User getDataAdd() {
        String ten = txtTen.getText();
        String ngaySinhString = txtNgaySinh.getText();
        String sdt = TxtSDT.getText();
        String taiKhoan = txtTaiKhoan.getText();
        String matKhau = txtMatKhau.getText();
        String email = txtEmail.getText();

        if (ten.isEmpty() || sdt.isEmpty() || taiKhoan.isEmpty() || matKhau.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng điền đủ trường");
            return null;
        }

        if (!sdt.matches("^0+[0-9]{9}$")) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ");
            return null;
        }

        if (ngaySinhString.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập ngày sinh");
            return null;
        }

        Date ngaySinh;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            ngaySinh = sdf.parse(ngaySinhString);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ngay Sinh chưa đúng định dạng dd/MM/yyyy !");
            return null;
        }

        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(ngaySinh.getTime());

        boolean gioiTinh = rdoNam.isSelected();
        boolean trangThai = !ckbTrangThai.isSelected();
        String chucVuString = cbbChucVu.getSelectedItem().toString();
        int chucVu = chucVuString.equals("Quản Lý") ? 1 : 2;

        User u = new User();
        u.setTen(ten);
        u.setNgaySinh((java.sql.Date) sqlDate);
        u.setSdt(sdt);
        u.setTaiKhoan(taiKhoan);
        u.setMatKhau(matKhau);
        u.setEmail(email);
        u.setGioTinh(gioiTinh);
        u.setIdCV(chucVu);
        u.setTrangThai(trangThai);

        boolean checkAccount = true;
        for (int i = 0; i < us.getAll().size(); i++) {
            if (us.getAll().get(i).getTaiKhoan().equals(taiKhoan)) {
                checkAccount = false;
                break;
            }
        }

        if (!checkAccount) {
            JOptionPane.showMessageDialog(this, "Tài khoản đã tồn tại");
            return null;
        } else {
            JOptionPane.showMessageDialog(this, "Thêm thành công");
            return u;
        }
    }

    private User getDataUpdate() {
    String ten = txtTen.getText();
    String ngaySinhString = txtNgaySinh.getText();
    Date ngaySinh = null;
    
    // Parse ngaySinhString to Date
    try {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        ngaySinh = sdf.parse(ngaySinhString);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Ngày sinh không đúng định dạng dd/MM/yyyy !");
        return null;
    }
    
    // Convert ngaySinh to java.sql.Date
    java.sql.Date sqlDate = new java.sql.Date(ngaySinh.getTime());
    
    String sdt = TxtSDT.getText();
    String taiKhoan = txtTaiKhoan.getText();
    String matKhau = txtMatKhau.getText();
    String email = txtEmail.getText();
    
    // Determine gioiTinh based on radio button selection
    boolean gioiTinh = rdoNam.isSelected();
    
    // Determine trangThai based on checkbox selection
    boolean trangThai = !ckbTrangThai.isSelected();
    
    // Determine chucVu based on combo box selection
    String chucVuString = cbbChucVu.getSelectedItem().toString();
    int chucVu = chucVuString.equals("Quản Lý") ? 1 : 2;
    
    User u = new User();
    u.setTen(ten);
    u.setNgaySinh(sqlDate);
    u.setSdt(sdt);
    u.setTaiKhoan(taiKhoan);
    u.setMatKhau(matKhau);
    u.setEmail(email);
    u.setGioTinh(gioiTinh);
    u.setIdCV(chucVu);
    u.setTrangThai(trangThai);
    
    // Validation
    if (ten.isEmpty() || sdt.isEmpty() || taiKhoan.isEmpty() || matKhau.isEmpty() || email.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ trường");
        return null;
    } else if (!sdt.matches("^0+[0-9]{9}$")) {
        JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ");
        return null;
    } else {
        JOptionPane.showMessageDialog(this, "Sửa thành công");
        return u;
    }
}
  

    public void clear() {
        TxtSDT.setText("");
        txtEmail.setText("");
        txtMatKhau.setText("");
        txtNgaySinh.setText("");
        txtTaiKhoan.setText("");
        txtTen.setText("");
        cbbChucVu.setSelectedIndex(0);
        rdoNam.setSelected(true);
        ckbTrangThai.setSelected(false);
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
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl = new javax.swing.JTable();
        txtTen = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtNgaySinh = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        TxtSDT = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtTaiKhoan = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cbbChucVu = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        jLabel10 = new javax.swing.JLabel();
        ckbTrangThai = new javax.swing.JCheckBox();
        txtMatKhau = new javax.swing.JPasswordField();

        setBackground(new java.awt.Color(204, 255, 255));

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Tên", "Ngày Sinh", "Giới Tính", "SĐT", "Tài Khoản", "Chức vụ", "Email", "Trạng Thái", "Ngày Tạo", "Ngày Sửa"
            }
        ));
        tbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl);

        jLabel2.setText("Tên");

        jLabel3.setText("Ngày Sinh");

        jLabel4.setText("SĐT");

        jLabel5.setText("Tài Khoản");

        jLabel6.setText("Mật Khẩu");

        jLabel7.setText("Email");

        jLabel8.setText("Chức Vụ");

        cbbChucVu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel9.setText("Giới Tính");

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

        jLabel10.setText("Trạng thái");

        ckbTrangThai.setText("Làm Việc");
        ckbTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckbTrangThaiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(66, 66, 66)
                                        .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(68, 68, 68)
                                        .addComponent(jLabel3))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(66, 66, 66)
                                        .addComponent(TxtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(68, 68, 68)
                                        .addComponent(jLabel4))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(66, 66, 66)
                                        .addComponent(btnThem)
                                        .addGap(82, 82, 82)
                                        .addComponent(btnSua))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(68, 68, 68)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(txtMatKhau)
                                                .addComponent(txtTaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
                                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING))
                                            .addComponent(jLabel6))))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(129, 129, 129)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel8)
                                            .addComponent(cbbChucVu, 0, 311, Short.MAX_VALUE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(rdoNam)
                                                .addGap(36, 36, 36)
                                                .addComponent(rdoNu))
                                            .addComponent(jLabel10)
                                            .addComponent(ckbTrangThai)
                                            .addComponent(jLabel7)
                                            .addComponent(txtEmail)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(137, 137, 137)
                                        .addComponent(jLabel9))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(68, 68, 68)
                                .addComponent(jLabel2))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(66, 66, 66)
                                .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(TxtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(ckbTrangThai))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnThem)
                            .addComponent(btnSua))
                        .addGap(34, 34, 34))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(214, 214, 214)
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdoNam)
                            .addComponent(rdoNu))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void rdoNuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoNuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoNuActionPerformed

    private void tblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMouseClicked
        int row = tbl.getSelectedRow();
        fillData(row);
    }//GEN-LAST:event_tblMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        us.them(getDataAdd());

        showData();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        try {
    int index = tbl.getSelectedRow();
    if (index < 0) {
        JOptionPane.showMessageDialog(this, "Vui lòng chọn người muốn sửa");
        clear();
        return;
    }

    int id = us.getAll().get(index).getId();

    int Choice = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn sửa không?");
    if (Choice == JOptionPane.YES_OPTION) {
        User updatedUser = getDataUpdate();
        if (updatedUser != null) {
            us.sua(updatedUser, id);
            showData();
        }
    } else {
        clear();
        return;
    }
} catch (Exception e) {
    JOptionPane.showMessageDialog(this, "Có lỗi xảy ra. Vui lòng thử lại sau.");
    return;
}


    }//GEN-LAST:event_btnSuaActionPerformed

    private void ckbTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckbTrangThaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ckbTrangThaiActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField TxtSDT;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbbChucVu;
    private javax.swing.JCheckBox ckbTrangThai;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTable tbl;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JPasswordField txtMatKhau;
    private javax.swing.JTextField txtNgaySinh;
    private javax.swing.JTextField txtTaiKhoan;
    private javax.swing.JTextField txtTen;
    // End of variables declaration//GEN-END:variables
}

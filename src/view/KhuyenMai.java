/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import interfacee.KhuyenMaiService;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import service.KhuyenMaiService_IMPL;

/**
 *
 * @author trant
 */
public class KhuyenMai extends javax.swing.JPanel {

    DefaultTableModel mol = new DefaultTableModel();
    KhuyenMaiService kmsv = new KhuyenMaiService_IMPL();

    /**
     * Creates new form promotionalView
     */
    public KhuyenMai() {
        initComponents();
        showCombodata();
        loadData();
    }

    void loadData() {
        mol = (DefaultTableModel) tbl_Bang.getModel();
        mol.setRowCount(0);
        for (model.KhuyenMai km : kmsv.getAll()) {
            mol.addRow(new Object[]{
                km.getId(),
                km.getTenKhuyenMai(),
                km.getHinhThucKM(),
                km.getGiaTriGiam(),
                km.getSoLuong(),
                km.getCodeKhuyenMai()
            });
        }
    }

    private void showCombodata() {
        List<String> data = model.KhuyenMai.KMData.getdata();
        for (String s : data) {
            cbo_hinhthuc.addItem(s);
        }
    }

    void add() {
    Random random = new Random();
    String ten = txt_Ten.getText();
    String giaTri = txt_giatrigiam.getText();
    String soLuongStr = txt_soluong.getText();
    String hinhThuc = cbo_hinhthuc.getSelectedItem().toString();
    String codeKhuyenMai = "KM"+(100000 + random.nextInt(900000));
  
    if (ten.isEmpty() || giaTri.isEmpty() || soLuongStr.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin.");
        return;
    }

    double giaTriGiam;
    try {
        giaTriGiam = Double.parseDouble(giaTri);
        if (giaTriGiam < 0) {
            JOptionPane.showMessageDialog(this, "Giá trị giảm không thể là số âm.");
            return;
        }
        
       
        if (hinhThuc.equalsIgnoreCase("%") && giaTriGiam > 90) {
            JOptionPane.showMessageDialog(this, "Giá trị giảm không được nhập quá 90%.");
            return;
        }
        if (hinhThuc.equalsIgnoreCase("VND") && giaTriGiam < 10000) {
    JOptionPane.showMessageDialog(this, "Giá trị giảm không được ít hơn 10,000 VND.");
    return;
}
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Giá trị giảm phải là một số.");
        return;
    }

  
    int soLuong;
    try {
        soLuong = Integer.parseInt(soLuongStr);
        if (soLuong <= 0) {
            JOptionPane.showMessageDialog(this, "Số lượng phải là một số nguyên dương.");
            return;
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Số lượng phải là một số nguyên.");
        return;
    }

    
    for (model.KhuyenMai existingKm : kmsv.Getbyten(ten)) {
        if (existingKm.getTenKhuyenMai().equals(ten)) {
            JOptionPane.showMessageDialog(this, "Tên khuyến mãi đã tồn tại.");
            return;
        }
    }
    model.KhuyenMai km = new model.KhuyenMai();
    km.setTenKhuyenMai(ten);
    km.setGiaTriGiam(giaTri);
    km.setSoLuong(soLuong);
    km.setHinhThucKM(hinhThuc);
    km.setCodeKhuyenMai(codeKhuyenMai);
    boolean addResult = kmsv.add(km);
    if (addResult) {
        JOptionPane.showMessageDialog(this, "Thêm khuyến mãi thành công.");
    } else {
        JOptionPane.showMessageDialog(this, "Thêm khuyến mãi không thành công.");
    }
}


    void showDT(int index) {
        List<model.KhuyenMai> List = kmsv.getAll();
        model.KhuyenMai km = List.get(index);
        txt_Ten.setText(km.getTenKhuyenMai());
        txt_giatrigiam.setText(km.getGiaTriGiam());
        txt_soluong.setText(String.valueOf(km.getSoLuong()));
        cbo_hinhthuc.setSelectedItem(String.valueOf(km.getHinhThucKM()));
    }

   void update() {
   
    int selectedRow = tbl_Bang.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Vui lòng chọn một khuyến mãi để cập nhật.");
        return;
    }

  
    int id = Integer.parseInt(tbl_Bang.getValueAt(selectedRow, 0) + "");

    String ten = txt_Ten.getText();
    String giaTri = txt_giatrigiam.getText();
    String soLuongStr = txt_soluong.getText();
    String hinhThuc = cbo_hinhthuc.getSelectedItem().toString();

    
    if (ten.isEmpty() || giaTri.isEmpty() || soLuongStr.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin.");
        return;
    }

 
    double giaTriGiam;
    try {
        giaTriGiam = Double.parseDouble(giaTri);
        if (giaTriGiam < 0) {
            JOptionPane.showMessageDialog(this, "Giá trị giảm không thể là số âm.");
            return;
        }

       
        if (hinhThuc.equalsIgnoreCase("%") && giaTriGiam > 90) {
            JOptionPane.showMessageDialog(this, "Giá trị giảm không được nhập quá 90%.");
            return;
        }
        if (hinhThuc.equalsIgnoreCase("VND") && giaTriGiam < 10000) {
            JOptionPane.showMessageDialog(this, "Giá trị giảm không được ít hơn 10,000 VND.");
            return;
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Giá trị giảm phải là một số.");
        return;
    }

   
    int soLuong;
    try {
        soLuong = Integer.parseInt(soLuongStr);
        if (soLuong <= 0) {
            JOptionPane.showMessageDialog(this, "Số lượng phải là một số nguyên dương.");
            return;
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Số lượng phải là một số nguyên.");
        return;
    }

    
    for (model.KhuyenMai existingKm : kmsv.Getbyten(ten)) {
        if (existingKm.getTenKhuyenMai().equals(ten) && existingKm.getId() != id) {
            JOptionPane.showMessageDialog(this, "Tên khuyến mãi đã tồn tại.");
            return;
        }
    }

    model.KhuyenMai km = new model.KhuyenMai();
    km.setId(id); 
    km.setTenKhuyenMai(ten);
    km.setGiaTriGiam(giaTri);
    km.setSoLuong(soLuong);
    km.setHinhThucKM(hinhThuc);

    
    if (kmsv.Update(km, id)) {
        JOptionPane.showMessageDialog(this, "Cập nhật khuyến mãi thành công.");
    } else {
        JOptionPane.showMessageDialog(this, "Cập nhật khuyến mãi không thành công.");
    }
}


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_giatrigiam = new javax.swing.JTextField();
        txt_soluong = new javax.swing.JTextField();
        cbo_hinhthuc = new javax.swing.JComboBox<>();
        btn_update = new javax.swing.JButton();
        btn_add = new javax.swing.JButton();
        btn_reset = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_Bang = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        txt_Ten = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(204, 255, 255));
        setMaximumSize(new java.awt.Dimension(940, 580));
        setMinimumSize(new java.awt.Dimension(940, 580));
        setPreferredSize(new java.awt.Dimension(940, 580));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Tên khuyến mại");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 100, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Số lượng");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 100, -1));

        txt_giatrigiam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_giatrigiamActionPerformed(evt);
            }
        });
        add(txt_giatrigiam, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 110, 30));
        add(txt_soluong, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 150, 30));

        cbo_hinhthuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_hinhthucActionPerformed(evt);
            }
        });
        add(cbo_hinhthuc, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 130, 60, 30));

        btn_update.setBackground(new java.awt.Color(125, 224, 237));
        btn_update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/floppy-disk.png"))); // NOI18N
        btn_update.setText("Update");
        btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateActionPerformed(evt);
            }
        });
        add(btn_update, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 170, 100, 40));

        btn_add.setBackground(new java.awt.Color(125, 224, 237));
        btn_add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add.png"))); // NOI18N
        btn_add.setText("Add");
        btn_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addActionPerformed(evt);
            }
        });
        add(btn_add, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 30, 100, 40));

        btn_reset.setBackground(new java.awt.Color(125, 224, 237));
        btn_reset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/refresh.png"))); // NOI18N
        btn_reset.setText("Refresh");
        btn_reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_resetActionPerformed(evt);
            }
        });
        add(btn_reset, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 100, 100, 40));

        tbl_Bang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Tên khuyến mại", "Hình Thức Khuyến Mại", "Giá Trị Giảm", "Số Lượng", "Code Khuyến mại"
            }
        ));
        tbl_Bang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_BangMouseClicked(evt);
            }
        });
        tbl_Bang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbl_BangKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_Bang);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 910, 290));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setText("Bảng khuyến mãi");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 230, 160, 30));
        add(txt_Ten, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 150, 30));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Giá trị giảm");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 100, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void txt_giatrigiamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_giatrigiamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_giatrigiamActionPerformed

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
        // TODO add your handling code here:
        update();
        loadData();
    }//GEN-LAST:event_btn_updateActionPerformed

    private void btn_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addActionPerformed
        // TODO add your handling code here:
        add();
        loadData();
    }//GEN-LAST:event_btn_addActionPerformed

    private void btn_resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_resetActionPerformed
        // TODO add your handling code here:
        txt_Ten.setText("");
        txt_giatrigiam.setText("");
        txt_soluong.setText("");
        cbo_hinhthuc.setSelectedIndex(0);
    }//GEN-LAST:event_btn_resetActionPerformed

    private void tbl_BangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_BangMouseClicked
        // TODO add your handling code here:
        int selectedIndex = tbl_Bang.getSelectedRow();
        if (selectedIndex >= 0) {
            this.showDT(selectedIndex);
        }
    }//GEN-LAST:event_tbl_BangMouseClicked

    private void tbl_BangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbl_BangKeyPressed

    }//GEN-LAST:event_tbl_BangKeyPressed

    private void cbo_hinhthucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_hinhthucActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbo_hinhthucActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_add;
    private javax.swing.JButton btn_reset;
    private javax.swing.JButton btn_update;
    private javax.swing.JComboBox<String> cbo_hinhthuc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tbl_Bang;
    private javax.swing.JTextField txt_Ten;
    private javax.swing.JTextField txt_giatrigiam;
    private javax.swing.JTextField txt_soluong;
    // End of variables declaration//GEN-END:variables
}

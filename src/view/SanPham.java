/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.ChatLieu;
import model.Chucvu;
import model.KichCo;
import model.MauSac;
import model.NSX;
import model.SanPhamChiTiet;
import model.ThuongHieu;
import service.ChatLieuServicesImpl;
import service.KichCoServiceImpl;
import service.MauSacServicesImpl;
import service.NSXServicesImpl;
import service.SanPhamChiTietService;
import service.SanPhamService;
import service.ThuongHieuServicesImpl;

/**
 *
 * @author H
 */
public class SanPham extends javax.swing.JPanel {

    SanPhamService service = new SanPhamService();
    SanPhamChiTietService serviceCt = new SanPhamChiTietService();
    DefaultTableModel model = new DefaultTableModel();
    KichCoServiceImpl serviceKichCo = new KichCoServiceImpl();
    MauSacServicesImpl serviceMauSac = new MauSacServicesImpl();
    NSXServicesImpl serviceNSX = new NSXServicesImpl();
    ThuongHieuServicesImpl serviceThuongHieu = new ThuongHieuServicesImpl();
    ChatLieuServicesImpl serviceChatLieu = new ChatLieuServicesImpl();

    /**
     * Creates new form SanPham
     */
    public SanPham() {
        initComponents();
        loadTableSp();
        loadTableSpCt();
        loadForm();

    }

    private void loadForm() {
        cboTenSp.removeAllItems();
        cboSize.removeAllItems();
        cboMauSac.removeAllItems();
        cboNSX.removeAllItems();
        cboThuonghieu.removeAllItems();
        cboChatLieu.removeAllItems();

        for (model.SanPham s : service.getAll()) {
            cboTenSp.addItem(s.getTen());
        }
        for (KichCo s : serviceKichCo.getAll()) {
            cboSize.addItem(s.getTen());
        }
        for (MauSac s : serviceMauSac.getAll()) {
            cboMauSac.addItem(s.getTen());
        }
        for (NSX s : serviceNSX.getAll()) {
            cboNSX.addItem(s.getTen());
        }
        for (ThuongHieu s : serviceThuongHieu.getAll()) {
            cboThuonghieu.addItem(s.getTen());
        }
        for (ChatLieu s : serviceChatLieu.getAll()) {
            cboChatLieu.addItem(s.getTen());
        }
    }

    public void loadTableSp() {
        model = (DefaultTableModel) tblSanPham.getModel();

        model.setRowCount(0);

        List<model.SanPham> list = service.getAll();

        int stt = 1;

        for (model.SanPham sp : list) {
            model.addRow(new Object[]{
                stt,
                sp.getTen(),
                sp.getNgayTao(),
                sp.getNgaySua()
            });
            stt++;
        }

    }

    public void loadTableSpCt() {
        model = (DefaultTableModel) tblSanPhamChiTiet.getModel();

        model.setRowCount(0);

        List<model.SanPhamChiTiet> list = serviceCt.getAll();

        int stt = 1;

        for (SanPhamChiTiet sp : list) {
            model.addRow(new Object[]{
                sp.getId(),
                sp.getTenSp().getTen(),
                sp.getChatLieu(),
                sp.getKichCo(),
                sp.getMauSac(),
                sp.getNhaSx(),
                sp.getThuongHieu(),
                sp.getSoLuongTon(),
                sp.getGiaNhap(),
                sp.getGiaBan(),
                sp.getMoTa()
            });
        }

    }

    private model.SanPham getFormSanPham() {
        model.SanPham s = new model.SanPham();
        s.setTen(txtTen.getText());
        return s;
    }

    private boolean checkFrmSPCT() {
        if (txtGiaNhap.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "gia nhap không được để trống");
            txtGiaNhap.requestFocus();
            return false;
        } else {
            try {
                if (Float.parseFloat(txtGiaNhap.getText().trim()) < 0) {
                    JOptionPane.showMessageDialog(this, "gia nhap phải lớn hơn 0");
                    txtGiaNhap.requestFocus();
                    return false;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "gia nhap phải là số");
                txtGiaNhap.requestFocus();
                return false;
            }
        }
        if (txtGiaBan.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "gia ban không được để trống");
            txtGiaBan.requestFocus();
            return false;
        } else {
            try {
                if (Float.parseFloat(txtGiaBan.getText().trim()) < 0) {
                    JOptionPane.showMessageDialog(this, "gia ban phải lớn hơn 0");
                    txtGiaBan.requestFocus();
                    return false;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "gia ban phải là số");
                txtGiaBan.requestFocus();
                return false;
            }
        }
        if (txtSoLuongTon.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Số lượng không được để trống");
            txtSoLuongTon.requestFocus();
            return false;
        } else {
            try {
                if (Integer.parseInt(txtSoLuongTon.getText().trim()) < 0) {
                    JOptionPane.showMessageDialog(this, "Số lượng phải lớn hơn 0");
                    txtSoLuongTon.requestFocus();
                    return false;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Số lượng phải là số");
                txtSoLuongTon.requestFocus();
                return false;
            }
        }
        return true;
    }

    private SanPhamChiTiet getFrmSanPhamChiTiet() {
        SanPhamChiTiet spct = new SanPhamChiTiet();
        for (model.SanPham sp : service.getAll()) {
            if (cboTenSp.getSelectedItem().equals(sp.getTen())) {
                spct.setId(sp.getId());
                System.out.println(spct.getId());
            }
        }
        spct.setGiaBan(Double.parseDouble(txtGiaBan.getText().trim()));
        spct.setGiaNhap(Double.parseDouble(txtGiaNhap.getText().trim()));
        spct.setSoLuongTon(Integer.parseInt(txtSoLuongTon.getText().trim()));
        for (MauSac m : serviceMauSac.getAll()) {
            if (cboMauSac.getSelectedItem().equals(m.getTen())) {
                spct.setMauSac(m);
            }
        }
        for (KichCo s : serviceKichCo.getAll()) {
            if (cboSize.getSelectedItem().equals(s.getTen())) {
                spct.setKichCo(s);
            }
        }
        for (ChatLieu s : serviceChatLieu.getAll()) {
            if (cboChatLieu.getSelectedItem().equals(s.getTen())) {
                spct.setChatLieu(s);
            }
        }
        for (NSX s : serviceNSX.getAll()) {
            if (cboNSX.getSelectedItem().equals(s.getTen())) {
                spct.setNhaSx(s);
            }
        }
        for (ThuongHieu s : serviceThuongHieu.getAll()) {
            if (cboThuonghieu.getSelectedItem().equals(s.getTen())) {
                spct.setThuongHieu(s);
            }
        }

        return spct;
    }

    private void showDataSPCT(int row) {
//        cboTenSp.setSelectedItem(tblSanPhamChiTiet.getValueAt(row, 0));
        cboTenSp.setSelectedItem(tblSanPhamChiTiet.getValueAt(row, 1));
        cboNSX.setSelectedItem(tblSanPhamChiTiet.getValueAt(row, 2));
        cboMauSac.setSelectedItem(tblSanPhamChiTiet.getValueAt(row, 3));
        cboSize.setSelectedItem(tblSanPhamChiTiet.getValueAt(row, 4));
        cboChatLieu.setSelectedItem(tblSanPhamChiTiet.getValueAt(row, 5));
        cboThuonghieu.setSelectedItem(tblSanPhamChiTiet.getValueAt(row, 6));
        txtSoLuongTon.setText(tblSanPhamChiTiet.getValueAt(row, 7).toString());
        txtGiaNhap.setText(tblSanPhamChiTiet.getValueAt(row, 8).toString());
        txtGiaBan.setText(tblSanPhamChiTiet.getValueAt(row, 9).toString());
        txtMoTa.setText(tblSanPhamChiTiet.getValueAt(row, 10).toString());
    }

    private void clearFormCTSP() {
        txtGiaBan.setText("");
        txtGiaNhap.setText("");
        txtMoTa.setText("");
        txtSoLuongTon.setText("");
        cboChatLieu.setSelectedIndex(0);
        cboMauSac.setSelectedIndex(0);
        cboNSX.setSelectedIndex(0);
        cboSize.setSelectedIndex(0);
        cboTenSp.setSelectedIndex(0);
        cboThuonghieu.setSelectedIndex(0);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtTen = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtMoTa = new javax.swing.JTextArea();
        cboMauSac = new javax.swing.JComboBox<>();
        cboSize = new javax.swing.JComboBox<>();
        cboThuonghieu = new javax.swing.JComboBox<>();
        cboTenSp = new javax.swing.JComboBox<>();
        cboChatLieu = new javax.swing.JComboBox<>();
        cboNSX = new javax.swing.JComboBox<>();
        txtGiaNhap = new javax.swing.JTextField();
        txtGiaBan = new javax.swing.JTextField();
        txtSoLuongTon = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblSanPhamChiTiet = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();

        setBackground(new java.awt.Color(204, 204, 255));

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(236, 255, 255));

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Tên", "Ngay Tao", "Ngay Sua"
            }
        ));
        jScrollPane2.setViewportView(tblSanPham);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("tên sản phẩm");

        txtTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(125, 224, 237));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add.png"))); // NOI18N
        jButton1.setText("Add");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(125, 224, 237));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/floppy-disk.png"))); // NOI18N
        jButton3.setText("Sua");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(106, 106, 106)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(156, 156, 156)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("San Pham", jPanel1);

        jPanel4.setBackground(new java.awt.Color(236, 255, 255));
        jPanel4.setForeground(new java.awt.Color(51, 51, 51));
        jPanel4.setMaximumSize(new java.awt.Dimension(940, 580));
        jPanel4.setMinimumSize(new java.awt.Dimension(940, 580));
        jPanel4.setPreferredSize(new java.awt.Dimension(580, 580));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Tên Sản Phẩm");
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 82, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Thuong Hieu");
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 160, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("Mau Sac");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("Gia Nhap");
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 90, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("Chat Lieu");
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 20, -1, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("So Luong");
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 20, -1, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setText("Gia Ban");
        jPanel4.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 160, 70, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setText("Kich Co");
        jPanel4.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, -1, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setText("Nha San Xuat");
        jPanel4.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 90, -1, -1));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setText("Mo Ta");
        jPanel4.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 40, -1));

        jButton4.setBackground(new java.awt.Color(125, 224, 237));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add.png"))); // NOI18N
        jButton4.setText("Add");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 90, 130, 50));

        jButton5.setBackground(new java.awt.Color(125, 224, 237));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/floppy-disk.png"))); // NOI18N
        jButton5.setText("Update");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 160, 130, 50));

        jButton6.setBackground(new java.awt.Color(125, 224, 237));
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/refresh.png"))); // NOI18N
        jButton6.setText("Refesh");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 20, 130, 50));

        txtMoTa.setColumns(20);
        txtMoTa.setRows(5);
        jScrollPane1.setViewportView(txtMoTa);

        jPanel4.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 240, 560, 70));

        jPanel4.add(cboMauSac, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 160, 40));

        jPanel4.add(cboSize, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 160, 40));

        jPanel4.add(cboThuonghieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 180, 160, 40));

        jPanel4.add(cboTenSp, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 160, 40));

        jPanel4.add(cboChatLieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 40, 160, 40));

        jPanel4.add(cboNSX, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 110, 160, 40));
        jPanel4.add(txtGiaNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 110, 190, 40));

        txtGiaBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGiaBanActionPerformed(evt);
            }
        });
        jPanel4.add(txtGiaBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 180, 190, 40));
        jPanel4.add(txtSoLuongTon, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 40, 190, 40));

        tblSanPhamChiTiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Ten San Pham", "Ten NSX", "Ten Mau Sac", "Ten Kich Co", "Ten Chat Lieu", "Ten Thuong Hieu", "So Luong", "Gia Nhap", "Gia Ban", "Mo Ta"
            }
        ));
        tblSanPhamChiTiet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamChiTietMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblSanPhamChiTiet);

        jPanel4.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, 1060, 230));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add.png"))); // NOI18N
        jLabel14.setText(" ");
        jPanel4.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 120, 30, 20));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add.png"))); // NOI18N
        jLabel15.setText(" ");
        jPanel4.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 190, 30, 20));

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add.png"))); // NOI18N
        jLabel16.setText(" ");
        jPanel4.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 190, 30, 20));

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add.png"))); // NOI18N
        jLabel17.setText(" ");
        jPanel4.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 50, 30, 20));

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add.png"))); // NOI18N
        jLabel18.setText(" ");
        jPanel4.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 120, 30, 20));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 1059, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Chi Tiet SP", jPanel2);

        jPanel3.setBackground(new java.awt.Color(236, 255, 255));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1059, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 599, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Thuoc TInh SP", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        model.SanPham s = getFormSanPham();
        if (service.add(s) != 0) {
            JOptionPane.showMessageDialog(this, "them thanh cong");
            loadTableSp();
        } else {
            JOptionPane.showMessageDialog(this, "them that bai");
        }


    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        try {
            int row = -1;
            row = tblSanPham.getSelectedRow();
            String maSP = tblSanPham.getValueAt(row, 0).toString();
            int hoi = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn chỉnh sửa sản phẩm này không?");
            if (hoi != JOptionPane.YES_OPTION) {
                return;
            }
            model.SanPham s = this.getFormSanPham();
            if (service.update(s, Integer.valueOf(maSP)) != 0) {
                JOptionPane.showMessageDialog(this, "Chỉnh sửa sản phẩm thành công");
                loadTableSp();
            } else {
                JOptionPane.showMessageDialog(this, "Chỉnh sửa sản phẩm thất bại");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng dữ liệu cần sửa");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        int hoi = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn thêm sản phẩm này không?");
        if (hoi != JOptionPane.YES_OPTION) {
            return;
        }
        SanPhamChiTiet spct = this.getFrmSanPhamChiTiet();
        if (serviceCt.add(spct) != 0) {
            JOptionPane.showMessageDialog(this, "Thêm thành công");
            loadTableSpCt();
        } else {
            JOptionPane.showMessageDialog(this, "them sản phẩm thất bại");
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void tblSanPhamChiTietMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamChiTietMouseClicked
        // TODO add your handling code here:
        try {
            int row = -1;
            row = tblSanPhamChiTiet.getSelectedRow();
            this.showDataSPCT(row);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_tblSanPhamChiTietMouseClicked

    private void txtTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        try {
            int row = -1;
            row = tblSanPhamChiTiet.getSelectedRow();
            String id = tblSanPhamChiTiet.getValueAt(row, 0).toString();
            int hoi = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn chỉnh sửa sản phẩm này không?");
            if (hoi != JOptionPane.YES_OPTION) {
                return;
            }
            if (checkFrmSPCT()) {
                SanPhamChiTiet spct = this.getFrmSanPhamChiTiet();
                if (serviceCt.update(spct, id) != 0) {
                    JOptionPane.showMessageDialog(this, "Chỉnh sửa sản phẩm thành công");
                    loadTableSpCt();
                } else {
                    JOptionPane.showMessageDialog(this, "Chỉnh sửa sản phẩm thất bại");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng dữ liệu cần sửa");
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void txtGiaBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGiaBanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGiaBanActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        clearFormCTSP();
        loadForm();
        JOptionPane.showMessageDialog(this, "Refresh Thanh Cong !");
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTabbedPane1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cboChatLieu;
    private javax.swing.JComboBox<String> cboMauSac;
    private javax.swing.JComboBox<String> cboNSX;
    private javax.swing.JComboBox<String> cboSize;
    private javax.swing.JComboBox<String> cboTenSp;
    private javax.swing.JComboBox<String> cboThuonghieu;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTable tblSanPhamChiTiet;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtGiaNhap;
    private javax.swing.JTextArea txtMoTa;
    private javax.swing.JTextField txtSoLuongTon;
    private javax.swing.JTextField txtTen;
    // End of variables declaration//GEN-END:variables
}

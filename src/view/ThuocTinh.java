/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import model.ChatLieu;
import model.DanhMucSP;
import model.KichCo;
import model.MauSac;
import model.NSX;
import model.Objecttt;
import model.ThuongHieu;
import interfacee.ChatLieuServices;
import interfacee.DanhMucService;
import interfacee.MauSacServices;
import interfacee.NhaSanXuatServices;
import interfacee.ThuongHieuService;
import service.ChatLieuServicesImpl;
import service.DanhMucServiceImpl;
import service.MauSacServicesImpl;
import service.NSXServicesImpl;
import service.KichCoServiceImpl;
import service.ThuongHieuServicesImpl;
import interfacee.KichCoService;

/**
 *
 * @author trant
 */
public class ThuocTinh extends javax.swing.JPanel {

    ChatLieuServices chatLieuService = new ChatLieuServicesImpl();
    DanhMucService danhmucService = new DanhMucServiceImpl();
    MauSacServices mauSacServices = new MauSacServicesImpl();
    KichCoService kichCoService = new KichCoServiceImpl();
    ThuongHieuService thuongHieuService = new ThuongHieuServicesImpl();
    NhaSanXuatServices NSXService = new NSXServicesImpl();

    /**
     * Creates new form addProperties
     */
    public ThuocTinh() {

        initComponents();
        loadData("chatLieu");
    }

    private void loadData(String thuocTinh) {
        DefaultTableModel model = (DefaultTableModel) tbl_bang.getModel();
        model.setRowCount(0);
        if (thuocTinh.equalsIgnoreCase("chatLieu")) {
            for (ChatLieu cl : chatLieuService.getAll()) {
                Object dataRow[] = new Object[2];
                dataRow[0] = cl.getId();
                dataRow[1] = cl.getTen();

                model.addRow(dataRow);
            }
        }
        if (thuocTinh.equalsIgnoreCase("danhMuc")) {
            for (DanhMucSP danhMuc : danhmucService.getAll()) {
                model.addRow(new Object[]{
                    danhMuc.getId(),
                    danhMuc.getTen()
                });
            }
        }
        if (thuocTinh.equalsIgnoreCase("mauSac")) {
            for (MauSac mauSac : mauSacServices.getAll()) {
                model.addRow(new Object[]{
                    mauSac.getId(),
                    mauSac.getTen()
                });
            }
        }
        if (thuocTinh.equalsIgnoreCase("KichCo")) {
            for (KichCo kichCo : kichCoService.getAll()) {
                model.addRow(new Object[]{
                    kichCo.getId(),
                    kichCo.getTen()
                });
            }
        }
        if (thuocTinh.equalsIgnoreCase("ThuongHieu")) {
            for (ThuongHieu th : thuongHieuService.getAll()) {
                model.addRow(new Object[]{
                    th.getId(),
                    th.getTen()
                });
            }
        }
        if (thuocTinh.equalsIgnoreCase("NSX")) {
            for (NSX nsx : NSXService.getAll()) {
                model.addRow(new Object[]{
                    nsx.getId(),
                    nsx.getTen()
                });
            }
        }
    }

    private void addThuocTinh(String thuocTinh) {
        String ten = txtThem.getText();
        if (thuocTinh.equalsIgnoreCase("chatLieu")) {
            ChatLieu chatLieu = new ChatLieu();
            chatLieu.setTen(ten);
            boolean add = chatLieuService.add(chatLieu);
            if (add) {
                JOptionPane.showMessageDialog(this, "ADD thanh cong Thuoc TInh");
            } else {
                JOptionPane.showMessageDialog(this, "ADD that bai");
            }
        }
        if (thuocTinh.equalsIgnoreCase("danhMuc")) {
            DanhMucSP danhMucSP = new DanhMucSP();
            danhMucSP.setTen(ten);
            boolean add = danhmucService.add(danhMucSP);
            if (add) {
                JOptionPane.showMessageDialog(this, "ADD thanh cong Danh Muc");
            } else {
                JOptionPane.showMessageDialog(this, "ADD that bai");
            }
        }
        if (thuocTinh.equalsIgnoreCase("mauSac")) {
            MauSac mauSac = new MauSac();
            mauSac.setTen(ten);
            boolean add = mauSacServices.add(mauSac);
            if (add) {
                JOptionPane.showMessageDialog(this, "ADD thanh cong Mau Sac");
            } else {
                JOptionPane.showMessageDialog(this, "ADD that bai");
            }
        }
        if (thuocTinh.equalsIgnoreCase("KichCo")) {
            KichCo kichCo = new KichCo();
            kichCo.setTen(ten);
            boolean add = kichCoService.add(kichCo);
            if (add) {
                JOptionPane.showMessageDialog(this, "ADD thanh cong kich co");
            } else {
                JOptionPane.showMessageDialog(this, "ADD that bai");
            }
        }
        if (thuocTinh.equalsIgnoreCase("ThuongHieu")) {
            ThuongHieu thuonghieu = new ThuongHieu();
            thuonghieu.setTen(ten);
            boolean add = thuongHieuService.add(thuonghieu);
            if (add) {
                JOptionPane.showMessageDialog(this, "ADD thanh cong ThuongHieu");
            } else {
                JOptionPane.showMessageDialog(this, "ADD that bai");
            }
        }
        if (thuocTinh.equalsIgnoreCase("NSX")) {
            NSX nsx = new NSX();
            nsx.setTen(ten);
            boolean add = NSXService.add(nsx);
            if (add) {
                JOptionPane.showMessageDialog(this, "ADD thanh cong NSX");
            } else {
                JOptionPane.showMessageDialog(this, "ADD that bai");
            }
        }
        txtThem.setText("");
    }

    private void DeleteThuocTinh(String thuocTinhDelete) {
        int idSelecting = -1;
        try {
              idSelecting = Integer.parseInt(tbl_bang.getValueAt(tbl_bang.getSelectedRow(), 0) + "");
        } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, "Chua Chon dong can Xoa!");
        }
        if (idSelecting != -1) {
            if (thuocTinhDelete.equalsIgnoreCase("danhMuc")) {
                DanhMucSP danhMucSp = new DanhMucSP();
                danhMucSp.setId(idSelecting);
                if (danhmucService.delete(danhMucSp)) {
                    JOptionPane.showMessageDialog(this, "XOa Danh Muc Thanh Cong");
                } else {
                    JOptionPane.showMessageDialog(this, "XOa Danh Muc That bai");

                }
            }
            if (thuocTinhDelete.equalsIgnoreCase("ChatLieu")) {
                ChatLieu cl = new ChatLieu();
                cl.setId(idSelecting);
                if (chatLieuService.delete(cl)) {
                    JOptionPane.showMessageDialog(this, "XOa Chat Lieu Thanh Cong");
                } else {
                    JOptionPane.showMessageDialog(this, "XOa chat lieu That bai");

                }
            }
            if (thuocTinhDelete.equalsIgnoreCase("MauSac")) {
                MauSac cl = new MauSac();
                cl.setId(idSelecting);
                if (mauSacServices.delete(cl)) {
                    JOptionPane.showMessageDialog(this, "XOa Mau Sac Thanh Cong");
                } else {
                    JOptionPane.showMessageDialog(this, "XOa MauSac That bai");

                }
            }
            if (thuocTinhDelete.equalsIgnoreCase("NSX")) {
                NSX cl = new NSX();
                cl.setId(idSelecting);
                if (NSXService.delete(cl)) {
                    JOptionPane.showMessageDialog(this, "XOa NSX Thanh Cong");
                } else {
                    JOptionPane.showMessageDialog(this, "XOa NSX That bai");

                }
            }
            if (thuocTinhDelete.equalsIgnoreCase("KichCo")) {
                KichCo cl = new KichCo();
                cl.setId(idSelecting);
                if (kichCoService.delete(cl)) {
                    JOptionPane.showMessageDialog(this, "XOa Kich Co Thanh Cong");
                } else {
                    JOptionPane.showMessageDialog(this, "XOa Kich Co That bai");
                }
            }
            if (thuocTinhDelete.equalsIgnoreCase("ThuongHieu")) {
                ThuongHieu cl = new ThuongHieu();
                cl.setId(idSelecting);
                if (thuongHieuService.delete(cl)) {
                    JOptionPane.showMessageDialog(this, "XOa Kich Co Thanh Cong");
                } else {
                    JOptionPane.showMessageDialog(this, "XOa Kich Co That bai");
                }
            }
            txtThem.setText("");
        } else {
            JOptionPane.showMessageDialog(this, " Chon lai dong can xoa di!");
        }
    }

    private void updateThuocTinh(String thuocTinhUpdate) {
        int idSelecting = -1;
        try {
              idSelecting = Integer.parseInt(tbl_bang.getValueAt(tbl_bang.getSelectedRow(), 0) + "");
        } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, "Chua Chon dong can Update!");
        }
        System.out.println(idSelecting);
        if (idSelecting >=0) {
            if (thuocTinhUpdate.equalsIgnoreCase("chatLieu")) {
                ChatLieu chatlieu = new ChatLieu();
                chatlieu.setTen(txtThem.getText());
                if (chatLieuService.update(chatlieu, idSelecting)) {
                    JOptionPane.showMessageDialog(this, "Update Chat Lieu Thanh Cong");

                } else {
                    JOptionPane.showMessageDialog(this, "Update Chat Lieu Thanh Cong");
                }
            }
            if (thuocTinhUpdate.equalsIgnoreCase("DanhMuc")) {
                DanhMucSP danhMuc = new DanhMucSP();
                danhMuc.setTen(txtThem.getText());
                if (danhmucService.update(danhMuc, idSelecting)) {
                    JOptionPane.showMessageDialog(this, "Update Danh Muc Thanh Cong");
                } else {
                    JOptionPane.showMessageDialog(this, "Update Danh Muc Thanh Cong");
                }
            }
            if (thuocTinhUpdate.equalsIgnoreCase("KichCo")) {
                KichCo kichCo = new KichCo();
                kichCo.setTen(txtThem.getText());
                if (kichCoService.update(kichCo, idSelecting)) {
                    JOptionPane.showMessageDialog(this, "Update Kich Co Thanh Cong");
                } else {
                    JOptionPane.showMessageDialog(this, "Update Kich Co Thanh Cong");
                }
            }
            if (thuocTinhUpdate.equalsIgnoreCase("MauSac")) {
                MauSac mausac = new MauSac();
                mausac.setTen(txtThem.getText());
                if (mauSacServices.update(mausac, idSelecting)) {
                    JOptionPane.showMessageDialog(this, "Update mau sac Thanh Cong");
                } else {
                    JOptionPane.showMessageDialog(this, "Update mau sac Thanh Cong");
                }
            }
            if (thuocTinhUpdate.equalsIgnoreCase("NSX")) {
                NSX nsx = new NSX();
                nsx.setTen(txtThem.getText());
                if (NSXService.update(nsx, idSelecting)) {
                    JOptionPane.showMessageDialog(this, "Update nha san xuat Thanh Cong");
                } else {
                    JOptionPane.showMessageDialog(this, "Update nha san xuat Thanh Cong");
                }
            }
            if (thuocTinhUpdate.equalsIgnoreCase("ThuongHieu")) {
                ThuongHieu nsx = new ThuongHieu();
                nsx.setTen(txtThem.getText());
                if (thuongHieuService.update(nsx, idSelecting)) {
                    JOptionPane.showMessageDialog(this, "Update Thuong hieu Thanh Cong");
                } else {
                    JOptionPane.showMessageDialog(this, "Update Thuong hieu Thanh Cong");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Chon lai dong can update di !");

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

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel13 = new javax.swing.JPanel();
        rdoChatlieu12 = new javax.swing.JRadioButton();
        rdodanhmuc12 = new javax.swing.JRadioButton();
        rdoMausac12 = new javax.swing.JRadioButton();
        rdoSize12 = new javax.swing.JRadioButton();
        rdoThuonghieu12 = new javax.swing.JRadioButton();
        rdoNSX12 = new javax.swing.JRadioButton();
        btnAdd = new javax.swing.JButton();
        txtThem = new javax.swing.JTextField();
        btnXoa = new javax.swing.JButton();
        btnsua = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_bang = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(204, 255, 255));
        setMaximumSize(new java.awt.Dimension(930, 580));
        setMinimumSize(new java.awt.Dimension(930, 580));

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));

        rdoChatlieu12.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup2.add(rdoChatlieu12);
        rdoChatlieu12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rdoChatlieu12.setSelected(true);
        rdoChatlieu12.setText("Chất liệu");
        rdoChatlieu12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoChatlieu12rdoChatlieuActionPerformed(evt);
            }
        });

        rdodanhmuc12.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdodanhmuc12);
        rdodanhmuc12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rdodanhmuc12.setText("Danh mục");
        rdodanhmuc12.setEnabled(false);
        rdodanhmuc12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdodanhmuc12rdodanhmucActionPerformed(evt);
            }
        });

        rdoMausac12.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup2.add(rdoMausac12);
        rdoMausac12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rdoMausac12.setText("Màu sắc");
        rdoMausac12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoMausac12rdoMausacActionPerformed(evt);
            }
        });

        rdoSize12.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup2.add(rdoSize12);
        rdoSize12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rdoSize12.setText("Size");
        rdoSize12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoSize12rdoSizeActionPerformed(evt);
            }
        });

        rdoThuonghieu12.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup2.add(rdoThuonghieu12);
        rdoThuonghieu12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rdoThuonghieu12.setText("Thương hiệu");
        rdoThuonghieu12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoThuonghieu12rdoThuonghieuActionPerformed(evt);
            }
        });

        rdoNSX12.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup2.add(rdoNSX12);
        rdoNSX12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rdoNSX12.setText("Nhà sản xuất");
        rdoNSX12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoNSX12rdoNSXActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rdoChatlieu12, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdoSize12, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(94, 94, 94)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(rdoThuonghieu12, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rdoNSX12, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(rdodanhmuc12, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(102, 102, 102)
                        .addComponent(rdoMausac12, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(14, 14, 14))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoChatlieu12, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdodanhmuc12, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdoMausac12, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoSize12, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdoThuonghieu12, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdoNSX12, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40))
        );

        btnAdd.setBackground(new java.awt.Color(125, 224, 237));
        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(125, 224, 237));
        btnXoa.setText("Disable");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnsua.setBackground(new java.awt.Color(125, 224, 237));
        btnsua.setText("Update");
        btnsua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsuaActionPerformed(evt);
            }
        });

        tbl_bang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ID", "Tên Thuộc Tính"
            }
        ));
        tbl_bang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_bangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_bang);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Tên thuộc tính");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtThem, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnsua, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(76, 76, 76)
                                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 122, Short.MAX_VALUE)))
                .addGap(28, 28, 28))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtThem, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnsua, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void rdoChatlieu12rdoChatlieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoChatlieu12rdoChatlieuActionPerformed
        // TODO add your handling code here:
        loadData("chatLieu");
    }//GEN-LAST:event_rdoChatlieu12rdoChatlieuActionPerformed

    private void rdodanhmuc12rdodanhmucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdodanhmuc12rdodanhmucActionPerformed
        // TODO add your handling code here:
        loadData("danhMuc");
    }//GEN-LAST:event_rdodanhmuc12rdodanhmucActionPerformed

    private void rdoMausac12rdoMausacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoMausac12rdoMausacActionPerformed
        loadData("mauSac");
    }//GEN-LAST:event_rdoMausac12rdoMausacActionPerformed

    private void rdoSize12rdoSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoSize12rdoSizeActionPerformed
        loadData("kichCo");
    }//GEN-LAST:event_rdoSize12rdoSizeActionPerformed

    private void rdoThuonghieu12rdoThuonghieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoThuonghieu12rdoThuonghieuActionPerformed
        loadData("ThuongHieu");
    }//GEN-LAST:event_rdoThuonghieu12rdoThuonghieuActionPerformed

    private void rdoNSX12rdoNSXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoNSX12rdoNSXActionPerformed
        loadData("NSX");
    }//GEN-LAST:event_rdoNSX12rdoNSXActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        String thuocTinh = "";
        if (rdoChatlieu12.isSelected()) {
            thuocTinh = "chatLieu";
        } else if (rdodanhmuc12.isSelected()) {
            thuocTinh = "danhMuc";
        } else if (rdoMausac12.isSelected()) {
            thuocTinh = "mauSac";
        } else if (rdoSize12.isSelected()) {
            thuocTinh = "kichCo";
        } else if (rdoThuonghieu12.isSelected()) {
            thuocTinh = "thuongHieu";
        } else if (rdoNSX12.isSelected()) {
            thuocTinh = "NSX";
        }
        addThuocTinh(thuocTinh);
        loadData(thuocTinh);

    }//GEN-LAST:event_btnAddActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
//        String thuocTinh = "";
//        if (rdoChatlieu12.isSelected()) {
//            thuocTinh = "chatLieu";
//        } else if (rdodanhmuc12.isSelected()) {
//            thuocTinh = "danhMuc";
//        } else if (rdoMausac12.isSelected()) {
//            thuocTinh = "mauSac";
//        } else if (rdoSize12.isSelected()) {
//            thuocTinh = "kichCo";
//        } else if (rdoThuonghieu12.isSelected()) {
//            thuocTinh = "thuongHieu";
//        } else if (rdoNSX12.isSelected()) {
//            thuocTinh = "NSX";
//        }
//        DeleteThuocTinh(thuocTinh);
//        loadData(thuocTinh);
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnsuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsuaActionPerformed
        String thuocTinh = "";
        if (rdoChatlieu12.isSelected()) {
            thuocTinh = "chatLieu";
        } else if (rdodanhmuc12.isSelected()) {
            thuocTinh = "danhMuc";
        } else if (rdoMausac12.isSelected()) {
            thuocTinh = "mauSac";
        } else if (rdoSize12.isSelected()) {
            thuocTinh = "kichCo";
        } else if (rdoThuonghieu12.isSelected()) {
            thuocTinh = "thuongHieu";
        } else if (rdoNSX12.isSelected()) {
            thuocTinh = "NSX";
        }
        updateThuocTinh(thuocTinh);
        loadData(thuocTinh);
    }//GEN-LAST:event_btnsuaActionPerformed

    private void tbl_bangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_bangMouseClicked
        txtThem.setText(tbl_bang.getValueAt(tbl_bang.getSelectedRow(), 1) + "");
    }//GEN-LAST:event_tbl_bangMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnsua;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rdoChatlieu12;
    private javax.swing.JRadioButton rdoMausac12;
    private javax.swing.JRadioButton rdoNSX12;
    private javax.swing.JRadioButton rdoSize12;
    private javax.swing.JRadioButton rdoThuonghieu12;
    private javax.swing.JRadioButton rdodanhmuc12;
    private javax.swing.JTable tbl_bang;
    private javax.swing.JTextField txtThem;
    // End of variables declaration//GEN-END:variables
private Objecttt getdatafrom() {
        Pattern p = Pattern.compile("^[0-9]+$");
        if (txtThem.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, " Bạn chưa nhập tên thuộc tính!");
            txtThem.requestFocus();
            return null;
        }

        if (txtThem.getText().length() > 30) {
            JOptionPane.showMessageDialog(this, "Tên thuộc tính sản phẩm không quá 30 kí tự!");
            txtThem.requestFocus();
            return null;
        }

        if (p.matcher(txtThem.getText()).find() == true) {
            JOptionPane.showMessageDialog(this, "Tên thuộc tính sản phẩm phải là chữ!");
            txtThem.requestFocus();
            return null;
        }
        return new Objecttt(0, txtThem.getText());

    }

    private void clearfrom() {
        txtThem.setText("");
    }
}

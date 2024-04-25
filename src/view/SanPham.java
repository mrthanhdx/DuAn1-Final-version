/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import interfacee.ChatLieuServices;
import interfacee.DanhMucService;
import interfacee.KichCoService;
import interfacee.MauSacServices;
import interfacee.NhaSanXuatServices;
import interfacee.ThuongHieuService;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.ChatLieu;
import model.Chucvu;
import model.DanhMucSP;
import model.KichCo;
import model.MauSac;
import model.NSX;
import model.SanPhamChiTiet;
import model.ThuongHieu;
import service.ChatLieuServicesImpl;
import service.DanhMucServiceImpl;
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
     ChatLieuServices chatLieuService = new ChatLieuServicesImpl();
    DanhMucService danhmucService = new DanhMucServiceImpl();
    MauSacServices mauSacServices = new MauSacServicesImpl();
    KichCoService kichCoService = new KichCoServiceImpl();
    ThuongHieuService thuongHieuService = new ThuongHieuServicesImpl();
    NhaSanXuatServices NSXService = new NSXServicesImpl();

    /**
     * Creates new form SanPham
     */
    public SanPham() {
        initComponents();
        loadTableSp();
        loadTableSpCt();
        loadForm();
        loadData("chatLieu");
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
        if (txtGiaNhap.getText().trim().isEmpty() || txtGiaBan.getText().trim().isEmpty() || txtSoLuongTon.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui long dien du truong");
            return false;
        }
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
        cboChatLieu.setSelectedItem(0);
        cboMauSac.setSelectedItem(0);
        cboNSX.setSelectedItem(0);
        cboSize.setSelectedItem(0);
        cboTenSp.setSelectedItem(0);
        cboThuonghieu.setSelectedItem(0);
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
    String ten = txtThem.getText().trim();
    if (ten.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Vui lòng không để trống tên thuộc tính.");
        return;
    }
    switch (thuocTinh.toLowerCase()) {
        case "chatlieu":
            ChatLieu chatLieu = new ChatLieu();
            chatLieu.setTen(ten);
            boolean addChatLieu = chatLieuService.add(chatLieu);
            if (addChatLieu) {
                JOptionPane.showMessageDialog(this, "Thêm thành công thuộc tính 'Chất liệu'.");
            } else {
                JOptionPane.showMessageDialog(this, "Thêm thất bại.");
            }
            break;
        case "danhmuc":
            DanhMucSP danhMucSP = new DanhMucSP();
            danhMucSP.setTen(ten);
            boolean addDanhMuc = danhmucService.add(danhMucSP);
            if (addDanhMuc) {
                JOptionPane.showMessageDialog(this, "Thêm thành công thuộc tính 'Danh mục'.");
            } else {
                JOptionPane.showMessageDialog(this, "Thêm thất bại.");
            }
            break;
        case "mausac":
            MauSac mauSac = new MauSac();
            mauSac.setTen(ten);
            boolean addMauSac = mauSacServices.add(mauSac);
            if (addMauSac) {
                JOptionPane.showMessageDialog(this, "Thêm thành công thuộc tính 'Màu sắc'.");
            } else {
                JOptionPane.showMessageDialog(this, "Thêm thất bại.");
            }
            break;
        case "kichco":
            KichCo kichCo = new KichCo();
            kichCo.setTen(ten);
            boolean addKichCo = kichCoService.add(kichCo);
            if (addKichCo) {
                JOptionPane.showMessageDialog(this, "Thêm thành công thuộc tính 'Kích cỡ'.");
            } else {
                JOptionPane.showMessageDialog(this, "Thêm thất bại.");
            }
            break;
        case "thuonghieu":
            ThuongHieu thuonghieu = new ThuongHieu();
            thuonghieu.setTen(ten);
            boolean addThuongHieu = thuongHieuService.add(thuonghieu);
            if (addThuongHieu) {
                JOptionPane.showMessageDialog(this, "Thêm thành công thuộc tính 'Thương hiệu'.");
            } else {
                JOptionPane.showMessageDialog(this, "Thêm thất bại.");
            }
            break;
        case "nsx":
            NSX nsx = new NSX();
            nsx.setTen(ten);
            boolean addNSX = NSXService.add(nsx);
            if (addNSX) {
                JOptionPane.showMessageDialog(this, "Thêm thành công thuộc tính 'NSX'.");
            } else {
                JOptionPane.showMessageDialog(this, "Thêm thất bại.");
            }
            break;
        default:
            JOptionPane.showMessageDialog(this, "Loại thuộc tính không hợp lệ.");
            break;
    }

    txtThem.setText("");
}



    private void updateThuocTinh(String thuocTinhUpdate) {
    int idSelecting = -1;
    try {
        idSelecting = Integer.parseInt(tbl_bang.getValueAt(tbl_bang.getSelectedRow(), 0) + "");
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Chưa chọn dòng cần cập nhật!");
        return;
    }

    if (idSelecting >= 0) {
        String ten = txtThem.getText().trim();
        if (ten.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng không để trống tên thuộc tính.");
            return;
        }

        switch (thuocTinhUpdate.toLowerCase()) {
            case "chatlieu":
                ChatLieu chatlieu = new ChatLieu();
                chatlieu.setTen(ten);
                if (chatLieuService.update(chatlieu, idSelecting)) {
                    JOptionPane.showMessageDialog(this, "Cập nhật chất liệu thành công.");
                } else {
                    JOptionPane.showMessageDialog(this, "Cập nhật chất liệu không thành công.");
                }
                break;
            case "danhmuc":
                DanhMucSP danhMuc = new DanhMucSP();
                danhMuc.setTen(ten);
                if (danhmucService.update(danhMuc, idSelecting)) {
                    JOptionPane.showMessageDialog(this, "Cập nhật danh mục thành công.");
                } else {
                    JOptionPane.showMessageDialog(this, "Cập nhật danh mục không thành công.");
                }
                break;
            case "kichco":
                KichCo kichCo = new KichCo();
                kichCo.setTen(ten);
                if (kichCoService.update(kichCo, idSelecting)) {
                    JOptionPane.showMessageDialog(this, "Cập nhật kích cỡ thành công.");
                } else {
                    JOptionPane.showMessageDialog(this, "Cập nhật kích cỡ không thành công.");
                }
                break;
            case "mausac":
                MauSac mausac = new MauSac();
                mausac.setTen(ten);
                if (mauSacServices.update(mausac, idSelecting)) {
                    JOptionPane.showMessageDialog(this, "Cập nhật màu sắc thành công.");
                } else {
                    JOptionPane.showMessageDialog(this, "Cập nhật màu sắc không thành công.");
                }
                break;
            case "nsx":
                NSX nsx = new NSX();
                nsx.setTen(ten);
                if (NSXService.update(nsx, idSelecting)) {
                    JOptionPane.showMessageDialog(this, "Cập nhật nhà sản xuất thành công.");
                } else {
                    JOptionPane.showMessageDialog(this, "Cập nhật nhà sản xuất không thành công.");
                }
                break;
            case "thuonghieu":
                ThuongHieu thuongHieu = new ThuongHieu();
                thuongHieu.setTen(ten);
                if (thuongHieuService.update(thuongHieu, idSelecting)) {
                    JOptionPane.showMessageDialog(this, "Cập nhật thương hiệu thành công.");
                } else {
                    JOptionPane.showMessageDialog(this, "Cập nhật thương hiệu không thành công.");
                }
                break;
            default:
                JOptionPane.showMessageDialog(this, "Loại thuộc tính không hợp lệ.");
                break;
        }
    } else {
        JOptionPane.showMessageDialog(this, "Vui lòng chọn lại dòng cần cập nhật!");
    }
}
    private void showSP(int row){
        txtTen.setText(tblSanPham.getValueAt(row, 1).toString());
    }

    private boolean checkFrmSP() {
        if (txtTen.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "vui lòng điền tên sản phẩm");
            return false;
        } else {
            return true;
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
        jPanel3 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        rdoChatlieu12 = new javax.swing.JRadioButton();
        rdoMausac12 = new javax.swing.JRadioButton();
        rdoSize12 = new javax.swing.JRadioButton();
        rdoThuonghieu12 = new javax.swing.JRadioButton();
        rdoNSX12 = new javax.swing.JRadioButton();
        btnAdd = new javax.swing.JButton();
        txtThem = new javax.swing.JTextField();
        btnsua = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbl_bang = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(204, 255, 255));

        jTabbedPane1.setBackground(new java.awt.Color(204, 255, 255));
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
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        jTabbedPane1.addTab("Sản phẩm", jPanel1);

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
        jLabel3.setText("Thương hiệu");
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 160, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("Màu sắc");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("Giá nhập");
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 90, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("Chất liệu");
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 20, -1, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("Số lượng");
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 20, -1, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setText("Giá bán");
        jPanel4.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 160, 70, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setText("Kích cỡ");
        jPanel4.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, -1, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setText("Nhà sản xuất");
        jPanel4.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 90, -1, -1));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setText("Mô tả");
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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 1029, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Chi tiết SP", jPanel2);

        jPanel3.setBackground(new java.awt.Color(236, 255, 255));

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));

        rdoChatlieu12.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdoChatlieu12);
        rdoChatlieu12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rdoChatlieu12.setSelected(true);
        rdoChatlieu12.setText("Chất liệu");
        rdoChatlieu12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoChatlieu12rdoChatlieuActionPerformed(evt);
            }
        });

        rdoMausac12.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdoMausac12);
        rdoMausac12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rdoMausac12.setText("Màu sắc");
        rdoMausac12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoMausac12rdoMausacActionPerformed(evt);
            }
        });

        rdoSize12.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdoSize12);
        rdoSize12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rdoSize12.setText("Size");
        rdoSize12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoSize12rdoSizeActionPerformed(evt);
            }
        });

        rdoThuonghieu12.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdoThuonghieu12);
        rdoThuonghieu12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rdoThuonghieu12.setText("Thương hiệu");
        rdoThuonghieu12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoThuonghieu12rdoThuonghieuActionPerformed(evt);
            }
        });

        rdoNSX12.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdoNSX12);
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
                        .addGap(212, 212, 212)
                        .addComponent(rdoMausac12, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(14, 14, 14))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoChatlieu12, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        txtThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtThemActionPerformed(evt);
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
        jScrollPane4.setViewportView(tbl_bang);

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setText("Tên thuộc tính");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 798, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGap(27, 27, 27)
                                    .addComponent(txtThem, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGap(18, 18, 18)
                                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnsua, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(207, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtThem, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnsua, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57))
        );

        jTabbedPane1.addTab("Thuộc tính SP", jPanel3);

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

        if (checkFrmSP() == true) {
            model.SanPham s = getFormSanPham();
            if (service.add(s) != 0) {
                JOptionPane.showMessageDialog(this, "them thanh cong");
                loadTableSp();
            } else {
                JOptionPane.showMessageDialog(this, "them that bai");
            }
            
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
        if (checkFrmSPCT() == true) {
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

    private void rdoChatlieu12rdoChatlieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoChatlieu12rdoChatlieuActionPerformed
        // TODO add your handling code here:
        loadData("chatLieu");
    }//GEN-LAST:event_rdoChatlieu12rdoChatlieuActionPerformed

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
//        } else if (rdodanhmuc12.isSelected()) {
//            thuocTinh = "danhMuc";
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

    private void btnsuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsuaActionPerformed
        String thuocTinh = "";
        if (rdoChatlieu12.isSelected()) {
            thuocTinh = "chatLieu";
//        } else if (rdodanhmuc12.isSelected()) {
//            thuocTinh = "danhMuc";
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

    private void txtThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtThemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtThemActionPerformed

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        // TODO add your handling code here:
        try {
            int row = -1;
            row = tblSanPham.getSelectedRow();
            this.showSP(row);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_tblSanPhamMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnsua;
    private javax.swing.ButtonGroup buttonGroup1;
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
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JRadioButton rdoChatlieu12;
    private javax.swing.JRadioButton rdoMausac12;
    private javax.swing.JRadioButton rdoNSX12;
    private javax.swing.JRadioButton rdoSize12;
    private javax.swing.JRadioButton rdoThuonghieu12;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTable tblSanPhamChiTiet;
    private javax.swing.JTable tbl_bang;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtGiaNhap;
    private javax.swing.JTextArea txtMoTa;
    private javax.swing.JTextField txtSoLuongTon;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextField txtThem;
    // End of variables declaration//GEN-END:variables
}

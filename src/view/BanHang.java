/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import interfacee.HoaDonChiTietInterface;
import interfacee.ThuongHieuService;
import interfacee.hoaDonInterface;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.HoaDon;
import model.SanPhamChiTiet;
import service.HoaDonServiceImpl;
import service.KhachHangInterface;
import service.KhachHangServiceImpl;
import service.SanPhamChiTietService;
import model.KhachHang;
import model.HoaDonChiTiet;
import model.ThuongHieu;
import service.HoaDonChiTietImpl;
import service.ThuongHieuServicesImpl;

/**
 *
 * @author trant
 */
public class BanHang extends javax.swing.JPanel {

    private hoaDonInterface hoadonService = new HoaDonServiceImpl();
    private SanPhamChiTietService chiTietSpService = new SanPhamChiTietService();
    private KhachHangServiceImpl khachHangServiceImpl = new KhachHangServiceImpl();
    private HoaDonChiTietInterface hoaDonChiTietService = new HoaDonChiTietImpl();
    private ThuongHieuService thuongHieuService = new ThuongHieuServicesImpl();

    List<HoaDonChiTiet> listHDCT;

    /**
     * Creates new form sellProductView
     */
    public BanHang() {
        initComponents();
        loadHoaDon();
        reFreshListHDCT();
        loadTableSpCt();
        DefaultTableModel giohangModel = (DefaultTableModel) tbl_gioHang.getModel();
        giohangModel.setRowCount(0);
        loadCategoryThuongHieu();
    }

    private void loadHoaDon() {
        List<HoaDon> list = hoadonService.getAll();
        DefaultTableModel model = (DefaultTableModel) tbl_hoadon.getModel();
        model.setRowCount(0);
//        System.out.println(getTenKH(1));
//        System.out.println(getTenKH(2));
        for (HoaDon hoaDon : list) {

            model.addRow(new Object[]{
                hoaDon.getId(), hoaDon.getMa(), hoaDon.getIDKhachHang() == 0 ? "Trống" : getTenKH(hoaDon.getIDKhachHang()),
                hoaDon.getTongTien(), hoaDon.getTinhTrang() == 0 ? "Chưa Thanh Toán" : "Đã Thanh Toán", hoaDon.getNgayTao()
            });
        }
    }

    private void loadCategoryThuongHieu() {
        List<ThuongHieu> listThuongHieu = thuongHieuService.getAll();
        DefaultComboBoxModel model = (DefaultComboBoxModel) cbo_filterThuongHieu.getModel();
        if (!listThuongHieu.isEmpty()) {
            for (ThuongHieu thuongHieu : listThuongHieu) {
                model.addElement(thuongHieu.getTen());
            }
        }

    }

    public void loadTableSpCt() {
        DefaultTableModel model = (DefaultTableModel) tbl_sanPham.getModel();

        model.setRowCount(0);

        List<SanPhamChiTiet> list = chiTietSpService.getAll();

        int stt = 1;

        for (SanPhamChiTiet sp : list) {
            model.addRow(new Object[]{
                sp.getId(),
                sp.getTenSp().getTen(),
                sp.getChatLieu(),
                sp.getKichCo(),
                sp.getMauSac(),
                sp.getThuongHieu(),
                sp.getSoLuongTon(),
                sp.getGiaBan(),});
        }

    }

    private void taoHoaDon() {
        Random random = new Random();

        // Generate a random 6-digit integer
        int randomNumber = random.nextInt(899999) + 100000;
        String ma = "HD-" + randomNumber;
        HoaDon hd = new HoaDon();
        hd.setMa(ma);
        if (hoadonService.addHoaDon(hd)) {
            JOptionPane.showMessageDialog(this, "Tạo hóa đơn thành công");
        } else {
            JOptionPane.showMessageDialog(this, "Tạo hóa đơn thất bại");

        }
    }

    private String getTenKH(int idKH) {
        List<KhachHang> listKH = khachHangServiceImpl.getAll();
        String tenKH = "";
        for (KhachHang khachHang : listKH) {
            if (khachHang.getId() == idKH) {
                tenKH = khachHang.getTen();
                return tenKH;
            }
        }
        return null;
    }

    private int getIdKhFromIdHD(int idHD) {
        int idKH = 0;
        List<HoaDon> listHD = hoadonService.getAll();
        for (HoaDon hoaDon : listHD) {
            if (hoaDon.getId() == idHD) {
                idKH = hoaDon.getIDKhachHang();
            }
        }
        return idKH;
    }

    private String getSDTKH(int idKH) {
        List<KhachHang> listKH = khachHangServiceImpl.getAll();
        String SDT = "";
        for (KhachHang khachHang : listKH) {
            if (khachHang.getId() == idKH) {
                SDT = khachHang.getSdt();
            }
        }
        return SDT;
    }

    private void themSpVaoHD() {
        if (tbl_hoadon.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn hóa đơn ");
            System.out.println(tbl_hoadon.getSelectedRow());
            return;
        } else if (tbl_sanPham.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn sản phẩm ");
            System.out.println(tbl_hoadon.getSelectedRow());
            return;
        } else {
            int idHD = Integer.parseInt(tbl_hoadon.getValueAt(tbl_hoadon.getSelectedRow(), 0) + "");
            int idSP = Integer.parseInt(tbl_sanPham.getValueAt(tbl_sanPham.getSelectedRow(), 0) + "");
            int soLuong;
            try {
                soLuong = Integer.parseInt(JOptionPane.showInputDialog("Nhập số lượng"));
                if (soLuong < 1) {
                    JOptionPane.showMessageDialog(this, "So luong khong hop le");
                    return;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "So luong khong hop le");
                return;
            }
            double donGia = Double.parseDouble(tbl_sanPham.getValueAt(tbl_sanPham.getSelectedRow(), 7) + "");
            HoaDon hd = new HoaDon();
            hd.setId(idHD);
            SanPhamChiTiet spct = new SanPhamChiTiet();
            spct.setId(idSP + "");

            for (HoaDonChiTiet hoaDonChiTiet : listHDCT) {
                if (hoaDonChiTiet.getHaoDon().getId() == idHD && Integer.parseInt(hoaDonChiTiet.getSanPham().getId()) == idSP) {
                    JOptionPane.showMessageDialog(this, "Sản phẩm đã có trong giỏ hàng!");
                    return;
                }
            }
            if (hoaDonChiTietService.addHDCT(hd, spct, soLuong, donGia)) {
                JOptionPane.showMessageDialog(this, "Thêm sản phẩm vào giỏ thành công !");
            } else {
                JOptionPane.showMessageDialog(this, "Thêm Sản phẩm Thất Bại!");
            }
        }
    }

    private void reFreshListHDCT() {
        listHDCT = hoaDonChiTietService.getAllHDCT();

    }

    private SanPhamChiTiet getSPCTbyIDSP(String id) {
        List<SanPhamChiTiet> listSPCT = chiTietSpService.getAll();
        for (SanPhamChiTiet sanPhamChiTiet : listSPCT) {
            if (sanPhamChiTiet.getId().equalsIgnoreCase(id)) {
                SanPhamChiTiet spct = new SanPhamChiTiet();
                spct.setId(sanPhamChiTiet.getId());
                spct.setTenSp(sanPhamChiTiet.getTenSp());
                spct.setSoLuongTon(sanPhamChiTiet.getSoLuongTon());
                spct.setGiaBan(sanPhamChiTiet.getGiaBan());
                spct.setGiaNhap(sanPhamChiTiet.getGiaNhap());
                spct.setNhaSx(sanPhamChiTiet.getNhaSx());
                spct.setChatLieu(sanPhamChiTiet.getChatLieu());
                spct.setKhuyenMai(sanPhamChiTiet.getKhuyenMai());
                spct.setMauSac(sanPhamChiTiet.getMauSac());
                spct.setThuongHieu(sanPhamChiTiet.getThuongHieu());
                spct.setMoTa(sanPhamChiTiet.getMoTa());
                spct.setKichCo(sanPhamChiTiet.getKichCo());
                return spct;
            }

        }
        return null;
    }

    private void loadGioHangByIDHD(int idHD) {
        List<SanPhamChiTiet> listSPCTLoad = new ArrayList<>();
        List<Integer> listSoLuong = new ArrayList<>();
       
        for (HoaDonChiTiet hoaDonChiTiet : listHDCT) {
            if (hoaDonChiTiet.getHaoDon().getId() == idHD) {
                listSPCTLoad.add(getSPCTbyIDSP(hoaDonChiTiet.getSanPham().getId()));
                listSoLuong.add(hoaDonChiTiet.getSoluong());
            }
        }

        DefaultTableModel model = (DefaultTableModel) tbl_gioHang.getModel();
        model.setRowCount(0);

        for (int i = 0; i < listSPCTLoad.size(); i++) {
            SanPhamChiTiet sanPhamChiTiet = listSPCTLoad.get(i);
            int soLuong = listSoLuong.get(i); // Retrieve the corresponding quantity
            model.addRow(new Object[]{
                sanPhamChiTiet.getId(),
                sanPhamChiTiet.getTenSp().getTen(),
                sanPhamChiTiet.getChatLieu(),
                sanPhamChiTiet.getMauSac(),
                sanPhamChiTiet.getKichCo(),
                sanPhamChiTiet.getGiaBan(),
                soLuong, // Display the quantity in the table column
                sanPhamChiTiet.getGiaBan() * soLuong
            });
           
        }
        
        
        

    }

    private void SearchByName() {
        List<SanPhamChiTiet> list = chiTietSpService.getAll();
        List<SanPhamChiTiet> listAfterSearch = new ArrayList<>();
        String keySearch = txtSpSearch.getText();
        if (!list.isEmpty()) {
            for (SanPhamChiTiet sanPhamChiTiet : list) {
                if (sanPhamChiTiet.getTenSp().getTen().contains(keySearch)) {
                    listAfterSearch.add(sanPhamChiTiet);
                }

            }
        }
        DefaultTableModel model = (DefaultTableModel) tbl_sanPham.getModel();
        model.setRowCount(0);

        for (SanPhamChiTiet sp : listAfterSearch) {
            model.addRow(new Object[]{
                sp.getId(),
                sp.getTenSp().getTen(),
                sp.getChatLieu(),
                sp.getKichCo(),
                sp.getMauSac(),
                sp.getThuongHieu(),
                sp.getSoLuongTon(),
                sp.getGiaBan(),});
        }

    }

    private void filterByThuongHieu() {
        String thuongHieuSelecting = cbo_filterThuongHieu.getSelectedItem() + "";
        System.out.println(thuongHieuSelecting);
    }
    private void updateTongTien(int idHD){
         List<SanPhamChiTiet> listSPCTLoad = new ArrayList<>();
        List<Integer> listSoLuong = new ArrayList<>();
        Double tongTien = 0.0;
        for (HoaDonChiTiet hoaDonChiTiet : listHDCT) {
            if (hoaDonChiTiet.getHaoDon().getId() == idHD) {
                listSPCTLoad.add(getSPCTbyIDSP(hoaDonChiTiet.getSanPham().getId()));
                listSoLuong.add(hoaDonChiTiet.getSoluong());
            }
        }

      

        for (int i = 0; i < listSPCTLoad.size(); i++) {
            SanPhamChiTiet sanPhamChiTiet = listSPCTLoad.get(i);
            int soLuong = listSoLuong.get(i); // Retrieve the corresponding quantity
           
            tongTien+=sanPhamChiTiet.getGiaBan() *soLuong ;
        }
        hoadonService.updateTongTien(idHD, tongTien);
        loadHoaDon();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_gioHang = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_hoadon = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_sanPham = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        btnTaoHoaDon = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lbl_tenKH = new javax.swing.JLabel();
        lbl_sdtKH = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtSpSearch = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        cbo_filterThuongHieu = new javax.swing.JComboBox<>();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        btnThemSpVaoHD = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(204, 204, 255));
        setMaximumSize(new java.awt.Dimension(940, 580));
        setMinimumSize(new java.awt.Dimension(940, 580));
        setName(""); // NOI18N
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbl_gioHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "IDSP", "Tên SP", "Chất Liệu", "Màu Sắc", "Kích Cỡ", "Đơn Giá", "Số Lượng", "Thành Tiền"
            }
        ));
        jScrollPane1.setViewportView(tbl_gioHang);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 590, 147));

        jLabel1.setText("Hóa đơn");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 9, 100, -1));

        jLabel2.setText("Giỏ hàng");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 190, 60, -1));

        tbl_hoadon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "MaHD", "Khách Hàng", "Tổng tiền", "Tình trạng", "Ngày tạo"
            }
        ));
        tbl_hoadon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_hoadonMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_hoadon);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 31, 670, 147));

        tbl_sanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Tên SP", "Chất liệu", "Kích Cỡ", "Màu Sắc", "Thương Hiệu", "Số Lượng ", "Giá Bán"
            }
        ));
        tbl_sanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_sanPhamMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tbl_sanPhamMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbl_sanPhamMousePressed(evt);
            }
        });
        jScrollPane3.setViewportView(tbl_sanPham);

        add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 420, 670, 147));

        jPanel1.setBackground(new java.awt.Color(153, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setBackground(new java.awt.Color(0, 153, 51));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Khách hàng");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 10, -1, 34));

        jButton2.setBackground(new java.awt.Color(0, 153, 51));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Áp dụng");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 330, 80, 32));

        jButton3.setBackground(new java.awt.Color(0, 153, 51));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Làm mới");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 460, 90, 35));

        btnTaoHoaDon.setBackground(new java.awt.Color(0, 153, 51));
        btnTaoHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTaoHoaDon.setForeground(new java.awt.Color(255, 255, 255));
        btnTaoHoaDon.setText("Tạo hóa đơn");
        btnTaoHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoHoaDonActionPerformed(evt);
            }
        });
        jPanel1.add(btnTaoHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 460, -1, 35));

        jButton5.setBackground(new java.awt.Color(0, 153, 51));
        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Thanh toán");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 510, 160, 40));

        jLabel3.setText("SĐT");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 40, -1));

        jLabel4.setText("Tên khách hàng");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        jLabel5.setText("Voucher Price:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, -1, 20));

        jLabel6.setText("Tổng tiền");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, -1, -1));

        jLabel8.setText("...");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 410, 130, -1));

        lbl_tenKH.setText("...");
        jPanel1.add(lbl_tenKH, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, 170, -1));

        lbl_sdtKH.setText("...");
        jPanel1.add(lbl_sdtKH, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, 170, -1));

        jLabel11.setText("...");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 160, 130, -1));

        jLabel12.setText("Giá giảm");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, -1, 20));

        jLabel13.setText("...");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 190, 130, -1));

        jLabel14.setText("Final Price");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, -1, 10));

        jLabel15.setText("...");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 230, 130, -1));
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 330, 160, 30));

        jLabel16.setText("Voucher");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, -1, -1));

        jLabel17.setText("Tiền phải trả");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, -1, -1));

        jLabel18.setText("...");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 270, 130, -1));

        jLabel19.setText("Tiền trả lại khách");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, -1, -1));

        jLabel20.setText("...");
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 370, 130, 20));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 0, 270, 570));

        jLabel7.setText("Sản phẩm");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 395, -1, -1));

        txtSpSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSpSearchActionPerformed(evt);
            }
        });
        add(txtSpSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 382, 150, 30));

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search_24px.png"))); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 380, 40, 30));

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(102, 102, 102));
        jLabel21.setText("Lọc Thương Hiệu");
        add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 360, -1, -1));

        cbo_filterThuongHieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All" }));
        cbo_filterThuongHieu.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbo_filterThuongHieuItemStateChanged(evt);
            }
        });
        cbo_filterThuongHieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbo_filterThuongHieuMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cbo_filterThuongHieuMouseEntered(evt);
            }
        });
        cbo_filterThuongHieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_filterThuongHieuActionPerformed(evt);
            }
        });
        cbo_filterThuongHieu.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                cbo_filterThuongHieuPropertyChange(evt);
            }
        });
        add(cbo_filterThuongHieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 380, 130, 30));

        jButton7.setBackground(new java.awt.Color(0, 204, 0));
        jButton7.setText("Xóa SP");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 230, 70, 30));

        jButton8.setBackground(new java.awt.Color(0, 204, 0));
        jButton8.setText("Xóa Hết SP");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 290, 90, 30));

        btnThemSpVaoHD.setBackground(new java.awt.Color(0, 204, 0));
        btnThemSpVaoHD.setText("Thêm Sản Phẩm");
        btnThemSpVaoHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSpVaoHDActionPerformed(evt);
            }
        });
        add(btnThemSpVaoHD, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 370, -1, 40));

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(102, 102, 102));
        jLabel22.setText("Tìm theo tên");
        add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 360, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void txtSpSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSpSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSpSearchActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        SearchByName();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnTaoHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoHoaDonActionPerformed
        taoHoaDon();
        loadHoaDon();
    }//GEN-LAST:event_btnTaoHoaDonActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void tbl_sanPhamMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_sanPhamMousePressed
//       JOptionPane.showMessageDialog(this, "hien thi");
    }//GEN-LAST:event_tbl_sanPhamMousePressed

    private void tbl_sanPhamMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_sanPhamMouseEntered
        // TODO add your handling code here:

    }//GEN-LAST:event_tbl_sanPhamMouseEntered

    private void tbl_sanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_sanPhamMouseClicked
//
//        System.out.println(evt.getClickCount());
//        if (evt.getClickCount() == 2) {
//            JOptionPane.showMessageDialog(this, "click twice");
//        }

        // TODO add your handling code here:
    }//GEN-LAST:event_tbl_sanPhamMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int rowHD = tbl_hoadon.getSelectedRow();
        if (rowHD < 0) {
            JOptionPane.showMessageDialog(this, "chọn hoá đơn bạn muốn thêm khách hàng vào");
            return;
        }

// Define a callback to loadHoaDon after the dialog is closed
        Runnable afterDialogClosed = new Runnable() {
            @Override
            public void run() {
                loadHoaDon();
            }
        };

// Pass the callback to the dialog
        new khachhangdialog(Integer.parseInt(tbl_hoadon.getValueAt(rowHD, 0).toString()), afterDialogClosed).setVisible(true);


    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here: 
        loadHoaDon();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void tbl_hoadonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_hoadonMouseClicked
        try {
            Integer.parseInt(tbl_hoadon.getValueAt(tbl_hoadon.getSelectedRow(), 0) + "");
        } catch (Exception e) {
            return;
        }
        int idHD = Integer.parseInt(tbl_hoadon.getValueAt(tbl_hoadon.getSelectedRow(), 0) + "");
        lbl_sdtKH.setText(getSDTKH(getIdKhFromIdHD(idHD)));
        lbl_tenKH.setText(tbl_hoadon.getValueAt(tbl_hoadon.getSelectedRow(), 2) + "");
        loadGioHangByIDHD(idHD);
        
    }//GEN-LAST:event_tbl_hoadonMouseClicked

    private void btnThemSpVaoHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSpVaoHDActionPerformed
        themSpVaoHD();
        reFreshListHDCT();
        try {
            Integer.parseInt(tbl_hoadon.getValueAt(tbl_hoadon.getSelectedRow(), 0) + "");
        } catch (Exception e) {
            return;
        }
        int idHD = Integer.parseInt(tbl_hoadon.getValueAt(tbl_hoadon.getSelectedRow(), 0) + "");
        loadGioHangByIDHD(idHD);
        updateTongTien(idHD);
        

    }//GEN-LAST:event_btnThemSpVaoHDActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        try {
            Integer.parseInt(tbl_hoadon.getValueAt(tbl_hoadon.getSelectedRow(), 0) + "");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Chưa chọn sản phẩm trong giỏ cần xóa");
            return;
        }
        try {
            Integer.parseInt(tbl_gioHang.getValueAt(tbl_gioHang.getSelectedRow(), 0) + "");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Chưa chọn sản phẩm trong giỏ cần xóa");
            return;

        }
        int idHD = Integer.parseInt(tbl_hoadon.getValueAt(tbl_hoadon.getSelectedRow(), 0) + "");
        int idSP = Integer.parseInt(tbl_gioHang.getValueAt(tbl_gioHang.getSelectedRow(), 0) + "");
        if (hoaDonChiTietService.deleteHDCT(idHD, idSP)) {
            JOptionPane.showMessageDialog(this, "Xoa Thanh Cong");
            reFreshListHDCT();
            loadGioHangByIDHD(idHD);
            updateTongTien(idHD);
        } else {
            JOptionPane.showMessageDialog(this, "Xoa That Bai");
        }


    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        try {
            Integer.parseInt(tbl_hoadon.getValueAt(tbl_hoadon.getSelectedRow(), 0) + "");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Chưa chọn sản phẩm trong giỏ cần xóa");
            return;
        }
        int idHD = Integer.parseInt(tbl_hoadon.getValueAt(tbl_hoadon.getSelectedRow(), 0) + "");
        if (hoaDonChiTietService.deleteAllHDCT(idHD)) {
            JOptionPane.showMessageDialog(this, "Xoa Thanh Cong");
            reFreshListHDCT();
            loadGioHangByIDHD(idHD);
            updateTongTien(idHD);
        } else {
            JOptionPane.showMessageDialog(this, "Xoa That Bai");
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void cbo_filterThuongHieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_filterThuongHieuActionPerformed
//        filterByThuongHieu();

        // TODO add your handling code here:
    }//GEN-LAST:event_cbo_filterThuongHieuActionPerformed

    private void cbo_filterThuongHieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbo_filterThuongHieuMouseClicked
        // TODO add your handling code here:
//        System.out.println("123");
    }//GEN-LAST:event_cbo_filterThuongHieuMouseClicked

    private void cbo_filterThuongHieuMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbo_filterThuongHieuMouseEntered
        // TODO add your handling code here:
//                System.out.println("123");

    }//GEN-LAST:event_cbo_filterThuongHieuMouseEntered

    private void cbo_filterThuongHieuPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_cbo_filterThuongHieuPropertyChange
//                        System.out.println("123");

    }//GEN-LAST:event_cbo_filterThuongHieuPropertyChange

    private void cbo_filterThuongHieuItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbo_filterThuongHieuItemStateChanged
        String tenThuongHieu = cbo_filterThuongHieu.getSelectedItem().toString();
        if (tenThuongHieu.equals("All")) {
            List<SanPhamChiTiet> sanPham = chiTietSpService.getAll();
            sanPham.clear();
            loadTableSpCt();
            return;
        }
       DefaultTableModel model = (DefaultTableModel) tbl_sanPham.getModel();
        model.setRowCount(0);
        List<SanPhamChiTiet> listSpCT = chiTietSpService.getAll();
        List<SanPhamChiTiet> listLoc = new ArrayList<>();
        for (SanPhamChiTiet sanPhamChiTiet : listSpCT) {
            if (sanPhamChiTiet.getThuongHieu().getTen().equalsIgnoreCase(tenThuongHieu)) {
                listLoc.add(sanPhamChiTiet);
            }
        }
        for (SanPhamChiTiet sp : listLoc) {
            model.addRow(new Object[]{
                sp.getId(),
                sp.getTenSp().getTen(),
                sp.getChatLieu(),
                sp.getKichCo(),
                sp.getMauSac(),
                sp.getThuongHieu(),
                sp.getSoLuongTon(),
                sp.getGiaBan(),});

        }

    }//GEN-LAST:event_cbo_filterThuongHieuItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTaoHoaDon;
    private javax.swing.JButton btnThemSpVaoHD;
    private javax.swing.JComboBox<String> cbo_filterThuongHieu;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lbl_sdtKH;
    private javax.swing.JLabel lbl_tenKH;
    private javax.swing.JTable tbl_gioHang;
    private javax.swing.JTable tbl_hoadon;
    private javax.swing.JTable tbl_sanPham;
    private javax.swing.JTextField txtSpSearch;
    // End of variables declaration//GEN-END:variables
}

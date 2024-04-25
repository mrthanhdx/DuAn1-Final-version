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
import model.KhuyenMai;
import model.SanPhamChiTiet;
import service.HoaDonServiceImpl;
import interfacee.KhachHangInterface;
import interfacee.KhuyenMaiService;
import service.KhachHangServiceImpl;
import service.SanPhamChiTietService;
import model.KhachHang;
import model.HoaDonChiTiet;
import model.ThuongHieu;
import service.HoaDonChiTietImpl;
import service.KhuyenMaiService_IMPL;
import service.ThuongHieuServicesImpl;
import java.text.DecimalFormat;

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
    private KhuyenMaiService khuyenMaiService = new KhuyenMaiService_IMPL();
    List<HoaDonChiTiet> listHDCT;
    private List<KhuyenMai> listKhuyenMai = khuyenMaiService.getAll();
    DecimalFormat decimalFormat = new DecimalFormat("#,###");

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

        for (int i = 0; i < list.size(); i++) {
            HoaDon hoaDon = list.get(i);
            if (hoaDon.getTinhTrang() == 0) {
                model.addRow(new Object[]{
                    hoaDon.getId(), hoaDon.getMa(), hoaDon.getIDKhachHang() == 0 ? "Trống" : getTenKH(hoaDon.getIDKhachHang()),
                    hoaDon.getTongTien(), hoaDon.getTinhTrang() == 0 ? "Chưa Thanh Toán" : "Đã Thanh Toán", hoaDon.getNgayTao()
                });
            }
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
        if (!listKhuyenMai.isEmpty()) {
            for (KhuyenMai khuyenMai : listKhuyenMai) {
                cbx_khuyenMai.addItem(khuyenMai.getTenKhuyenMai());
            }

        }
    }

    public void loadTableSpCt() {
        DefaultTableModel model = (DefaultTableModel) tbl_sanPham.getModel();

        model.setRowCount(0);

        List<SanPhamChiTiet> list = chiTietSpService.getAll();

        int stt = 1;

        for (SanPhamChiTiet sp : list) {
            if (sp.getSoLuongTon()!=0) {
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

    }

    private void taoHoaDon() {
        Random random = new Random();

       
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

    private boolean themSpVaoHD() {
        if (tbl_hoadon.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn hóa đơn ");
            System.out.println(tbl_hoadon.getSelectedRow());
            return false;
        } else if (tbl_sanPham.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn sản phẩm ");
            System.out.println(tbl_hoadon.getSelectedRow());
            return false;
        } else {
            int idHD = Integer.parseInt(tbl_hoadon.getValueAt(tbl_hoadon.getSelectedRow(), 0) + "");
            int idSP = Integer.parseInt(tbl_sanPham.getValueAt(tbl_sanPham.getSelectedRow(), 0) + "");
            int soLuong;
            try {
                soLuong = Integer.parseInt(JOptionPane.showInputDialog("Nhập số lượng"));
                if (soLuong < 1) {
                    JOptionPane.showMessageDialog(this, "So luong khong hop le");
                    return false;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "So luong khong hop le");
                return false;
            }

            double donGia = Double.parseDouble(tbl_sanPham.getValueAt(tbl_sanPham.getSelectedRow(), 7) + "");
            HoaDon hd = new HoaDon();
            hd.setId(idHD);
            SanPhamChiTiet spct = new SanPhamChiTiet();
            spct.setId(idSP + "");
            int soLuongTon = Integer.parseInt(tbl_sanPham.getValueAt(tbl_sanPham.getSelectedRow(), 6) + "");
            int SoluongMoi = soLuongTon - soLuong;
            if (SoluongMoi < 0) {
                JOptionPane.showMessageDialog(this, "Sản phẩm trong kho không đủ");
                return false;
            }

            for (HoaDonChiTiet hoaDonChiTiet : listHDCT) {
                if (hoaDonChiTiet.getHaoDon().getId() == idHD && Integer.parseInt(hoaDonChiTiet.getSanPham().getId()) == idSP) {
                    int isBuyMore = JOptionPane.showConfirmDialog(this, "sản phẩm đã có trong giỏ hàng ! Tiếp tục mua thêm ?");
                    System.out.println(isBuyMore);
                    int soLuongDaCo = hoaDonChiTiet.getSoluong();
                    if (isBuyMore == 0) {
                        if (hoaDonChiTietService.updateSoLuongSPHoaDonCT(idHD, idSP, soLuong + soLuongDaCo)) {
                            JOptionPane.showMessageDialog(this, "Thêm sản phẩm vào giỏ thành công ! ");
                            hoaDonChiTietService.updateSoLuongSP(idSP, SoluongMoi);
                            loadTableSpCt();
                            return true;
                        } else {
                            JOptionPane.showMessageDialog(this, "Thêm sản phẩm vào giỏ thất bại ! ");
                            return false;
                        }
                    } else {
                        return false;
                    }

                }
            }
            if (hoaDonChiTietService.addHDCT(hd, spct, soLuong, donGia)) {
                JOptionPane.showMessageDialog(this, "Thêm sản phẩm vào giỏ thành công !");
                hoaDonChiTietService.updateSoLuongSP(idSP, SoluongMoi);
                loadTableSpCt();
                return true;
            } else {
                JOptionPane.showMessageDialog(this, "Thêm Sản phẩm Thất Bại!");
            }
        }
        return true;
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

    private void updateTongTien(int idHD) {
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

            tongTien += sanPhamChiTiet.getGiaBan() * soLuong;
        }
        hoadonService.updateTongTien(idHD, tongTien);
        loadHoaDon();
    }

    private void loadTienThua() {
        double TongTien;
        double tienKhachTra;
        try {
            TongTien = Double.parseDouble(lbTongTien.getText());
        } catch (Exception e) {
            return;
        }

        try {
            tienKhachTra = Double.parseDouble(txtTienKhachTra.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Tien Nhap Khong Hop Le");
            return;
        }
        Double tienThua = tienKhachTra - TongTien;

        if (tienThua > 0) {
            lblTienThua.setText(tienThua + "");
        }
    }

    private boolean thanhToan() {
        try {
            Integer.parseInt(tbl_hoadon.getValueAt(tbl_hoadon.getSelectedRow(), 0) + "");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn hóa đơn");
            return false;
        }
        try {
            String kh = (tbl_hoadon.getValueAt(tbl_hoadon.getSelectedRow(), 2) + "");
            if (kh.equalsIgnoreCase("Trống")) {
                JOptionPane.showMessageDialog(this, "Chưa chọn khách hàng thanh toán");
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn khách hàng");
            return false;
        }

        double tongTien;
        double tienPhaiTra;
        double tienGiam = 0;
        try {
            tongTien = Double.parseDouble(tbl_hoadon.getValueAt(tbl_hoadon.getSelectedRow(), 3).toString());
        } catch (Exception e) {
            return false;
        }

        double tienKhachTra;

        try {
            String inputKhuyenMai = cbx_khuyenMai.getSelectedItem() + "";
            for (KhuyenMai khuyenMai : listKhuyenMai) {
                if (khuyenMai.getTenKhuyenMai().equals(inputKhuyenMai)) {
                    if (khuyenMai.getHinhThucKM().equals("VND")) {
                        tienGiam = Double.parseDouble(khuyenMai.getGiaTriGiam());
                    } else if (khuyenMai.getHinhThucKM().equals("%")) {
                        tienGiam = tongTien * (Double.parseDouble(khuyenMai.getGiaTriGiam())) / 100;
                    }

                }
            }
        } catch (Exception e) {
            return false;
        }

        try {
            tienKhachTra = Double.parseDouble(txtTienKhachTra.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Tien Nhap Khong Hop Le");
            return false;
        }
        tienPhaiTra = tongTien - tienGiam;
        Double tienThua = tienKhachTra - tienPhaiTra;

        try {
            Double tongtien = Double.parseDouble(tbl_hoadon.getValueAt(tbl_hoadon.getSelectedRow(), 3) + "");
            if (tongtien == 0) {
                JOptionPane.showMessageDialog(this, "Hóa đơn thanh toán trống");
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn khách hàng");
            return false;
        }

        try {
            tienKhachTra = Double.parseDouble(txtTienKhachTra.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Tien Nhap Khong Hop Le");
            return false;
        }

        int idHD = Integer.parseInt(tbl_hoadon.getValueAt(tbl_hoadon.getSelectedRow(), 0) + "");
        if (tienThua >= 0) {
            lblTienThua.setText(tienThua + "");
            int isPaying = JOptionPane.showConfirmDialog(this, "Xác nhận thanh toán");
            if (isPaying == 0) {
                try {
                    hoadonService.updateThanhToan(idHD);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "thanh toán thất bại(lỗi sql)");
                    return false;
                }

                if (hoadonService.updateThanhToan(idHD)) {
                    JOptionPane.showMessageDialog(this, "Thanh Toán Thành Công");
                    updateSoLuongVoucher();
                } else {
                    JOptionPane.showMessageDialog(this, "Thanh Toán Thất bại (1 lí do gì đó)");
                    return false;

                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Chưa đưa đủ tiền cần thanh toán");
            return false;
        }
        return true;
    }

    private void updateSoLuongVoucher() {
        String voucher = cbx_khuyenMai.getSelectedItem() + "";
        listKhuyenMai = khuyenMaiService.getAll();
        for (KhuyenMai khuyenMai : listKhuyenMai) {
            if (khuyenMai.getTenKhuyenMai().equalsIgnoreCase(voucher)) {
                if (khuyenMai.getSoLuong() > 0) {
                    int soLuong = khuyenMai.getSoLuong() - 1;
                    KhuyenMai km = new KhuyenMai();
                    km.setSoLuong(soLuong);
                    km.setGiaTriGiam(khuyenMai.getGiaTriGiam());
                    km.setHinhThucKM(khuyenMai.getHinhThucKM());
                    km.setTenKhuyenMai(khuyenMai.getTenKhuyenMai());
                    khuyenMaiService.Update(km, khuyenMai.getId());
                } else {
                    return;
                }

            }
        }
    }

    private void loadLableThongTin() {
        int idHD = Integer.parseInt(tbl_hoadon.getValueAt(tbl_hoadon.getSelectedRow(), 0) + "");
        double tongTien = Double.parseDouble(tbl_hoadon.getValueAt(tbl_hoadon.getSelectedRow(), 3).toString());
        lbl_sdtKH.setText(getSDTKH(getIdKhFromIdHD(idHD)));
        lbl_tenKH.setText(tbl_hoadon.getValueAt(tbl_hoadon.getSelectedRow(), 2) + "");
        lbTongTien.setText(decimalFormat.format(tongTien));
        lb_idHD.setText(tbl_hoadon.getValueAt(tbl_hoadon.getSelectedRow(), 0) + "");
        lbl_tienPhaitra.setText(decimalFormat.format(tongTien));

    }

    private void applyKhuyenMai() {
        double giaTienGiam = 0;
        try {
            double tongTien = Double.parseDouble(tbl_hoadon.getValueAt(tbl_hoadon.getSelectedRow(), 3).toString());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Chưa chọn hóa đơn áp dụng voucher !");
            return;
        }
        boolean isAvaliable = true;
        double tongTien = Double.parseDouble(tbl_hoadon.getValueAt(tbl_hoadon.getSelectedRow(), 3).toString());
        String inputKhuyenMai = cbx_khuyenMai.getSelectedItem() + "";
        for (KhuyenMai khuyenMai : listKhuyenMai) {

            if (khuyenMai.getTenKhuyenMai().equals(inputKhuyenMai)) {

                if (khuyenMai.getSoLuong() <= 0) {
                    isAvaliable = false;
                } else {
                    if (khuyenMai.getHinhThucKM().equals("VND")) {
                        giaTienGiam = Double.parseDouble(khuyenMai.getGiaTriGiam());
                        lbl_giamVoucher.setText(decimalFormat.format(giaTienGiam));
                        lbl_tienPhaitra.setText(decimalFormat.format((tongTien - giaTienGiam)));
                        isAvaliable = true;
                        return;
                    } else if (khuyenMai.getHinhThucKM().equals("%")) {
                        giaTienGiam = tongTien * (Double.parseDouble(khuyenMai.getGiaTriGiam())) / 100;
                        lbl_giamVoucher.setText(decimalFormat.format(giaTienGiam));
                        lbl_tienPhaitra.setText(decimalFormat.format((tongTien - giaTienGiam)));
                        isAvaliable = true;
                        return;
                    }
                }

            } else {
                isAvaliable = false;
            }
        }
        if (!isAvaliable) {
            JOptionPane.showMessageDialog(this, "Khuyến mãi không chính xác hoặc đã hết!");
            return;
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
        jButton3 = new javax.swing.JButton();
        btnTaoHoaDon = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblTienThua = new javax.swing.JLabel();
        lbl_tenKH = new javax.swing.JLabel();
        lbl_sdtKH = new javax.swing.JLabel();
        lbTongTien = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lbl_giamVoucher = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lbl_tienPhaitra = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtTienKhachTra = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        lb_idHD = new javax.swing.JLabel();
        cbx_khuyenMai = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        txtSpSearch = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        cbo_filterThuongHieu = new javax.swing.JComboBox<>();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        btnThemSpVaoHD = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(204, 255, 255));
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
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbl_gioHang);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 580, 147));

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
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
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
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
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
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 0, -1, 34));

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
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 40, -1));

        jLabel4.setText("Tên khách hàng");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        jLabel5.setText("Giảm giá voucher");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, -1, 20));

        jLabel6.setText("Tổng tiền");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, -1));

        lblTienThua.setText("...");
        jPanel1.add(lblTienThua, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 410, 130, -1));

        lbl_tenKH.setText("...");
        jPanel1.add(lbl_tenKH, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, 160, -1));

        lbl_sdtKH.setText("...");
        jPanel1.add(lbl_sdtKH, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 110, 160, -1));

        lbTongTien.setText("...");
        jPanel1.add(lbTongTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 150, 130, -1));

        jLabel14.setText("Tiền phải trả:");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, -1, 20));

        lbl_giamVoucher.setText("...");
        jPanel1.add(lbl_giamVoucher, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 190, 130, -1));

        jLabel16.setText("Chọn Khuyến Mãi:");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, -1, -1));

        jLabel17.setText("Tiền Khách Đưa");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, -1, -1));

        lbl_tienPhaitra.setText("...");
        jPanel1.add(lbl_tienPhaitra, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 230, 130, 20));

        jLabel19.setText("Tiền trả lại khách");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, -1, -1));

        txtTienKhachTra.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                txtTienKhachTraInputMethodTextChanged(evt);
            }
        });
        txtTienKhachTra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTienKhachTraActionPerformed(evt);
            }
        });
        txtTienKhachTra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTienKhachTraKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTienKhachTraKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTienKhachTraKeyTyped(evt);
            }
        });
        jPanel1.add(txtTienKhachTra, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 340, 130, 30));

        jLabel8.setText("ID hóa đơn:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        lb_idHD.setText("...");
        jPanel1.add(lb_idHD, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, 90, 20));

        cbx_khuyenMai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chọn khuyến mãi" }));
        cbx_khuyenMai.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbx_khuyenMaiItemStateChanged(evt);
            }
        });
        cbx_khuyenMai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbx_khuyenMaiActionPerformed(evt);
            }
        });
        jPanel1.add(cbx_khuyenMai, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 170, 30));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 0, 260, 570));

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
        jButton8.setText("Sửa Số Lượng");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 290, 110, 30));

        btnThemSpVaoHD.setBackground(new java.awt.Color(0, 204, 0));
        btnThemSpVaoHD.setText("Thêm Sản Phẩm");
        btnThemSpVaoHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSpVaoHDActionPerformed(evt);
            }
        });
        add(btnThemSpVaoHD, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 370, 130, 40));

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

    private void btnTaoHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoHoaDonActionPerformed
        int newHD = JOptionPane.showConfirmDialog(this, "Tạo 1 hóa đơn mới ?");
        if (newHD == 0) {
            taoHoaDon();
            loadHoaDon();
        }
    }//GEN-LAST:event_btnTaoHoaDonActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if (!thanhToan()) {
            return;
        } else {
            
        loadHoaDon();
        lblTienThua.setText("");
        loadTableSpCt();
        lbl_giamVoucher.setText("");
        lbl_sdtKH.setText("");
        lbl_tenKH.setText("");
        lbTongTien.setText("");
        lbl_tienPhaitra.setText("");
        lb_idHD.setText("");
        cbx_khuyenMai.setSelectedIndex(0);
        txtTienKhachTra.setText("");
        DefaultTableModel giohangModel = (DefaultTableModel) tbl_gioHang.getModel();
        giohangModel.setRowCount(0);
        
        }

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
        String currentKhachHang = tbl_hoadon.getValueAt(rowHD, 2) + "";
        if (!currentKhachHang.equalsIgnoreCase("Trống")) {
           int isChooseAgain = JOptionPane.showConfirmDialog(this, "Thông tin khách hàng đã có trong hóa đơn, bạn có muốn chọn lại khách hàng ?");
            System.out.println(isChooseAgain);
            if (isChooseAgain!=0) {
                  return;
            }
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
        lblTienThua.setText("");
        loadTableSpCt();
        lbl_giamVoucher.setText("");
        lbl_sdtKH.setText("");
        lbl_tenKH.setText("");
        lbTongTien.setText("");
        lbl_tienPhaitra.setText("");
        lb_idHD.setText("");
        cbx_khuyenMai.setSelectedIndex(0);

    }//GEN-LAST:event_jButton3ActionPerformed

    private void tbl_hoadonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_hoadonMouseClicked
        try {
            Integer.parseInt(tbl_hoadon.getValueAt(tbl_hoadon.getSelectedRow(), 0) + "");
        } catch (Exception e) {
            return;
        }
        int idHD = Integer.parseInt(tbl_hoadon.getValueAt(tbl_hoadon.getSelectedRow(), 0) + "");
        loadLableThongTin();
        loadGioHangByIDHD(idHD);
        try {
            Double.parseDouble(txtTienKhachTra.getText() + "");
        } catch (Exception e) {
            return;
        }
        loadTienThua();
    }//GEN-LAST:event_tbl_hoadonMouseClicked

    private void btnThemSpVaoHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSpVaoHDActionPerformed
        try {
            Integer.parseInt(tbl_hoadon.getValueAt(tbl_hoadon.getSelectedRow(), 0) + "");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "chưa chọn hóa đơn thêm sản phẩm");
            return;
        }
        try {
            int selectedRow = tbl_hoadon.getSelectedRow();
        } catch (Exception e) {
            return;
        }
        
          int selectedRow = tbl_hoadon.getSelectedRow();
         
        String currentKhachHang = tbl_hoadon.getValueAt(selectedRow, 2) + "";
        if (currentKhachHang.equalsIgnoreCase("Trống")) {
            JOptionPane.showMessageDialog(this, "Hóa đơn chưa có thông tin khách hàng");
            return;
        }
        
        
        if (!themSpVaoHD()) {
            return;
        }

        reFreshListHDCT();
       
      

        int idHD = Integer.parseInt(tbl_hoadon.getValueAt(tbl_hoadon.getSelectedRow(), 0) + "");
        loadGioHangByIDHD(idHD);
        updateTongTien(idHD);
        if (selectedRow != -1 && selectedRow < tbl_hoadon.getRowCount()) {
            tbl_hoadon.setRowSelectionInterval(selectedRow, selectedRow);
        }
        loadLableThongTin();


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
        int selectedRow = tbl_hoadon.getSelectedRow();
        int idHD = Integer.parseInt(tbl_hoadon.getValueAt(tbl_hoadon.getSelectedRow(), 0) + "");
        int idSP = Integer.parseInt(tbl_gioHang.getValueAt(tbl_gioHang.getSelectedRow(), 0) + "");
        int soLuong = Integer.parseInt(tbl_gioHang.getValueAt(tbl_gioHang.getSelectedRow(), 6) + "");
        if (hoaDonChiTietService.deleteHDCT(idHD, idSP)) {
            JOptionPane.showMessageDialog(this, "Xoa Thanh Cong");
            List<SanPhamChiTiet> listSPCT = chiTietSpService.getAll();
            for (SanPhamChiTiet sanPhamChiTiet : listSPCT) {
                if (Integer.parseInt(sanPhamChiTiet.getId()) == idSP) {
                    int soLuongMoi = sanPhamChiTiet.getSoLuongTon() +soLuong;
                    chiTietSpService.phucHoiSoLuong(idSP, soLuongMoi);
                    reFreshListHDCT();
                    loadTableSpCt();
                }
            }
            reFreshListHDCT();
            loadGioHangByIDHD(idHD);
            updateTongTien(idHD);
        } else {
            JOptionPane.showMessageDialog(this, "Xoa That Bai");
        }
        if (selectedRow != -1 && selectedRow < tbl_hoadon.getRowCount()) {
            tbl_hoadon.setRowSelectionInterval(selectedRow, selectedRow);
        }
        loadLableThongTin();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        int selectedRow = tbl_hoadon.getSelectedRow();
        try {
            Integer.parseInt(tbl_hoadon.getValueAt(tbl_hoadon.getSelectedRow(), 0) + "");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Chưa chọn sản phẩm trong giỏ cần sửa");
            return;
        }
        try {
            Integer.parseInt(tbl_gioHang.getValueAt(tbl_gioHang.getSelectedRow(), 0) + "");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Chưa chọn sản phẩm trong giỏ cần sửa");
            return;

        }
        
        int idSP = Integer.parseInt(tbl_gioHang.getValueAt(tbl_gioHang.getSelectedRow(), 0) + "");
        int soLuong = Integer.parseInt(tbl_gioHang.getValueAt(tbl_gioHang.getSelectedRow(), 6) + "");
        int idHD = Integer.parseInt(tbl_hoadon.getValueAt(selectedRow, 0) + "");
        int soLuongUpdate;
        try {
            soLuongUpdate = Integer.parseInt(JOptionPane.showInputDialog("nhập số lượng sửa"));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this , "số lượng nhập vào không hợp lệ");
            return;
        }
            int soLuongChenhLech = soLuong-soLuongUpdate;
            
            List<SanPhamChiTiet> listSPCT = chiTietSpService.getAll();
            for (SanPhamChiTiet sanPhamChiTiet : listSPCT) {
                if (Integer.parseInt(sanPhamChiTiet.getId()) == idSP) {
                    int soLuongMoi = sanPhamChiTiet.getSoLuongTon() +soLuongChenhLech;
                    hoaDonChiTietService.updateSoLuongSPHoaDonCT(idHD, idSP, soLuongUpdate);
                    chiTietSpService.phucHoiSoLuong(idSP, soLuongMoi);
                    reFreshListHDCT();
                    loadTableSpCt();
                }
            }
            
            JOptionPane.showMessageDialog(this, "Sửa Thành Công");
            reFreshListHDCT();
            loadGioHangByIDHD(idHD);
            updateTongTien(idHD);
        
        if (selectedRow != -1 && selectedRow < tbl_hoadon.getRowCount()) {
            tbl_hoadon.setRowSelectionInterval(selectedRow, selectedRow);
        }
        loadLableThongTin();
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

    private void txtTienKhachTraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTienKhachTraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTienKhachTraActionPerformed

    private void txtTienKhachTraInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txtTienKhachTraInputMethodTextChanged

        // TODO add your handling code here:
    }//GEN-LAST:event_txtTienKhachTraInputMethodTextChanged

    private void txtTienKhachTraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienKhachTraKeyPressed

    }//GEN-LAST:event_txtTienKhachTraKeyPressed

    private void txtTienKhachTraKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienKhachTraKeyTyped
        // TODO add your handling code here: 

    }//GEN-LAST:event_txtTienKhachTraKeyTyped

    private void txtTienKhachTraKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienKhachTraKeyReleased
        double tongTien;
        double tienPhaiTra;
        double tienGiam = 0;
        try {
            tongTien = Double.parseDouble(tbl_hoadon.getValueAt(tbl_hoadon.getSelectedRow(), 3).toString());
        } catch (Exception e) {
            return;
        }

        double tienKhachTra;

        try {
            String inputKhuyenMai = cbx_khuyenMai.getSelectedItem() + "";
            for (KhuyenMai khuyenMai : listKhuyenMai) {
                if (khuyenMai.getTenKhuyenMai().equals(inputKhuyenMai)) {
                    if (khuyenMai.getHinhThucKM().equals("VND")) {
                        tienGiam = Double.parseDouble(khuyenMai.getGiaTriGiam());
                    } else if (khuyenMai.getHinhThucKM().equals("%")) {
                        tienGiam = tongTien * (Double.parseDouble(khuyenMai.getGiaTriGiam())) / 100;
                    }

                }
            }
        } catch (Exception e) {
            return;
        }

        try {
            tienKhachTra = Double.parseDouble(txtTienKhachTra.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Tien Nhap Khong Hop Le");
            return;
        }
        tienPhaiTra = tongTien - tienGiam;
        Double tienThua = tienKhachTra - tienPhaiTra;

        if (tienThua >= 0) {
            lblTienThua.setText(tienThua + "");
        } else {
            lblTienThua.setText("Chưa Đủ");
        }
    }//GEN-LAST:event_txtTienKhachTraKeyReleased

    private void cbx_khuyenMaiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbx_khuyenMaiItemStateChanged
        if (!(cbx_khuyenMai.getSelectedItem() + "").equalsIgnoreCase("Chọn khuyến mãi")) {
            applyKhuyenMai();
        } else {
            lbl_giamVoucher.setText("");
            try {
                double tongTien = Double.parseDouble(tbl_hoadon.getValueAt(tbl_hoadon.getSelectedRow(), 3).toString());
            } catch (Exception e) {
                return;
            }
            double tongTien = Double.parseDouble(tbl_hoadon.getValueAt(tbl_hoadon.getSelectedRow(), 3).toString());
            lbl_tienPhaitra.setText(decimalFormat.format(tongTien) + "");
        }
    }//GEN-LAST:event_cbx_khuyenMaiItemStateChanged

    private void cbx_khuyenMaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbx_khuyenMaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbx_khuyenMaiActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTaoHoaDon;
    private javax.swing.JButton btnThemSpVaoHD;
    private javax.swing.JComboBox<String> cbo_filterThuongHieu;
    private javax.swing.JComboBox<String> cbx_khuyenMai;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JLabel lbTongTien;
    private javax.swing.JLabel lb_idHD;
    private javax.swing.JLabel lblTienThua;
    private javax.swing.JLabel lbl_giamVoucher;
    private javax.swing.JLabel lbl_sdtKH;
    private javax.swing.JLabel lbl_tenKH;
    private javax.swing.JLabel lbl_tienPhaitra;
    private javax.swing.JTable tbl_gioHang;
    private javax.swing.JTable tbl_hoadon;
    private javax.swing.JTable tbl_sanPham;
    private javax.swing.JTextField txtSpSearch;
    private javax.swing.JTextField txtTienKhachTra;
    // End of variables declaration//GEN-END:variables

}

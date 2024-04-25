/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfacee;

import java.util.List;
import model.ChatLieu;
import model.KichCo;
import model.MauSac;
import model.NSX;
import model.SanPham;
import model.SanPhamChiTiet;
import model.ThuongHieu;

/**
 *
 * @author H
 */
public interface SanPhamChiTietInterface {
    List<SanPhamChiTiet> getAll();
    public int add(SanPhamChiTiet spct);
    public int update(SanPhamChiTiet sp,String id);
    public boolean phucHoiSoLuong(int maSP,int soLuongMoi);
}

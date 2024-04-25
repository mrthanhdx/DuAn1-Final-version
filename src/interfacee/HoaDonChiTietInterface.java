/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfacee;

import java.util.List;
import model.HoaDon;
import model.HoaDonChiTiet;
import model.SanPhamChiTiet;

/**
 *
 * @author trant
 */
public interface HoaDonChiTietInterface {
    public List<HoaDonChiTiet> getAllHDCT();
    public boolean addHDCT(HoaDon hoaDon,SanPhamChiTiet sanPhamChiTiet,int soLuong, double DonGia);
    public boolean deleteHDCT(int IdHD,int IdSP);
    public boolean deleteAllHDCT(int IdHD);
    public boolean updateSoLuongSP(int id, int soLuong);
    public boolean updateSoLuongSPHoaDonCT(int IDHD, int IDSPCT, int soLuong);
}

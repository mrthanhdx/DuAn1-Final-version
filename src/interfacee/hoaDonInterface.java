/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfacee;

import java.util.List;
import model.HoaDon;

/**
 *
 * @author trant
 */
public interface hoaDonInterface {
    public List<HoaDon> getAll();
    public boolean addHoaDon(HoaDon hd);
    public boolean xoaHoaDon(HoaDon hd,int idHD);
    public boolean updateMaKHforHoaDon(int idKh,int idHD);
    public boolean updateTongTien(int idHD,double tongTien);
}

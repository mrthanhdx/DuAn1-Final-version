/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Luu Huynh
 */
public class ThongKe {
    private double doanhThu;
    private HoaDon HoaDon;
//    private ChiTietSP ChiTietSP;

    public ThongKe(double doanhThu, HoaDon HoaDon) {
        this.doanhThu = doanhThu;
        this.HoaDon = HoaDon;
//        this.ChiTietSP = ChiTietSP;
    }

    public double getDoanhThu() {
        return doanhThu;
    }

    public void setDoanhThu(double doanhThu) {
        this.doanhThu = doanhThu;
    }

    public HoaDon getHoaDon() {
        return HoaDon;
    }

    public void setHoaDon(HoaDon HoaDon) {
        this.HoaDon = HoaDon;
    }

//    public ChiTietSP getChiTietSP() {
//        return ChiTietSP;
//    }
//
//    public void setChiTietSP(ChiTietSP ChiTietSP) {
//        this.ChiTietSP = ChiTietSP;
    
    

    
}

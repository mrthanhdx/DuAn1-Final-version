/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class HoaDonChiTiet {
    private int Soluong;
    private Double DonGia;
    private Double DonKhiGiam;
    private HoaDon hoaDon;
    private SanPhamChiTiet sanPham;

    public HoaDonChiTiet() {
    }

    public HoaDonChiTiet(Integer idHoaDon, Integer idCTSP, int Soluong, Double DonGia, Double DonKhiGiam, HoaDon haoDon, SanPhamChiTiet sanPham) {
        this.Soluong = Soluong;
        this.DonGia = DonGia;
        this.DonKhiGiam = DonKhiGiam;
        this.hoaDon = haoDon;
        this.sanPham = sanPham;
    }

    public int getSoluong() {
        return Soluong;
    }

    public void setSoluong(int Soluong) {
        this.Soluong = Soluong;
    }

    public Double getDonGia() {
        return DonGia;
    }

    public void setDonGia(Double DonGia) {
        this.DonGia = DonGia;
    }

    public Double getDonKhiGiam() {
        return DonKhiGiam;
    }

    public void setDonKhiGiam(Double DonKhiGiam) {
        this.DonKhiGiam = DonKhiGiam;
    }

    public HoaDon getHaoDon() {
        return hoaDon;
    }

    public void setHaoDon(HoaDon haoDon) {
        this.hoaDon = haoDon;
    }

    public SanPhamChiTiet getSanPham() {
        return sanPham;
    }

    public void setSanPham(SanPhamChiTiet sanPham) {
        this.sanPham = sanPham;
    }

}
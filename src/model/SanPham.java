/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class SanPham {
   private int id;
    private String Ma;
    private String Ten;
    private int SoLuongTon;
    private Double GiaNhap;
    private Double GiaBan;
    private MauSac mauSac;
    private ChatLieu chatLieu;
    private KichCo kichCo;
    private KhuyenMai khuenMai;
    private String QrCode;

    public SanPham() {
    }

    public SanPham( int id,String Ma, String Ten, int SoLuongTon, Double GiaNhap, Double GiaBan, MauSac mauSac, ChatLieu chatLieu, KichCo kichCo, KhuyenMai khuenMai, String QrCode) {
        this.Ma = Ma;
        this.Ten = Ten;
        this.SoLuongTon = SoLuongTon;
        this.GiaNhap = GiaNhap;
        this.GiaBan = GiaBan;
        this.mauSac = mauSac;
        this.chatLieu = chatLieu;
        this.kichCo = kichCo;
        this.khuenMai = khuenMai;
         this.QrCode = QrCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQrCode() {
        return QrCode;
    }

    public void setQrCode(String QrCode) {
        this.QrCode = QrCode;
    }

  

  

    public String getMa() {
        return Ma;
    }

    public void setMa(String Ma) {
        this.Ma = Ma;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String Ten) {
        this.Ten = Ten;
    }

    public int getSoLuongTon() {
        return SoLuongTon;
    }

    public void setSoLuongTon(int SoLuongTon) {
        this.SoLuongTon = SoLuongTon;
    }

    public Double getGiaNhap() {
        return GiaNhap;
    }

    public void setGiaNhap(Double GiaNhap) {
        this.GiaNhap = GiaNhap;
    }

    public Double getGiaBan() {
        return GiaBan;
    }

    public void setGiaBan(Double GiaBan) {
        this.GiaBan = GiaBan;
    }

    public MauSac getMauSac() {
        return mauSac;
    }

    public void setMauSac(MauSac mauSac) {
        this.mauSac = mauSac;
    }

    public ChatLieu getChatLieu() {
        return chatLieu;
    }

    public void setChatLieu(ChatLieu chatLieu) {
        this.chatLieu = chatLieu;
    }

    public KichCo getKichCo() {
        return kichCo;
    }

    public void setKichCo(KichCo kichCo) {
        this.kichCo = kichCo;
    }

    public KhuyenMai getKhuenMai() {
        return khuenMai;
    }

    public void setKhuenMai(KhuyenMai khuenMai) {
        this.khuenMai = khuenMai;
    }

}

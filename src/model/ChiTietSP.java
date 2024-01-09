/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author vieta
 */
public class ChiTietSP {

    private String ma;
    private String ten;
    private int idnsx;
    private int idmausac;
    private int iddanhmuc;
    private int idkc;
    private int idcl;
    private int idth;
    private int idkm;
    private int soluongton;
    private Double gianhap;
    private Double giaban;
    private String mota;
    private String qrcode;

    public ChiTietSP() {
    }

    public ChiTietSP(String ma, String ten, int idnsx, int idmausac, int iddanhmuc, int idkc, int idcl, int idth, int idkm, int soluongton, Double gianhap, Double giaban, String mota, String qrcode) {
        this.ma = ma;
        this.ten = ten;
        this.idnsx = idnsx;
        this.idmausac = idmausac;
        this.iddanhmuc = iddanhmuc;
        this.idkc = idkc;
        this.idcl = idcl;
        this.idth = idth;
        this.idkm = idkm;
        this.soluongton = soluongton;
        this.gianhap = gianhap;
        this.giaban = giaban;
        this.mota = mota;
        this.qrcode = qrcode;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getIdnsx() {
        return idnsx;
    }

    public void setIdnsx(int idnsx) {
        this.idnsx = idnsx;
    }

    public int getIdmausac() {
        return idmausac;
    }

    public void setIdmausac(int idmausac) {
        this.idmausac = idmausac;
    }

    public int getIddanhmuc() {
        return iddanhmuc;
    }

    public void setIddanhmuc(int iddanhmuc) {
        this.iddanhmuc = iddanhmuc;
    }

    public int getIdkc() {
        return idkc;
    }

    public void setIdkc(int idkc) {
        this.idkc = idkc;
    }

    public int getIdcl() {
        return idcl;
    }

    public void setIdcl(int idcl) {
        this.idcl = idcl;
    }

    public int getIdth() {
        return idth;
    }

    public void setIdth(int idth) {
        this.idth = idth;
    }

    public int getIdkm() {
        return idkm;
    }

    public void setIdkm(int idkm) {
        this.idkm = idkm;
    }

    public int getSoluongton() {
        return soluongton;
    }

    public void setSoluongton(int soluongton) {
        this.soluongton = soluongton;
    }

    public Double getGianhap() {
        return gianhap;
    }

    public void setGianhap(Double gianhap) {
        this.gianhap = gianhap;
    }

    public Double getGiaban() {
        return giaban;
    }

    public void setGiaban(Double giaban) {
        this.giaban = giaban;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    @Override
    public String toString() {
        return   ten ;
    }

}

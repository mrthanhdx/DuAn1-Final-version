
package model;

import java.sql.Date;

/**
 *
 * @author PC
 */
public class HoaDon {
    private int idNV;
    private Integer id;
    private int IDkhachHang;
    private String Ma;
    private String Ghichu;
    private Date NgayTao;
    private Date NgayThanhToan;
    private int TinhTrang;
    private Double tongTien;
    

    public HoaDon() {
    }

    public HoaDon(int idUser, Integer id, int khachHang, String Ma, String Ghichu, Date NgayTao, Date NgayThanhToan, int TinhTrang,Double tongTien) {
        this.idNV = idUser;
        this.id = id;
        this.IDkhachHang = khachHang;
        this.Ma = Ma;
        this.Ghichu = Ghichu;
        this.NgayTao = NgayTao;
        this.NgayThanhToan = NgayThanhToan;
        this.TinhTrang = TinhTrang;
        this.tongTien = tongTien;
    }

    public Double getTongTien() {
        return tongTien;
    }

    public void setTongTien(Double tongTien) {
        this.tongTien = tongTien;
    }

    public Integer getId() {
        return id;
    }

    public String getGhichu() {
        return Ghichu;
    }

    public void setGhichu(String Ghichu) {
        this.Ghichu = Ghichu;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdUser() {
        return idNV;
    }

    public void setIdUser(int user) {
        this.idNV = user;
    }

    public int getIDKhachHang() {
        return IDkhachHang;
    }

    public void setIDKhachHang(int khachHang) {
        this.IDkhachHang = khachHang;
    }

    public String getMa() {
        return Ma;
    }

    public void setMa(String Ma) {
        this.Ma = Ma;
    }

    public Date getNgayTao() {
        return NgayTao;
    }

    public void setNgayTao(Date NgayTao) {
        this.NgayTao = NgayTao;
    }

    public Date getNgayThanhToan() {
        return NgayThanhToan;
    }

    public void setNgayThanhToan(Date NgayThanhToan) {
        this.NgayThanhToan = NgayThanhToan;
    }

    public int getTinhTrang() {
        return TinhTrang;
    }

    public void setTinhTrang(int TinhTrang) {
        this.TinhTrang = TinhTrang;
    }


   
    
     

}
 
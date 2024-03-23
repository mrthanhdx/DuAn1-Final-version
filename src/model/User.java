/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author Admin
 */
public class User {

    private int id;
    private String Ten;
    private Date NgaySinh;
    private Boolean GioTinh;
    private String Sdt;
    private int IdCV;
    private String TaiKhoan;
    private String MatKhau;
    private String Email;
    private boolean TrangThai;
    private Date NgayTao;
    private Date NgaySua;

    public User() {
    }

    public User(int id, String Ten, Date NgaySinh, Boolean GioTinh, String Sdt, int IdCV, String TaiKhoan, String MatKhau, String Email, boolean TrangThai, Date NgayTao, Date NgaySua) {
        this.id = id;
        this.Ten = Ten;
        this.NgaySinh = NgaySinh;
        this.GioTinh = GioTinh;
        this.Sdt = Sdt;
        this.IdCV = IdCV;
        this.TaiKhoan = TaiKhoan;
        this.MatKhau = MatKhau;
        this.Email = Email;
        this.TrangThai = TrangThai;
        this.NgayTao = NgayTao;
        this.NgaySua = NgaySua;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String Ten) {
        this.Ten = Ten;
    }

    public Date getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(Date NgaySinh) {
        this.NgaySinh = NgaySinh;
    }

    public Boolean getGioTinh() {
        return GioTinh;
    }

    public void setGioTinh(Boolean GioTinh) {
        this.GioTinh = GioTinh;
    }

    public String getSdt() {
        return Sdt;
    }

    public void setSdt(String Sdt) {
        this.Sdt = Sdt;
    }

    public int getIdCV() {
        return IdCV;
    }

    public void setIdCV(int IdCV) {
        this.IdCV = IdCV;
    }

    public String getTaiKhoan() {
        return TaiKhoan;
    }

    public void setTaiKhoan(String TaiKhoan) {
        this.TaiKhoan = TaiKhoan;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String MatKhau) {
        this.MatKhau = MatKhau;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public boolean isTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(boolean TrangThai) {
        this.TrangThai = TrangThai;
    }

    public Date getNgayTao() {
        return NgayTao;
    }

    public void setNgayTao(Date NgayTao) {
        this.NgayTao = NgayTao;
    }

    public Date getNgaySua() {
        return NgaySua;
    }

    public void setNgaySua(Date NgaySua) {
        this.NgaySua = NgaySua;
    }

    
}

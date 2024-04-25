/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
/**
 *
 * @author ADMIN
 */
public class KhuyenMai {
    private int id;
    private String tenKhuyenMai;
    private String hinhThucKM;
    private String giaTriGiam;
    private int soLuong;
    private String codeKhuyenMai;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenKhuyenMai() {
        return tenKhuyenMai;
    }

    public void setTenKhuyenMai(String tenKhuyenMai) {
        this.tenKhuyenMai = tenKhuyenMai;
    }

    public String getHinhThucKM() {
        return hinhThucKM;
    }

    public void setHinhThucKM(String hinhThucKM) {
        this.hinhThucKM = hinhThucKM;
    }
    
    public String getGiaTriGiam() {
        return giaTriGiam;
    }

    public void setGiaTriGiam(String giaTriGiam) {
        this.giaTriGiam = giaTriGiam;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

   

    public class KMData {

        private static List<String> data = new ArrayList<>();

        public static List<String> getdata() {
            data.add("%");
            data.add("VND");
            return data;
        }
    }
    
}

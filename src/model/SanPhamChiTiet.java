/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

/**
 *
 * @author Admin
 */
public class SanPhamChiTiet {
   private String id;
    private SanPham tenSp;
    private int soLuongTon;
    private double giaNhap;
    private double giaBan;
    private MauSac mauSac;
    private NSX nhaSx;
    private KichCo kichCo;
    private ChatLieu chatLieu;
    private ThuongHieu thuongHieu;
    private String khuyenMai;
    private String moTa;
    
}

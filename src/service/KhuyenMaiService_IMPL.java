/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.List;
import model.KhuyenMai;
import service.KhuyenMaiService_IMPL;
import java.sql.*;
import connection.ConenctionProvider;
import interfacee.KhuyenMaiService;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luu Huynh
 */
public class KhuyenMaiService_IMPL implements KhuyenMaiService {

    Connection con = ConenctionProvider.getConnection();

    @Override
    public List<KhuyenMai> getAll() {
        try {
            String sql = "select id,ten,hinhthuckm,giatrigiam,soluong,MaCode from KhuyenMai";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            List<KhuyenMai> list = new ArrayList<>();
            while (rs.next()) {
                KhuyenMai km = new KhuyenMai();
                km.setId(rs.getInt(1));
                km.setTenKhuyenMai(rs.getString(2));
                km.setHinhThucKM(rs.getString(3));
                km.setGiaTriGiam(rs.getString(4));
                km.setSoLuong(rs.getInt(5));
                km.setCodeKhuyenMai(rs.getString("MaCode"));
                list.add(km);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public boolean add(KhuyenMai Khuyenmai) {
        String sql = "INSERT INTO KHUYENMAI(TEN,HINHTHUCKM,GIATRIGIAM,SOLUONG,MACODE) VALUES(?,?,?,?,?)";
        try {

            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, Khuyenmai.getTenKhuyenMai());
            pstm.setString(2, Khuyenmai.getHinhThucKM());
            pstm.setString(3, Khuyenmai.getGiaTriGiam());
            pstm.setInt(4, Khuyenmai.getSoLuong());
            pstm.setString(5, Khuyenmai.getCodeKhuyenMai());
            pstm.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
   public boolean Update(KhuyenMai km, int ID) {
    String sql = "UPDATE KHUYENMAI SET TEN=?, HINHTHUCKM=?, GIATRIGIAM=?, SOLUONG=? WHERE ID = ?";
    try {
        PreparedStatement pstm = con.prepareStatement(sql);
        pstm.setString(1, km.getTenKhuyenMai());
        pstm.setString(2, km.getHinhThucKM());
        pstm.setString(3, km.getGiaTriGiam());
        pstm.setInt(4, km.getSoLuong());
        pstm.setInt(5, ID); // Use the provided name to identify the record
        pstm.execute();
        return true;
    } catch (SQLException e) {
        e.printStackTrace(); // Print the stack trace for debugging
        return false;
    }
}

   public List<KhuyenMai> Getbyten(String ten) {
    List<KhuyenMai> lst = new ArrayList<>();
    try {
        String sql = "SELECT * FROM dbo.KhuyenMai WHERE TEN = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, ten);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            lst.add(new KhuyenMai(rs.getInt(1),
                    rs.getString(2), rs.getString(3), rs.getString(4),
                    rs.getInt(5),rs.getString("maCode")));
        }
    } catch (SQLException ex) {
        Logger.getLogger(KhuyenMaiService_IMPL.class.getName()).log(Level.SEVERE, null, ex);
    }
    return lst;
}


}

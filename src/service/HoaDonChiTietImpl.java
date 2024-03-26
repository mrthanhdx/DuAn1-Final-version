/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import interfacee.HoaDonChiTietInterface;
import java.sql.*;
import model.HoaDonChiTiet;
import connection.ConenctionProvider;
import java.util.ArrayList;
import java.util.List;
import model.HoaDon;
import model.SanPhamChiTiet;

/**
 *
 * @author trant
 */
public class HoaDonChiTietImpl implements HoaDonChiTietInterface {

    Connection conn = ConenctionProvider.getConnection();

    @Override
    public List<HoaDonChiTiet> getAllHDCT() {
        List<HoaDonChiTiet> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM HoaDonChiTiet";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                HoaDonChiTiet hdct = new HoaDonChiTiet();
                SanPhamChiTiet spct = new SanPhamChiTiet();
                HoaDon hd = new HoaDon();
                spct.setId(rs.getString("IDCTSP"));
                hd.setId(rs.getInt("IDHD"));
                hdct.setHaoDon(hd);
                hdct.setSanPham(spct);
                hdct.setDonGia(rs.getDouble("DonGia"));
                hdct.setSoluong(rs.getInt("soLuong"));
                list.add(hdct);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean addHDCT(HoaDon hoaDon, SanPhamChiTiet sanPhamChiTiet, int soLuong, double DonGia) {
           try {
            String sql = "insert into HoaDonChiTiet(IdHD,IdCTSP,SoLuong,DonGia) values(?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, hoaDon.getId());
            stmt.setInt(2, Integer.parseInt(sanPhamChiTiet.getId()));
            stmt.setInt(3, soLuong);
            stmt.setDouble(4, DonGia);
            stmt.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteHDCT(int IdHD, int IdSP) {
        try {
            String sql = "DELETE FROM HoaDonChiTiet WHERE IdHD = ? AND IdCTSP = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, IdHD);
            stmt.setInt(2, IdSP);
            stmt.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteAllHDCT(int IdHD) {
         try {
            String sql = "DELETE FROM HoaDonChiTiet WHERE IdHD = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, IdHD);
            stmt.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}

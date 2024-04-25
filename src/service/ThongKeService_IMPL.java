/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.sql.*;
import java.util.ArrayList;
import model.ThongKe;
import service.ThongKeService_IMPL;
import connection.ConenctionProvider;
import interfacee.ThongKeService;
import java.util.List;
import model.HoaDon;

/**
 *
 * @author Luu Huynh
 */
public class ThongKeService_IMPL implements ThongKeService {

    private ArrayList<ThongKe> list = new ArrayList<>();
    private Connection con = ConenctionProvider.getConnection();

    @Override
    public int getdt() {
        int box = 0;
        String sql = "select Sum(TongTien) from HoaDon where TinhTrang = 1 ";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                box = (int) rs.getDouble(1);
            }
        } catch (Exception e) {
            return 0;
        }
        return box;
    }

    @Override
    public int getHoadon() {
        int box = 0;
        String sql = "select COUNT(Id) from HoaDon where TinhTrang =1 ";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                box = rs.getInt(1);
            }
            return box;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int getmonth1() {
        int box = 0;
        String sql = "select sum(TongTien) from HoaDon where TinhTrang = 1 and MONTH(NgayThanhToan) = 1";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                box = rs.getInt(1);
            }
            return box;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int getmonth2() {
        int box = 0;
        String sql = "select sum(TongTien) from HoaDon where TinhTrang = 1 and MONTH(NgayThanhToan) = 2";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                box = rs.getInt(1);
            }
            return box;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int getmonth3() {
        int box = 0;
        String sql = "select sum(TongTien) from HoaDon where TinhTrang = 1 and MONTH(NgayThanhToan) = 3";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                box = rs.getInt(1);
            }
            return box;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int getmonth4() {
        int box = 0;
        String sql = "select sum(TongTien) from HoaDon where TinhTrang = 1 and MONTH(NgayThanhToan) = 4";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                box = rs.getInt(1);
            }
            return box;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int getmonth5() {
        int box = 0;
        String sql = "select sum(TongTien) from HoaDon where TinhTrang = 1 and MONTH(NgayThanhToan) = 5";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                box = rs.getInt(1);
            }
            return box;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int getmonth6() {
        int box = 0;
        String sql = "select sum(TongTien) from HoaDon where TinhTrang = 1 and MONTH(NgayThanhToan) = 6";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                box = rs.getInt(1);
            }
            return box;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int getmonth7() {
        int box = 0;
        String sql = "select sum(TongTien) from HoaDon where TinhTrang = 1 and MONTH(NgayThanhToan) = 7";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                box = rs.getInt(1);
            }
            return box;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int getmonth8() {
        int box = 0;
        String sql = "select sum(TongTien) from HoaDon where TinhTrang = 1 and MONTH(NgayThanhToan) = 8";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                box = rs.getInt(1);
            }
            return box;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int getmonth9() {
        int box = 0;
        String sql = "select sum(TongTien) from HoaDon where TinhTrang = 1 and MONTH(NgayThanhToan) = 9";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                box = rs.getInt(1);
            }
            return box;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int getmonth10() {
        int box = 0;
        String sql = "select sum(TongTien) from HoaDon where TinhTrang = 1 and MONTH(NgayThanhToan) = 10";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                box = rs.getInt(1);
            }
            return box;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int getmonth11() {
        int box = 0;
        String sql = "select sum(TongTien) from HoaDon where TinhTrang = 1 and MONTH(NgayThanhToan) = 11";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                box = rs.getInt(1);
            }
            return box;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int getmonth12() {
        int box = 0;
        String sql = "select sum(TongTien) from HoaDon where TinhTrang = 1 and MONTH(NgayThanhToan) = 12";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                box = rs.getInt(1);
            }
            return box;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<HoaDon> getAll() {
        try {
            String sql = "SELECT id,ngaytao, ngaythanhtoan, tongtien FROM HoaDon where tinhtrang = 1 ";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            List<HoaDon> List = new ArrayList<>();
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setId(rs.getInt(1));
                hd.setNgayTao(rs.getDate(2));
                hd.setNgayThanhToan(rs.getDate(3));
                hd.setTongTien(rs.getDouble(4));
                List.add(hd);
            }
            return List;
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public int getmonthdt(String date) {
        int box = 0;
        String sql = "select SUM(TongTien) from HoaDon where TinhTrang = 1 and MONTH(NgayThanhToan) = ? and  YEAR(NGAYTHANHTOAN) = YEAR(GETDATE()) ";
        try {

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, date);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                box = (int) rs.getDouble(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return box;
    }

    @Override
    public int getyeardt(String date) {
        int box = 0;
        String sql = "select SUM(TongTien) from HoaDon where TinhTrang = 1 and YEAR(NgayThanhToan) = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, date);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                box = (int) rs.getDouble(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return box;
    }

    @Override
    public int getmonthhoadon(String date) {
        int box = 0;
        String sql = "select COUNT(Id) from HoaDon where TinhTrang =1 and MONTH(NgayThanhToan) = ? and YEAR(NgayThanhToan) =YEAR(GETDATE())";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, date);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                box = rs.getInt(1);
            }
            return box;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int getyearhoadon(String date) {
        int box = 0;
        String sql = "select COUNT(Id) from HoaDon where TinhTrang =1 and YEAR(NgayThanhToan) = ?";
        try {

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, date);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                box = rs.getInt(1);
            }
            return box;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<HoaDon> gethdmonth(String month) {
        try {
            String sql = "SELECT id, ngaytao, ngaythanhtoan, tongtien FROM HoaDon WHERE tinhtrang = 1 AND MONTH(ngaythanhtoan) = ? ";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, month);
            ResultSet rs = ps.executeQuery();
            List<HoaDon> list = new ArrayList<>();
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setId(rs.getInt(1));
                hd.setNgayTao(rs.getDate(2));
                hd.setNgayThanhToan(rs.getDate(3));
                hd.setTongTien(rs.getDouble(4));
                list.add(hd);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<HoaDon> gethdyear(String year) {
        try {
            String sql = "SELECT id, ngaytao, ngaythanhtoan, tongtien FROM HoaDon WHERE tinhtrang = 1 AND YEAR(ngaythanhtoan) = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, year);
            ResultSet rs = ps.executeQuery();
            List<HoaDon> list = new ArrayList<>();
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setId(rs.getInt(1));
                hd.setNgayTao(rs.getDate(2));
                hd.setNgayThanhToan(rs.getDate(3));
                hd.setTongTien(rs.getDouble(4));
                list.add(hd);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int getdtThang(String month) {
        int box = 0;
        String sql = "select Sum(TongTien) from HoaDon where TinhTrang = 1 and MONTH(ngaythanhtoan) = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, month);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                box = (int) rs.getDouble(1);
            }
        } catch (Exception e) {
            return 0;
        }
        return box;
    }

    @Override
    public int gethdThang(String month) {
        int box = 0;
        String sql = "select COUNT(Id) from HoaDon where TinhTrang =1 and MONTH(ngaythanhtoan) = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, month);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                box = rs.getInt(1);
            }
            return box;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int getdtNam(String year) {
        int box = 0;
        String sql = "select Sum(TongTien) from HoaDon where TinhTrang = 1 and YEAR(ngaythanhtoan) = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, year);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                box = (int) rs.getDouble(1);
            }
        } catch (Exception e) {
            return 0;
        }
        return box;
    }

    @Override
    public int gethdNam(String year) {
        int box = 0;
        String sql = "select COUNT(Id) from HoaDon where TinhTrang =1 and year(ngaythanhtoan) = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, year);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                box = rs.getInt(1);
            }
            return box;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

}

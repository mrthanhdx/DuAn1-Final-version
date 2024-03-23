/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import connection.ConenctionProvider;
import interfacee.SanPhamInterface;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.SanPham;

/**
 *
 * @author H
 */
public class SanPhamService implements SanPhamInterface{
    Connection con = connection.ConenctionProvider.getConnection();

    @Override
    public List<SanPham> getAll() {
        List<SanPham> list = new ArrayList<>();
        String sql = "Select* from SanPham";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                SanPham sp = new SanPham();
                sp.setId(rs.getInt("id")+"");
                sp.setTen(rs.getString("TenSanPham"));
                sp.setNgayTao(rs.getString("Ngaytao"));
                sp.setNgaySua(rs.getString("Ngaysua"));
                list.add(sp);
            }return list;
            
        } catch (Exception e) {
            return null;
        }
        
    }

    @Override
    public int add(SanPham sp) {
        String sql = """
                     INSERT INTO dbo.SanPham(TenSanPham)
                     VALUES( ?  )""";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, sp.getTen());
            return ps.executeUpdate();
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public int update(SanPham sp, int index) {
        String sql = """
                     UPDATE dbo.SanPham
                     SET TenSanPham = ?,NgaySua=GETDATE()
                     WHERE ID= ?""";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, sp.getTen());
            ps.setObject(2, index);
            return ps.executeUpdate();
        } catch (Exception e) {
            return 0;
        }
    }
    
    
}

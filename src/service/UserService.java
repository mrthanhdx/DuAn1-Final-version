/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import connection.ConenctionProvider;
import java.util.ArrayList;
import model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author duong
 */
public class UserService {
    private Connection con = ConenctionProvider.getConnection();
    public ArrayList<User> getAll(){
        String sql = """
                     SELECT [Id]
                           ,[Ten]
                           ,[NgaySinh]
                           ,[Gioitinh]
                           ,[Sdt]
                           ,[IdCV]
                           ,[TaiKhoan]
                           ,[MatKhau]
                           ,[Email]
                           ,[TrangThai]
                           ,[Ngaytao]
                           ,[NgaySua]
                       FROM [dbo].[Users]
                     """;
        
        try(PreparedStatement ps = con.prepareCall(sql);
                ResultSet rs = ps.executeQuery()) {
                    ArrayList<User> ls = new ArrayList<>();
                    while(rs.next()){
                        User u = new User();
                        u.setId(rs.getInt(1));
                        u.setTen(rs.getString(2));
                        u.setNgaySinh(rs.getDate(3));
                        u.setGioTinh(rs.getBoolean(4));
                        u.setSdt(rs.getString(5));
                        u.setIdCV(rs.getInt(6));
                        u.setTaiKhoan(rs.getString(7));
                        u.setMatKhau(rs.getString(8));
                        u.setEmail(rs.getString(9));
                        u.setTrangThai(rs.getBoolean(10));
                        u.setNgayTao(rs.getDate(11));
                        u.setNgaySua(rs.getDate(12));
                        ls.add(u);
                    }
                    return ls;
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return null;
    }
     
    
    public boolean them(User u){
        String sql = """
                     INSERT INTO [dbo].[Users]
                                ([Ten]
                                ,[NgaySinh]
                                ,[Gioitinh]
                                ,[Sdt]
                                ,[IdCV]
                                ,[TaiKhoan]
                                ,[MatKhau]
                                ,[Email]
                                ,[TrangThai])
                          VALUES
                                (?,?,?,?,?,?,?,?,?)
                     """;
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, u.getTen());
            ps.setObject(2, u.getNgaySinh());
            ps.setObject(3, u.getGioTinh());
            ps.setObject(4, u.getSdt());
            ps.setObject(5, u.getIdCV());
            ps.setObject(6, u.getTaiKhoan());
            ps.setObject(7, u.getMatKhau());
            ps.setObject(8, u.getEmail());
            ps.setObject(9, u.isTrangThai());
            int result = ps.executeUpdate();
            return result > 0;
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return false;
    }
    public boolean xoa(int id){
        String sql = """
                     DELETE FROM [dbo].[Users]
                           WHERE Id = ?
                     """;
        try (PreparedStatement ps = con.prepareStatement(sql)){
            ps.setObject(1, id);
            int result = ps.executeUpdate();
            return result > 0;
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return false;
    }
    public boolean sua(User u ,int id){
        String sql = """
                    UPDATE [dbo].[Users]
                        SET [Ten] = ?
                           ,[NgaySinh] = ?
                           ,[Gioitinh] = ?
                           ,[Sdt] = ?
                           ,[IdCV] = ?
                           ,[TaiKhoan] = ?
                           ,[MatKhau] = ?
                           ,[Email] = ?
                           ,[TrangThai] = ?
                      WHERE Id = ? 
                     """;
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, u.getTen());
            ps.setObject(2, u.getNgaySinh());
            ps.setObject(3, u.getGioTinh());
            ps.setObject(4, u.getSdt());
            ps.setObject(5, u.getIdCV());
            ps.setObject(6, u.getTaiKhoan());
            ps.setObject(7, u.getMatKhau());
            ps.setObject(8, u.getEmail());
            ps.setObject(9, u.isTrangThai());
            ps.setObject(10, id);
            int result = ps.executeUpdate();
            return result > 0;
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return false;
    }
    
    public boolean getAllAccount(String userAccount) {
        ArrayList<User> ls = new ArrayList<>();
        String sql = """
                     SELECT [TaiKhoan]
                       FROM [dbo].[Users]
                     """;
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            User u = new User();
            while (rs.next()) {
                u.setTaiKhoan(rs.getString(1));
                ls.add(u);
            }
            if (u.getTaiKhoan().equals(userAccount)) {
                return false;
            }

        } catch (Exception e) {
//            e.printStackTrace();
        }
        return true;
    }
}

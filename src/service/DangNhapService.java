
package service;

import connection.ConenctionProvider;
import java.beans.Statement;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Chucvu;
import model.User;

/**
 *
 * @author Dung
 */
public class DangNhapService  {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=DuAn2";
    private static final String USERNAME = "sa";
    private static final String PASSWORD = "12345678";


    public static List<User> getUserList(){
       List<User> dataList = new ArrayList<>();
       Connection con = null;
       PreparedStatement statement = null;
       try{
           
           con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
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
           statement = con.prepareStatement(sql);
           ResultSet resultSet = statement.executeQuery();
           while(resultSet.next()){
               User u = new User(
                        resultSet.getInt("Id"),
                        resultSet.getString("Ten"),
                        resultSet.getDate("NgaySinh"),
                        resultSet.getBoolean("Gioitinh"),
                        resultSet.getString("Sdt"),
                       resultSet.getInt("IdCV"),
                       resultSet.getString("TaiKhoan"),
                        resultSet.getString("MatKhau"),
                        resultSet.getString("Email"),
                        resultSet.getBoolean("TrangThai"),
                        resultSet.getDate("Ngaytao"),
                       resultSet.getDate("NgaySua")
                       );
               dataList.add(u);
           }
       }catch(SQLException ex){
          Logger.getLogger(DangNhapService.class.getName()).log(Level.SEVERE, null, ex);

       } finally{
           if(statement != null){
               try {
                   statement.close();
               } catch (SQLException ex) {
                   Logger.getLogger(DangNhapService.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
           if(con != null){
               try {
                   statement.close();
               } catch (SQLException ex) {
                   Logger.getLogger(DangNhapService.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
       }
       return dataList;
    }
    
   public static User login(String TaiKhoan, String MatKhau) {
    User user = null;
    Connection con = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    
    try {
        // Thiết lập kết nối đến cơ sở dữ liệu
      
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        String sql = "SELECT * FROM users WHERE TaiKhoan = ? AND MatKhau = ?";
        statement = con.prepareStatement(sql);
        statement.setString(1, TaiKhoan);
        statement.setString(2, MatKhau);
        
        resultSet = statement.executeQuery();
        if (resultSet.next()) {
            user = new User(
                resultSet.getInt("Id"),
                resultSet.getString("Ten"),
                resultSet.getDate("NgaySinh"),
                resultSet.getBoolean("GioiTinh"),
                resultSet.getString("Sdt"),
                resultSet.getInt("IdCV"),
                resultSet.getString("TaiKhoan"),
                resultSet.getString("MatKhau"),
                resultSet.getString("Email"),
                resultSet.getBoolean("TrangThai"),
                resultSet.getDate("NgayTao"),
                resultSet.getDate("NgaySua")
            );
        }
    } catch (SQLException ex) {
        Logger.getLogger(DangNhapService.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        // Đóng tất cả các tài nguyên, bao gồm cả kết nối, câu lệnh và tập kết quả
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException ex) {
                Logger.getLogger(DangNhapService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(DangNhapService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DangNhapService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    return user;
}

    
    
     public static void insert(User user){
       List<User> dataList = new ArrayList<>();
       Connection con = null;
       PreparedStatement statement = null;
       try{
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
                                   ,[TrangThai]
                                   ,[Ngaytao]
                                   ,[NgaySua])
                             + VALUES(?,?,?,?,?,?,?,?,?,?,?)
                        """;
           statement.setString(1, user.getTen());
           statement.setDate(2, user.getNgaySinh());
           statement.setBoolean(3, user.getGioTinh());
           statement.setString(4, user.getSdt());
           statement.setInt(5, user.getIdCV());
           statement.setString(6, user.getTaiKhoan());
           statement.setString(7, user.getMatKhau());
           statement.setString(8, user.getEmail());
           statement.setBoolean(9, user.getTrangThai());
           statement.setDate(10, user.getNgayTao());
           statement.setDate(11, user.getNgaySua());
           statement.execute();
         
       }catch(SQLException ex){
          Logger.getLogger(DangNhapService.class.getName()).log(Level.SEVERE, null, ex);

       } finally{
           if(statement != null){
               try {
                   statement.close();
               } catch (SQLException ex) {
                   Logger.getLogger(DangNhapService.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
           if(con != null){
               try {
                   con.close();
               } catch (SQLException ex) {
                   Logger.getLogger(DangNhapService.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
       }
       
    }
   


    
   
        
}

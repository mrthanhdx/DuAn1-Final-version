package service;


import connection.ConenctionProvider;
import java.sql.Connection;
import java.util.List;
import model.ThuongHieu;
import interfacee.ThuongHieuService;
import java.sql.*;
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author trant
 */
public class ThuongHieuServicesImpl implements ThuongHieuService{
 private Connection con = ConenctionProvider.getConnection();    
    @Override
    public List<ThuongHieu> getAll() {
         try {
            String SQL = "SELECT * FROM ThuongHieu";
            
            Statement st =  con.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            List<ThuongHieu> List = new ArrayList<>();
            while(rs.next()){
            ThuongHieu cl = new ThuongHieu();
            cl.setId(rs.getInt(1));
            cl.setTen(rs.getString(2));
            
            List.add(cl);
            }
            return List;
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public boolean add(ThuongHieu thuongHieu) {
        String SQL = "INSERT INTO ThuongHieu (Ten) VALUES(?)";
        try {
            PreparedStatement pstm = con.prepareStatement(SQL);
            pstm.setString(1, thuongHieu.getTen());
            
            pstm.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean  update(ThuongHieu cl, int id) {
         String sql = "UPDATE ThuongHieu SET  TEN = ? WHERE ID =?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, cl.getTen());
            stmt.setInt(2, id);
            stmt.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(ThuongHieu cl) {
         String sql = "DELETE FROM ThuongHieu WHERE ID = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, cl.getId());
            stmt.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ThuongHieu getbyId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}

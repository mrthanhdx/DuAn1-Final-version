/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import connection.ConenctionProvider;
import java.util.List;
import model.DanhMucSP;
import interfacee.DanhMucService;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Luu Huynh
 */
public class DanhMucServiceImpl implements DanhMucService {

    private Connection con = ConenctionProvider.getConnection();
    List<DanhMucSP> list = new ArrayList<>();

    @Override
    public List<DanhMucSP> getAll() {
        try {
            String SQL = "SELECT * FROM DanhMucSP";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            List<DanhMucSP> List = new ArrayList<>();
            while (rs.next()) {
                DanhMucSP cl = new DanhMucSP();
                cl.setId(rs.getInt(1));
                cl.setTen(rs.getString(2));

                List.add(cl);
            }
            return List;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean add(DanhMucSP DanhMuc) {
        String SQL = "INSERT INTO DanhMucSP (Ten) VALUES(?)";
        try {
            PreparedStatement pstm = con.prepareStatement(SQL);
            pstm.setString(1, DanhMuc.getTen());

            pstm.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(DanhMucSP danhMucSP) {
        String sql = "DELETE FROM DANHMUCSP WHERE ID = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, danhMucSP.getId());
            stmt.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(DanhMucSP danhMucSP, int id) {
       String sql = "UPDATE DanhMuc SET  TEN = ? WHERE ID =?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, danhMucSP.getTen());
            stmt.setInt(2, id);
            stmt.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

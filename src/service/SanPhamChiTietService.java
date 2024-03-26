/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import interfacee.SanPhamChiTietInterface;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.ChatLieu;
import model.KichCo;
import model.MauSac;
import model.NSX;
import model.SanPham;
import model.SanPhamChiTiet;
import model.ThuongHieu;

/**
 *
 * @author H
 */
public class SanPhamChiTietService implements SanPhamChiTietInterface {

    Connection con = connection.ConenctionProvider.getConnection();

    @Override
    public List<model.SanPhamChiTiet> getAll() {
        List<model.SanPhamChiTiet> list = new ArrayList<>();
        String sql = """
                     	SELECT 
                         CT.Id,
                         SP.id As idSP,
                     	SP.TenSanPham AS TenSanPham,
                         NSX.Ten AS TenNSX,
                         MS.Ten AS TenMauSac,
                         KC.Ten AS TenKichCo,
                         CL.Ten AS TenChatLieu,
                         TH.Ten AS TenThuongHieu,
                         KM.Ten AS TenKhuyenMai,
                         CT.MoTa,
                         CT.SoLuongTon,
                         CT.GiaNhap,
                         CT.GiaBan,
                         CT.QrCode
                     FROM 
                         dbo.ChitietSP AS CT
                     LEFT JOIN 
                         dbo.NSX AS NSX ON CT.IdNsx = NSX.Id
                     LEFT JOIN 
                         dbo.MauSac AS MS ON CT.IdMauSac = MS.Id
                     LEFT JOIN 
                         dbo.KichCo AS KC ON CT.IdKC = KC.Id
                     LEFT JOIN 
                         dbo.ChatLieu AS CL ON CT.IdCL = CL.Id
                     LEFT JOIN 
                         dbo.ThuongHieu AS TH ON CT.IdTH = TH.Id
                     LEFT JOIN 
                         dbo.KhuyenMai AS KM ON CT.IdKM = KM.Id
                     LEFT JOIN
                         dbo.SanPham AS SP ON CT.IdSP = SP.ID;
                     """;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPhamChiTiet sp = new SanPhamChiTiet();
                sp.setId(rs.getString("Id"));

                ChatLieu cl = new ChatLieu();
                cl.setTen(rs.getString("TenChatLieu"));
                sp.setChatLieu(cl);

                sp.setGiaBan(rs.getDouble("GiaBan"));
                sp.setGiaNhap(rs.getDouble("GiaNhap"));

                KichCo kc = new KichCo();
                kc.setTen(rs.getString("TenKichCo"));
                sp.setKichCo(kc);

                MauSac ms = new MauSac();
                ms.setTen(rs.getString("TenMauSac"));
                sp.setMauSac(ms);

                NSX nsx = new NSX();
                nsx.setTen(rs.getString("TenNSX"));
                sp.setNhaSx(nsx);

                sp.setSoLuongTon(rs.getInt("SoLuongTon"));

                SanPham sp1 = new SanPham();
                sp1.setTen(rs.getString("TenSanPham"));
                sp1.setId(rs.getString("idSP"));
                sp.setTenSp(sp1);

                ThuongHieu th = new ThuongHieu();
                th.setTen(rs.getString("TenThuongHieu"));
                sp.setThuongHieu(th);

                sp.setKhuyenMai(rs.getString("TenKhuyenMai"));
                sp.setMoTa(rs.getString("MoTa"));
                list.add(sp);

            }
            return list;
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public int add(model.SanPhamChiTiet spct) {
        String sql = """
                    INSERT INTO dbo.ChitietSP
                    	(
                    	    IdNsx,
                    	    IdMauSac,
                    	    IdKC,
                    	    IdCL,
                    	    IdTH,
                    	    MoTa,
                    	    SoLuongTon,
                    	    GiaNhap,
                    	    GiaBan,
                    	    IdSP
                    	)
                    	VALUES
                    	(   ?,?,?,?,?,?,?,?,?,?
                    	    )""";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, spct.getNhaSx().getId());
            ps.setObject(2, spct.getMauSac().getId());
            ps.setObject(3, spct.getKichCo().getId());
            ps.setObject(4, spct.getChatLieu().getId());
            ps.setObject(5, spct.getThuongHieu().getId());
            ps.setObject(6, spct.getMoTa());
            ps.setObject(7, spct.getSoLuongTon());
            ps.setObject(8, spct.getGiaNhap());
            ps.setObject(9, spct.getGiaBan());
            ps.setObject(10, spct.getId());
            return ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int update(model.SanPhamChiTiet sp, String id) {
        String sql = "update chitietSP set idSP = ?,idNsx=?,idMausac=?,idkc=?,idcl=?,idth=?,mota=?,soluongton=?,gianhap=?,giaban=? where id=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, sp.getId());
            ps.setObject(2, sp.getNhaSx().getId());
            ps.setObject(3, sp.getMauSac().getId());
            ps.setObject(4, sp.getKichCo().getId());
            ps.setObject(5, sp.getChatLieu().getId());
            ps.setObject(6, sp.getThuongHieu().getId());
            ps.setObject(7, sp.getMoTa());
            ps.setObject(8, sp.getSoLuongTon());
            ps.setObject(9, sp.getGiaNhap());
            ps.setObject(10, sp.getGiaBan());
            ps.setObject(11, id);
            return ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

}

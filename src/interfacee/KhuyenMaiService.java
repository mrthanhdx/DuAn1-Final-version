/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfacee;

import java.util.List;
import model.KhuyenMai;

/**
 *
 * @author Luu Huynh
 */
public interface KhuyenMaiService {
   public List<KhuyenMai> getAll();
   public boolean add(KhuyenMai Khuyenmai);
   public boolean Update(KhuyenMai km, int ID) ;
    public List<KhuyenMai> Getbyten(String ten);
}

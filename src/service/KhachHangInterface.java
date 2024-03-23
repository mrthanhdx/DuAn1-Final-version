/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.util.List;
import model.KhachHang;
/**
 *
 * @author Dung
 */
public interface KhachHangInterface {
    
    public List<KhachHang> getAll();
    
    public boolean add(KhachHang khachHang);

    public void update(KhachHang khachHang , int index);
    
    public void delete(int khachHang);
    
    public void find (String khachHang);

}

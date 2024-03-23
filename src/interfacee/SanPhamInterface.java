/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfacee;

import java.awt.List;
import model.SanPham;

/**
 *
 * @author H
 */
public interface SanPhamInterface {
    public java.util.List<SanPham> getAll();
    
    public int add(SanPham sp);
    
    public int update(SanPham sp, int index);


}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfacee;

import java.util.List;
import model.NSX;

/**
 *
 * @author trant
 */
public interface NhaSanXuatServices {
    public List<NSX> getAll();
    
    public boolean add(NSX NSX);
    public boolean update (NSX nsx, int id);
    public boolean delete (NSX nsx);
    public NSX getbyId (int id);
}

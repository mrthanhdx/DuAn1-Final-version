/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfacee;

import model.MauSac;
import java.util.List;

/**
 *
 * @author trant
 */
public interface MauSacServices {
     public List<MauSac> getAll();
    
    public boolean add(MauSac MauSac);
    public boolean update (MauSac cl, int id);
    public boolean delete (MauSac cl);
    public MauSac getbyId (int id);
}

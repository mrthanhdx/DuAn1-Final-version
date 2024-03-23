/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfacee;

import java.util.List;
import model.DanhMucSP;

/**
 *
 * @author Luu Huynh
 */
public interface DanhMucService {
    public List<DanhMucSP> getAll();
    
    public boolean add(DanhMucSP DanhMuc);
    public boolean delete(DanhMucSP danhMucSP);
    public boolean update(DanhMucSP danhMucSP,int id);
}

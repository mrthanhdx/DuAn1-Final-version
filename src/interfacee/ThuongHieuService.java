/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfacee;

import java.util.List;
import model.ThuongHieu;

/**
 *
 * @author trant
 */
public interface ThuongHieuService {
    public List<ThuongHieu> getAll();
    
    public boolean add(ThuongHieu ThuongHieu);
    public boolean update (ThuongHieu cl, int id);
    public boolean delete (ThuongHieu cl);
    public ThuongHieu getbyId (int id);
}

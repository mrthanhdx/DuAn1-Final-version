/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfacee;

import java.util.List;
import model.KichCo;

/**
 *
 * @author trant
 */
public interface KichCoService {
    public List<KichCo> getAll();
    
    public boolean add(KichCo kichCo);
    public boolean update (KichCo cl, int id);
    public boolean delete (KichCo cl);
    public KichCo getbyId (int id);
}

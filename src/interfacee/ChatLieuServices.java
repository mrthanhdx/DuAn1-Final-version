/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfacee;

import java.util.List;
import model.ChatLieu;

/**
 *
 * @author Luu Huynh
 */
public interface ChatLieuServices {
    public List<ChatLieu> getAll();
    
    public boolean add(ChatLieu ChatLieu);
    public boolean update (ChatLieu cl, int id);
    public boolean delete (ChatLieu cl);
    public ChatLieu getbyId (int id);
    
    
    
}


package model;


public class ChatLieu {
    private int id;
    private String Ten;

    public ChatLieu() {
    }

    public ChatLieu(int id, String Ten) {
        this.id = id;
        this.Ten = Ten;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String Ten) {
        this.Ten = Ten;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return Ten;
    }
    
    
 
}

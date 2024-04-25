/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfacee;

import java.util.List;
import model.HoaDon;

/**
 *
 * @author Luu Huynh
 */
public interface ThongKeService {
    public List<HoaDon> getAll();
    public int getdt();
    public int getHoadon();
    public int getmonth1();
    public int getmonth2();
    public int getmonth3();
    public int getmonth4();
    public int getmonth5();
    public int getmonth6();
    public int getmonth7();
    public int getmonth8();
    public int getmonth9();
    public int getmonth10();
    public int getmonth11();
    public int getmonth12();
    public int getmonthdt(String date);
    public int getyeardt(String date);
    public int getmonthhoadon(String date);
    public int getyearhoadon(String date);
    public List<HoaDon> gethdmonth(String month);
    public List<HoaDon> gethdyear(String year);
    public int getdtThang(String month);
    public int gethdThang(String month);
    public int getdtNam(String year);
    public int gethdNam(String year);
}

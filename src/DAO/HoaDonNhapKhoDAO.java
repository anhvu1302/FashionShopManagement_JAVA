/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import POJO.HoaDonNhapKho;
import java.sql.ResultSet;
import java.util.ArrayList;

public class HoaDonNhapKhoDAO {
    public static ArrayList<HoaDonNhapKho> getAll(){
        ArrayList<HoaDonNhapKho> dsHDNK = new ArrayList<HoaDonNhapKho>();
        try{
            String sql= "Select * from HoaDonNhapKho";
            SQLServerDataProvider provider = new SQLServerDataProvider();
            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            while (rs.next()) {                
                HoaDonNhapKho hd = new HoaDonNhapKho();
                hd.getIdHoaDonNhapKho(rs.getInt(1));
                hd.getNgayNhap(rs.getDate(2));
                hd.getIdNhanVien(rs.getInt(3));
                dsHDNK.add(hd);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return dsHDNK;
    }
}

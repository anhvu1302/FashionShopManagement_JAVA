/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import POJO.NhanVien;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Nah nah
 */
public class NhanVienDAO {
    public static ArrayList<NhanVien> layDSTaiKhoan(){
        ArrayList<NhanVien> dsTK = new ArrayList<NhanVien>();
        try {
            String sql="SELECT TenTaiKhoan,MatKhau FROM NhanVien";
            SQLServerDataProvider provider= new SQLServerDataProvider();
            provider.open();
            ResultSet rs=provider.executeQuery(sql);
            while(rs.next()){
                NhanVien nv= new NhanVien();
                nv.setTenNhanVien(rs.getString(1));
                nv.setMatKhau(rs.getString(2));
                dsTK.add(nv);
            }
            provider.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsTK;
    }
    public static boolean ktTKTonTai(String tenTK,String matKhau)
    {
        try {
            String sqlSelect=String.format("select TenTaiKhoan,MatKhau from NhanVien where TenTaiKhoan='%s'"+" and MatKhau= '%s'",tenTK,matKhau);
            SQLServerDataProvider provider= new SQLServerDataProvider();
            provider.open();
            ResultSet rs= provider.executeQuery(sqlSelect);
            if(rs.next())
                return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
}

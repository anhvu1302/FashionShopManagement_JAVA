/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import POJO.KhachHang;
import java.util.ArrayList;
import java.sql.ResultSet;

public class KhachHangDAO {

    public static ArrayList<KhachHang> layDSKhachHang() {
        ArrayList<KhachHang> dsKH = new ArrayList<KhachHang>();
        try {
            String sql = "SELECT * FROM KhachHang";
            SQLServerDataProvider provider = new SQLServerDataProvider();
            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setIdKhachHang(rs.getInt(1));
                kh.setTenKhachHang(rs.getString(2));
                kh.setGioiTinh(rs.getString(3));
                kh.setSoDienThoai(rs.getString(4));
                kh.setEmail(rs.getString(5));
                kh.setDiem(rs.getLong(6));
                dsKH.add(kh);
            }
            provider.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsKH;
    }

}

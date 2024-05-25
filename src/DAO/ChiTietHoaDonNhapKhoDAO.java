/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import POJO.ChiTietHoaDonNhapKho;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ChiTietHoaDonNhapKhoDAO {
    public static ArrayList<ChiTietHoaDonNhapKho> getAll() {
        ArrayList<ChiTietHoaDonNhapKho> dsCTHDNK = new ArrayList<ChiTietHoaDonNhapKho>();
        SQLServerDataProvider provider = new SQLServerDataProvider();
        try {
            String sql = "Select * from ChiTietHoaDonNhapKho";

            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            while (rs.next()) {
                ChiTietHoaDonNhapKho cthd = new ChiTietHoaDonNhapKho();
                cthd.setIdHoaDonNhapKho(rs.getLong(1));
                cthd.setIdKieuSanPham(rs.getLong(2));
                cthd.setSoLuong(rs.getInt(3));
                cthd.setDonGia(rs.getLong(4));
                dsCTHDNK.add(cthd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsCTHDNK;
    }
}

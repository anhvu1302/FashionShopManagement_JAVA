/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import POJO.ChiTietHoaDon;
import POJO.KieuSanPham;
import POJO.SanPham;
import java.util.ArrayList;
import java.sql.ResultSet;

public class ChiTietHoaDonDAO {
    public static ArrayList<ChiTietHoaDon> getAllByID(long id) {
        ArrayList<ChiTietHoaDon> dsCTHD = new ArrayList<ChiTietHoaDon>();
        SQLServerDataProvider provider = new SQLServerDataProvider();
        try {
            String sql = String.format("SELECT cthd.IdHoaDon, cthd.IdKieuSanPham, sp.TenSanPham, ksp.Mau, ksp.Size, cthd.SoLuong, cthd.DonGia "
                    + "FROM ChiTietHoaDon cthd "
                    + "INNER JOIN KieuSanPham ksp ON ksp.IdKieuSanPham = cthd.IdKieuSanPham "
                    + "INNER JOIN SanPham sp ON sp.IdSanPham = ksp.IdSanPham "
                     +"WHERE IdHoaDon=%d", id);
           
            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            while (rs.next()) {
                ChiTietHoaDon cthd = new ChiTietHoaDon();
                cthd.setIdHoaDon(rs.getLong(1));
                cthd.setIdKieuSanPham(rs.getLong(2));
                SanPham sp= new SanPham();
                sp.setTenSanPham(rs.getString(3));
                cthd.setSp(sp);
                KieuSanPham ksp= new KieuSanPham();
                ksp.setMau(rs.getString(4));
                ksp.setSize(rs.getString(5));
                cthd.setKieuSanPham(ksp);
                cthd.setSoLuong(rs.getInt(6));
                cthd.setDonGia(rs.getLong(7));
                dsCTHD.add(cthd);
            }
            rs.close();
            provider.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsCTHD;
    }
}

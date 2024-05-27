/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import POJO.ChiTietHoaDonNhapKho;
import POJO.SanPham;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ChiTietHoaDonNhapKhoDAO {

    public static ArrayList<ChiTietHoaDonNhapKho> getAll() {
        ArrayList<ChiTietHoaDonNhapKho> dsCTHDNK = new ArrayList<ChiTietHoaDonNhapKho>();
        SQLServerDataProvider provider = new SQLServerDataProvider();
        try {
            String sql = "SELECT cthdnk.IdHoaDonNhapKho, cthdnk.IdKieuSanPham, sp.TenSanPham, cthdnk.SoLuong, cthdnk.DonGia "
                    + "FROM ChiTietHoaDonNhapKho cthdnk "
                    + "INNER JOIN KieuSanPham ksp ON ksp.IdKieuSanPham = cthdnk.IdKieuSanPham "
                    + "INNER JOIN SanPham sp ON sp.IdSanPham = ksp.IdSanPham";
            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            while (rs.next()) {
                ChiTietHoaDonNhapKho cthd = new ChiTietHoaDonNhapKho();
                cthd.setIdHoaDonNhapKho(rs.getLong(1));
                cthd.setIdKieuSanPham(rs.getLong(2));
                SanPham sp= new SanPham();
                sp.setTenSanPham(rs.getString(3));
                cthd.setSp(sp);
                cthd.setSoLuong(rs.getInt(4));
                cthd.setDonGia(rs.getLong(5));
                dsCTHDNK.add(cthd);
            }
            rs.close();
            provider.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsCTHDNK;
    }
    public static boolean deleteById(long id, long maksp) {
        boolean kq = false;
        String sql = String.format("Delete From ChiTietHoaDonNhapKho Where IdHoaDonNhapKho=%d AND IdKieuSanPham='%d'", id,maksp);
        SQLServerDataProvider provider = new SQLServerDataProvider();
        provider.open();
        int n = provider.executeUpdate(sql);
        if (n == 1) {
            kq = true;
        }
        provider.close();
        return kq;
    }

    public static boolean updateById(ChiTietHoaDonNhapKho cthd) {
        boolean kq = false;
        String sql = String.format("update ChiTietHoaDonNhapKho set SoLuong= '%d',DonGia='%d' where IdHoaDonNhapKho ='%d' AND IdKieuSanPham='%d'", cthd.getSoLuong(), cthd.getDonGia(), cthd.getIdHoaDonNhapKho(),cthd.getIdKieuSanPham());
        SQLServerDataProvider provider = new SQLServerDataProvider();
        provider.open();
        int n = provider.executeUpdate(sql);
        if (n == 1) {
            kq = true;
        }
        provider.close();
        return kq;
    }
    public static boolean insert(ChiTietHoaDonNhapKho cthd) {
        boolean kq = false;
        String sql = String.format("INSERT INTO ChiTietHoaDonNhapKho VALUES(%d,%d,%d,%d);", cthd.getIdHoaDonNhapKho(), cthd.getIdKieuSanPham(), cthd.getSoLuong(), cthd.getDonGia());
        SQLServerDataProvider provider = new SQLServerDataProvider();
        provider.open();
        int n = provider.executeUpdate(sql);
        if (n == 1) {
            kq = true;
        }
        provider.close();
        return kq;
    }
}

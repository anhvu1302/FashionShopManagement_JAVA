/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import POJO.ChiTietHoaDonNhapKho;
import POJO.KieuSanPham;
import POJO.SanPham;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ChiTietHoaDonNhapKhoDAO {

    public static ArrayList<ChiTietHoaDonNhapKho> getByIdHoaDon(long idHoaDon) {
        ArrayList<ChiTietHoaDonNhapKho> dsCTHDNK = new ArrayList<ChiTietHoaDonNhapKho>();
        SQLServerDataProvider provider = new SQLServerDataProvider();
        try {
            String sql = String.format("SELECT cthd.IdHoaDonNhapKho, cthd.IdKieuSanPham, sp.TenSanPham, ksp.Mau, ksp.Size, cthd.SoLuong, cthd.DonGia "
                    + "FROM ChiTietHoaDonNhapKho cthd "
                    + "INNER JOIN KieuSanPham ksp ON ksp.IdKieuSanPham = cthd.IdKieuSanPham "
                    + "INNER JOIN SanPham sp ON sp.IdSanPham = ksp.IdSanPham "
                    + "WHERE IdHoaDonNhapKho= %d", idHoaDon);

            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            while (rs.next()) {
                ChiTietHoaDonNhapKho cthd = new ChiTietHoaDonNhapKho();
                cthd.setIdHoaDonNhapKho(rs.getLong("IdHoaDonNhapKho"));
                cthd.setIdKieuSanPham(rs.getLong("IdKieuSanPham"));
                KieuSanPham ksp = new KieuSanPham();
                ksp.setMau(rs.getString("Mau"));
                ksp.setSize(rs.getString("Size"));

                SanPham sp = new SanPham();
                sp.setTenSanPham(rs.getString("TenSanPham"));
                ksp.setSanPham(sp);
                cthd.setKieuSanPham(ksp);

                cthd.setSoLuong(rs.getInt("SoLuong"));
                cthd.setDonGia(rs.getLong("DonGia"));
                dsCTHDNK.add(cthd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsCTHDNK;
    }

    public static boolean add(ChiTietHoaDonNhapKho cthd) {
        boolean kq = false;
        String sql = String.format("INSERT ChiTietHoaDonNhapKho(IdHoaDonNhapKho,IdKieuSanPham,DonGia,SoLuong) VALUES(%d,%d,%d,%d);", cthd.getIdHoaDonNhapKho(), cthd.getIdKieuSanPham(), cthd.getDonGia(), cthd.getSoLuong());
        SQLServerDataProvider provider = new SQLServerDataProvider();
        provider.open();
        int n = provider.executeUpdate(sql);
        if (n == 1) {
            kq = true;
        }
        provider.close();
        return kq;
    }

    public static boolean update(ChiTietHoaDonNhapKho cthd) {
        boolean kq = false;
        String sql = String.format("UPDATE ChiTietHoaDonNhapKho SET SoLuong = %d, DonGia= %d WHERE IdHoaDonNhapKho = %d AND IdKieuSanPham = %d;", cthd.getSoLuong(), cthd.getDonGia(), cthd.getIdHoaDonNhapKho(), cthd.getIdKieuSanPham());
        SQLServerDataProvider provider = new SQLServerDataProvider();
        provider.open();
        int n = provider.executeUpdate(sql);
        if (n == 1) {
            kq = true;
        }
        provider.close();
        return kq;
    }

    public static boolean delete(ChiTietHoaDonNhapKho cthd) {
        boolean kq = false;
        String sql = String.format("DELETE ChiTietHoaDonNhapKho WHERE IdHoaDonNhapKho = %d AND IdKieuSanPham = %d;", cthd.getIdHoaDonNhapKho(), cthd.getIdKieuSanPham());
        SQLServerDataProvider provider = new SQLServerDataProvider();
        provider.open();
        int n = provider.executeUpdate(sql);
        if (n == 1) {
            kq = true;
        }
        provider.close();
        return kq;
    }

    public static boolean isCTHDExists(long idHoaDon, long idKSP) {
        SQLServerDataProvider provider = new SQLServerDataProvider();
        try {
            String sqlSelect = String.format("SELECT * FROM ChiTietHoaDonNhapKho WHERE IdHoaDonNhapKho =%d AND IdKieuSanPham =%d;", idHoaDon, idKSP);
            provider.open();
            ResultSet rs = provider.executeQuery(sqlSelect);
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            provider.close();
        }
        return false;
    }
}

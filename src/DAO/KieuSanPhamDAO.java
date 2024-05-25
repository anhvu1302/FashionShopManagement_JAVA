/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import POJO.*;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author VanAnh
 */
public class KieuSanPhamDAO {

    public static ArrayList<KieuSanPham> getByIdSanPham(long id) {
        ArrayList<KieuSanPham> dsKieu = new ArrayList<>();
        try {
            String sql = String.format("SELECT IdKieuSanPham,BarCode,KSP.IdSanPham,Mau,AnhSP,Size,SoLuongTonKho,TenSanPham FROM KieuSanPham KSP INNER JOIN SanPham SP ON KSP.IdSanPham = SP.IdSanPham WHERE KSP.IdSanPham = %d", id);
            SQLServerDataProvider provider = new SQLServerDataProvider();
            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            while (rs.next()) {
                KieuSanPham ksp = new KieuSanPham();
                ksp.setIdKieuSanPham(rs.getInt(1));
                ksp.setBarCode(rs.getInt(2));
                ksp.setIdSanPham(rs.getInt(3));
                ksp.setMau(rs.getString(4));
                ksp.setAnhSP(rs.getString(5));
                ksp.setSize(rs.getString(6));
                ksp.setSoLuongTonKho(rs.getInt(7));
                SanPham sp = new SanPham();
                sp.setIdSanPham(rs.getInt(3));
                sp.setTenSanPham(rs.getString(8));
                ksp.setSanPham(sp);

                dsKieu.add(ksp);
            }
            provider.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsKieu;
    }

    public static KieuSanPham getByBarcode(long barcode) {
        KieuSanPham ksp = null;
        SQLServerDataProvider provider = new SQLServerDataProvider();
        try {
            String sql = String.format("SELECT IdKieuSanPham, BarCode, KSP.IdSanPham, Mau, AnhSP, Size, SoLuongTonKho, TenSanPham, GiaBan, GiamGia FROM KieuSanPham KSP INNER JOIN SanPham SP ON KSP.IdSanPham = SP.IdSanPham WHERE BarCode = %d", barcode);
            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            while (rs.next()) {
                ksp = new KieuSanPham();
                ksp.setIdKieuSanPham(rs.getLong("IdKieuSanPham"));
                ksp.setBarCode(rs.getInt("BarCode"));
                ksp.setIdSanPham(rs.getInt("IdSanPham"));
                ksp.setMau(rs.getString("Mau"));
                ksp.setAnhSP(rs.getString("AnhSP"));
                ksp.setSize(rs.getString("Size"));
                ksp.setSoLuongTonKho(rs.getInt("SoLuongTonKho"));

                SanPham sp = new SanPham();
                sp.setIdSanPham(rs.getInt("IdSanPham"));
                sp.setTenSanPham(rs.getString("TenSanPham"));
                sp.setGiaBan(rs.getLong("GiaBan"));
                sp.setGiamGia(rs.getInt("GiamGia"));
                ksp.setSanPham(sp);
                return ksp;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            provider.close();

        }
        return ksp;
    }

    public static boolean add(KieuSanPham ksp) {
        boolean kq = false;
        String sql = String.format("INSERT INTO KieuSanPham(BarCode, IdSanPham, Mau, AnhSP, Size, SoLuongTonKho) VALUES(%d,%d,N'%s','%s',N'%s',0);", ksp.getBarCode(), ksp.getIdSanPham(), ksp.getMau(), ksp.getAnhSP(), ksp.getSize());
        SQLServerDataProvider provider = new SQLServerDataProvider();
        provider.open();
        int n = provider.executeUpdate(sql);
        if (n == 1) {
            kq = true;
        }
        provider.close();
        return kq;
    }

    public static boolean updateById(KieuSanPham ksp) {
        boolean kq = false;
        String sql = String.format("UPDATE KieuSanPham SET BarCode= %d, IdSanPham = %d, Mau = N'%s', AnhSP='%s', Size=N'%s' WHERE IdKieuSanPham = %d", ksp.getBarCode(), ksp.getIdSanPham(), ksp.getMau(), ksp.getAnhSP(), ksp.getSize(), ksp.getIdKieuSanPham());
        SQLServerDataProvider provider = new SQLServerDataProvider();
        provider.open();
        int n = provider.executeUpdate(sql);
        if (n == 1) {
            kq = true;
        }
        provider.close();
        return kq;
    }

    public static boolean deleteById(long id) {
        boolean success = false;
        String sql = String.format("DELETE FROM KieuSanPham WHERE IdKieuSanPham = %d", id);
        SQLServerDataProvider provider = new SQLServerDataProvider();
        provider.open();
        int n = provider.executeUpdate(sql);
        if (n == 1) {
            success = true;
        }
        provider.close();
        return success;
    }

    public static boolean isKieuSanPhamExists(long id, String mau, String size) {
        SQLServerDataProvider provider = new SQLServerDataProvider();
        try {
            String sqlSelect = String.format("SELECT * FROM KieuSanPham WHERE IdSanPham = %d AND Mau = N'%s'AND Size = '%s'", id, mau, size);
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

    public static boolean isBarcodeExists(int barcode) {
        SQLServerDataProvider provider = new SQLServerDataProvider();
        try {
            String sqlSelect = String.format("SELECT * FROM KieuSanPham WHERE BarCode = %d", barcode);
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

    public static boolean isForeignKeyExists(long id) {
        SQLServerDataProvider provider = new SQLServerDataProvider();
        try {
            String sqlSelect1 = String.format("SELECT * FROM ChiTietHoaDon WHERE IdKieuSanPham = %d", id);
            String sqlSelect2 = String.format("SELECT * FROM ChiTietHoaDonNhapKho WHERE IdKieuSanPham = %d", id);

            provider.open();

            ResultSet rs1 = provider.executeQuery(sqlSelect1);
            if (rs1.next()) {
                return true;
            }

            ResultSet rs2 = provider.executeQuery(sqlSelect2);
            if (rs2.next()) {
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

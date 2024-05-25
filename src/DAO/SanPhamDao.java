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
public class SanPhamDao {

    public static ArrayList<SanPham> getAll() {
        ArrayList<SanPham> dsSanPham = new ArrayList<>();
        SQLServerDataProvider provider = new SQLServerDataProvider();
        try {
            String sql = "SELECT IdSanPham, TenSanPham, LSP.IdLoaiSP, GiaBan, GiamGia, MoTa, NgayThem, TonTai,LSP.TenLoaiSP,IdLoaiSPCha FROM SanPham SP INNER JOIN LoaiSanPham LSP ON SP.IdLoaiSP = LSP.IdLoaiSP ORDER BY IdSanPham";
            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setIdSanPham(rs.getInt(1));
                sp.setTenSanPham(rs.getString(2));
                sp.setIdLoaiSP(rs.getInt(3));
                sp.setGiaBan(rs.getLong(4));
                sp.setGiamGia(rs.getInt(5));
                sp.setMoTa(rs.getString(6));
                sp.setNgayThem(rs.getDate(7));
                sp.setTonTai(rs.getBoolean(8));
                sp.setLoaiSanPham(new LoaiSanPham(rs.getInt(3), rs.getString(9), rs.getInt(10)));
                dsSanPham.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            provider.close();
        }
        return dsSanPham;
    }

    public static ArrayList<SanPham> getByTen(String ten) {
        ArrayList<SanPham> dsSanPham = new ArrayList<>();
        SQLServerDataProvider provider = new SQLServerDataProvider();
        try {
            String sql = String.format("SELECT IdSanPham, TenSanPham, LSP.IdLoaiSP, GiaBan, GiamGia, MoTa, NgayThem, TonTai,LSP.TenLoaiSP,IdLoaiSPCha FROM SanPham SP INNER JOIN LoaiSanPham LSP ON SP.IdLoaiSP = LSP.IdLoaiSP WHERE TenSanPham LIKE N'%%%s%%' ORDER BY IdSanPham", ten);
            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setIdSanPham(rs.getInt(1));
                sp.setTenSanPham(rs.getString(2));
                sp.setIdLoaiSP(rs.getInt(3));
                sp.setGiaBan(rs.getLong(4));
                sp.setGiamGia(rs.getInt(5));
                sp.setMoTa(rs.getString(6));
                sp.setNgayThem(rs.getDate(7));
                sp.setTonTai(rs.getBoolean(8));
                sp.setLoaiSanPham(new LoaiSanPham(rs.getInt(3), rs.getString(9), rs.getInt(10)));
                dsSanPham.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            provider.close();
        }
        return dsSanPham;
    }

    public static ArrayList<SanPham> getByLoaiSanPham(long idLoaiSanPham) {
        ArrayList<SanPham> dsSanPham = new ArrayList<>();
        SQLServerDataProvider provider = new SQLServerDataProvider();
        try {
            String sql = String.format("SELECT IdSanPham, TenSanPham, LSP.IdLoaiSP, GiaBan, GiamGia, MoTa, NgayThem, TonTai,LSP.TenLoaiSP,IdLoaiSPCha FROM SanPham SP INNER JOIN LoaiSanPham LSP ON SP.IdLoaiSP = LSP.IdLoaiSP WHERE LSP.IdLoaiSP = %d ORDER BY IdSanPham", idLoaiSanPham);
            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setIdSanPham(rs.getInt(1));
                sp.setTenSanPham(rs.getString(2));
                sp.setIdLoaiSP(rs.getInt(3));
                sp.setGiaBan(rs.getLong(4));
                sp.setGiamGia(rs.getInt(5));
                sp.setMoTa(rs.getString(6));
                sp.setNgayThem(rs.getDate(7));
                sp.setTonTai(rs.getBoolean(8));
                sp.setLoaiSanPham(new LoaiSanPham(rs.getInt(3), rs.getString(9), rs.getInt(10)));
                dsSanPham.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            provider.close();
        }
        return dsSanPham;
    }

    public static ArrayList<SanPham> getByGia(long giaBatDau, long giaKetThuc) {
        ArrayList<SanPham> dsSanPham = new ArrayList<>();
        SQLServerDataProvider provider = new SQLServerDataProvider();
        try {
            String sql = String.format("SELECT IdSanPham, TenSanPham, LSP.IdLoaiSP, GiaBan, GiamGia, MoTa, NgayThem, TonTai,LSP.TenLoaiSP,IdLoaiSPCha FROM SanPham SP INNER JOIN LoaiSanPham LSP ON SP.IdLoaiSP = LSP.IdLoaiSP WHERE GiaBan >= %d AND GiaBan <= %d ORDER BY IdSanPham", giaBatDau, giaKetThuc);
            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setIdSanPham(rs.getLong(1));
                sp.setTenSanPham(rs.getString(2));
                sp.setIdLoaiSP(rs.getInt(3));
                sp.setGiaBan(rs.getLong(4));
                sp.setGiamGia(rs.getInt(5));
                sp.setMoTa(rs.getString(6));
                sp.setNgayThem(rs.getDate(7));
                sp.setTonTai(rs.getBoolean(8));
                sp.setLoaiSanPham(new LoaiSanPham(rs.getInt(3), rs.getString(9), rs.getInt(10)));
                dsSanPham.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            provider.close();
        }
        return dsSanPham;
    }

    public static boolean add(SanPham sp) {
        boolean kq = false;
        String sql = String.format("INSERT INTO SanPham (TenSanPham, IdLoaiSP, GiaBan, GiamGia, MoTa, NgayThem, TonTai) VALUES (N'%s', %d, %d, %d, N'%s', GETDATE(), %d);", sp.getTenSanPham(), sp.getIdLoaiSP(), sp.getGiaBan(), sp.getGiamGia(), sp.getMoTa(), sp.isTonTai() ? 1 : 0);
        SQLServerDataProvider provider = new SQLServerDataProvider();
        provider.open();
        int n = provider.executeUpdate(sql);
        if (n == 1) {
            kq = true;
        }
        provider.close();
        return kq;
    }

    public static boolean updateById(SanPham sp) {
        boolean kq = false;
        String sql = String.format("UPDATE SanPham SET TenSanPham=N'%s', IdLoaiSP = %d, GiaBan = %d, GiamGia = %d,MoTa =N'%s',TonTai = %d WHERE IdSanPham = %d", sp.getTenSanPham(), sp.getIdLoaiSP(), sp.getGiaBan(), sp.getGiamGia(), sp.getMoTa(), sp.isTonTai() ? 1 : 0, sp.getIdSanPham());
        SQLServerDataProvider provider = new SQLServerDataProvider();
        provider.open();
        int n = provider.executeUpdate(sql);
        if (n == 1) {
            kq = true;
        }
        provider.close();
        return kq;
    }

    public static boolean updateByList(ArrayList<SanPham> lst) {
        boolean result = true;

        for (SanPham sp : lst) {
            if (!updateById(sp)) {
                result = false;
                break;
            }
        }
        return result;
    }

    public static boolean deleteById(long id) {
        boolean success = false;
        String sql = String.format("DELETE FROM SanPham WHERE IdSanPham = %d", id);
        SQLServerDataProvider provider = new SQLServerDataProvider();
        provider.open();
        int n = provider.executeUpdate(sql);
        if (n == 1) {
            success = true;
        }
        provider.close();
        return success;
    }

    public static boolean deleteByList(ArrayList<SanPham> lst) {
        boolean result = true;

        for (SanPham sp : lst) {
            if (!deleteById(sp.getIdSanPham())) {
                result = false;
                break;
            }
        }
        return result;
    }

    public static boolean isTenExists(String ten) {
        SQLServerDataProvider provider = new SQLServerDataProvider();
        try {
            String sqlSelect = String.format("SELECT * FROM SanPham WHERE TenSanPham = N'%s'", ten);
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

    public static boolean isForeignKeyExists(int id) {
        try {
            String sqlSelect = String.format("SELECT * FROM KieuSanPham WHERE IdSanPham = %d", id);
            SQLServerDataProvider provider = new SQLServerDataProvider();
            provider.open();
            ResultSet rs = provider.executeQuery(sqlSelect);
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}

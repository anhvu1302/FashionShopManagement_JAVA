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
 * @author Nah nah
 */
public class LoaiSanPhamDAO {

    public static ArrayList<LoaiSanPham> getAll() {
        ArrayList<LoaiSanPham> dsLoaiSP = new ArrayList<>();
        try {
            String sql = " SELECT IdLoaiSP,TenLoaiSP,LSP.IdLoaiSPCha, TenLoaiSPCha FROM LoaiSanPham LSP INNER JOIN LoaiSanPhamCha LSPC ON LSP.IdLoaiSPCha = LSPC.IdLoaiSPCha ORDER BY IdLoaiSP";
            SQLServerDataProvider provider = new SQLServerDataProvider();
            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            while (rs.next()) {
                LoaiSanPham lsp = new LoaiSanPham();
                lsp.setIdLoaiSP(rs.getInt(1));
                lsp.setTenLoaiSP(rs.getString(2));
                LoaiSanPhamCha lspc = new LoaiSanPhamCha(rs.getInt(3), rs.getString(4));
                lsp.setLoaiSanPhamCha(lspc);
                dsLoaiSP.add(lsp);
            }
            provider.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsLoaiSP;
    }

    public static ArrayList<LoaiSanPham> getByTen(String ten) {
        ArrayList<LoaiSanPham> dsLoaiSP = new ArrayList<>();
        try {
            String sql = String.format("  SELECT IdLoaiSP,TenLoaiSP,LSP.IdLoaiSPCha, TenLoaiSPCha FROM LoaiSanPham LSP INNER JOIN LoaiSanPhamCha LSPC ON LSP.IdLoaiSPCha = LSPC.IdLoaiSPCha WHERE TenLoaiSP LIKE '%%%s%%' ORDER BY IdLoaiSP", ten);
            SQLServerDataProvider provider = new SQLServerDataProvider();
            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            while (rs.next()) {
                LoaiSanPham lsp = new LoaiSanPham();
                lsp.setIdLoaiSP(rs.getInt(1));
                lsp.setTenLoaiSP(rs.getString(2));
                LoaiSanPhamCha lspc = new LoaiSanPhamCha(rs.getInt(3), rs.getString(4));
                lsp.setLoaiSanPhamCha(lspc);
                dsLoaiSP.add(lsp);
            }
            provider.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsLoaiSP;
    }

    public static boolean add(LoaiSanPham lsp) {
        boolean kq = false;
        String sql = String.format("INSERT INTO LoaiSanPham(TenLoaiSP,IdLoaiSPCha) VALUES (N'%s','%d');", lsp.getTenLoaiSP(), lsp.getIdLoaiSPCha());
        SQLServerDataProvider provider = new SQLServerDataProvider();
        provider.open();
        int n = provider.executeUpdate(sql);
        if (n == 1) {
            kq = true;
        }
        provider.close();
        return kq;
    }

    public static boolean updateById(LoaiSanPham lsp) {
        boolean kq = false;
        String sql = String.format("UPDATE LoaiSanPham SET TenLoaiSP=N'%s', IdLoaiSPCha=%d WHERE IdLoaiSP=%d", lsp.getTenLoaiSP(), lsp.getIdLoaiSPCha(), lsp.getIdLoaiSP());
        SQLServerDataProvider provider = new SQLServerDataProvider();
        provider.open();
        int n = provider.executeUpdate(sql);
        if (n == 1) {
            kq = true;
        }
        provider.close();
        return kq;
    }

    public static boolean updateByList(ArrayList<LoaiSanPham> lst) {
        boolean result = true;

        for (LoaiSanPham lsp : lst) {
            if (!updateById(lsp)) {
                result = false;
                break;
            }
        }
        return result;
    }

    public static boolean deleteById(int id) {
        boolean success = false;
        String sql = String.format("DELETE FROM LoaiSanPham WHERE IdLoaiSP = %d", id);
        SQLServerDataProvider provider = new SQLServerDataProvider();
        provider.open();
        int n = provider.executeUpdate(sql);
        if (n == 1) {
            success = true;
        }
        provider.close();
        return success;
    }

    public static boolean deleteByList(ArrayList<LoaiSanPham> lst) {
        boolean result = true;

        for (LoaiSanPham lsp : lst) {
            if (!deleteById(lsp.getIdLoaiSP())) {
                result = false;
                break;
            }
        }
        return result;
    }

    public static boolean isForeignKeyExists(int maLSP) {
        try {
            String sqlSelect = String.format("SELECT * FROM SanPham WHERE IdLoaiSP = %d", maLSP);
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

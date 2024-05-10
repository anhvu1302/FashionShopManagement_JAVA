/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import POJO.LoaiSanPham;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Nah nah
 */
public class LoaiSanPhamDAO {

    public static ArrayList<LoaiSanPham> layDSLoaiSP() {
        ArrayList<LoaiSanPham> dsLoaiSP = new ArrayList<>();
        try {
            String sql = "SELECT A.*, TenLoaiSPCha FROM LoaiSanPham A INNER JOIN LoaiSanPhamCha B ON A.IdLoaiSPCha = B.IdLoaiSPCha";
            SQLServerDataProvider provider = new SQLServerDataProvider();
            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            while (rs.next()) {
                LoaiSanPham lsp = new LoaiSanPham();
                lsp.setIdLoaiSP(rs.getInt(1));
                lsp.setTenLoaiSP(rs.getString(2));
                lsp.setTenLoaiSPCha(rs.getString(4));
                dsLoaiSP.add(lsp);
            }
            provider.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsLoaiSP;
    }

    public static boolean themLoaiSanPham(LoaiSanPham lsp) {
        boolean kq = false;
        String sql = String.format("INSERT INTO LoaiSanPham(TenLoaiSP,IdLoaiSPCha) VALUES ('%s','%d');", lsp.getTenLoaiSP(), lsp.getIdLoaiSPCha());
        SQLServerDataProvider provider = new SQLServerDataProvider();
        provider.open();
        int n = provider.executeUpdate(sql);
        if (n == 1) {
            kq = true;
        }
        provider.close();
        return kq;
    }

    public static int timMaLoaiSanPhamCha(String ten) {
        try {
            String sqlSelect = String.format("Select IdLoaiSPCha from LoaiSanPhamCha where TenLoaiSPCha=N'%s'", ten);
            SQLServerDataProvider provider = new SQLServerDataProvider();
            provider.open();
            ResultSet rs = provider.executeQuery(sqlSelect);
            if (rs.next()) {
                return rs.getInt("IdLoaiSPCha");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static boolean updateLoaiSanPham(LoaiSanPham lsp) {
        boolean kq = false;
        String sql = String.format("UPDATE LoaiSanPham SET TenLoaiSP='%s', IdLoaiSPCha='%d' WHERE IdLoaiSP='%d'", lsp.getTenLoaiSP(), lsp.getIdLoaiSPCha(), lsp.getIdLoaiSP());
        SQLServerDataProvider provider = new SQLServerDataProvider();
        provider.open();
        int n = provider.executeUpdate(sql);
        if (n == 1) {
            kq = true;
        }
        provider.close();
        return kq;
    }

    public static boolean xoaLoaiSanPham(int idLoaiSP) {
        boolean success = false;
        String sql = String.format("DELETE FROM LoaiSanPham WHERE IdLoaiSP = %d", idLoaiSP);
        SQLServerDataProvider provider = new SQLServerDataProvider();
        provider.open();
        int n = provider.executeUpdate(sql);
        if (n == 1) {
            success = true;
        }
        provider.close();
        return success;
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import POJO.LoaiSanPhamCha;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Nah nah
 */
public class LoaiSanPhamChaDAO {

    public static ArrayList<LoaiSanPhamCha> getAll() {
        ArrayList<LoaiSanPhamCha> lst = new ArrayList<>();
        SQLServerDataProvider provider = new SQLServerDataProvider();
        try {
            String sql = "SELECT IdLoaiSPCha, TenLoaiSPCha FROM LoaiSanPhamCha ORDER BY IdLoaiSPCha";
            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            while (rs.next()) {
                LoaiSanPhamCha lspc = new LoaiSanPhamCha();
                lspc.setIdLoaiSPCha(rs.getInt(1));
                lspc.setTenLoaiSPCha(rs.getString(2));
                lst.add(lspc);
            }
            provider.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            provider.close();
        }
        return lst;
    }

    public static ArrayList<LoaiSanPhamCha> getByTen(String ten) {
        ArrayList<LoaiSanPhamCha> lst = new ArrayList<>();
        SQLServerDataProvider provider = new SQLServerDataProvider();
        try {
            String sql = String.format("SELECT IdLoaiSPCha, TenLoaiSPCha FROM LoaiSanPhamCha WHERE TenLoaiSPCha LIKE '%%%s%%' ORDER BY IdLoaiSPCha", ten);
            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            while (rs.next()) {
                LoaiSanPhamCha lspc = new LoaiSanPhamCha();
                lspc.setIdLoaiSPCha(rs.getInt(1));
                lspc.setTenLoaiSPCha(rs.getString(2));
                lst.add(lspc);
            }
            provider.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            provider.close();
        }
        return lst;
    }

    public static boolean checkTenTonTai(String tenLoai) {
        SQLServerDataProvider provider = new SQLServerDataProvider();
        try {
            String sql = String.format("SELECT * FROM LoaiSanPhamCha WHERE TenLoaiSPCha = LOWER(N'%s')", tenLoai);
            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            provider.close();

        }
        return false;
    }

    public static boolean add(LoaiSanPhamCha lspc) {
        boolean kq = false;
        String sql = String.format("Insert into LoaiSanPhamCha(TenLoaiSPCha) values(N'%s');", lspc.getTenLoaiSPCha());
        SQLServerDataProvider provider = new SQLServerDataProvider();
        provider.open();
        int n = provider.executeUpdate(sql);
        if (n == 1) {
            kq = true;
        }
        provider.close();
        return kq;
    }

    public static boolean updateById(LoaiSanPhamCha lspc) {
        boolean kq = false;
        String sql = String.format("update LoaiSanPhamCha set TenLoaiSPCha= N'%s' where IdLoaiSPCha =%d", lspc.getTenLoaiSPCha(), lspc.getIdLoaiSPCha());
        SQLServerDataProvider provider = new SQLServerDataProvider();
        provider.open();
        int n = provider.executeUpdate(sql);
        if (n == 1) {
            kq = true;
        }
        provider.close();
        return kq;
    }

    public static boolean updateByList(ArrayList<LoaiSanPhamCha> lst) {
        boolean result = true;

        for (LoaiSanPhamCha lspc : lst) {
            if (!updateById(lspc)) {
                result = false;
                break;
            }
        }
        return result;
    }

    public static boolean deleteById(int id) {
        boolean kq = false;
        String sql = String.format("Delete From LoaiSanPhamCha Where IdLoaiSPCha=%s", id);
        SQLServerDataProvider provider = new SQLServerDataProvider();
        provider.open();
        int n = provider.executeUpdate(sql);
        if (n == 1) {
            kq = true;
        }
        provider.close();
        return kq;
    }

    public static boolean deleteByList(ArrayList<LoaiSanPhamCha> lst) {
        boolean result = true;

        for (LoaiSanPhamCha lspc : lst) {
            if (!deleteById(lspc.getIdLoaiSPCha())) {
                result = false;
                break;
            }
        }
        return result;
    }

    public static boolean isForeignKeyExists(int maLSPCha) {
        try {
            String sqlSelect = String.format("SELECT * FROM LoaiSanPham WHERE IdLoaiSPCha = %d", maLSPCha);
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

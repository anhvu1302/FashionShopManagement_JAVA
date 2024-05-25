/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import POJO.KhachHang;
import java.util.ArrayList;
import java.sql.ResultSet;

public class KhachHangDAO {

    public static ArrayList<KhachHang> getAll() {
        ArrayList<KhachHang> dsKH = new ArrayList<KhachHang>();
        try {
            String sql = "SELECT * FROM KhachHang";
            SQLServerDataProvider provider = new SQLServerDataProvider();
            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setIdKhachHang(rs.getLong(1));
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

    public static KhachHang getBySDT(String sdt) {
        KhachHang kh = null;
        SQLServerDataProvider provider = new SQLServerDataProvider();
        try {
            String sql = String.format("SELECT IdKhachHang,TenKhachHang,GioiTinh,SoDienThoai,Email,Diem FROM KhachHang WHERE SoDienThoai = '%s' ", sdt);
            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            while (rs.next()) {
                kh = new KhachHang();
                kh.setIdKhachHang(rs.getLong("IdKhachHang"));
                kh.setTenKhachHang(rs.getString("TenKhachHang"));
                kh.setGioiTinh(rs.getString("GioiTinh"));
                kh.setSoDienThoai(rs.getString("SoDienThoai"));
                kh.setEmail(rs.getString("Email"));
                kh.setDiem(rs.getLong("Diem"));
            }
            provider.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            provider.close();
        }
        return kh;
    }

    public static boolean add(KhachHang kh) {
        boolean kq = false;
        String sql = String.format("INSERT INTO KhachHang(TenKhachHang, GioiTinh, SoDienThoai, Email, Diem) "
                + "VALUES (N'%s', N'%s', '%s', '%s', %d)", kh.getTenKhachHang(), kh.getGioiTinh(), kh.getSoDienThoai(), kh.getEmail(), kh.getDiem());
        SQLServerDataProvider provider = new SQLServerDataProvider();
        provider.open();
        int n = provider.executeUpdate(sql);
        if (n == 1) {
            kq = true;
        }
        provider.close();
        return kq;
    }

    public static boolean checkTrungMailorSDT(String mail, String sdt) {
        SQLServerDataProvider provider = new SQLServerDataProvider();
        try {
            String sql = String.format("SELECT * FROM KhachHang WHERE Email = '%s' or SoDienThoai ='%s'", mail, sdt);
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

    public static boolean deleteById(long id) {
        boolean kq = false;
        String sql = String.format("Delete From KhachHang Where IdKhachHang=%d", id);
        SQLServerDataProvider provider = new SQLServerDataProvider();
        provider.open();
        int n = provider.executeUpdate(sql);
        if (n == 1) {
            kq = true;
        }
        provider.close();
        return kq;
    }

    public static boolean deleteByList(ArrayList<KhachHang> lst) {
        boolean result = true;

        for (KhachHang kh : lst) {
            if (!deleteById(kh.getIdKhachHang())) {
                result = false;
                break;
            }
        }
        return result;
    }

    public static boolean updateById(KhachHang kh) {
        boolean kq = false;
        String sql = String.format("update KhachHang set TenKhachHang= N'%s',GioiTinh=N'%s',SoDienThoai='%s',Email='%s',Diem='%d' where IdKhachHang =%d", kh.getTenKhachHang(), kh.getGioiTinh(), kh.getSoDienThoai(), kh.getEmail(), kh.getDiem(), kh.getIdKhachHang());
        SQLServerDataProvider provider = new SQLServerDataProvider();
        provider.open();
        int n = provider.executeUpdate(sql);
        if (n == 1) {
            kq = true;
        }
        provider.close();
        return kq;
    }

    public static boolean updateByList(ArrayList<KhachHang> lst) {
        boolean result = true;

        for (KhachHang kh : lst) {
            if (!updateById(kh)) {
                result = false;
                break;
            }
        }
        return result;
    }

    public static boolean isForeignKeyExists(long ma) {
        try {
            String sqlSelect = String.format("SELECT * FROM HoaDon WHERE IdKhachHang = %d", ma);
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

    public static boolean updateDiem(long id, long diem) {
        String sqlUpdate = String.format("UPDATE KhachHang SET Diem = Diem + %d WHERE IdKhachHang = %d;", diem, id);
        try {
            SQLServerDataProvider provider = new SQLServerDataProvider();
            provider.open();
            int rowsAffected = provider.executeUpdate(sqlUpdate);
            provider.close();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}

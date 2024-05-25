/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import POJO.HoaDonNhapKho;
import POJO.NhanVien;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class HoaDonNhapKhoDAO {
    public static ArrayList<HoaDonNhapKho> getAll() {
        ArrayList<HoaDonNhapKho> dsHDNK = new ArrayList<HoaDonNhapKho>();
        SQLServerDataProvider provider = new SQLServerDataProvider();
        try {
            String sql = "Select IdHoaDonNhapKho,NhanVien.IdNhanVien,NhanVien.TenNhanVien, TongTien, NgayNhap from HoaDonNhapKho\n" +
                            "inner join NhanVien on NhanVien.IdNhanVien=HoaDonNhapKho.IdNhanVien;";

            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            while (rs.next()) {
                HoaDonNhapKho hd = new HoaDonNhapKho();
                hd.setIdHoaDonNhapKho(rs.getLong(1));
                hd.setIdNhanVien(rs.getLong(2));
                NhanVien nv= new NhanVien(rs.getLong(2), rs.getString(3));
                hd.setNhanvien(nv);
                hd.setTongTien(rs.getLong(4));
//                java.sql.Date ngayNhap = rs.getDate(4);
//                if (ngayNhap != null) {
//                    hd.setNgayNhap(new Date(ngayNhap.getTime()));
//                }
                hd.setNgayNhap(rs.getDate(5));
                dsHDNK.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsHDNK;
    }

    public static boolean add(HoaDonNhapKho hdnk) {
        boolean kq = false;
        SQLServerDataProvider provider = new SQLServerDataProvider();
        try {
            String sql = String.format("INSERT INTO HoaDonNhapKho(IdNhanVien,NgayNhap,TongTien) VALUES(%d,GETDATE(),0)", hdnk.getIdNhanVien());
            provider.open();
            int n = provider.executeUpdate(sql);
            if (n == 1) {
                kq = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            provider.close();
        }
        return kq;
    }

    public static boolean isForeignKeyExistsCTHDNhapKho(long ma) {
        try {
            String sqlSelect = String.format("SELECT IdHoaDonNhapKho FROM ChiTietHoaDonNhapKho WHERE IdHoaDonNhapKho = %d", ma);
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

    public static boolean deleteById(long id) {
        boolean kq = false;
        String sql = String.format("Delete From HoaDonNhapKho Where IdHoaDonNhapKho=%d", id);
        SQLServerDataProvider provider = new SQLServerDataProvider();
        provider.open();
        int n = provider.executeUpdate(sql);
        if (n == 1) {
            kq = true;
        }
        provider.close();
        return kq;
    }

    public static boolean updateById(HoaDonNhapKho hd) {
        boolean kq = false;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String ngayNhapFormatted = dateFormat.format(hd.getNgayNhap());
        String sql = String.format("update HoaDonNhapKho set NgayNhap= '%s',IdNhanVien='%d' where IdHoaDonNhapKho ='%d'", ngayNhapFormatted, hd.getIdNhanVien(), hd.getIdHoaDonNhapKho());

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

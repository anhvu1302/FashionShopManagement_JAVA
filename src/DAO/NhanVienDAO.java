/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import POJO.NhanVien;
import Utils.PasswordHashing;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Nah nah
 */
public class NhanVienDAO {

    public static ArrayList<NhanVien> layDSTaiKhoan() {
        ArrayList<NhanVien> dsTK = new ArrayList<NhanVien>();
        try {
            String sql = "SELECT TenTaiKhoan,MatKhau FROM NhanVien";
            SQLServerDataProvider provider = new SQLServerDataProvider();
            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setTenNhanVien(rs.getString(1));
                nv.setMatKhau(rs.getString(2));
                dsTK.add(nv);
            }
            provider.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsTK;
    }

    public static NhanVien getNhanVienByTenTk(String tenTK) {
        NhanVien nv = null;
        SQLServerDataProvider provider = new SQLServerDataProvider();
        try {
            String sqlSelect = String.format("SELECT IdNhanVien, TenTaiKhoan, MatKhau, IdVaiTro, TenNhanVien, NgaySinh, GioiTinh, DiaChi, SoDienThoai, Email, TonTai, Cam FROM NhanVien WHERE TenTaiKhoan = '%s';", tenTK);
            provider.open();
            ResultSet rs = provider.executeQuery(sqlSelect);
            if (rs.next()) {
                nv = new NhanVien();
                nv.setIdNhanVien(rs.getInt("IdNhanVien"));
                nv.setTenTaiKhoan(rs.getString("TenTaiKhoan"));
                nv.setMatKhau(rs.getString("MatKhau"));
                nv.setIdVaiTro(rs.getInt("IdVaiTro"));
                nv.setTenNhanVien(rs.getString("TenNhanVien"));
                nv.setNgaySinh(rs.getDate("NgaySinh"));
                nv.setGioiTinh(rs.getString("GioiTinh"));
                nv.setDiaChi(rs.getString("DiaChi"));
                nv.setSoDienThoai(rs.getString("SoDienThoai"));
                nv.setEmail(rs.getString("Email"));
                nv.setTonTai(rs.getBoolean("TonTai"));
                nv.setCam(rs.getBoolean("Cam"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            provider.close();
        }
        return nv;
    }

}

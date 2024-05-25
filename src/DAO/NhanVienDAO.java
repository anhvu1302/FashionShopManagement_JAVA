/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import POJO.NhanVien;
import POJO.VaiTro;
import static java.nio.channels.spi.SelectorProvider.provider;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
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
            String sqlSelect = String.format("SELECT IdNhanVien, TenTaiKhoan, MatKhau, NV.IdVaiTro, TenNhanVien, NgaySinh, GioiTinh, DiaChi, SoDienThoai, Email, TonTai, Cam,TenVaiTro "
                    + "FROM NhanVien NV "
                    + "INNER JOIN VaiTro VT ON NV.IdVaiTro = VT.IdVaiTro "
                    + "WHERE TenTaiKhoan = '%s';", tenTK);
            provider.open();
            ResultSet rs = provider.executeQuery(sqlSelect);
            while (rs.next()) {
                nv = new NhanVien();
                nv.setIdNhanVien(rs.getLong("IdNhanVien"));
                nv.setTenTaiKhoan(rs.getString("TenTaiKhoan"));
                nv.setMatKhau(rs.getString("MatKhau"));
                nv.setIdVaiTro(rs.getInt("IdVaiTro"));
                VaiTro vt = new VaiTro(rs.getInt("IdVaiTro"), rs.getString("TenVaiTro"));
                nv.setVaitro(vt);
                nv.setTenNhanVien(rs.getString("TenNhanVien"));
                nv.setNgaySinh(rs.getString("NgaySinh"));
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

    public static ArrayList<NhanVien> getAll() {
        ArrayList<NhanVien> dsNV = new ArrayList<NhanVien>();
        SQLServerDataProvider provider = new SQLServerDataProvider();
        ResultSet rs = null;
        try {
            String sqlSelect = "SELECT IdNhanVien, TenTaiKhoan, MatKhau, nv.IdVaiTro, vt.TenVaiTro, TenNhanVien, NgaySinh, GioiTinh, DiaChi, SoDienThoai, Email, TonTai, Cam "
                    + "FROM NhanVien nv "
                    + "INNER JOIN VaiTro vt ON nv.IdVaiTro = vt.IdVaiTro ORDER BY IdNhanVien";

            provider.open();
            rs = provider.executeQuery(sqlSelect);
            int dem = 0;
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setIdNhanVien(rs.getLong(1));
                nv.setTenTaiKhoan(rs.getString(2));
                nv.setMatKhau(rs.getString(3));
                nv.setIdVaiTro(rs.getInt(4));
                VaiTro vt = new VaiTro(rs.getInt(4), rs.getString(5));
                nv.setVaitro(vt);
                nv.setTenNhanVien(rs.getString(6));
                nv.setNgaySinh(rs.getString(7));
                nv.setGioiTinh(rs.getString(8));
                nv.setDiaChi(rs.getString(9));
                nv.setSoDienThoai(rs.getString(10));
                nv.setEmail(rs.getString(11));
                nv.setTonTai(rs.getBoolean(12));
                nv.setCam(rs.getBoolean(13));

                dsNV.add(nv);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            provider.close();
        }
        return dsNV;
    }

    public static ArrayList<NhanVien> findByTenTk(String tenTK) {
        ArrayList<NhanVien> dsNV = new ArrayList<NhanVien>();
        SQLServerDataProvider provider = new SQLServerDataProvider();
        try {
            String sqlSelect = String.format("SELECT IdNhanVien, TenTaiKhoan, MatKhau, IdVaiTro, TenNhanVien, NgaySinh, GioiTinh, DiaChi, SoDienThoai, Email, TonTai, Cam FROM NhanVien WHERE TenTaiKhoan = '%s';", tenTK);
            provider.open();
            ResultSet rs = provider.executeQuery(sqlSelect);
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setIdNhanVien(rs.getLong("IdNhanVien"));
                nv.setTenTaiKhoan(rs.getString("TenTaiKhoan"));
                nv.setMatKhau(rs.getString("MatKhau"));
                nv.setIdVaiTro(rs.getInt("IdVaiTro"));
                nv.setTenNhanVien(rs.getString("TenNhanVien"));
                nv.setNgaySinh(rs.getString("NgaySinh"));
                nv.setGioiTinh(rs.getString("GioiTinh"));
                nv.setDiaChi(rs.getString("DiaChi"));
                nv.setSoDienThoai(rs.getString("SoDienThoai"));
                nv.setEmail(rs.getString("Email"));
                nv.setTonTai(rs.getBoolean("TonTai"));
                nv.setCam(rs.getBoolean("Cam"));
                dsNV.add(nv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            provider.close();
        }
        return dsNV;
    }

    public static ArrayList<NhanVien> findBySdt(String sdt) {
        ArrayList<NhanVien> dsNV = new ArrayList<NhanVien>();
        SQLServerDataProvider provider = new SQLServerDataProvider();
        try {
            String sqlSelect = String.format("SELECT * FROM NhanVien WHERE SoDienThoai = '%s';", sdt);
            provider.open();
            ResultSet rs = provider.executeQuery(sqlSelect);
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setIdNhanVien(rs.getLong("IdNhanVien"));
                nv.setTenTaiKhoan(rs.getString("TenTaiKhoan"));
                nv.setMatKhau(rs.getString("MatKhau"));
                nv.setIdVaiTro(rs.getInt("IdVaiTro"));
                nv.setTenNhanVien(rs.getString("TenNhanVien"));
                nv.setNgaySinh(rs.getString("NgaySinh"));
                nv.setGioiTinh(rs.getString("GioiTinh"));
                nv.setDiaChi(rs.getString("DiaChi"));
                nv.setSoDienThoai(rs.getString("SoDienThoai"));
                nv.setEmail(rs.getString("Email"));
                nv.setTonTai(rs.getBoolean("TonTai"));
                nv.setCam(rs.getBoolean("Cam"));
                dsNV.add(nv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            provider.close();
        }
        return dsNV;
    }

    public static ArrayList<NhanVien> searchNhanVien(String searchValue) {
        ArrayList<NhanVien> dsNV = new ArrayList<NhanVien>();
        SQLServerDataProvider provider = new SQLServerDataProvider();
        try {
            // Xử lý giá trị searchValue để tránh SQL Injection
            searchValue = searchValue.replace("'", "''");

            // Tạo câu lệnh SQL với giá trị tìm kiếm
            String sqlSelect = String.format(
                    "SELECT IdNhanVien, TenTaiKhoan, MatKhau, nv.IdVaiTro, vt.TenVaiTro, TenNhanVien, NgaySinh, GioiTinh, DiaChi, SoDienThoai, Email, TonTai, Cam "
                    + "FROM NhanVien nv "
                    + "INNER JOIN VaiTro vt ON nv.IdVaiTro = vt.IdVaiTro "
                    + "WHERE TenNhanVien LIKE N'%%%s%%' OR TenTaiKhoan = '%s' OR SoDienThoai = '%s' "
                    + "ORDER BY IdNhanVien",
                    searchValue, searchValue, searchValue
            );
            provider.open();
            ResultSet rs = provider.executeQuery(sqlSelect);
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setIdNhanVien(rs.getLong(1));
                nv.setTenTaiKhoan(rs.getString(2));
                nv.setMatKhau(rs.getString(3));
                //nv.setIdVaiTro(rs.getInt(4));
                VaiTro vt = new VaiTro(rs.getInt(4), rs.getString(5));
                nv.setVaitro(vt);
                nv.setTenNhanVien(rs.getString(6));
                nv.setNgaySinh(rs.getString(7));
                nv.setGioiTinh(rs.getString(8));
                nv.setDiaChi(rs.getString(9));
                nv.setSoDienThoai(rs.getString(10));
                nv.setEmail(rs.getString(11));
                nv.setTonTai(rs.getBoolean(12));
                nv.setCam(rs.getBoolean(13));
                dsNV.add(nv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            provider.close();
        }
        return dsNV;
    }

    public static boolean add(NhanVien nv) {
        boolean kq = false;
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        String ngaySinhFormatted = dateFormat.format(nv.getNgaySinh());

        String sql = String.format(
                "INSERT INTO NhanVien (TenTaiKhoan, MatKhau, IdVaiTro, TenNhanVien, NgaySinh, GioiTinh, DiaChi, SoDienThoai, Email, TonTai, Cam) "
                + "VALUES ('%s', '%s', %d, N'%s', '%s', N'%s', N'%s', '%s', '%s', %d, %d);",
                nv.getTenTaiKhoan(), nv.getMatKhau(), nv.getIdVaiTro(), nv.getTenNhanVien(),
                nv.getNgaySinh(), nv.getGioiTinh(), nv.getDiaChi(), nv.getSoDienThoai(), nv.getEmail(),
                nv.isTonTai() ? 1 : 0, nv.isCam() ? 1 : 0
        );
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
        boolean kq = false;
        String sql = String.format("Delete From NhanVien Where IdNhanVien=%d", id);
        SQLServerDataProvider provider = new SQLServerDataProvider();
        provider.open();
        int n = provider.executeUpdate(sql);
        if (n == 1) {
            kq = true;
        }
        provider.close();
        return kq;
    }

    public static boolean isForeignKeyExistsHD(long ma) {
        try {
            String sqlSelect = String.format("SELECT IdNhanVien FROM HoaDon WHERE IdNhanVien = %d", ma);
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

    public static boolean isForeignKeyExistsHDNK(long ma) {
        try {
            String sqlSelect = String.format("SELECT IdNhanVien FROM HoaDonNhapKho WHERE IdNhanVien = %d", ma);
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

    public static boolean updateById(NhanVien nv) {
        boolean kq = false;
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        String ngaySinhFormatted = dateFormat.format(nv.getNgaySinh());
        String sql = String.format("update NhanVien set TenTaiKhoan= '%s',MatKhau= '%s',IdVaiTro=%d,TenNhanVien=N'%s',NgaySinh='%s',GioiTinh=N'%s',DiaChi=N'%s',SoDienThoai='%s',Email='%s',TonTai='%d',Cam='%d' where IdNhanVien =%d", nv.getTenTaiKhoan(), nv.getMatKhau(), nv.getIdVaiTro(), nv.getTenNhanVien(), nv.getNgaySinh(), nv.getGioiTinh(), nv.getDiaChi(), nv.getSoDienThoai(), nv.getEmail(), nv.isTonTai() ? 1 : 0, nv.isCam() ? 1 : 0, nv.getIdNhanVien());

        SQLServerDataProvider provider = new SQLServerDataProvider();
        provider.open();
        int n = provider.executeUpdate(sql);
        if (n == 1) {
            kq = true;
        }
        provider.close();
        return kq;
    }

    public static boolean isExistsForAdd(String mail, String sdt, String tentk) {
        try {
            String sqlSelect = String.format("SELECT * FROM NhanVien WHERE Email = '%s' or SoDienThoai ='%s' OR TenTaiKhoan='%s'", mail, sdt, tentk);
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

    public static boolean isExistsForUpdate(String mail, String sdt, String tentk, int currentEmployeeId) {
        try {
            String sqlSelect = String.format("SELECT * FROM NhanVien WHERE (Email = '%s' OR SoDienThoai = '%s' OR TenTaiKhoan = '%s') AND IdNhanVien != %d", mail, sdt, tentk, currentEmployeeId);
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

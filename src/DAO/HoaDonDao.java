/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import POJO.ChiTietHoaDon;
import POJO.HoaDon;
import POJO.KhachHang;
import POJO.NhanVien;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author VanAnh
 */
public class HoaDonDao {

    public static boolean add(HoaDon hd) {
        boolean kq = false;
        String sql = String.format(
                "INSERT INTO HoaDon (IdNhanVien, IdKhachHang, TongTien,DiemSuDung, PhuongThucThanhToan, NgayXuatHD) VALUES (%d, %s, %d,%d, N'%s', GETDATE());",
                hd.getIdNhanVien(),
                hd.getIdKhachHang() == -1 ? "null" : String.valueOf(hd.getIdKhachHang()),
                hd.getTongTien(),
                hd.getDiemSuDung(),
                hd.getPhuongThucThanhToan()
        );
        SQLServerDataProvider provider = new SQLServerDataProvider();
        try {
            provider.open();
            provider.startTransaction();

            int n = provider.executeUpdate(sql);
            if (n == 1) {
                if (hd.getIdKhachHang() != -1) {
                    if (hd.getDiemSuDung() > 0) {
                        KhachHangDAO.updateDiem(hd.getIdKhachHang(), -hd.getDiemSuDung());
                    }
                    KhachHangDAO.updateDiem(hd.getIdKhachHang(), (long) (hd.getTongTien() * 0.01));
                }
                String getIdSql = "SELECT SCOPE_IDENTITY()";
                ResultSet rs = provider.executeQuery(getIdSql);
                int idHoaDon = -1;
                if (rs.next()) {
                    idHoaDon = rs.getInt(1);
                }
                rs.close();

                // Insert ChiTietHoaDon for each HoaDon
                for (ChiTietHoaDon cthd : hd.getChiTietHoaDons()) {
                    String sql2 = String.format(
                            "INSERT INTO ChiTietHoaDon (IdHoaDon, IdKieuSanPham, SoLuong, DonGia) VALUES (%d, %d, %d, %d)",
                            idHoaDon, cthd.getIdKieuSanPham(), cthd.getSoLuong(), cthd.getDonGia()
                    );
                    provider.executeUpdate(sql2);
                }

                provider.commitTransaction();
                kq = true;
            } else {
                provider.rollbackTransaction();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            provider.rollbackTransaction();
        } finally {
            provider.close();
        }

        return kq;
    }

    public static ArrayList<HoaDon> getAll() {
        ArrayList<HoaDon> dsHD = new ArrayList<HoaDon>();
        SQLServerDataProvider provider = new SQLServerDataProvider();
        try {
            String sql = "select hd.IdHoaDon, hd.IdNhanVien, nv.TenNhanVien, "
                    + "hd.IdKhachHang, kh.TenKhachHang,hd.TongTien, hd.DiemSuDung, "
                    + "hd.PhuongThucThanhToan, hd.NgayXuatHD "
                    + "from HoaDon hd "
                    + "inner join NhanVien nv on nv.IdNhanVien = hd.IdNhanVien "
                    + "inner join KhachHang kh on kh.IdKhachHang = hd.IdKhachHang";
            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setIdHoaDon(rs.getLong("IdHoaDon"));
                hd.setIdNhanVien(rs.getLong("IdNhanVien"));
                NhanVien nv = new NhanVien();
                nv.setIdNhanVien(rs.getLong("IdNhanVien"));
                nv.setTenNhanVien(rs.getString("TenNhanVien"));
                hd.setNhanvien(nv);
                hd.setIdKhachHang(rs.getLong("IdKhachHang"));
                KhachHang kh = new KhachHang();
                kh.setIdKhachHang(rs.getLong("IdKhachHang"));
                kh.setTenKhachHang(rs.getString("TenKhachHang"));
                hd.setKh(kh);
                hd.setTongTien(rs.getLong("TongTien"));
                hd.setDiemSuDung(rs.getLong("DiemSuDung"));
                hd.setPhuongThucThanhToan(rs.getString("PhuongThucThanhToan"));
                hd.setNgayXuatHD(rs.getDate("NgayXuatHD"));
                dsHD.add(hd);
            }
            rs.close();
            provider.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsHD;
    }
}

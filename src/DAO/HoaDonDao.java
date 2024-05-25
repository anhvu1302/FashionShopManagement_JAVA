/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import POJO.ChiTietHoaDon;
import POJO.HoaDon;
import java.sql.ResultSet;
import java.sql.SQLException;

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
                    KhachHangDAO.updateDiem(hd.getIdKhachHang(),  (long) (hd.getTongTien() * 0.01));
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

}

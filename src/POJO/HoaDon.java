/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POJO;

import java.util.ArrayList;
import java.util.Date;

public class HoaDon {

    private long idHoaDon;
    private long idNhanVien;
    private long idKhachHang;
    private String phuongThucThanhToan;
    private long tongTien;
    private long diemSuDung;
    private Date ngayXuatHD;
    private ArrayList<ChiTietHoaDon> chiTietHoaDons;

    public HoaDon(long idHoaDon, long idNhanVien, long idKhachHang, String phuongThucThanhToan, long tongTien, long diemSuDung, Date ngayXuatHD) {
        this.idHoaDon = idHoaDon;
        this.idNhanVien = idNhanVien;
        this.idKhachHang = idKhachHang;
        this.phuongThucThanhToan = phuongThucThanhToan;
        this.tongTien = tongTien;
        this.diemSuDung = diemSuDung;
        this.ngayXuatHD = ngayXuatHD;
    }

    public HoaDon() {
    }

    public long getIdHoaDon() {
        return idHoaDon;
    }

    public void setIdHoaDon(long idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

    public long getIdNhanVien() {
        return idNhanVien;
    }

    public void setIdNhanVien(long idNhanVien) {
        this.idNhanVien = idNhanVien;
    }

    public long getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(long idKhachHang) {
        this.idKhachHang = idKhachHang;
    }

    public String getPhuongThucThanhToan() {
        return phuongThucThanhToan;
    }

    public void setPhuongThucThanhToan(String phuongThucThanhToan) {
        this.phuongThucThanhToan = phuongThucThanhToan;
    }

    public long getTongTien() {
        return tongTien;
    }

    public long getDiemSuDung() {
        return diemSuDung;
    }

    public void setDiemSuDung(long diemSuDung) {
        this.diemSuDung = diemSuDung;
    }

    public void setTongTien(long tongTien) {
        this.tongTien = tongTien;
    }

    public Date getNgayXuatHD() {
        return ngayXuatHD;
    }

    public void setNgayXuatHD(Date ngayXuatHD) {
        this.ngayXuatHD = ngayXuatHD;
    }

    public ArrayList<ChiTietHoaDon> getChiTietHoaDons() {
        return chiTietHoaDons;
    }

    public void setChiTietHoaDons(ArrayList<ChiTietHoaDon> chiTietHoaDons) {
        this.chiTietHoaDons = chiTietHoaDons;
    }

    public void addCTHD(ChiTietHoaDon cthd) {
        if (this.chiTietHoaDons == null) {
            this.chiTietHoaDons = new ArrayList<>();
        }

        boolean found = false;
        for (ChiTietHoaDon existingCTHD : this.chiTietHoaDons) {
            if (existingCTHD.getIdKieuSanPham() == cthd.getIdKieuSanPham()) {
                existingCTHD.setSoLuong(existingCTHD.getSoLuong() + cthd.getSoLuong());
                found = true;
                break;
            }
        }

        if (!found) {
            this.chiTietHoaDons.add(cthd);
        }
    }

    public void removeCTHD(ChiTietHoaDon cthd) {
        this.chiTietHoaDons.remove(cthd);
    }

    public long tinhTongTien() {
        long tongTien = 0;
        if (chiTietHoaDons != null) {
            for (ChiTietHoaDon cthd : chiTietHoaDons) {
                tongTien += cthd.getSoLuong() * cthd.getDonGia();
            }
        }
        return tongTien;
    }
}

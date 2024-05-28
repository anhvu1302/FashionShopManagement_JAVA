/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POJO;

public class ChiTietHoaDonNhapKho {

    private long idHoaDonNhapKho;
    private long idKieuSanPham;
    private int soLuong;
    private long donGia;
    private KieuSanPham kieuSanPham;

    public ChiTietHoaDonNhapKho() {
    }

    public ChiTietHoaDonNhapKho(long idHoaDonNhapKho, long idKieuSanPham, int soLuong, long donGia, KieuSanPham kieuSanPham) {
        this.idHoaDonNhapKho = idHoaDonNhapKho;
        this.idKieuSanPham = idKieuSanPham;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.kieuSanPham = kieuSanPham;
    }

    public long getIdHoaDonNhapKho() {
        return idHoaDonNhapKho;
    }

    public void setIdHoaDonNhapKho(long idHoaDonNhapKho) {
        this.idHoaDonNhapKho = idHoaDonNhapKho;
    }

    public long getIdKieuSanPham() {
        return idKieuSanPham;
    }

    public void setIdKieuSanPham(long idKieuSanPham) {
        this.idKieuSanPham = idKieuSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public long getDonGia() {
        return donGia;
    }

    public void setDonGia(long donGia) {
        this.donGia = donGia;
    }

    public KieuSanPham getKieuSanPham() {
        return kieuSanPham;
    }

    public void setKieuSanPham(KieuSanPham kieuSanPham) {
        this.kieuSanPham = kieuSanPham;
    }

}

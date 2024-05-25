/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POJO;

public class ChiTietHoaDon {

    private int idHoaDon;
    private long idKieuSanPham;
    private int soLuong;
    private long donGia;
    private KieuSanPham kieuSanPham;

    public ChiTietHoaDon() {
    }

    public ChiTietHoaDon(ChiTietHoaDon cthd) {
        this.idHoaDon = cthd.idHoaDon;
        this.idKieuSanPham = cthd.idKieuSanPham;
        this.soLuong = cthd.soLuong;
        this.donGia = cthd.donGia;
        this.kieuSanPham = cthd.kieuSanPham;
    }

    public ChiTietHoaDon(int idHoaDon, long idKieuSanPham, int soLuong, long donGia, KieuSanPham kieuSanPham) {
        this.idHoaDon = idHoaDon;
        this.idKieuSanPham = idKieuSanPham;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.kieuSanPham = kieuSanPham;
    }

    public int getIdHoaDon() {
        return idHoaDon;
    }

    public void setIdHoaDon(int idHoaDon) {
        this.idHoaDon = idHoaDon;
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

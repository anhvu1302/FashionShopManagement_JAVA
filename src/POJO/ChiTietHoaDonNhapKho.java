/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POJO;

public class ChiTietHoaDonNhapKho {

    private int idHoaDonNhapKho;
    private int idKieuSanPham;
    private int soLuong;
    private long donGia;

    public ChiTietHoaDonNhapKho() {
    }

    public ChiTietHoaDonNhapKho(int idHoaDonNhapKho, int idKieuSanPham, int soLuong, long donGia) {
        this.idHoaDonNhapKho = idHoaDonNhapKho;
        this.idKieuSanPham = idKieuSanPham;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public int getIdHoaDonNhapKho() {
        return idHoaDonNhapKho;
    }

    public void setIdHoaDonNhapKho(int idHoaDonNhapKho) {
        this.idHoaDonNhapKho = idHoaDonNhapKho;
    }

    public int getIdKieuSanPham() {
        return idKieuSanPham;
    }

    public void setIdKieuSanPham(int idKieuSanPham) {
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

}

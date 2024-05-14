/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POJO;


public class ChiTietHoaDon {
    private int idHoaDon;
    private int idKieuSanPham;
    private int soLuong;
    private long donGia;

    public ChiTietHoaDon() {
    }

    public ChiTietHoaDon(int idHoaDon, int idKieuSanPham, int soLuong, long donGia) {
        this.idHoaDon = idHoaDon;
        this.idKieuSanPham = idKieuSanPham;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public int getIdHoaDon() {
        return idHoaDon;
    }

    public void setIdHoaDon(int idHoaDon) {
        this.idHoaDon = idHoaDon;
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

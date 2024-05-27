/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POJO;

/**
 *
 * @author VanAnh
 */
public class ThongTinHoaDon {

    private String tenSanPham;
    private String mau;
    private String size;
    private long giaBan;
    private int giamGia;
    private int soLuong;
    private long thanhTien;

    public ThongTinHoaDon() {
    }

    public ThongTinHoaDon(String tenSanPham, String mau, String size, long giaBan, int giamGia, int soLuong, long thanhTien) {
        this.tenSanPham = tenSanPham;
        this.mau = mau;
        this.size = size;
        this.giaBan = giaBan;
        this.giamGia = giamGia;
        this.soLuong = soLuong;
        this.thanhTien = thanhTien;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getMau() {
        return mau;
    }

    public void setMau(String mau) {
        this.mau = mau;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public long getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(long giaBan) {
        this.giaBan = giaBan;
    }

    public int getGiamGia() {
        return giamGia;
    }

    public void setGiamGia(int giamGia) {
        this.giamGia = giamGia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public long getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(long thanhTien) {
        this.thanhTien = thanhTien;
    }

}

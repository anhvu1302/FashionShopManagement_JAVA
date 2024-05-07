/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POJO;

import java.util.Date;

/**
 *
 * @author Nah nah
 */
public class DanhGiaSanPham {
    private int idDanhGia;
    private int idSanPham;
    private int idKhachHang;
    private int diemDanhGia;
    private String nhanXet;
    private Date thoiGianDanhGia;

    public DanhGiaSanPham() {
    }

    public DanhGiaSanPham(int idDanhGia, int idSanPham, int idKhachHang, int diemDanhGia, String nhanXet, Date thoiGianDanhGia) {
        this.idDanhGia = idDanhGia;
        this.idSanPham = idSanPham;
        this.idKhachHang = idKhachHang;
        this.diemDanhGia = diemDanhGia;
        this.nhanXet = nhanXet;
        this.thoiGianDanhGia = thoiGianDanhGia;
    }

    public int getIdDanhGia() {
        return idDanhGia;
    }

    public void setIdDanhGia(int idDanhGia) {
        this.idDanhGia = idDanhGia;
    }

    public int getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(int idSanPham) {
        this.idSanPham = idSanPham;
    }

    public int getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(int idKhachHang) {
        this.idKhachHang = idKhachHang;
    }

    public int getDiemDanhGia() {
        return diemDanhGia;
    }

    public void setDiemDanhGia(int diemDanhGia) {
        this.diemDanhGia = diemDanhGia;
    }

    public String getNhanXet() {
        return nhanXet;
    }

    public void setNhanXet(String nhanXet) {
        this.nhanXet = nhanXet;
    }

    public Date getThoiGianDanhGia() {
        return thoiGianDanhGia;
    }

    public void setThoiGianDanhGia(Date thoiGianDanhGia) {
        this.thoiGianDanhGia = thoiGianDanhGia;
    }
    
}

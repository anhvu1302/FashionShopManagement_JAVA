/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POJO;

import java.util.Date;


public class HoaDonNhapKho {
    private int idHoaDonNhapKho;
    private Date ngayNhap;
    private int idNhanVien;

    public HoaDonNhapKho() {
    }

    public HoaDonNhapKho(int idHoaDonNhapKho, Date ngayNhap, int idNhanVien) {
        this.idHoaDonNhapKho = idHoaDonNhapKho;
        this.ngayNhap = ngayNhap;
        this.idNhanVien = idNhanVien;
    }

    public int getIdHoaDonNhapKho() {
        return idHoaDonNhapKho;
    }

    public void setIdHoaDonNhapKho(int idHoaDonNhapKho) {
        this.idHoaDonNhapKho = idHoaDonNhapKho;
    }

    public Date getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(Date ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public long getIdNhanVien() {
        return idNhanVien;
    }

    public void setIdNhanVien(int idNhanVien) {
        this.idNhanVien = idNhanVien;
    }

    public void getIdHoaDonNhapKho(int aInt) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void getNgayNhap(java.sql.Date date) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void getIdNhanVien(int aInt) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

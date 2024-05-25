/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POJO;

import java.util.Date;

public class HoaDonNhapKho {

    private int idHoaDonNhapKho;
    private int idNhanVien;
    private long tongTien;
    private Date ngayNhap;

    public HoaDonNhapKho() {
    }

    public HoaDonNhapKho(int idHoaDonNhapKho, int idNhanVien, long tongTien, Date ngayNhap) {
        this.idHoaDonNhapKho = idHoaDonNhapKho;
        this.idNhanVien = idNhanVien;
        this.tongTien = tongTien;
        this.ngayNhap = ngayNhap;
    }

    public int getIdHoaDonNhapKho() {
        return idHoaDonNhapKho;
    }

    public void setIdHoaDonNhapKho(int idHoaDonNhapKho) {
        this.idHoaDonNhapKho = idHoaDonNhapKho;
    }

    public int getIdNhanVien() {
        return idNhanVien;
    }

    public void setIdNhanVien(int idNhanVien) {
        this.idNhanVien = idNhanVien;
    }

    public long getTongTien() {
        return tongTien;
    }

    public void setTongTien(long tongTien) {
        this.tongTien = tongTien;
    }

    public Date getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(Date ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

}

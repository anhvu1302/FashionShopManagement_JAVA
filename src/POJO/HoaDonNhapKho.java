/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POJO;

import java.util.Date;

public class HoaDonNhapKho {

    private long idHoaDonNhapKho;
    private long idNhanVien;
    private long tongTien;
    private Date ngayNhap;
    private NhanVien nhanvien;

    public HoaDonNhapKho() {
    }

    public HoaDonNhapKho(long idHoaDonNhapKho, long idNhanVien, long tongTien, Date ngayNhap) {
        this.idHoaDonNhapKho = idHoaDonNhapKho;
        this.idNhanVien = idNhanVien;
        this.tongTien = tongTien;
        this.ngayNhap = ngayNhap;
    }

    public HoaDonNhapKho(long idHoaDonNhapKho, long idNhanVien, long tongTien, Date ngayNhap, NhanVien nhanvien) {
        this.idHoaDonNhapKho = idHoaDonNhapKho;
        this.idNhanVien = idNhanVien;
        this.tongTien = tongTien;
        this.ngayNhap = ngayNhap;
        this.nhanvien = nhanvien;
    }

    public long getIdHoaDonNhapKho() {
        return idHoaDonNhapKho;
    }

    public void setIdHoaDonNhapKho(long idHoaDonNhapKho) {
        this.idHoaDonNhapKho = idHoaDonNhapKho;
    }

    public long getIdNhanVien() {
        return idNhanVien;
    }

    public void setIdNhanVien(long idNhanVien) {
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

    public NhanVien getNhanvien() {
        return nhanvien;
    }

    public void setNhanvien(NhanVien nhanvien) {
        this.nhanvien = nhanvien;
    }

    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POJO;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author VanAnh
 */
public class SanPhamThongKe extends SanPham {

    private int soLuongBan;
    private long doanhThu;

    public SanPhamThongKe() {
    }

    public SanPhamThongKe(int soLuongBan, long doanhThu, long idSanPham, String tenSanPham, int idLoaiSP, long giaBan, int giamGia, String moTa, Date ngayThem, boolean tonTai) {
        super(idSanPham, tenSanPham, idLoaiSP, giaBan, giamGia, moTa, ngayThem, tonTai);
        this.soLuongBan = soLuongBan;
        this.doanhThu = doanhThu;
    }

    public int getSoLuongBan() {
        return soLuongBan;
    }

    public void setSoLuongBan(int soLuongBan) {
        this.soLuongBan = soLuongBan;
    }

    public long getDoanhThu() {
        return doanhThu;
    }

    public void setDoanhThu(long doanhThu) {
        this.doanhThu = doanhThu;
    }

}

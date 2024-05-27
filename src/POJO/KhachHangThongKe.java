/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POJO;

import java.util.Date;

/**
 *
 * @author VanAnh
 */
public class KhachHangThongKe extends KhachHang {

    private int soLuongKhachHang;

    public KhachHangThongKe() {
    }

    public KhachHangThongKe(int soLuongKhachHang, long idKhachHang, String tenKhachHang, String gioiTinh, String soDienThoai, String email, long diem, Date ngayThem) {
        super(idKhachHang, tenKhachHang, gioiTinh, soDienThoai, email, diem, ngayThem);
        this.soLuongKhachHang = soLuongKhachHang;
    }

    public int getSoLuongKhachHang() {
        return soLuongKhachHang;
    }

    public void setSoLuongKhachHang(int soLuongKhachHang) {
        this.soLuongKhachHang = soLuongKhachHang;
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POJO;

/**
 *
 * @author VanAnh
 */
public class NhanVienLogin {

    private static NhanVienLogin nhanVienLogin;
    public NhanVien nhanVien;

    public NhanVienLogin() {
        nhanVien = new NhanVien();
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public static NhanVienLogin getNhanVienLogin() {
        if (nhanVienLogin == null) {
            nhanVienLogin = new NhanVienLogin();
        }
        return nhanVienLogin;
    }
}

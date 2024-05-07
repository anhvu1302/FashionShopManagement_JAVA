/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POJO;

/**
 *
 * @author Nah nah
 */
public class ChiTietHoaDonNhapKho {
    private int idHoaDonNhapKho;
    private int idSanPham;
    private int soLuong;

    public ChiTietHoaDonNhapKho(int idHoaDonNhapKho, int idSanPham, int soLuong) {
        this.idHoaDonNhapKho = idHoaDonNhapKho;
        this.idSanPham = idSanPham;
        this.soLuong = soLuong;
    }

    public ChiTietHoaDonNhapKho() {
    }

    public int getIdHoaDonNhapKho() {
        return idHoaDonNhapKho;
    }

    public void setIdHoaDonNhapKho(int idHoaDonNhapKho) {
        this.idHoaDonNhapKho = idHoaDonNhapKho;
    }

    public int getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(int idSanPham) {
        this.idSanPham = idSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}

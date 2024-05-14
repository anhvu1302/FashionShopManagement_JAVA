/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POJO;


public class KieuSanPham {
    private int idKieuSanPham;
    private int barCode;
    private int idSanPham;
    private String mau;
    private String anhSP;
    private String size;
    private int soLuongTonKho;

    public KieuSanPham() {
    }

    public KieuSanPham(int idKieuSanPham, int barCode, int idSanPham, String mau, String anhSP, String size, int soLuongTonKho) {
        this.idKieuSanPham = idKieuSanPham;
        this.barCode = barCode;
        this.idSanPham = idSanPham;
        this.mau = mau;
        this.anhSP = anhSP;
        this.size = size;
        this.soLuongTonKho = soLuongTonKho;
    }

    public int getIdKieuSanPham() {
        return idKieuSanPham;
    }

    public void setIdKieuSanPham(int idKieuSanPham) {
        this.idKieuSanPham = idKieuSanPham;
    }

    public int getBarCode() {
        return barCode;
    }

    public void setBarCode(int barCode) {
        this.barCode = barCode;
    }

    public int getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(int idSanPham) {
        this.idSanPham = idSanPham;
    }

    public String getMau() {
        return mau;
    }

    public void setMau(String mau) {
        this.mau = mau;
    }

    public String getAnhSP() {
        return anhSP;
    }

    public void setAnhSP(String anhSP) {
        this.anhSP = anhSP;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getSoLuongTonKho() {
        return soLuongTonKho;
    }

    public void setSoLuongTonKho(int soLuongTonKho) {
        this.soLuongTonKho = soLuongTonKho;
    }
    
}

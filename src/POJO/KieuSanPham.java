/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POJO;

public class KieuSanPham {

    private long idKieuSanPham;
    private long barCode;
    private long idSanPham;
    private String mau;
    private String anhSP;
    private String size;
    private int soLuongTonKho;
    private SanPham sanPham;

    public KieuSanPham() {
    }

    public KieuSanPham(long idKieuSanPham, long barCode, long idSanPham, String mau, String anhSP, String size, int soLuongTonKho, SanPham sanPham) {
        this.idKieuSanPham = idKieuSanPham;
        this.barCode = barCode;
        this.idSanPham = idSanPham;
        this.mau = mau;
        this.anhSP = anhSP;
        this.size = size;
        this.soLuongTonKho = soLuongTonKho;
        this.sanPham = sanPham;
    }

    public long getIdKieuSanPham() {
        return idKieuSanPham;
    }

    public void setIdKieuSanPham(long idKieuSanPham) {
        this.idKieuSanPham = idKieuSanPham;
    }

    public long getBarCode() {
        return barCode;
    }

    public void setBarCode(long barCode) {
        this.barCode = barCode;
    }

    public long getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(long idSanPham) {
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

    public SanPham getSanPham() {
        return sanPham;
    }

    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
    }

    @Override
    public String toString() {
        return mau + " " + size;
    }
}

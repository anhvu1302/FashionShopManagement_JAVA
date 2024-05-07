/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POJO;

/**
 *
 * @author Nah nah
 */
public class ProductStyle {
    private int idSanPham;
    private String mauSP;
    private String anhSP;
    private String kichCoSP;
    private int soLuongTonKho;

    public int getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(int idSanPham) {
        this.idSanPham = idSanPham;
    }

    public String getMauSP() {
        return mauSP;
    }

    public void setMauSP(String mauSP) {
        this.mauSP = mauSP;
    }

    public String getAnhSP() {
        return anhSP;
    }

    public void setAnhSP(String anhSP) {
        this.anhSP = anhSP;
    }

    public String getKichCoSP() {
        return kichCoSP;
    }

    public void setKichCoSP(String kichCoSP) {
        this.kichCoSP = kichCoSP;
    }

    public int getSoLuongTonKho() {
        return soLuongTonKho;
    }

    public void setSoLuongTonKho(int soLuongTonKho) {
        this.soLuongTonKho = soLuongTonKho;
    }

    public ProductStyle(int idSanPham, String mauSP, String anhSP, String kichCoSP, int soLuongTonKho) {
        this.idSanPham = idSanPham;
        this.mauSP = mauSP;
        this.anhSP = anhSP;
        this.kichCoSP = kichCoSP;
        this.soLuongTonKho = soLuongTonKho;
    }
    public ProductStyle() {
    }
}

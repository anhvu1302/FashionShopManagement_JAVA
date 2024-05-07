/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POJO;

/**
 *
 * @author Nah nah
 */
public class SanPham {
    private int idSanPham;
    private String tenSanPham;
    private int idLoaiSP;
    private long giaBan;
    private int giamGia;
    private String noiDungSanPham;
    private boolean tonTai;

    public SanPham() {
    }

    public SanPham(int idSanPham, String tenSanPham, int idLoaiSP, long giaBan, int giamGia, String noiDungSanPham, boolean tonTai) {
        this.idSanPham = idSanPham;
        this.tenSanPham = tenSanPham;
        this.idLoaiSP = idLoaiSP;
        this.giaBan = giaBan;
        this.giamGia = giamGia;
        this.noiDungSanPham = noiDungSanPham;
        this.tonTai = tonTai;
    }

    public long getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(int idSanPham) {
        this.idSanPham = idSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public int getIdLoaiSP() {
        return idLoaiSP;
    }

    public void setIdLoaiSP(int idLoaiSP) {
        this.idLoaiSP = idLoaiSP;
    }

    public long getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(long giaBan) {
        this.giaBan = giaBan;
    }

    public int getGiamGia() {
        return giamGia;
    }

    public void setGiamGia(int giamGia) {
        this.giamGia = giamGia;
    }

    public String getNoiDungSanPham() {
        return noiDungSanPham;
    }

    public void setNoiDungSanPham(String noiDungSanPham) {
        this.noiDungSanPham = noiDungSanPham;
    }

    public boolean isTonTai() {
        return tonTai;
    }

    public void setTonTai(boolean tonTai) {
        this.tonTai = tonTai;
    }
    
}

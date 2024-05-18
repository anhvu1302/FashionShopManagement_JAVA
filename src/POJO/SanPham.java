/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POJO;

import java.util.ArrayList;
import java.util.Date;

public class SanPham {

    private long idSanPham;
    private String tenSanPham;
    private int idLoaiSP;
    private long giaBan;
    private int giamGia;
    private String moTa;
    private Date ngayThem;
    private boolean tonTai;
    private LoaiSanPham loaiSanPham;
    private ArrayList<KieuSanPham> kieuSanPhams;

    public SanPham() {
    }

    public SanPham(long idSanPham, String tenSanPham, int idLoaiSP, long giaBan, int giamGia, String moTa, Date ngayThem, boolean tonTai) {
        this.idSanPham = idSanPham;
        this.tenSanPham = tenSanPham;
        this.idLoaiSP = idLoaiSP;
        this.giaBan = giaBan;
        this.giamGia = giamGia;
        this.moTa = moTa;
        this.ngayThem = ngayThem;
        this.tonTai = tonTai;
    }

    public SanPham(long idSanPham, String tenSanPham, int idLoaiSP, long giaBan, int giamGia, String moTa, Date ngayThem, boolean tonTai, LoaiSanPham loaiSanPham, ArrayList<KieuSanPham> kieuSanPhams) {
        this.idSanPham = idSanPham;
        this.tenSanPham = tenSanPham;
        this.idLoaiSP = idLoaiSP;
        this.giaBan = giaBan;
        this.giamGia = giamGia;
        this.moTa = moTa;
        this.ngayThem = ngayThem;
        this.tonTai = tonTai;
        this.loaiSanPham = loaiSanPham;
        this.kieuSanPhams = kieuSanPhams;
    }

    public long getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(long idSanPham) {
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

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Date getNgayThem() {
        return ngayThem;
    }

    public void setNgayThem(Date ngayThem) {
        this.ngayThem = ngayThem;
    }

    public boolean isTonTai() {
        return tonTai;
    }

    public void setTonTai(boolean tonTai) {
        this.tonTai = tonTai;
    }

    public LoaiSanPham getLoaiSanPham() {
        return loaiSanPham;
    }

    public void setLoaiSanPham(LoaiSanPham loaiSanPham) {
        this.loaiSanPham = loaiSanPham;
    }

    public ArrayList<KieuSanPham> getKieuSanPhams() {
        return kieuSanPhams;
    }

    public void setKieuSanPhams(ArrayList<KieuSanPham> kieuSanPhams) {
        this.kieuSanPhams = kieuSanPhams;
    }

    @Override
    public String toString() {
        return tenSanPham;
    }
}

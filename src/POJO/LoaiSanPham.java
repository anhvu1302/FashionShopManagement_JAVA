/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POJO;


public class LoaiSanPham {
    private int idLoaiSP;
    private String tenLoaiSP;
    private int idLoaiSPCha;
    private String tenLoaiSPCha;

    public String getTenLoaiSPCha() {
        return tenLoaiSPCha;
    }

    public void setTenLoaiSPCha(String tenLoaiSPCha) {
        this.tenLoaiSPCha = tenLoaiSPCha;
    }

    public int getIdLoaiSP() {
        return idLoaiSP;
    }

    public void setIdLoaiSP(int idLoaiSP) {
        this.idLoaiSP = idLoaiSP;
    }

    public String getTenLoaiSP() {
        return tenLoaiSP;
    }

    public void setTenLoaiSP(String tenLoaiSP) {
        this.tenLoaiSP = tenLoaiSP;
    }

    public int getIdLoaiSPCha() {
        return idLoaiSPCha;
    }

    public void setIdLoaiSPCha(int idLoaiSPCha) {
        this.idLoaiSPCha = idLoaiSPCha;
    }

    public LoaiSanPham(int idLoaiSP, String tenLoaiSP, int idLoaiSPCha) {
        this.idLoaiSP = idLoaiSP;
        this.tenLoaiSP = tenLoaiSP;
        this.idLoaiSPCha = idLoaiSPCha;
    }

    public LoaiSanPham() {
    }
}

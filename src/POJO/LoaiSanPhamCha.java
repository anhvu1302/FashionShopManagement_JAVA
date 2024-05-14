/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POJO;


public class LoaiSanPhamCha {
    private int idLoaiSPCha;
    private String tenLoaiSPCha;

    public LoaiSanPhamCha(int idLoaiSPCha, String tenLoaiSPCha) {
        this.idLoaiSPCha = idLoaiSPCha;
        this.tenLoaiSPCha = tenLoaiSPCha;
    }

    public LoaiSanPhamCha() {
    }

    public int getIdLoaiSPCha() {
        return idLoaiSPCha;
    }

    public void setIdLoaiSPCha(int idLoaiSPCha) {
        this.idLoaiSPCha = idLoaiSPCha;
    }

    public String getTenLoaiSPCha() {
        return tenLoaiSPCha;
    }

    public void setTenLoaiSPCha(String tenLoaiSPCha) {
        this.tenLoaiSPCha = tenLoaiSPCha;
    }
}

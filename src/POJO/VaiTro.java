/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POJO;


public class VaiTro {
    private int idVaiTro ;
    private String tenVaiTro;

    public VaiTro(int idVaiTro, String tenVaiTro) {
        this.idVaiTro = idVaiTro;
        this.tenVaiTro = tenVaiTro;
    }

    public VaiTro() {
    }

    public int getIdVaiTro() {
        return idVaiTro;
    }

    public void setIdVaiTro(int idVaiTro) {
        this.idVaiTro = idVaiTro;
    }

    public String getTenVaiTro() {
        return tenVaiTro;
    }

    public void setTenVaiTro(String tenVaiTro) {
        this.tenVaiTro = tenVaiTro;
    }
    
}

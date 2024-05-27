/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POJO;

/**
 *
 * @author VanAnh
 */
public class LoaiSanPhamChaThongKe extends LoaiSanPhamCha {

    private int soLuongBan;
    private long doanhThu;

    public LoaiSanPhamChaThongKe(int idLoaiSPCha, String tenLoaiSPCha, int soLuongBan, long doanhThu) {
        super(idLoaiSPCha, tenLoaiSPCha);
        this.soLuongBan = soLuongBan;
        this.doanhThu = doanhThu;
    }

    public LoaiSanPhamChaThongKe() {
        super();
    }

    public int getSoLuongBan() {
        return soLuongBan;
    }

    public void setSoLuongBan(int soLuongBan) {
        this.soLuongBan = soLuongBan;
    }

    public long getDoanhThu() {
        return doanhThu;
    }

    public void setDoanhThu(long doanhThu) {
        this.doanhThu = doanhThu;
    }

}

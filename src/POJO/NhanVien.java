/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POJO;


public class NhanVien {

    private long idNhanVien;
    private String tenTaiKhoan;
    private String matKhau;
    private int idVaiTro;
    private String tenNhanVien;
    private String ngaySinh;
    private String gioiTinh;
    private String diaChi;
    private String soDienThoai;
    private String email;
    private boolean tonTai;
    private boolean cam;
    private VaiTro vaitro;

    public NhanVien() {
    }

    public NhanVien(long idNhanVien, String tenNhanVien) {
        this.idNhanVien = idNhanVien;
        this.tenNhanVien = tenNhanVien;
    }

    public NhanVien(NhanVien nv) {
        this.idNhanVien = nv.idNhanVien;
        this.tenTaiKhoan = nv.tenTaiKhoan;
        this.matKhau = nv.matKhau;
        this.idVaiTro = nv.idVaiTro;
        this.tenNhanVien = nv.tenNhanVien;
        this.ngaySinh = nv.ngaySinh;
        this.gioiTinh = nv.gioiTinh;
        this.diaChi = nv.diaChi;
        this.soDienThoai = nv.soDienThoai;
        this.email = nv.email;
        this.tonTai = nv.tonTai;
        this.cam = nv.cam;
    }

    public NhanVien(long idNhanVien, String tenTaiKhoan, String matKhau, int idVaiTro, String tenNhanVien, String ngaySinh, String gioiTinh, String diaChi, String soDienThoai, String email, boolean tonTai, boolean cam) {
        this.idNhanVien = idNhanVien;
        this.tenTaiKhoan = tenTaiKhoan;
        this.matKhau = matKhau;
        this.idVaiTro = idVaiTro;
        this.tenNhanVien = tenNhanVien;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
        this.email = email;
        this.tonTai = tonTai;
        this.cam = cam;
    }

    public NhanVien(long idNhanVien, String tenTaiKhoan, String matKhau, int idVaiTro, String tenNhanVien, String ngaySinh, String gioiTinh, String diaChi, String soDienThoai, String email, boolean tonTai, boolean cam, VaiTro vaitro) {
        this.idNhanVien = idNhanVien;
        this.tenTaiKhoan = tenTaiKhoan;
        this.matKhau = matKhau;
        this.idVaiTro = idVaiTro;
        this.tenNhanVien = tenNhanVien;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
        this.email = email;
        this.tonTai = tonTai;
        this.cam = cam;
        this.vaitro = vaitro;
    }

    public long getIdNhanVien() {
        return idNhanVien;
    }

    public void setIdNhanVien(long idNhanVien) {
        this.idNhanVien = idNhanVien;
    }

    public String getTenTaiKhoan() {
        return tenTaiKhoan;
    }

    public void setTenTaiKhoan(String tenTaiKhoan) {
        this.tenTaiKhoan = tenTaiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public int getIdVaiTro() {
        return idVaiTro;
    }

    public void setIdVaiTro(int idVaiTro) {
        this.idVaiTro = idVaiTro;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isTonTai() {
        return tonTai;
    }

    public void setTonTai(boolean tonTai) {
        this.tonTai = tonTai;
    }

    public boolean isCam() {
        return cam;
    }

    public void setCam(boolean cam) {
        this.cam = cam;
    }

    public VaiTro getVaitro() {
        return vaitro;
    }

    public void setVaitro(VaiTro vaitro) {
        this.vaitro = vaitro;
    }
    @Override
    public String toString(){
        return tenNhanVien;
    }
}

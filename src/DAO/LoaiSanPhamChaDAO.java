/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import POJO.LoaiSanPhamCha;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Nah nah
 */
public class LoaiSanPhamChaDAO {
    public static ArrayList<LoaiSanPhamCha> layDSLoaiSPCha(){
        ArrayList<LoaiSanPhamCha> dsLoaiSPCha = new ArrayList<>();
        try {
            String sql="SELECT * FROM LoaiSanPhamCha";
            SQLServerDataProvider provider= new SQLServerDataProvider();
            provider.open();
            ResultSet rs=provider.executeQuery(sql);
            while(rs.next()){
                LoaiSanPhamCha lspc= new LoaiSanPhamCha();
                lspc.setIdLoaiSPCha(rs.getInt(1));
                lspc.setTenLoaiSPCha(rs.getString(2));
                dsLoaiSPCha.add(lspc);
            }
            provider.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsLoaiSPCha;
    }
    public static boolean themLoaiSPCha(LoaiSanPhamCha lspc){
        boolean kq=false;
        String sql = String.format("Insert into LoaiSanPhamCha(TenLoaiSPCha) values('%s');", lspc.getTenLoaiSPCha());
        SQLServerDataProvider provider= new SQLServerDataProvider();
        provider.open();
        int n = provider.executeUpdate(sql);
        if(n==1){
            kq=true;
        }
        provider.close();
        return kq;
    }
    public static boolean capNhatLoaiSPCha(LoaiSanPhamCha lspc){
        boolean kq=false;
        String sql = String.format("update LoaiSanPhamCha set TenLoaiSPCha= '%s'"+"where IdLoaiSPCha =%d", lspc.getTenLoaiSPCha(),lspc.getIdLoaiSPCha());
        SQLServerDataProvider provider= new SQLServerDataProvider();
        provider.open();
        int n = provider.executeUpdate(sql);
        if(n==1){
            kq=true;
        }
        provider.close();
        return kq;
    }
    public static boolean xoaLoaiSPCha(String maLSPCha){
        boolean kq = false;
        String sql = String.format("Delete From LoaiSanPhamCha Where IdLoaiSPCha=%s", maLSPCha);
        SQLServerDataProvider provider= new SQLServerDataProvider();
        provider.open();
        int n = provider.executeUpdate(sql);
        if(n==1){
            kq=true;
        }
        provider.close();
        return kq;
    }
    public static boolean kiemTraTonTaiLoaiSPCha(String maLSPCha) {
    try {
        String sqlSelect = String.format("SELECT * FROM LoaiSanPham WHERE IdLoaiSPCha = %s", maLSPCha);
        SQLServerDataProvider provider = new SQLServerDataProvider();
        provider.open();
        ResultSet rs = provider.executeQuery(sqlSelect);
        if (rs.next()) {
            return true; // Loại sản phẩm cha tồn tại trong bảng LoaiSanPham
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return false; // Loại sản phẩm cha không tồn tại trong bảng LoaiSanPham
}

    
}

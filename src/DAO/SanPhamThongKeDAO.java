package DAO;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import POJO.*;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author VanAnh
 */
public class SanPhamThongKeDAO {

    public static ArrayList<SanPhamThongKe> GetSalesByProduct(Date startDate, Date endDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        ArrayList<SanPhamThongKe> dsTk = new ArrayList<>();
        SQLServerDataProvider provider = new SQLServerDataProvider();
        try {
            String sql = "EXEC sp_GetSalesByProduct @StartDate = '" + dateFormat.format(startDate) + "', @EndDate = '" + dateFormat.format(endDate) + "'";

            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            while (rs.next()) {
                SanPhamThongKe sp = new SanPhamThongKe();
                sp.setIdSanPham(rs.getLong("IdSanPham"));
                sp.setTenSanPham(rs.getString("TenSanPham"));
                sp.setSoLuongBan(rs.getInt("SoLuongBan"));
                sp.setDoanhThu(rs.getLong("DoanhThu"));
                dsTk.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            provider.close();
        }
        return dsTk;
    }
}

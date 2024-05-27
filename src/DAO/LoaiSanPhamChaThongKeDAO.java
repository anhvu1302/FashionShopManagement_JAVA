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
public class LoaiSanPhamChaThongKeDAO {

    public static ArrayList<LoaiSanPhamChaThongKe> GetSalesByCategory(Date startDate, Date endDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        ArrayList<LoaiSanPhamChaThongKe> dsTk = new ArrayList<>();
        SQLServerDataProvider provider = new SQLServerDataProvider();
        try {
            String sql = "EXEC sp_GetSalesByCategory @StartDate = '" + dateFormat.format(startDate) + "', @EndDate = '" + dateFormat.format(endDate) + "'";
            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            while (rs.next()) {
                LoaiSanPhamChaThongKe lspc = new LoaiSanPhamChaThongKe(rs.getInt("IdLoaiSPCha"),rs.getString("TenLoaiSPCha"),rs.getInt("SoLuongBan"),rs.getLong("DoanhThu"));
                dsTk.add(lspc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            provider.close();
        }
        return dsTk;
    }
}

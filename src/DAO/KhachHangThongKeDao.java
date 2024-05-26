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
public class KhachHangThongKeDao {

    public static ArrayList<KhachHangThongKe> ThongKeKhachHangTheoNgay(Date startDate, Date endDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        ArrayList<KhachHangThongKe> dsTk = new ArrayList<>();
        SQLServerDataProvider provider = new SQLServerDataProvider();
        try {
            String sql = "EXEC sp_ThongKeKhachHangTheoNgay @StartDate = '" + dateFormat.format(startDate) + "', @EndDate = '" + dateFormat.format(endDate) + "'";

            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            while (rs.next()) {
                KhachHangThongKe kh = new KhachHangThongKe();
                kh.setNgayThem(rs.getDate("NgayThem"));
                kh.setSoLuongKhachHang(rs.getInt("SoLuongKhachHang"));
                dsTk.add(kh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            provider.close();
        }
        return dsTk;
    }
}

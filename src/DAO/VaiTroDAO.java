/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import POJO.*;
import java.sql.ResultSet;
import java.util.ArrayList;

public class VaiTroDAO {
    public static ArrayList<VaiTro> getAll() {
        ArrayList<VaiTro> dsVaiTro = new ArrayList<>();
        try {
            String sql = "SELECT * FROM VaiTro";
            SQLServerDataProvider provider = new SQLServerDataProvider();
            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            while (rs.next()) {
                VaiTro vt = new VaiTro();
                vt.setIdVaiTro(rs.getInt(1));
                vt.setTenVaiTro(rs.getString(2));
                dsVaiTro.add(vt);
            }
            provider.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsVaiTro;
    }
}

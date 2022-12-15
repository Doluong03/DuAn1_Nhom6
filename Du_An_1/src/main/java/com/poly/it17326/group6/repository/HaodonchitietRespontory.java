/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group6.repository;

import com.poly.it17326.group6.domainmodel.HaoDonChitietdomain;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hp
 */
public class HaodonchitietRespontory {
    

    public List<HaoDonChitietdomain> getlistALLHDCT() {
        List<HaoDonChitietdomain> list = new ArrayList<>();
        String sql = "select hd.Create_at as ngay,count(hd.Ma) as hoadon,SUM(TongTien) as tongtien,count(SoLuong) as SoluongSp from hoadon hd \n"
                + "join HoaDonChiTiet hdct on hd.Id = hdct.IdHoaDon \n"
                + "join ChiTietSP ctsp on hdct.IdChiTietSP = ctsp.Id \n"
                + "group by hd.Create_at";

        try ( Connection con = ConectionRespon.getConnection();  PreparedStatement ps = con.prepareStatement(sql);) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HaoDonChitietdomain hd = new HaoDonChitietdomain();
                hd.setNgay(rs.getString(1));
                hd.setSoHd(rs.getInt(2));
                hd.setSoluongSp(rs.getInt(4));
                hd.setTongtien(rs.getBigDecimal(3));
                list.add(hd);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<HaoDonChitietdomain> getlistTehongay(String bd, String end) {
        List<HaoDonChitietdomain> list = new ArrayList<>();
        String sql = "select hd.Create_at as ngay,count(hd.Ma) as hoadon,SUM(TongTien) as tongtien,count(SoLuong) as SoluongSp from hoadon hd \n"
                + "join HoaDonChiTiet hdct on hd.Id = hdct.IdHoaDon \n"
                + "join ChiTietSP ctsp on hdct.IdChiTietSP = ctsp.Id \n"
                + "where hd.Create_at between  '" + bd + "' and '" + end + "' \n"
                + "group by hd.Create_at";

        try ( Connection con = ConectionRespon.getConnection();  PreparedStatement ps = con.prepareStatement(sql);) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HaoDonChitietdomain hd = new HaoDonChitietdomain();
                hd.setNgay(rs.getString(1));
                hd.setSoHd(rs.getInt(2));
                hd.setSoluongSp(rs.getInt(4));
                hd.setTongtien(rs.getBigDecimal(3));
                list.add(hd);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
        HaodonchitietRespontory s = new HaodonchitietRespontory();
        List<HaoDonChitietdomain> list = s.getlistALLHDCT();
        System.out.println("" + list);
    }
}

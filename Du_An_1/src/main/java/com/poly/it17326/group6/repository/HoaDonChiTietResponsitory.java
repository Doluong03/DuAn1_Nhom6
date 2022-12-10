/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group6.repository;

import com.poly.it17326.group6.config.HibernateConfig;
import com.poly.it17326.group6.domainmodel.ChiTietSP;
import com.poly.it17326.group6.domainmodel.HaoDonChitietdomain;
import com.poly.it17326.group6.domainmodel.HoaDonChiTiet;
import com.poly.it17326.group6.response.HoaDonCTResponse;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Hp
 */
public class HoaDonChiTietResponsitory {

//    public ArrayList<Integer> getIDHD(String maHD) {
//        Query query = session.createQuery("from HoaDonChiTiet  where Ma = :maHD");
//        query.setParameter("Ma", maHD);
//        ArrayList<Integer> listIDHD = (ArrayList<Integer>) query.getResultList();
//        return listIDHD;
//    }
    public boolean saveHDCT(HoaDonChiTiet hdct) {
        Transaction tran = null;
        try ( Session session = HibernateConfig.getFACTORY().openSession();) {
            tran = session.beginTransaction();
            session.save(hdct);
            tran.commit();
            return true;
        } catch (Exception e) {
            e.getMessage();
        }
        return false;

    }

    public List<HoaDonChiTiet> getListHDCT() {
        Session session = HibernateConfig.getFACTORY().openSession();
        String sql = "from HoaDonChiTiet";
        Query q = session.createQuery(sql);
        List<HoaDonChiTiet> list = q.getResultList();
        session.close();
        return list;
    }

    public boolean deleteSP(String ma) {
        Transaction tran = null;
        try ( Session session = HibernateConfig.getFACTORY().openSession();) {
            tran = session.beginTransaction();
            String sql = "delete HoaDonChiTiet where MaHD = :ma";
            Query q = session.createQuery(sql);
            q.setParameter("ma", ma);
            q.executeUpdate();
            tran.commit();
            return true;
        } catch (Exception e) {
            e.getMessage();
        }
        return false;
    }



    public static void main(String[] args) {
        HoaDonChiTietResponsitory hd = new HoaDonChiTietResponsitory();
            for (HoaDonChiTiet hoaDonChiTiet : hd.getListHDCT()) {
                System.out.println(hoaDonChiTiet.toString());
        }
    }

}

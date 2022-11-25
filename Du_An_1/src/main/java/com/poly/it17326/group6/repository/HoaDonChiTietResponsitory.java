/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group6.repository;

import com.poly.it17326.group6.config.HibernateConfig;
import com.poly.it17326.group6.domainmodel.ChiTietSP;
import com.poly.it17326.group6.domainmodel.HoaDonChiTiet;
import com.poly.it17326.group6.response.HoaDonCTResponse;
import java.math.BigDecimal;
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

    Session session = HibernateConfig.getFACTORY().openSession();

//    public ArrayList<Integer> getIDHD(String maHD) {
//        Query query = session.createQuery("from HoaDonChiTiet  where Ma = :maHD");
//        query.setParameter("Ma", maHD);
//        ArrayList<Integer> listIDHD = (ArrayList<Integer>) query.getResultList();
//        return listIDHD;
//    }
    public boolean saveHDCT(HoaDonChiTiet hdct) {
        Transaction tran = null;
        try (Session session = HibernateConfig.getFACTORY().openSession();) {
            tran = session.beginTransaction();
            session.save(hdct);
            tran.commit();
            session.close();
            return true;
        } catch (Exception e) {
            e.getMessage();
        }
        return false;

    }

    public ArrayList<HoaDonChiTiet> getListHDCT() {
        String sql="from HoaDonChiTiet";
        Query q = session.createQuery(sql);
        return (ArrayList<HoaDonChiTiet>) q.getResultList();
    }
    
    
    public boolean deleteSP(String ma){
        Transaction tran = null;
        try (Session session = HibernateConfig.getFACTORY().openSession();) {
            tran = session.beginTransaction();
            String sql ="delete HoaDonChiTiet where MaHD = :ma";
            Query q =session.createQuery(sql);
            q.setParameter("ma", ma);
            q.executeUpdate();
            tran.commit();
            session.close();
            return true;
        } catch (Exception e) {
            e.getMessage();
        }
        return false;
    }
    public static void main(String[] args) {
        String ma= "HD7";
        HoaDonChiTietResponsitory s = new HoaDonChiTietResponsitory();
        s.deleteSP(ma);
    }

//    public static void main(String[] args) {
//        HoaDonChiTietResponsitory hd = new HoaDonChiTietResponsitory();
//        List<HoaDonChiTiet> list = new ArrayList<>();
//        HoaDonChiTiet hdct1 = new HoaDonChiTiet();
//        hdct1.setDonGia(new BigDecimal(1));
//        hdct1.setIdChiTietSP(2);
//        hdct1.setIdHoaDon(1);
//        hdct1.setMaHD("hd01");
//        hdct1.setSoLuong(1);
//        hdct1.setTenKH("anh");
//        hdct1.setTongTien(new BigDecimal(1000));
//        list.add(hdct1);
//        HoaDonChiTiet hdct2 = new HoaDonChiTiet();
//        hdct1.setDonGia(new BigDecimal(1));
//        hdct1.setIdChiTietSP(1);
//        hdct1.setIdHoaDon(1);
//        hdct1.setMaHD("hd01");
//        hdct1.setSoLuong(1);
//        hdct1.setTenKH("anh");
//        hdct1.setTongTien(new BigDecimal(1000));
//        list.add(hdct1);
//
//        for (HoaDonChiTiet hoaDonChiTiet : list) {
//            hd.saveHDCT(hoaDonChiTiet);
//        }
//    }

}

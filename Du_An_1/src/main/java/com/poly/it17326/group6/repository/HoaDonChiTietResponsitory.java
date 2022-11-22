/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group6.repository;

import com.poly.it17326.group6.config.HibernateConfig;
import com.poly.it17326.group6.domainmodel.ChiTietSP;
import com.poly.it17326.group6.domainmodel.HoaDonChiTiet;
import java.util.ArrayList;
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
        try ( Session session = HibernateConfig.getFACTORY().openSession();) {
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

}

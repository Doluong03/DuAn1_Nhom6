/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group6.repository;

import com.poly.it17326.group6.config.HibernateConfig;
import com.poly.it17326.group6.domainmodel.HoaDon;
import com.poly.it17326.group6.domainmodel.KhachHang;
import com.poly.it17326.group6.domainmodel.TaiKhoan;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author 123
 */
public class KhachHangRepository {

    String sql = "from KhachHang ";

    public List<KhachHang> getList() {
        Session session = HibernateConfig.getFACTORY().openSession();
        Query query = session.createQuery(sql);
        ArrayList<KhachHang> listHD = (ArrayList<KhachHang>) query.getResultList();
        session.close();
        return listHD;
    }

    public Boolean addKh(KhachHang kh) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.save(kh);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return false;
        }
    }
    
  
    
     public ArrayList<KhachHang> CheckUser(String email) {
        Session session = HibernateConfig.getFACTORY().openSession();
        Query query = session.createQuery("from KhachHang where ma = :ma or ten =:ten");
        query.setParameter("ma", email);
        query.setParameter("ten", email);
        ArrayList<KhachHang> listTK = (ArrayList<KhachHang>) query.getResultList();
        session.close();
        return listTK;
    }
     
     
}

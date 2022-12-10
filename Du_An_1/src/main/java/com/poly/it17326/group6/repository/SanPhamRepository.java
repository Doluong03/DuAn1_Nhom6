/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group6.repository;

import com.poly.it17326.group6.config.HibernateConfig;
import com.poly.it17326.group6.domainmodel.ChiTietSP;
import com.poly.it17326.group6.domainmodel.SanPham;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author DUC-DU
 */
public class SanPhamRepository {

    private String formTable = "from SanPham sp order by cast (SUBSTRING(sp.ma,3,3) as int) desc";

    public List<SanPham> getAll() {
     
    Session session = HibernateConfig.getFACTORY().openSession();
    Query query = session.createQuery(formTable);
    List<SanPham> listOut = query.getResultList();

    session.close ();
    return listOut ;
}

public Boolean add(SanPham sanPham) {
        Transaction transaction = null;
        try {
            Session session = HibernateConfig.getFACTORY().openSession();
            transaction = session.beginTransaction();
            session.save(sanPham);
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public Boolean update(SanPham sanPham) {
        Transaction transaction = null;
        try {
            Session session = HibernateConfig.getFACTORY().openSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(sanPham);
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public Boolean delete(SanPham sanPham) {
        Transaction transaction = null;
        try {
            Session session = HibernateConfig.getFACTORY().openSession();
            transaction = session.beginTransaction();
            session.delete(sanPham);
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public static void main(String[] args) {
        List<SanPham> sanPhams = new SanPhamRepository().getAll();
        for (SanPham sp : sanPhams) {
            System.out.println(sp.toString());
        }
    }
    public Boolean save(SanPham sanPham, ChiTietSP ctsp) {
        Transaction transaction = null;
        try {
            Session session = HibernateConfig.getFACTORY().openSession();
            transaction = session.beginTransaction();    
           ctsp.setSanPham(sanPham);
            sanPham.setChiTietSPs(Arrays.asList(ctsp));
            session.persist(sanPham);
            transaction.commit();
            
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    
}

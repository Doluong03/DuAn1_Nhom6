/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.IT17326.repository;

import com.poly.IT17326.config.Hibernateconjig;
import com.poly.IT17326.domainmodel.SanPham;
import java.util.List;
import java.util.UUID;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author DUC-DU
 */
public class SanPhamRepository {
    private Session session = Hibernateconjig.getFACTORY().openSession();

    private String formTable = "from SanPham";

    public List<SanPham> getAll() {
        Query query = session.createQuery(formTable);
        return query.getResultList();
    }

    public SanPham getOne(UUID id) {
        String sql = formTable + "where id =:id";
        Query query = session.createQuery(sql, SanPham.class);
        query.setParameter("id", id);
        return (SanPham) query.getSingleResult();

    }

    public Boolean add(SanPham sanPham) {
        Transaction transaction = null;
        try {
            Session session = Hibernateconjig.getFACTORY().openSession();
            transaction = session.beginTransaction();
            session.save(sanPham);
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    public Boolean update( SanPham sanPham) {
        Transaction transaction = null;
        try {
            Session session = Hibernateconjig.getFACTORY().openSession();
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
            Session session = Hibernateconjig.getFACTORY().openSession();
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
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group6.repository;

import com.poly.it17326.group6.config.HibernateConfig;
import com.poly.it17326.group6.domainmodel.Anh;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author OS
 */
public class AnhRepository {

    public List<Anh> getAll() {
        Session session = HibernateConfig.getFACTORY().openSession();
        String sql = "from Anh a order by cast (SUBSTRING(a.ma,2,3) as int) desc";
        Query query = session.createQuery(sql);
        List<Anh> listA = (List<Anh>) query.getResultList();
        session.close();
        return listA;
    }

    public boolean addAnh(Anh anh) {
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(anh);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateAnh(String Ma, String Ten) {
        Transaction tran = null;

        try (Session session = HibernateConfig.getFACTORY().openSession();) {
            tran = session.beginTransaction();
            String sql = "update Anh set Ten = : Ten where Ma = :Ma";
            Query query = session.createQuery(sql);
            query.setParameter("Ten", Ten);
            query.setParameter("Ma", Ma);
            query.executeUpdate();
            tran.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean XoaAnh(String ma) {
        Transaction tran = null;

        try (Session session = HibernateConfig.getFACTORY().openSession();) {
            tran = session.beginTransaction();
            String sql = "  delete from Anh where Ma = :Ma";
            Query query = session.createQuery(sql);
            query.setParameter("Ma", ma);
            query.executeUpdate();
            tran.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }

//    public static void main(String[] args) {
//        AnhRepository anhRepository = new AnhRepository();
//        anhRepository.updateAnh("A001", "Canxiproo_950g");
//    }
}

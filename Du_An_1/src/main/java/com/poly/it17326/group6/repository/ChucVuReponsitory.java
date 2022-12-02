/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group6.repository;

import com.poly.it17326.group6.config.HibernateConfig;
import com.poly.it17326.group6.domainmodel.ChucVu;
import java.util.ArrayList;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author bachc
 */
public class ChucVuReponsitory {

  
        // Session session=HibernateConfig.getFACTORY().openSession();

        String sql = "from ChucVu cv order by cast (SUBSTRING(cv.ma,4,3) as int) desc";

        public ArrayList<ChucVu> getAll() {
            Session session = HibernateConfig.getFACTORY().openSession();
            Query query = session.createQuery(sql);
            ArrayList<ChucVu> listCV = (ArrayList<ChucVu>) query.getResultList();
            session.close();
            return listCV;
        }

        public Boolean addChucVu(ChucVu cv) {
            Transaction transaction = null;
            try ( Session session = HibernateConfig.getFACTORY().openSession()) {
                transaction = session.beginTransaction();
                session.save(cv);
                transaction.commit();
                return true;
            } catch (Exception e) {
                e.printStackTrace(System.out);
                return false;
            }
        }

        public ChucVu updateCV(ChucVu cv) {
            try ( Session session = HibernateConfig.getFACTORY().openSession()) {
                Transaction trans = session.getTransaction();
                trans.begin();
                try {
                    session.saveOrUpdate(cv);
                    trans.commit();
                    session.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    trans.rollback();
                    cv = null;
                }
            } finally {
                return cv;
            }
        }

        public Boolean deleteCV(String ma) {

            Transaction tran = null;
            try ( Session session = HibernateConfig.getFACTORY().openSession();) {
                tran = session.beginTransaction();
                String sql = "Delete ChucVu where ma=:ma";
                Query query = session.createQuery(sql);
                query.setParameter("ma", ma);
                query.executeUpdate();
                tran.commit();
                return true;
            }

        }
    
}

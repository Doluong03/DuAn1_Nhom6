/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.repository;

import com.mycompany.config.hibernaretconfig;
import com.mycompany.domainmodel.TaiKhoan;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author TRAN VAN HUY
 */
public class TaiKhoanRepository {
    
    private Session session = hibernaretconfig.getFACTORY().openSession();
    
    private String fromTable = "FROM TaiKhoan";
    public List<TaiKhoan> getAll(){
        Query query = session.createQuery(fromTable, TaiKhoan.class);
        return  query.getResultList();
    }
    
    public TaiKhoan getOne(Long id){
        String sql= fromTable+"WHERE id =:id";
        Query query = session.createQuery(sql, TaiKhoan.class);
        query.setParameter("id", id);
        return(TaiKhoan) query.getSingleResult();
    }
    
    public Boolean add(TaiKhoan taiKhoan){
        Transaction transaction = null;
        try (Session session = hibernaretconfig.getFACTORY().openSession())
        {
            transaction = session
                    .beginTransaction();
            session.save(taiKhoan);
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    
    
        public Boolean update(TaiKhoan taiKhoan){
        Transaction transaction = null;
        try (Session session = hibernaretconfig.getFACTORY().openSession())
        {
            transaction = session
                    .beginTransaction();
            session.saveOrUpdate(taiKhoan);
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
        
            public Boolean delete(TaiKhoan taiKhoan){
        Transaction transaction = null;
        try (Session session = hibernaretconfig.getFACTORY().openSession())
        {
            transaction = session
                    .beginTransaction();
            session.delete(taiKhoan);
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
            public static void main(String[] args) {
        List<TaiKhoan> taiKhoans = new TaiKhoanRepository().getAll();
                for (TaiKhoan taiKhoan : taiKhoans) {
                    System.out.println(taiKhoan.toString());
                }
    }
}

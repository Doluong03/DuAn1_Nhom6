/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group6.repository;

import com.poly.it17326.group6.config.HibernateConfig;
import com.poly.it17326.group6.domainmodel.NSX;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.sql.Update;

/**
 *
 * @author bachc
 */
public class NSXRespository {
    Session session=HibernateConfig.getFACTORY().openSession();
    String sql="from NSX nsx order by cast (SUBSTRING(nsx.ma,4,3) as int) desc";
    public List<NSX> getAll(){
        Query query=session.createQuery(sql);
        List<NSX> listNsx=(ArrayList<NSX>) query.getResultList();
        return listNsx;
    }
    public Boolean addNSX(NSX nsx){
        Transaction transaction = null;
        try(Session session = HibernateConfig.getFACTORY().openSession()){
            transaction=session.beginTransaction();
            session.save(nsx);
            transaction.commit();
            return true;
        }catch(Exception e){
        e.printStackTrace(System.out);
        return false;
        }
    }
  public Boolean updateNSX(NSX nsx){
         Transaction transaction = null;
        try {
            Session session = HibernateConfig.getFACTORY().openSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(nsx);
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
  public Boolean deleteNSX(String ma){
      
 Transaction tran = null;
        try ( Session session = HibernateConfig.getFACTORY().openSession();) {
            tran = session.beginTransaction();
            String sql = "Delete ChiTietSP where nsx.ma =:ma ,"
                    + "Delete NSX where ma=:ma";
            Query query = session.createQuery(sql);
            query.setParameter("ma", ma);
            query.executeUpdate();
            tran.commit();
        return true;
    }
        
  }
    public static void main(String[] args) {
        NSXRespository a=new NSXRespository();
        System.out.println(a.deleteNSX("333222"));
    }
}

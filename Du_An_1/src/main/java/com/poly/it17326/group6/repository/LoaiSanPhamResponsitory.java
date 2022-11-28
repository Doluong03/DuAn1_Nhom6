/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group6.repository;

import com.poly.it17326.group6.config.HibernateConfig;
import com.poly.it17326.group6.domainmodel.LoaiSP;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author bachc
 */
public class LoaiSanPhamResponsitory {
       
    String sql="from LoaiSP ";
    public List<LoaiSP> getAll(){
        Session session=HibernateConfig.getFACTORY().openSession();
        Query query=session.createQuery(sql);
        List<LoaiSP> listLsp=(ArrayList<LoaiSP>) query.getResultList();
        session.close();
        return listLsp;
    }
    public Boolean addLSP(LoaiSP lsp){
        Transaction transaction = null;
        try(Session session = HibernateConfig.getFACTORY().openSession()){
            transaction=session.beginTransaction();
            session.save(lsp);
            transaction.commit();
            return true;
        }catch(Exception e){
        e.printStackTrace(System.out);
        return false;
        }
    }
  public Boolean updateLSP(LoaiSP lsp){
         Transaction transaction = null;
        try {
            Session session = HibernateConfig.getFACTORY().openSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(lsp);
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
  public Boolean deleteLSP(LoaiSP lsp){
      
   Transaction transaction = null;
        try {
            Session session = HibernateConfig.getFACTORY().openSession();
            transaction = session.beginTransaction();
            session.delete(lsp);
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public static void main(String[] args) {
        
    }
}

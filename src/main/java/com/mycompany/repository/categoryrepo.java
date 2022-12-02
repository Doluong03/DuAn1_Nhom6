/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
//package com.mycompany.repository;
//
//import com.mycompany.config.hibernaretconfig;
//import com.mycompany.domainmodel.category;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Locale.Category;
//import javax.persistence.Query;
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//
///**
// *
// * @author TRAN VAN HUY
// */
//public class categoryrepo {
//
//    private Session session = hibernaretconfig.getFACTORY().openSession();
//
//    private String fromTable = "FROM category";
//
//    public List<category> getAll() {
//        Query query = session.createQuery(fromTable, category.class);
//        return query.getResultList();
//    }
//
//    public category getOne(Long id) {
//        String sql = fromTable + "WHERE id=: id";
//        Query query = session.createQuery(sql, category.class);
//        query.setParameter("id", id);
//        return (category) query.getSingleResult();
//    }
//
//    public Boolean add(category category) {
//        Transaction transaction = null;
//        try (Session session = hibernaretconfig.getFACTORY().openSession()) {
//
//            transaction = session.beginTransaction();
//            session.save(category);
//            transaction.commit();
//        } catch (Exception e) {
//            e.printStackTrace(System.out);
//        } 
//        return null;
//    }
//
//    public Boolean Upp(category category) {
//        Transaction transaction = null;
//        try (Session session = hibernaretconfig.getFACTORY().openSession()) {
//
//            transaction = session.beginTransaction();
//            session.saveOrUpdate(category);
//            transaction.commit();
//        } catch (Exception e) {
//            e.printStackTrace(System.out);
//        }
//        return null;
//    }
//
//    public Boolean delete(category category) {
//        Transaction transaction = null;
//        try (Session session = hibernaretconfig.getFACTORY().openSession()) {
//
//            transaction = session.beginTransaction();
//            session.delete(category);
//            transaction.commit();
//        } catch (Exception e) {
//            e.printStackTrace(System.out);
//        }
//        return null;
//    }
//
//    public static void main(String[] args) {
//        List<category> list = new categoryrepo().getAll();
//        for (category category : list) {
//            System.out.println("x" + list.toString());
//        }
//    }
//}

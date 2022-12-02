/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group6.repository;

import com.poly.it17326.group6.config.HibernateConfig;
import com.poly.it17326.group6.domainmodel.ChucVu;
import com.poly.it17326.group6.domainmodel.TaiKhoan;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author OS
 */
public class TaiKhoanRepository {

    String sql = "from TaiKhoan";

    public ArrayList<TaiKhoan> CheckUser(String email) {
        Session session = HibernateConfig.getFACTORY().openSession();
        Query query = session.createQuery("from TaiKhoan where email = :email or ma =:ma");
        query.setParameter("email", email);
        query.setParameter("ma", email);
        ArrayList<TaiKhoan> listTK = (ArrayList<TaiKhoan>) query.getResultList();
        session.close();
        return listTK;
    }

    public ArrayList<TaiKhoan> CheckPasswork(String matkhau) {
        Session session = HibernateConfig.getFACTORY().openSession();
        Query query = session.createQuery("from TaiKhoan where MatKhau = :MatKhau");
        query.setParameter("MatKhau", matkhau);
        ArrayList<TaiKhoan> listTK = (ArrayList<TaiKhoan>) query.getResultList();
        session.close();
        return listTK;
    }
    
       public ArrayList<TaiKhoan> CheckName(String name) {
        Session session = HibernateConfig.getFACTORY().openSession();
        Query query = session.createQuery("from TaiKhoan where HoTenNV = :name");
        query.setParameter("name", name);
        ArrayList<TaiKhoan> listTK = (ArrayList<TaiKhoan>) query.getResultList();
        session.close();
        return listTK;
    }

//    public static void main(String[] args) {
//        ArrayList<TaiKhoan> list = new TaiKhoanRepository().CheckPasswork("123456789");
//        if (list.isEmpty()) {
//            System.out.println("trống");
//        } else {
//            System.out.println("oke");
//        }
//    }
       public ArrayList<TaiKhoan> findCv(String cv) {
        Session session = HibernateConfig.getFACTORY().openSession();
        Query query = session.createQuery(" select tk from TaiKhoan tk join tk.chucVu cv where cv.ten = :id");
        query.setParameter("id", cv);
        ArrayList<TaiKhoan> listTK = (ArrayList<TaiKhoan>) query.getResultList();
        session.close();
        return listTK;
    }
    
    public ArrayList<TaiKhoan> findTT(Boolean cv) {
        Session session = HibernateConfig.getFACTORY().openSession();
        Query query = session.createQuery(" select tk from TaiKhoan tk  where tk.Status = :id");
        query.setParameter("id", cv);
        ArrayList<TaiKhoan> listTK = (ArrayList<TaiKhoan>) query.getResultList();
        session.close();
        return listTK;
    }

//    public static void main(String[] args) {
//        ArrayList<TaiKhoan> list = new TaiKhoanRepository().CheckPasswork("123456789");
//        if (list.isEmpty()) {
//            System.out.println("trống");
//        } else {
//            System.out.println("oke");
//        }
//    }
    private String fromTable = "FROM TaiKhoan";

    public List<TaiKhoan> getAll() {
        Session session = HibernateConfig.getFACTORY().openSession();
        Query query = session.createQuery(fromTable, TaiKhoan.class);
        List<TaiKhoan> list= query.getResultList();
        return list;
    }

    public TaiKhoan getOne(int id) {
        Session session = HibernateConfig.getFACTORY().openSession();
        String sql = fromTable + "WHERE id =:id";
        Query query = session.createQuery(sql, TaiKhoan.class);
        query.setParameter("id", id);
        TaiKhoan tk =(TaiKhoan) query.getSingleResult();
        return tk;
    }
    
    public List<ChucVu> getCB(){
        String sql1= "from ChucVu";
        Session session = HibernateConfig.getFACTORY().openSession();
        Query query = session.createQuery(sql1);
        List<ChucVu> list= query.getResultList();
        return list;
    }
    
    public List<Boolean> getCBTT(){
        String sql1= "select  distinct Status from TaiKhoan";
        Session session = HibernateConfig.getFACTORY().openSession();
        Query query = session.createQuery(sql1);
        List<Boolean> list= query.getResultList();
        return list;
    }
    
    public Boolean add(TaiKhoan taiKhoan) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
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

    public Boolean update(TaiKhoan taiKhoan) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
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

    public Boolean delete(TaiKhoan taiKhoan) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
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
        TaiKhoanRepository r = new TaiKhoanRepository();
        for (TaiKhoan taiKhoan : r.findTT(false)) {
            System.out.println(taiKhoan.toString());
        }
    }
}

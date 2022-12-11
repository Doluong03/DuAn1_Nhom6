/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group6.repository;

import com.poly.it17326.group6.config.HibernateConfig;
import com.poly.it17326.group6.domainmodel.ChitietKhuyenMai;
import com.poly.it17326.group6.domainmodel.khuyenmai;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class KhuyenMaiResponsitory {

    public ArrayList<khuyenmai> getALL() {
        Session session = HibernateConfig.getFACTORY().openSession();
        Query query = session.createQuery("from khuyenmai");
        ArrayList<khuyenmai> listKM = (ArrayList<khuyenmai>) query.getResultList();
        session.close();
        return listKM;
    }

    public ArrayList<khuyenmai> FindKM(String ma) {
        Session session = HibernateConfig.getFACTORY().openSession();
        Query query = session.createQuery("from khuyenmai where ma = '" + ma + "' or ten = '" + ma + "'");
        ArrayList<khuyenmai> listKM = (ArrayList<khuyenmai>) query.getResultList();
        session.close();
        return listKM;
    }

    public ArrayList<ChitietKhuyenMai> getALLSPKM() {
        Session session = HibernateConfig.getFACTORY().openSession();
        Query query = session.createQuery("from ChitietKhuyenMai");
        ArrayList<ChitietKhuyenMai> listKM = (ArrayList<ChitietKhuyenMai>) query.getResultList();
        session.close();
        return listKM;
    }

    public ArrayList<khuyenmai> FindTT(int trangthai) {
        Session session = HibernateConfig.getFACTORY().openSession();
        Query query = session.createQuery("from khuyenmai where trang_thai = " + trangthai + "");
        ArrayList<khuyenmai> listKM = (ArrayList<khuyenmai>) query.getResultList();
        session.close();
        return listKM;
    }

    public ArrayList<ChitietKhuyenMai> FindCTKM(int ma) {
        Session session = HibernateConfig.getFACTORY().openSession();
        Query query = session.createQuery("from ChitietKhuyenMai where id_km = " + ma + "");
        ArrayList<ChitietKhuyenMai> listKM = (ArrayList<ChitietKhuyenMai>) query.getResultList();
        session.close();
        return listKM;
    }

    public boolean AddKM(khuyenmai km) {
        Transaction tran = null;
        try ( Session session = HibernateConfig.getFACTORY().openSession();) {
            tran = session.beginTransaction();
            session.save(km);
            tran.commit();
            session.close();
        } catch (Exception e) {
            e.getCause();
        }
        return false;
    }

    public boolean AddCTKM(ChitietKhuyenMai ctkm) {
        Transaction tran = null;
        try ( Session session = HibernateConfig.getFACTORY().openSession();) {
            tran = session.beginTransaction();
            session.save(ctkm);
            tran.commit();
            session.close();
        } catch (Exception e) {
            e.getCause();
        }
        return false;
    }

    public boolean UpdateKM(int id, Date ngayKetThuc, String loaiKhuyenMai,
            String ten, String ma, Date ngayBatDau,int giaTri) {
        Transaction transaction = null;
        try ( Session session = HibernateConfig.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            String sql = "update khuyenmai set giaTri =:giaTri ,"
                    + " loaiKhuyenMai =:loaiKhuyenMai, ma =:ma,ten =:ten,\n"
                    + "ngayBatDau =:ngayBatDau,"
                    + "ngayKetThuc =:ngayKetThuc\n"
                    + "where id =:id";
            javax.persistence.Query query = session.createQuery(sql);
            query.setParameter("loaiKhuyenMai", loaiKhuyenMai);
            query.setParameter("ten", ten);
            query.setParameter("ma", ma);
            query.setParameter("ngayKetThuc", ngayKetThuc);
            query.setParameter("ngayBatDau", ngayBatDau);
            query.setParameter("giaTri", giaTri);
            query.setParameter("id", id);
            query.executeUpdate();
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

   public boolean UpdateTT(int id) {
        Transaction transaction = null;
        try ( Session session = HibernateConfig.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            String sql = "update khuyenmai set trangThai = 0 where id =:id";           
            javax.persistence.Query query = session.createQuery(sql);
            query.setParameter("id", id);
            query.executeUpdate();
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
   
    public static void main(String[] args) {
        KhuyenMaiResponsitory s = new KhuyenMaiResponsitory();
        for (khuyenmai object : s.FindKM("KMTest")) {
            System.out.println(object.toString());
        }
    }
    
}

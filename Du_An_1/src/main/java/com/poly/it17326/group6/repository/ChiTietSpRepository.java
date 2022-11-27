/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group6.repository;

import com.poly.it17326.group6.config.HibernateConfig;
import com.poly.it17326.group6.domainmodel.Anh;

import com.poly.it17326.group6.domainmodel.SanPham;

import com.poly.it17326.group6.domainmodel.ChiTietSP;
import com.poly.it17326.group6.domainmodel.LoaiSP;
import com.poly.it17326.group6.domainmodel.NSX;
import java.lang.ref.Cleaner;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author 123
 */
public class ChiTietSpRepository {

    Session session = HibernateConfig.getFACTORY().openSession();
    String sql = "from ChiTietSP";

    public List<ChiTietSP> getAll() {
        Query q = session.createQuery(sql);
        List<ChiTietSP> listCTSP = (List<ChiTietSP>) q.getResultList();
        return listCTSP;
    }

//      
//    
////    
//    public static void main(String[] args) {
//       String ma="Sữa cho trẻ em";
//       ChiTietSpRepository s = new ChiTietSpRepository();
//       
//        System.out.println(s.getTimKiemLsp(ma).toString());
//    }
    public ChiTietSP getTimKiem(String ma) {
        Session session = HibernateConfig.getFACTORY().openSession();
        Query q = session.createQuery(" select ct from ChiTietSP as ct join ct.sanPham sp where sp.ma = :ma");
        q.setParameter("ma", ma);
        return (ChiTietSP) q.getSingleResult();
    }

    public ChiTietSP getTimKiemLsp(String ten) {
        Session session = HibernateConfig.getFACTORY().openSession();
        Query q = session.createQuery(" select ct from ChiTietSP as ct join ct.loaiSP lsp where lsp.ten = :ma");
        q.setParameter("ma", ten);
        return (ChiTietSP) q.getSingleResult();
    }

    public ChiTietSP addSP(ChiTietSP ctsp) {
        try ( Session session = HibernateConfig.getFACTORY().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(ctsp);
            transaction.commit();
            return ctsp;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ctsp;
    }

    public ChiTietSP updateSP(ChiTietSP ctsp) {
        try ( Session session = HibernateConfig.getFACTORY().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(ctsp);
            transaction.commit();
            return ctsp;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ctsp;
    }

    public ArrayList<SanPham> getListSP() {
        Query q = session.createQuery("from SanPham");
        ArrayList<SanPham> listSp = (ArrayList<SanPham>) q.getResultList();
        return listSp;
    }

    public ArrayList<LoaiSP> getListLSP() {
        Query q = session.createQuery("from LoaiSP");
        ArrayList<LoaiSP> listLSp = (ArrayList<LoaiSP>) q.getResultList();
        return listLSp;
    }

    public ArrayList<NSX> getListNsx() {
        Query q = session.createQuery("from NSX");
        ArrayList<NSX> listNsx = (ArrayList<NSX>) q.getResultList();
        return listNsx;
    }

    public ArrayList<Anh> getListAnh() {
        Query q = session.createQuery("from Anh");
        ArrayList<Anh> listA = (ArrayList<Anh>) q.getResultList();
        return listA;
    }

    public boolean updateSLSP(int id, int SoLuongTon) {
        Transaction tran = null;
        try ( Session session = HibernateConfig.getFACTORY().openSession()) {
            tran = session.beginTransaction();
            String sql = "update ChiTietSP set SoLuongTon =:SoLuongTon where id = :id";
            Query query = session.createQuery(sql);
            query.setParameter("id", id);
            query.setParameter("SoLuongTon", SoLuongTon);
            query.executeUpdate();

            tran.commit();
            return true;
        } catch (HibernateException e) {
            e.getMessage();
        }
        return false;
    }
    
    

}

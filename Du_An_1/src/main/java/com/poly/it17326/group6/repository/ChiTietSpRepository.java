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

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.apache.commons.lang3.StringEscapeUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author 123
 */
public class ChiTietSpRepository {

    public List<ChiTietSP> getAll() {
        Session session = HibernateConfig.getFACTORY().openSession();
        String sql = "select ct from ChiTietSP as ct join ct.sanPham sp order by cast (SUBSTRING(sp.ma,3,3) as int) asc";
        Query q = session.createQuery(sql);
        List<ChiTietSP> listCTSP = q.getResultList();
        session.close();
        return listCTSP;
    }

//    
    public static void main(String[] args) {
        ChiTietSpRepository s = new ChiTietSpRepository();
        for (LoaiSP loaiSP : s.getListLSP()) {
            System.out.println(String.format("%s", loaiSP.getTen()));
        }
    }

    public ChiTietSP getTimKiem(String ma) {
        Session session = HibernateConfig.getFACTORY().openSession();
        Query q = session.createQuery(" select ct from ChiTietSP as ct join ct.sanPham sp where sp.ma = :ma");
        q.setParameter("ma", ma);
        ChiTietSP ct = (ChiTietSP) q.getSingleResult();
        session.close();
        return ct;
    }

    public List<ChiTietSP> getTimKiemLsp(String ten) {
        Session session = HibernateConfig.getFACTORY().openSession();
        Query q = session.createQuery(" select ct from ChiTietSP as ct join ct.loaiSP lsp where lsp.ten = :ma");
        q.setParameter("ma", ten);
        List<ChiTietSP> list = q.getResultList();
        session.close();
        return list;
    }

    public ChiTietSP addSP(ChiTietSP ctsp) {
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
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
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
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
        Session session = HibernateConfig.getFACTORY().openSession();
        Query q = session.createQuery("from SanPham");
        ArrayList<SanPham> listSp = (ArrayList<SanPham>) q.getResultList();
        session.close();
        return listSp;
    }

    public ArrayList<LoaiSP> getListLSP() {
        Session session = HibernateConfig.getFACTORY().openSession();
        Query q = session.createQuery("from LoaiSP");
        ArrayList<LoaiSP> listLSp = (ArrayList<LoaiSP>) q.getResultList();
        session.close();
        return listLSp;
    }

    public ArrayList<NSX> getListNsx() {
        Session session = HibernateConfig.getFACTORY().openSession();
        Query q = session.createQuery("from NSX");
        ArrayList<NSX> listNsx = (ArrayList<NSX>) q.getResultList();
        session.close();
        return listNsx;
    }

    public ArrayList<Anh> getListAnh() {
        Session session = HibernateConfig.getFACTORY().openSession();
        Query q = session.createQuery("from Anh");
        ArrayList<Anh> listA = (ArrayList<Anh>) q.getResultList();
        session.close();
        return listA;
    }

    public boolean updateSL(int sl, int id) {
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            Transaction transaction = session.beginTransaction();
            String sql = "Update ChiTietSP  set soLuongTon = soLuongTon -:sl where id = :ma ";
            Query q = session.createQuery(sql);
            q.setParameter("sl", sl);
            q.setParameter("ma", id);
            q.executeUpdate();
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<ChiTietSP> getTimKiem2(String ma) {
        Session session = HibernateConfig.getFACTORY().openSession();
        Query q = session.createQuery(" select ct from ChiTietSP as ct join ct.sanPham sp where sp.ma = :ma");
        q.setParameter("ma", ma);
        ArrayList<ChiTietSP> list = (ArrayList<ChiTietSP>) q.getResultList();
        session.close();
        return list;

    }
}

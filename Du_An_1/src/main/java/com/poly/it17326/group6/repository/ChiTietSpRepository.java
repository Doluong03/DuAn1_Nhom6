/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group6.repository;

import com.poly.it17326.group6.config.HibernateConfig;

import com.poly.it17326.group6.domainmodel.SanPham;

import com.poly.it17326.group6.domainmodel.ChiTietSP;


import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;

/**
 *
 * @author 123
 */
public class ChiTietSpRepository {

    Session session = HibernateConfig.getFACTORY().openSession();
    String sql = "from ChiTietSP";
    public ArrayList<ChiTietSP> getAll() {
        Query q = session.createQuery(sql);
        ArrayList<ChiTietSP> listCTSP = (ArrayList<ChiTietSP>) q.getResultList();
        return listCTSP;
    }

    
      
    
//    
//    public static void main(String[] args) {
//        List<SanPham> list = new ChiTietSpRepository().getIDSP("binchilling");
//        int idsp = Integer.parseInt(list.get(0).toString());
//        ArrayList<ChiTietSP> lists = new ChiTietSpRepository().getIDCTSP(idsp);
//        System.out.println(lists);
//    }


    public ChiTietSP getTimKiem(String ma) {
        Session session = HibernateConfig.getFACTORY().openSession();
        Query q = session.createQuery(" select ct from ChiTietSP as ct join ct.sanPham where ct.sanPham.ma like: ma");
        q.setParameter("ma", ma);
        return  (ChiTietSP) q.getSingleResult();
    }


}

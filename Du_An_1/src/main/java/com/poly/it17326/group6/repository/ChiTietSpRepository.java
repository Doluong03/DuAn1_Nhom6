/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group6.repository;

import com.poly.it17326.group6.domainmodel.ChiTietSP;
import com.poly.it17326.group6.config.HibernateConfig;
import com.poly.it17326.group6.domainmodel.SanPham;
import com.poly.it17326.group6.response.ChiTietSpResponse;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author 123
 */
public class ChiTietSpRepository {

    String sql = "from ChiTietSP";

    public ArrayList<ChiTietSP> getAll() {
        Session session = HibernateConfig.getFACTORY().openSession();
        Query q = session.createQuery(sql);
        ArrayList<ChiTietSP> listCTSP = (ArrayList<ChiTietSP>) q.getResultList();
        return listCTSP;
    }


  
}

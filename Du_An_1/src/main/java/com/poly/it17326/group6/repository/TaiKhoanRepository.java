/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group6.repository;

import com.poly.it17326.group6.config.HibernateConfig;
import com.poly.it17326.group6.domainmodel.TaiKhoan;
import java.util.ArrayList;
import javax.persistence.Query;

import org.hibernate.Hibernate;
import org.hibernate.Session;

/**
 *
 * @author OS
 */
public class TaiKhoanRepository {

    String sql = "from TaiKhoan";

    public ArrayList<TaiKhoan> CheckUser(String email) {
            Session session = HibernateConfig.getFACTORY().openSession();
        Query query = session.createQuery("from TaiKhoan where email = :email");
        query.setParameter("email", email);
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

//    public static void main(String[] args) {
//        ArrayList<TaiKhoan> list = new TaiKhoanRepository().CheckPasswork("123456789");
//        if (list.isEmpty()) {
//            System.out.println("trống");
//        } else {
//            System.out.println("oke");
//        }
//    }
}

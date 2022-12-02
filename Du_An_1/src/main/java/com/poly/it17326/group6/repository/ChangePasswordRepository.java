/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group6.repository;

import com.poly.it17326.group6.config.HibernateConfig;
import com.poly.it17326.group6.domainmodel.TaiKhoan;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author Tung Anh
 */
public class ChangePasswordRepository {

    private Session session = HibernateConfig.getFACTORY().openSession();

    public List<TaiKhoan> getPassword(String ma, String MatKhau) {
        Session session = HibernateConfig.getFACTORY().openSession();
        String sql = "FROM TaiKhoan WHERE  MaNV = :MaNV and MatKhau =: MatKhau ";
        Query query = session.createQuery(sql);
        query.setParameter("MaNV", ma);
        query.setParameter("MatKhau", MatKhau);
        List<TaiKhoan> tk =  query.getResultList();
        session.close();
        return tk;
    }

    public TaiKhoan getMa(String ma) {
        String sql = "FROM TaiKhoan WHERE ma =: ma";
        Query query = session.createQuery(sql);
        query.setParameter("ma", ma);
        return (TaiKhoan) query.getSingleResult();
    }

    public String changePassword(String ma, String MatKhau, Date updateAt) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            String sql = "update TaiKhoan set MatKhau = :MatKhau , updateAt = :updateAt where ma = :ma";
            javax.persistence.Query query = session.createQuery(sql);
            query.setParameter("MatKhau", MatKhau);
            query.setParameter("updateAt", updateAt);
            query.setParameter("ma", ma);

            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        ChangePasswordRepository change = new ChangePasswordRepository();
        for (Object object : change.getPassword("das", "1")) {
            System.out.println(object.toString());
        }
        
    }
}

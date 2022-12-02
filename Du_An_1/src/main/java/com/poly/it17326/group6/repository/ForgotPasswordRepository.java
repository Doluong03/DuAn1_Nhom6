/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group6.repository;

import com.poly.it17326.group6.config.HibernateConfig;
import com.poly.it17326.group6.domainmodel.TaiKhoan;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.persistence.Query;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.data.jpa.provider.HibernateUtils;

/**
 *
 * @author Tung Anh
 */
public class ForgotPasswordRepository {

    private Session session1 = HibernateConfig.getFACTORY().openSession();

    private String fromTable = "FROM TaiKhoan";

    public List<TaiKhoan> getAll() {
        Query query = session1.createQuery(fromTable, TaiKhoan.class);
        return query.getResultList();
    }

    public TaiKhoan getID(int id) {
        String sql = fromTable + "WHERE id =: id";
        Query query = session1.createQuery(sql, TaiKhoan.class);
        query.setParameter("id", id);

        return (TaiKhoan) query.getSingleResult();
    }

    public TaiKhoan getEmail(String email) {
        String sql = fromTable + "WHERE email =: email";
        Query query = session1.createQuery(sql, TaiKhoan.class);
        query.setParameter("id", email);

        return (TaiKhoan) query.getSingleResult();
    }

    public TaiKhoan getPassword(String password) {
        String sql = fromTable + "WHERE MatKhau =: MatKhau";
        Query query = session1.createQuery(sql, TaiKhoan.class);
        query.setParameter("MatKhau", password);

        return (TaiKhoan) query.getSingleResult();
    }

    public String changePassword(String ma, String MatKhau, Date update) {
        Transaction transaction = null;
        try ( Session session = HibernateConfig.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            String sql = "update TaiKhoan set MatKhau = :MatKhau , updateAt = :updateAt where Ma = :Ma";
            Query query = session.createQuery(sql);
            query.setParameter("MatKhau", MatKhau);
            query.setParameter("updateAt", update);
            query.setParameter("Ma", ma);

            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        List<TaiKhoan> list = new ForgotPasswordRepository().getAll();
        ForgotPasswordRepository fhe = new ForgotPasswordRepository();
        Date now= new Date();
        SimpleDateFormat format =new SimpleDateFormat();
        format.applyPattern("yyyy-MM-dd");
        String newd = format.format(now);
        fhe.changePassword("nv2", "123",now);
    }
}

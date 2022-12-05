/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group6.repository;

import com.poly.it17326.group6.config.HibernateConfig;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Query;
import org.hibernate.Session;

/**
 *
 * @author 123
 */
public class ThongKeRepository {

    public List<BigDecimal> getListDtNgay(Date begin, Date end) {
        Session session = HibernateConfig.getFACTORY().openSession();
        String sql = "select sum(tongTien) from HoaDon where createAt between :begin and :end \n"
                + "group by createAt ";
        Query q = session.createQuery(sql);
        q.setParameter("begin", begin);
        q.setParameter("end", end);
        List<BigDecimal> listDT = q.getResultList();
        session.close();
        return listDT;
    }

    public List<BigDecimal> getListDtThang(String year, String Month) {
        Session session = HibernateConfig.getFACTORY().openSession();
        String sql = "select sum(tongTien) from HoaDon where Create_at like '" + year + "'+'-'+'" + Month + "%'";
        Query q = session.createQuery(sql);

        List<BigDecimal> listDT = q.getResultList();
        session.close();
        return listDT;
    }

    public List<BigDecimal> getListDtNam(String nam) {
        Session session = HibernateConfig.getFACTORY().openSession();
        String sql = "select sum(tongTien) from HoaDon where Create_at like '" + nam + "%'";
        Query q = session.createQuery(sql);
        List<BigDecimal> listDT = q.getResultList();
        session.close();
        return listDT;
    }

    public List<BigDecimal> getListDtNgay(String createAt) {
        Session session = HibernateConfig.getFACTORY().openSession();
        String sql = "select sum(tongTien) from HoaDon where createAt like '" + createAt + "'";
        Query q = session.createQuery(sql);
        List<BigDecimal> listDT = q.getResultList();
        session.close();
        return listDT;
    }

    public List<Integer> getListHdThanhcongNgay(String createAt) {
        Session session = HibernateConfig.getFACTORY().openSession();
        String sql = "select count(TrangThai) from HoaDon where createAt like '" + createAt + "' and TrangThai =1 ";
        Query q = session.createQuery(sql);
        List<Integer> listDT = q.getResultList();
        session.close();
        return listDT;
    }

    public List<Integer> getListHDDAHUYNgay(String createAt) {
        Session session = HibernateConfig.getFACTORY().openSession();
        String sql = "select count(TrangThai) from HoaDon where createAt like '" + createAt + "' and TrangThai =0";
        Query q = session.createQuery(sql);
        List<Integer> listDT = q.getResultList();
        session.close();
        return listDT;
    }

    public List<Integer> getListHDDahuyThang(String year, String Month) {
        Session session = HibernateConfig.getFACTORY().openSession();
        String sql = "select count(TrangThai) from HoaDon where Create_at like '" + year + "'+'-'+'" + Month + "%' and TrangThai =0";
        Query q = session.createQuery(sql);
        List<Integer> listDT = q.getResultList();
        session.close();
        return listDT;
    }

    public List<Integer> getListHDTAHNHCONGTHANg(String year, String Month) {
        Session session = HibernateConfig.getFACTORY().openSession();
        String sql = "select count(TrangThai)from HoaDon where Create_at like '" + year + "'+'-'+'" + Month + "%' and TrangThai =1";
        Query q = session.createQuery(sql);
        List<Integer> listDT = q.getResultList();
        session.close();
        return listDT;
    }

    public List<Integer> getListHDDAHUYnam(String nam) {
        Session session = HibernateConfig.getFACTORY().openSession();
        String sql = "select count(TrangThai) from HoaDon where Create_at like '" + nam + "%' and TrangThai =0";
        Query q = session.createQuery(sql);
        List<Integer> listDT = q.getResultList();
        session.close();
        return listDT;
    }

    public List<Integer> getListHDthanhcongNam(String nam) {
        Session session = HibernateConfig.getFACTORY().openSession();
        String sql = "select count(TrangThai) from HoaDon where Create_at like '" + nam + "%'and TrangThai =1";
        Query q = session.createQuery(sql);
        List<Integer> listDT = q.getResultList();
        session.close();
        return listDT;
    }
    
     public List<Double> getListDtThangs(String year, String Month) {
        Session session = HibernateConfig.getFACTORY().openSession();
        String sql = "select sum(tongTien) from HoaDon where Create_at like '" + year + "'+'-'+'" + Month + "%'";
        Query q = session.createQuery(sql);

        List<Double> listDT = q.getResultList();
        session.close();
        return listDT;
    }
    
    


    public static void main(String[] args) {
        ThongKeRepository s = new ThongKeRepository();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();

        String sw = formatter.format(date);
        String subtringYear = sw.substring(0, 4);
        String subtringMonth = sw.substring(5, 7);
        System.out.println(subtringMonth);

        List<BigDecimal> lists = s.getListDtThang(subtringYear, subtringMonth);
        BigDecimal a = null;
        for (BigDecimal list : lists) {
             a= list;
          
        }
        System.out.println(""+a);
    }
}

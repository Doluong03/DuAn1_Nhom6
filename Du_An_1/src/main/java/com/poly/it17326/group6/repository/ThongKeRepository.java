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

    public List<Long> getListHdNgay(Date begin, Date end) {
        Session session = HibernateConfig.getFACTORY().openSession();
        String sql = "select count(MaHD) from HoaDon from HoaDon where createAt between :begin and :end\n"
                + "group by createAt ";
        Query q = session.createQuery(sql);
        q.setParameter("begin", begin);
        q.setParameter("end", end);
        List<Long> listDT = q.getResultList();
        session.close();
        return listDT;
    }

    public static void main(String[] args) {
        ThongKeRepository s = new ThongKeRepository();
        Date date = new Date();
        String date7 = "2022-11-03";
        Date newDate = null;
        try {
            newDate = new SimpleDateFormat("yyyy-MM-dd").parse(date7);
            for (BigDecimal double1 : s.getListDtNgay(newDate, date)) {
                System.out.println(double1.toString());
            }
        } catch (ParseException ex) {
            Logger.getLogger(ThongKeRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}

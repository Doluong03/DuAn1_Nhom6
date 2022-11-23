/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group6.repository;

import com.poly.it17326.group6.config.HibernateConfig;
import com.poly.it17326.group6.domainmodel.ChiTietSP;
import com.poly.it17326.group6.domainmodel.HoaDon;
import com.poly.it17326.group6.domainmodel.TinhTrang;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import static java.time.Instant.now;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.Query;
import org.hibernate.HibernateError;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Hp
 */
public class HoaDonRepository {

    Session session = HibernateConfig.getFACTORY().openSession();
    String sql = "from HoaDon";

    public ArrayList<HoaDon> getAll() {
        Query query = session.createQuery(sql);
        ArrayList<HoaDon> listHD = (ArrayList<HoaDon>) query.getResultList();
        return listHD;
    }
    // lay id hoa don

    public ArrayList<HoaDon> getIDHD(String Ma) {
        Query query = session.createQuery("from HoaDon  where Ma = :Ma");
        query.setParameter("Ma", Ma);
        ArrayList<HoaDon> listIDHD = (ArrayList<HoaDon>) query.getResultList();
        return listIDHD;
    }

    public boolean updateHD(String Ma, BigDecimal tongtien, int IdTT) {
        Transaction tran = null;
        try ( Session session = HibernateConfig.getFACTORY().openSession();) {
            tran = session.beginTransaction();
            String sql = "update HoaDon \n"
                    + "set tongtien = :tongtien ,\n"
                    + " IdTT = :IdTT \n"
                    + "where Ma = :Ma \n";
            Query query = session.createQuery(sql);
            query.setParameter("tongtien", tongtien);
            query.setParameter("Ma", Ma);
            query.setParameter("IdTT", IdTT);
            query.executeUpdate();
            tran.commit();
            session.close();
            return true;

        } catch (HibernateError e) {
            e.getMessage();
        }
        return false;
    }

    public boolean add(HoaDon hd) {
        Transaction transaction = null;
        try ( Session session = HibernateConfig.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            String sql = "INSERT INTO HoaDon (Id,Ma,Create_at,HoTen,IdTT)\n"
                    + "VALUES ()";
            Query query = session.createQuery(sql);
            int i = (int) (getAll().size() + 1);
            query.setParameter("Ma", i);
            Date now = new Date();
            SimpleDateFormat format = new SimpleDateFormat();
            format.applyPattern("yyyy-MM-dd");
            String ngayTao = format.format(now);
            query.setParameter("Create_at", ngayTao);
            session.save(hd);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return false;
        }
    }
 public ArrayList<HoaDon> getSearch(String IDtt) {
        Query query = session.createQuery("select hd from HoaDon hd join hd.TinhTrang join hd.TaiKhoan where hd.tinhTrang.ma=? order by cast (SUBSTRING(hd.Ma,3,3) as int) asc");
        query.setParameter("hd.tinhTrang.ma", IDtt);
        ArrayList<HoaDon> listSearch = (ArrayList<HoaDon>) query.getResultList();
        return listSearch;
    }
    public static void main(String[] args) {
        HoaDonRepository hd=new HoaDonRepository();
        String id="TT01";
        System.out.println(hd.getSearch(id));
    }

}

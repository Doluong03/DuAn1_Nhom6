/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group6.repository;

import com.poly.it17326.group6.config.HibernateConfig;
import com.poly.it17326.group6.domainmodel.ChiTietSP;
import com.poly.it17326.group6.domainmodel.HoaDon;
import com.poly.it17326.group6.domainmodel.TaiKhoan;
import com.poly.it17326.group6.domainmodel.TinhTrang;
import com.poly.it17326.group6.domainmodel.Voucher;
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
    String sql = "From HoaDon hd order by cast (SUBSTRING(hd.MaHD,3,3) as int) desc";

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
        try (Session session = HibernateConfig.getFACTORY().openSession();) {
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

    public boolean addHD() {
        HoaDon hd = new HoaDon();
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            Transaction transaction = session.beginTransaction();
            int i = (int) (getAll().size() + 1);
            Date now = new Date();
            SimpleDateFormat format = new SimpleDateFormat();
            format.applyPattern("yyyy-MM-dd");
            String ngayTao = format.format(now);
            TaiKhoan tk = new TaiKhoan();
            tk.setId("3128BB67-3E05-4E29-ACCE-3C3DB77AEABE");
            TinhTrang tt = new TinhTrang();
            tt.setId("C187EB83-FE23-4707-9D2B-B6AB1299D200");
            Voucher vc = new Voucher();
            vc.setId("862854CC-E6B7-4B04-8950-51B941C44A31");
            Date nt = new SimpleDateFormat("yyyy-MM-dd").parse(ngayTao);
            hd.setMaHD("HD" + i++);
            hd.setMaKH("KH" + i++);
            hd.setCreateAt(nt);
            hd.setTaiKhoan(tk);
            hd.setTinhTrang(tt);
            session.save(hd);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<HoaDon> getSearch(int tt) {
        Query query = session.createQuery("select hd from HoaDon hd join hd.tinhTrang join hd.taiKhoan where hd.tinhTrang.TrangThai like: ma order by cast (SUBSTRING(hd.MaHD,3,3) as int) desc");
        query.setParameter("ma", tt);
        ArrayList<HoaDon> listSearch = (ArrayList<HoaDon>) query.getResultList();
        return listSearch;
    }
    
    public ArrayList<HoaDon> timKiemHD(String ma) {
        Query query = session.createQuery("select hd from HoaDon hd join hd.tinhTrang join hd.taiKhoan where hd.MaHD like: ma order by cast (SUBSTRING(hd.MaHD,3,3) as int) asc");
        query.setParameter("ma", ma);
        ArrayList<HoaDon> listSearch = (ArrayList<HoaDon>) query.getResultList();
        return listSearch;
    }
    public static void main(String[] args) {
        HoaDonRepository hd=new HoaDonRepository();
        int tt=0;
        System.out.println(hd.getSearch(tt));
    }

}

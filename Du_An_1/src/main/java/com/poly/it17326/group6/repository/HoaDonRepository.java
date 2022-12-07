/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group6.repository;

import com.poly.it17326.group6.config.HibernateConfig;
import com.poly.it17326.group6.domainmodel.HoaDon;
import com.poly.it17326.group6.domainmodel.KhachHang;
import com.poly.it17326.group6.domainmodel.TaiKhoan;
import com.poly.it17326.group6.domainmodel.Voucher;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Hp
 */
public class HoaDonRepository {

    public List<HoaDon> getAll() {
        String sql = "from HoaDon hd  order by cast (SUBSTRING(hd.MaHD,3,3) as int) desc ";
        Session session = HibernateConfig.getFACTORY().openSession();
        Query query = session.createQuery(sql);
        List<HoaDon> listHD = (ArrayList<HoaDon>) query.getResultList();
        session.close();
        return listHD;
    }
    // lay id hoa don

    public ArrayList<HoaDon> getIDHD(String Ma) {
        Session session = HibernateConfig.getFACTORY().openSession();
        Query query = session.createQuery("from HoaDon  where Ma = :Ma");
        query.setParameter("Ma", Ma);
        ArrayList<HoaDon> listIDHD = (ArrayList<HoaDon>) query.getResultList();
        session.close();
        return listIDHD;
    }

    public boolean updateHD(String ma, BigDecimal tongTien, int trangThai, KhachHang idKh) {
        Transaction tran = null;
        int check = 0;
        try (Session session = HibernateConfig.getFACTORY().openSession();) {
            tran = session.beginTransaction();
            String sql = "update HoaDon set khachHang = :kh , tongTien = :tongtien , TrangThai = :TrangThai where MaHD = :Ma";
            Query query = session.createQuery(sql);
            query.setParameter("kh", idKh);
            query.setParameter("tongtien", tongTien);
            query.setParameter("TrangThai", trangThai);
            query.setParameter("Ma", ma);
            check = query.executeUpdate();
            tran.commit();
            session.close();
        }
        if (check != 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean addHD(int idtk) {
        HoaDon hd = new HoaDon();
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            Transaction transaction = session.beginTransaction();
            int i = (int) (getAll().size() + 5);
            Date now = new Date();
            SimpleDateFormat format = new SimpleDateFormat();
            format.applyPattern("yyyy-MM-dd");
            String ngayTao = format.format(now);
            TaiKhoan tk = new TaiKhoan();
            tk.setId(idtk);
            Voucher vc = new Voucher();
            vc.setId(1);
            Date nt = new SimpleDateFormat("yyyy-MM-dd").parse(ngayTao);
            hd.setMaHD("HD" + i++);
            KhachHang kh = new KhachHang();
            kh.setId(3);
            hd.setKhachHang(kh);
            hd.setCreateAt(nt);
            hd.setTaiKhoan(tk);
            hd.setTrangThai(0);
            session.save(hd);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<HoaDon> getSearch(int tt) {
        Session session = HibernateConfig.getFACTORY().openSession();
        Query query = session.createQuery("select hd from HoaDon hd join hd.taiKhoan where hd.TrangThai = :ma order by cast (SUBSTRING(hd.MaHD,3,3) as int) desc");
        query.setParameter("ma", tt);
        List<HoaDon> listSearch = (ArrayList<HoaDon>) query.getResultList();
        session.close();
        return listSearch;
    }

    public List<HoaDon> timKiemHD(String ma, String sdt) {
        Session session = HibernateConfig.getFACTORY().openSession();
        Query query = session.createQuery("select hd from HoaDon hd  join hd.taiKhoan join hd.khachHang where hd.MaHD = :ma or hd.khachHang.sdt =: sdt order by cast (SUBSTRING(hd.MaHD,3,3) as int) asc");
        query.setParameter("ma", ma);
        query.setParameter("sdt", sdt);
        List<HoaDon> listSearch = (ArrayList<HoaDon>) query.getResultList();

        session.close();
        return listSearch;
    }

    public List<HoaDon> getALLHD() {
        Session session = HibernateConfig.getFACTORY().openSession();
        Query query = session.createQuery("from HoaDon ");

        List<HoaDon> listSearch = (List<HoaDon>) query.getResultList();
        session.close();
        return listSearch;
    }

    public boolean updateVCHHD(String ma, int IdVC) {
        Transaction tran = null;
        try (Session session = HibernateConfig.getFACTORY().openSession();) {
            tran = session.beginTransaction();
            String sql = "update HoaDon set IdVC = :IdVC where MaHD = :Ma";
            Query query = session.createQuery(sql);
            query.setParameter("IdVC", IdVC);
            query.setParameter("Ma", ma);
            query.executeUpdate();
            tran.commit();
            return true;
        } catch (HibernateException ex) {
            ex.getMessage();

        }
        return false;

    }

    public static void main(String[] args) {
        HoaDonRepository s = new HoaDonRepository();
        for (HoaDon hoaDon : s.getAll()) {
            System.out.println(hoaDon.toString());
        }
    }

}

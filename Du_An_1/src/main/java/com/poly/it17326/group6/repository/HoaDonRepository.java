/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group6.repository;

import com.poly.it17326.group6.config.HibernateConfig;
import com.poly.it17326.group6.domainmodel.HoaDon;
import com.poly.it17326.group6.domainmodel.HoaDonChiTiet;
import com.poly.it17326.group6.domainmodel.KhachHang;
import com.poly.it17326.group6.domainmodel.TaiKhoan;
import com.poly.it17326.group6.domainmodel.Voucher;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        String sql = "from HoaDon hd order by cast (SUBSTRING(hd.MaHD,3,3) as int) desc ";
        Session session = HibernateConfig.getFACTORY().openSession();
        Query query = session.createQuery(sql);
        List<HoaDon> listHD = (ArrayList<HoaDon>) query.getResultList();
        session.close();
        return listHD;
    }
    // lay id hoa don

    public List<HoaDon> getAllDay() {
        String sql = "from HoaDon hd where createAt = :date order by cast (SUBSTRING(hd.MaHD,3,3) as int) desc ";
        Session session = HibernateConfig.getFACTORY().openSession();
        Query query = session.createQuery(sql);
        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("yyyy-MM-dd");
        try {
            Date d = new SimpleDateFormat("yyyy-MM-dd").parse(format.format(now));
            query.setParameter("date", d);
        } catch (ParseException ex) {
            Logger.getLogger(HoaDonRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<HoaDon> listHD = (ArrayList<HoaDon>) query.getResultList();
        session.close();
        return listHD;
    }

    public ArrayList<HoaDon> getIDHD(String Ma) {
        Session session = HibernateConfig.getFACTORY().openSession();
        Query query = session.createQuery("from HoaDon  where Ma = :Ma");
        query.setParameter("Ma", Ma);
        ArrayList<HoaDon> listIDHD = (ArrayList<HoaDon>) query.getResultList();
        session.close();
        return listIDHD;
    }

    public boolean updateHD(String ma, BigDecimal tongTien, int trangThai, KhachHang idKh, BigDecimal tienKH, BigDecimal tienCK, BigDecimal tienThua) {
        Transaction tran = null;
        int check = 0;
        try (Session session = HibernateConfig.getFACTORY().openSession();) {
            tran = session.beginTransaction();
            String sql = "update HoaDon set khachHang = :kh , tongTien = :tongtien , TrangThai = :TrangThai , tienKhachDua = :tienKH , tienChuyenKhoan = :tienCK , tienThua = :tienThua where MaHD = :Ma";
            Query query = session.createQuery(sql);
            query.setParameter("kh", idKh);
            query.setParameter("tongtien", tongTien);
            query.setParameter("TrangThai", trangThai);
            query.setParameter("tienKH", tienKH);
            query.setParameter("tienCK", tienCK);
            query.setParameter("tienThua", tienThua);
            query.setParameter("Ma", ma);
            check = query.executeUpdate();
            tran.commit();
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
            int i = (int) (Integer.parseInt(getAll().get(0).getMaHD().substring(2, 5)) + 1);
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
        Query query = session.createQuery("select hd from HoaDon hd join hd.taiKhoan where hd.TrangThai = :ma and hd.createAt = :date  order by cast (SUBSTRING(hd.MaHD,3,3) as int) desc");
        query.setParameter("ma", tt);
        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("yyyy-MM-dd");
        try {
            Date d = new SimpleDateFormat("yyyy-MM-dd").parse(format.format(now));
            query.setParameter("date", d);
        } catch (ParseException ex) {
            Logger.getLogger(HoaDonRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<HoaDon> listSearch = (ArrayList<HoaDon>) query.getResultList();
        session.close();
        return listSearch;
    }

    public List<HoaDon> getSearch_all(int tt) {
        Session session = HibernateConfig.getFACTORY().openSession();
        Query query = session.createQuery("select hd from HoaDon hd join hd.taiKhoan where hd.TrangThai = :ma  order by cast (SUBSTRING(hd.MaHD,3,3) as int) desc");
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

    public boolean updateTTHD(String ma, int trangThai, String lyDo) {
        Transaction tran = null;
        int check = 0;
        try (Session session = HibernateConfig.getFACTORY().openSession();) {
            tran = session.beginTransaction();
            String sql = "update HoaDon set TrangThai = :TrangThai , lyDo =:lyDo where MaHD = :Ma";
            Query query = session.createQuery(sql);
            query.setParameter("TrangThai", trangThai);
            query.setParameter("lyDo", lyDo);
            query.setParameter("Ma", ma);
            check = query.executeUpdate();
            tran.commit();
        }
        if (check != 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean updateHTTT(String ma, int hinhThucTT) {
        Transaction tran = null;
        int check = 0;
        try (Session session = HibernateConfig.getFACTORY().openSession();) {
            tran = session.beginTransaction();
            String sql = "update HoaDon set hinhThucTT = :hinhThucTT where MaHD = :Ma";
            Query query = session.createQuery(sql);
            query.setParameter("hinhThucTT", hinhThucTT);
            query.setParameter("Ma", ma);
            check = query.executeUpdate();
            tran.commit();
        }
        if (check != 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean updateHTBH(String ma, int hinhThucBH) {
        Transaction tran = null;
        int check = 0;
        try (Session session = HibernateConfig.getFACTORY().openSession();) {
            tran = session.beginTransaction();
            String sql = "update HoaDon set hinhThucBH = :hinhThucBH where MaHD = :Ma";
            Query query = session.createQuery(sql);
            query.setParameter("hinhThucBH", hinhThucBH);
            query.setParameter("Ma", ma);
            check = query.executeUpdate();
            tran.commit();
        }
        if (check != 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean updateGH(String ma, String tenNguoiNhan, String sdtNguoiNhan, String tenNguoiShip, String sdtNguoiShip, BigDecimal tienShip, String diaChi) {
        Transaction tran = null;
        int check = 0;
        try (Session session = HibernateConfig.getFACTORY().openSession();) {
            tran = session.beginTransaction();
            String sql = "update HoaDon set tenNguoiNhan = :tenNguoiNhan , sdtNguoiNhan = :sdtNguoiNhan , tenNguoiShip = :tenNguoiShip, sdtNguoiShip = :sdtNguoiShip , tienShip =: tienShip , diaChi =:diaChi  where MaHD = :Ma";
            Query query = session.createQuery(sql);
            query.setParameter("sdtNguoiShip", sdtNguoiShip);
            query.setParameter("tenNguoiNhan", tenNguoiNhan);
            query.setParameter("sdtNguoiNhan", sdtNguoiNhan);
            query.setParameter("tenNguoiShip", tenNguoiShip);
            query.setParameter("tienShip", tienShip);
            query.setParameter("diaChi", diaChi);
            query.setParameter("Ma", ma);
            check = query.executeUpdate();
            tran.commit();
        }
        if (check != 1) {
            return true;
        } else {
            return false;
        }

    }

    public List<HoaDonChiTiet> getListHDCT(String mahd) {
        List<HoaDonChiTiet> list = new ArrayList<>();
        try {
            Session session = HibernateConfig.getFACTORY().openSession();
            String hql = "from HoaDonChiTiet where MaHD = :mahd";
            org.hibernate.query.Query query = session.createQuery(hql);
            query.setParameter("mahd", mahd);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<HoaDon> getAll_HTBH(int hinhThucBH) {
        String sql = "from HoaDon hd where hinhThucBH = :hinhThucBH order by cast (SUBSTRING(hd.MaHD,3,3) as int) desc ";
        Session session = HibernateConfig.getFACTORY().openSession();
        Query query = session.createQuery(sql);
        query.setParameter("hinhThucBH", hinhThucBH);
        List<HoaDon> listHD = (ArrayList<HoaDon>) query.getResultList();
        session.close();
        return listHD;
    }

}

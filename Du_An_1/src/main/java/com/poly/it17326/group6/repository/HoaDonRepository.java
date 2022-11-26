/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group6.repository;

import com.poly.it17326.group6.config.HibernateConfig;
import com.poly.it17326.group6.domainmodel.HoaDon;
import com.poly.it17326.group6.domainmodel.TaiKhoan;
import com.poly.it17326.group6.domainmodel.Voucher;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Hp
 */
public class HoaDonRepository {

    Session session= HibernateConfig.getFACTORY().openSession();
    String sql="from HoaDon hd order by cast (SUBSTRING(hd.MaHD,3,3) as int) desc ";
    public ArrayList<HoaDon> getAll(){
        Query query= session.createQuery(sql);
        ArrayList<HoaDon> listHD=(ArrayList<HoaDon>) query.getResultList();
        return listHD;
    }
    // lay id hoa don
public ArrayList<HoaDon> getIDHD(String Ma){
        Query query= session.createQuery("from HoaDon  where Ma = :Ma");
        query.setParameter("Ma",Ma);
        ArrayList<HoaDon> listIDHD=(ArrayList<HoaDon>) query.getResultList();
        return listIDHD;
    }


public boolean updateHD(String ma,BigDecimal tongTien , int trangThai) {
        Transaction tran = null;
        int check=0;
        try ( Session session= HibernateConfig.getFACTORY().openSession();) {
            tran = session.beginTransaction();
            String sql = "update HoaDon set tongTien = :tongtien , TrangThai = :TrangThai where MaHD = :Ma";
            Query query = session.createQuery(sql);
            query.setParameter("tongtien", tongTien);
            query.setParameter("TrangThai", trangThai);
            query.setParameter("Ma", ma);    
            check =query.executeUpdate();
            tran.commit();
        }
        if(check!=1){
            return true;
        }else{
            return false;
        }
}
 
     public static void main(String[] args) {
       HoaDonRepository s = new HoaDonRepository();     
//       String ma="HD11";
//       BigDecimal n= BigDecimal.valueOf(10.0);
//       int tt=1;
//        if(s.updateHD(ma, n, tt)){
//            System.out.println("ss");
//        }else{
//            System.out.println("a");
//        }
         System.out.println(s.getAll());
    }
  

    public boolean addHD() {
        HoaDon hd = new HoaDon();
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            Transaction transaction = session.beginTransaction();
            int i = (int) (getAll().size() + 2);
            Date now = new Date();
            SimpleDateFormat format = new SimpleDateFormat();
            format.applyPattern("yyyy-MM-dd");
            String ngayTao = format.format(now);
            TaiKhoan tk = new TaiKhoan();
            tk.setId(1);
            Voucher vc = new Voucher();
            vc.setId(1);
            Date nt = new SimpleDateFormat("yyyy-MM-dd").parse(ngayTao);
            hd.setMaHD("HD" + i++);
            hd.setMaKH("KH" + i++);
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

    public ArrayList<HoaDon> getSearch(int tt) {
        Query query = session.createQuery("select hd from HoaDon hd join hd.taiKhoan where hd.TrangThai = :ma order by cast (SUBSTRING(hd.MaHD,3,3) as int) desc");
        query.setParameter("ma", tt);
        ArrayList<HoaDon> listSearch = (ArrayList<HoaDon>) query.getResultList();
        return listSearch;
    }
    
    public ArrayList<HoaDon> timKiemHD(String ma) {
        Query query = session.createQuery("select hd from HoaDon hd  join hd.taiKhoan where hd.MaHD = :ma order by cast (SUBSTRING(hd.MaHD,3,3) as int) asc");
        query.setParameter("ma", ma);
        ArrayList<HoaDon> listSearch = (ArrayList<HoaDon>) query.getResultList();
        return listSearch;
    }
  

}
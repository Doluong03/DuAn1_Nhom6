/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group6.repository;

import com.poly.it17326.group6.config.HibernateConfig;
import com.poly.it17326.group6.domainmodel.LoaiSP;
import com.poly.it17326.group6.domainmodel.Voucher;

import java.util.ArrayList;
import java.util.Date;

import java.util.List;
import java.util.Scanner;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.annotations.Source;

/**
 *
 * @author Hp
 */
public class VoucherResponsitory {

    String sql = "from Voucher ";

    public List<Voucher> getAll() {
        List<Voucher> listLsp = null;
        try ( Session session = HibernateConfig.getFACTORY().openSession();) {
            Query query = session.createQuery(sql);
            listLsp = (List<Voucher>) query.getResultList();
            session.close();

        } catch (Exception e) {
            e.getMessage();
        }
        return listLsp;
    }

    public boolean addVCH(Voucher voucher) {
        Transaction tran = null;
        try ( Session session = HibernateConfig.getFACTORY().openSession();) {
            tran = session.beginTransaction();
            session.save(voucher);
            session.close();
            return true;
        } catch (Exception e) {
            e.getMessage();
        }
        return false;
    }

    public boolean updateVCH(String Ma, Voucher voucher) {
        Transaction tran = null;

        try ( Session session = HibernateConfig.getFACTORY().openSession();) {
            tran = session.beginTransaction();
            String sql = "update Voucher set Ten = :Ten , NgayApDung = :NgayApDung"
                    + " , NgayKetThuc = :NgayKetThuc, SoLuong = :SoLuong ,"
                    + "TrangThai = :TrangThai,PhanTram = :PhanTram"
                    + "  where Ma = :Ma";
            Query query = session.createQuery(sql);
            query.setParameter("Ten", voucher.getTen());
            query.setParameter("NgayApDung", voucher.getNgayApDung());
            query.setParameter("NgayKetThuc", voucher.getNgayKetThuc());
            query.setParameter("SoLuong", voucher.getSoLuong());
            query.setParameter("TrangThai", voucher.getTrangThai());
            query.setParameter("PhanTram", voucher.getPhanTram());
            query.setParameter("Ma", Ma);
            query.executeUpdate();
            tran.commit();
            session.close();
            return true;

        } catch (Exception e) {
            e.getMessage();
        }
        return false;

    }

    public boolean updateSLVCH(String Ten, int SoLuong) {
        Transaction tran = null;

        try ( Session session = HibernateConfig.getFACTORY().openSession();) {
            tran = session.beginTransaction();
            String sql = "update Voucher set "
                    + " SoLuong = :SoLuong "
                    + "  where Ten = :Ten";
            Query query = session.createQuery(sql);
            query.setParameter("SoLuong", SoLuong);
            query.setParameter("Ten", Ten);
            query.executeUpdate();
            tran.commit();
            session.close();
            return true;

        } catch (Exception e) {
            e.getMessage();
        }
        return false;

    }

    public boolean updateTrangThai(String Ma, int TrangThai) {
        Transaction tran = null;

        try ( Session session = HibernateConfig.getFACTORY().openSession();) {
            tran = session.beginTransaction();
            String sql = "update Voucher set "
                    + " TrangThai = :TrangThai "
                    + "  where Ma = :Ma";
            Query query = session.createQuery(sql);
            query.setParameter("TrangThai", TrangThai);
            query.setParameter("Ma", Ma);
            query.executeUpdate();
            tran.commit();
            session.close();
            return true;

        } catch (Exception e) {
            e.getMessage();
        }
        return false;

    }

    public List<Voucher> FindDate(String NgayApDung, String NgayKetThuc) { // timf theo ngay

        try ( Session session = HibernateConfig.getFACTORY().openSession();) {
            String sql = "from Voucher where NgayApDung = '" + NgayApDung + "' "
                    + "and NgayKetThuc = '" + NgayKetThuc + "' ";
            Query query = session.createQuery(sql);
            List<Voucher> listVCH = (List<Voucher>) query.getResultList();

            session.close();
            return listVCH;
        }

    }

    public boolean XoaVCh(String Ma) {
        Transaction tran = null;

        try ( Session session = HibernateConfig.getFACTORY().openSession();) {
            tran = session.beginTransaction();
            String sql = "delete from Voucher"
                    + "  where Ma = :Ma";
            Query query = session.createQuery(sql);
            query.setParameter("Ma", Ma);
            query.executeUpdate();
            tran.commit();
            session.close();
            return true;

        } catch (Exception e) {
            e.getMessage();
        }
        return false;

    }
    
     public List<Voucher> Find(String Ma) { // timf theo ngay

        try ( Session session = HibernateConfig.getFACTORY().openSession();) {
            String sql = "from Voucher where Ma like '%"+Ma+"%'"
                    + "or Ten like '%"+Ma+"%'";
            Query query = session.createQuery(sql);
            List<Voucher> listVCH = (List<Voucher>) query.getResultList();
            session.close();
            return listVCH;
        }

    }
     
     public List<Voucher> FindTT(int TrangThai) { // timf theo ngay

        try ( Session session = HibernateConfig.getFACTORY().openSession();) {
            String sql = "from Voucher where TrangThai = :TrangThai";                 
            Query query = session.createQuery(sql);
            query.setParameter("TrangThai", TrangThai);           
            List<Voucher> listVCH = (List<Voucher>) query.getResultList();

            session.close();
            return listVCH;
        }

    }
     
       public List<Voucher> FindTEN(String Ten) { // timf theo ngay

        try ( Session session = HibernateConfig.getFACTORY().openSession();) {
            String sql = "from Voucher where Ten = :Ten";                 
            Query query = session.createQuery(sql);
            query.setParameter("Ten", Ten);           
            List<Voucher> listVCH = (List<Voucher>) query.getResultList();

            session.close();
            return listVCH;
        }

    }

    public static void main(String[] args) {
        VoucherResponsitory s = new VoucherResponsitory();
////         Voucher vc = new Voucher();
////         String ma = "vc02";
////         vc.setNgayApDung(new java.util.Date("01/10/2022"));
////         vc.setNgayKetThuc(new java.util.Date("05/10/2022"));
////         vc.setPhanTram(20f);
////         vc.setSoLuong(10);
////         vc.setTen("vc002");
////         vc.setTrangThai(1);
////         vc.setPhanTram(20f);
////         s.updateVCH(ma, vc);
//     
         Scanner sc = new Scanner(System.in);
        List<Voucher> sa = s.FindTEN("vc002");
        int sss = sa.get(0).getSoLuong();
         String ma = sa.get(0).getTen();
         String chon;
         System.out.println("oke hay no");
          chon = sc.nextLine();
         if(chon==chon){
              s.updateSLVCH(ma,(sss-1));
         }
    }
}

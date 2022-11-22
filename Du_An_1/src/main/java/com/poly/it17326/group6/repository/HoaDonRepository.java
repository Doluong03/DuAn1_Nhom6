/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group6.repository;

import com.poly.it17326.group6.config.HibernateConfig;
import com.poly.it17326.group6.domainmodel.ChiTietSP;
import com.poly.it17326.group6.domainmodel.HoaDon;
import com.poly.it17326.group6.domainmodel.HoaDonChiTiet;
import com.poly.it17326.group6.domainmodel.TinhTrang;
import java.math.BigDecimal;
import java.util.ArrayList;
import javax.persistence.Query;
import org.hibernate.HibernateError;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Hp
 */
public class HoaDonRepository {
    Session session= HibernateConfig.getFACTORY().openSession();
    String sql="from HoaDon";
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


public boolean updateHD(String Ma,BigDecimal tongtien,int IdTT) {
        Transaction tran = null;
        try ( Session session= HibernateConfig.getFACTORY().openSession();) {
            tran = session.beginTransaction();
            String sql = "update HoaDon \n"
                    + "set tongtien = :tongtien ,\n"
                     +" IdTT = :IdTT \n"
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


 
//     
//     public static void main(String[] args) {
//        ArrayList<HoaDon> s = new HoaDonRepository().getIDHD("hd02");
//          for (HoaDon hoaDon : s) {
//               int sa = hoaDon.getId();
//               System.out.println(sa);
//         }
//    }
  
}

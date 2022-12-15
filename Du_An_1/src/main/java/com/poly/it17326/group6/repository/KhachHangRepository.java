/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group6.repository;

import com.poly.it17326.group6.config.HibernateConfig;
import com.poly.it17326.group6.domainmodel.HoaDon;
import com.poly.it17326.group6.domainmodel.KhachHang;
import com.poly.it17326.group6.domainmodel.TaiKhoan;
import com.poly.it17326.group6.response.HoaDonresponse;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author 123
 */
public class KhachHangRepository {

    String sql = "from KhachHang ";

    public List<KhachHang> getList() {
        Session session = HibernateConfig.getFACTORY().openSession();
        Query query = session.createQuery(sql);
        ArrayList<KhachHang> listHD = (ArrayList<KhachHang>) query.getResultList();
        session.close();
        return listHD;
    }

    public Boolean addKh(KhachHang kh) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.save(kh);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return false;
        }
    }

    public ArrayList<KhachHang> CheckUser(String email) {
        Session session = HibernateConfig.getFACTORY().openSession();
        Query query = session.createQuery("from KhachHang where sdt = :sdt or ten =:ten or ma =: ma");
        query.setParameter("ma", email);
        query.setParameter("ten", email);
        query.setParameter("sdt", email);
        ArrayList<KhachHang> listTK = (ArrayList<KhachHang>) query.getResultList();
        session.close();
        return listTK;
    }

    public Boolean updateRank(BigDecimal tong, KhachHang KhachHang) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("	Select dbo.HoaDon.IdKH,Sum(dbo.HoaDon.Tongtien) From\n"
                    + "	dbo.HoaDon INNER JOIN dbo.KhachHang ON dbo.HoaDon.IdKH = dbo.KhachHang.Id\n"
                    + "	where dbo.HoaDon.IdKH= :tong \n"
                    + "	group by dbo.HoaDon.IdKH");
            query.setParameter("tong", tong);
            query.executeUpdate();
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<HoaDonresponse> getListLS(String ma) {
        List<HoaDonresponse> listsv = new ArrayList<>();
        String query = " select HoaDon.Ma , KhachHang.Ma ,HoaDon.TongTien,HoaDon.Create_at from KhachHang JOIN HoaDon on KhachHang.Id=HoaDon.IdKH where KhachHang.Ma = ? ";
        try (Connection con = ConectionRespon.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, ma);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDonresponse sv = new HoaDonresponse();
                sv.setMaHD(rs.getString(1));
                sv.setMaKH(rs.getString(2));
                sv.setTongTien(rs.getBigDecimal(3));
                sv.setNgayTao(rs.getDate(4));

                listsv.add(sv);
            }
        } catch (Exception e) {
        }
        return listsv;
    }

    public KhachHang getOne(int id) {
        Session session = HibernateConfig.getFACTORY().openSession();
        String sql1 = sql + " Where  id = : id";
        Query querry = session.createQuery(sql1, KhachHang.class);
        querry.setParameter("id", id);
        return (KhachHang) querry.getSingleResult();
    }

    public Boolean updateKh(KhachHang kh) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(kh);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return false;
        }
    }
}

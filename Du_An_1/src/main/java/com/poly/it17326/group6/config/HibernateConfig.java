package com.poly.it17326.group6.config;

import com.poly.it17326.group6.domainmodel.KhoiLuong;
import com.poly.it17326.group6.domainmodel.ChiTietSP;
import com.poly.it17326.group6.domainmodel.ChitietKhuyenMai;
import com.poly.it17326.group6.domainmodel.ChucVu;
import com.poly.it17326.group6.domainmodel.DonViTinh;
import com.poly.it17326.group6.domainmodel.HoaDon;
import com.poly.it17326.group6.domainmodel.HoaDonChiTiet;
import com.poly.it17326.group6.domainmodel.KhachHang;

import com.poly.it17326.group6.domainmodel.LoaiSP;
import com.poly.it17326.group6.domainmodel.NSX;
import com.poly.it17326.group6.domainmodel.SanPham;
import com.poly.it17326.group6.domainmodel.TaiKhoan;
import com.poly.it17326.group6.domainmodel.ThanhPhan;

import com.poly.it17326.group6.domainmodel.Voucher;
import com.poly.it17326.group6.domainmodel.khuyenmai;

import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

public class HibernateConfig {

    private static final SessionFactory FACTORY;

    static {
        Configuration conf = new Configuration();

        Properties properties = new Properties();
        properties.put(Environment.DIALECT, "org.hibernate.dialect.SQLServerDialect");
        properties.put(Environment.DRIVER, "com.microsoft.sqlserver.jdbc.SQLServerDriver");
        properties.put(Environment.URL, "jdbc:sqlserver://localhost:1433;databaseName=FINALASS_GROUP6_PRO10411;useUnicode=true;characterEncoding=UTF-8");
        properties.put(Environment.USER, "sa");

        properties.put(Environment.PASS, "1");

        properties.put(Environment.SHOW_SQL, "true");

        conf.setProperties(properties);
        conf.addAnnotatedClass(TaiKhoan.class);
        conf.addAnnotatedClass(ChucVu.class);
        conf.addAnnotatedClass(KhoiLuong.class);
        conf.addAnnotatedClass(ChiTietSP.class);
        conf.addAnnotatedClass(LoaiSP.class);
        conf.addAnnotatedClass(NSX.class);
        conf.addAnnotatedClass(ThanhPhan.class);
        conf.addAnnotatedClass(TaiKhoan.class);
        conf.addAnnotatedClass(Voucher.class);
        conf.addAnnotatedClass(ChucVu.class);
        conf.addAnnotatedClass(HoaDon.class);
        conf.addAnnotatedClass(HoaDonChiTiet.class);
        conf.addAnnotatedClass(KhachHang.class);
        conf.addAnnotatedClass(khuyenmai.class);
        conf.addAnnotatedClass(ChitietKhuyenMai.class);
        conf.addAnnotatedClass(DonViTinh.class);
        conf.addAnnotatedClass(SanPham.class);
        conf.addAnnotatedClass(HoaDon.class);

        ServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySettings(conf.getProperties()).build();
        FACTORY = conf.buildSessionFactory(registry);

    }

    public static SessionFactory getFACTORY() {
        return FACTORY;
    }

    public static void main(String[] args) {
        getFACTORY();
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group6.config;
import com.poly.it17326.group6.domainmodel.Anh;
import com.poly.it17326.group6.domainmodel.ChiTietSP;
import com.poly.it17326.group6.domainmodel.ChucVu;
import com.poly.it17326.group6.domainmodel.HoaDon;
import com.poly.it17326.group6.domainmodel.LoaiSP;
import com.poly.it17326.group6.domainmodel.NSX;
import com.poly.it17326.group6.domainmodel.SanPham;
import com.poly.it17326.group6.domainmodel.TaiKhoan;
import com.poly.it17326.group6.domainmodel.ThanhPhan;
import com.poly.it17326.group6.domainmodel.TinhTrang;
import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
/**
 *
 * @author 123
 */
public class HibernateConfig {
    private static final SessionFactory FACTORY;

    static {
        Configuration conf = new Configuration();

        Properties properties = new Properties();
        properties.put(Environment.DIALECT, "org.hibernate.dialect.SQLServerDialect");
        properties.put(Environment.DRIVER, "com.microsoft.sqlserver.jdbc.SQLServerDriver");
        properties.put(Environment.URL, "jdbc:sqlserver://localhost:1433;databaseName=FINALASS_GROUP6_PRO1041");
        properties.put(Environment.USER, "sa");
        properties.put(Environment.PASS, "123123");
        properties.put(Environment.SHOW_SQL, "true");

        conf.setProperties(properties);
        conf.addAnnotatedClass(TaiKhoan.class);
        conf.addAnnotatedClass(ChucVu.class);
        conf.addAnnotatedClass(Anh.class);
        conf.addAnnotatedClass(ChiTietSP.class);
        conf.addAnnotatedClass(LoaiSP.class);
        conf.addAnnotatedClass(NSX.class);
        conf.addAnnotatedClass(ThanhPhan.class);
        conf.addAnnotatedClass(SanPham.class);
                conf.addAnnotatedClass(HoaDon.class);
conf.addAnnotatedClass(TinhTrang.class);
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

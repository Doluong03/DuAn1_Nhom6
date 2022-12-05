/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.poly.it17326.group6.view;

import com.poly.it17326.group6.domainmodel.HaoDonChitietdomain;
import com.poly.it17326.group6.response.HoaDonCTResponse;
import com.poly.it17326.group6.service.HoaDonChiTietService;
import com.poly.it17326.group6.service.ThongKeService;
import com.poly.it17326.group6.service.haodonservice;

import com.poly.it17326.group6.service.impl.HOaDonChiTietServiceIplm;
import com.poly.it17326.group6.service.impl.ThongKeServiceImpl;
import com.poly.it17326.group6.service.impl.haodonserviceImpl;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Hp
 */
public class NewJPanel extends javax.swing.JPanel {

    private ThongKeService thongKeService = new ThongKeServiceImpl();
    private haodonservice haodon = new haodonserviceImpl();

    public NewJPanel() {
        initComponents();
        loadcbbYear();
        loadDataNgay();
        loadtable(haodon.getlistALLHDCT());

    }
    public static String year;
    public static String year2;

    private void loadDataNgay() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();

        String sw = formatter.format(date);
        List<BigDecimal> listNgay = thongKeService.getListDtNgay(sw);
        if (listNgay.isEmpty()) {
            jlbTongtienngay.setText("0" + "VND");
            return;
        }

        jlbTongtienngay.setText(listNgay + "VND");

        List<Integer> ListHDThanhcong = thongKeService.getListHdThanhcongNgay(sw);
        List<Integer> ListHDhuy = thongKeService.getListHDDAHUYNgay(sw);

        jlbDahuyNgay.setText(String.valueOf(ListHDhuy));
        jlbthanhcongNgay.setText(String.valueOf(ListHDThanhcong));
    }

    private void loadtable(List<HaoDonChitietdomain> list) {
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"Thời gian", "Số HD", "Số Lượng", "Doanh thu"});
        for (HaoDonChitietdomain hddm : list) {
            model.addRow(new Object[]{hddm.getNgay(), hddm.getSoHd(), hddm.getSoluongSp(), hddm.getTongtien()});
        }

        tbThongke.setModel(model);

    }

    private void loadcbbYear() {
        List<Integer> listYear = new ArrayList<>();
        for (int i = 2022; i <= 2030; i++) {
            listYear.add(i);
        }
        for (Integer integer : listYear) {
            cbNam.addItem(String.valueOf(integer));
        }
        for (Integer integer : listYear) {
            cbNam2.addItem(String.valueOf(integer));
        }
    }

    private void load() {
        year2 = cbNam2.getSelectedItem().toString();

        List<BigDecimal> listThang1 = thongKeService.getListDtThang(year2, "01");
        List<BigDecimal> listThang2 = thongKeService.getListDtThang(year2, "02");
        List<BigDecimal> listThang3 = thongKeService.getListDtThang(year2, "03");
        List<BigDecimal> listThang4 = thongKeService.getListDtThang(year2, "04");

        BigDecimal val1 = new BigDecimal(0);
        BigDecimal val2 = new BigDecimal(0);
        BigDecimal val3 = new BigDecimal(0);
        BigDecimal val4 = new BigDecimal(0);
        for (BigDecimal bigDecimal : listThang1) {

        }

        for (BigDecimal bigDecimal : listThang2) {
            val2 = bigDecimal;
        }

        for (BigDecimal bigDecimal : listThang3) {
            val3 = bigDecimal;
        }

        for (BigDecimal bigDecimal : listThang4) {
            val4 = bigDecimal;
        }

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.setValue(val1, "", "THÁNG 1");
        dataset.setValue(val2, "", "THÁNG 2");
        dataset.setValue(val3, "", "THÁNG 3");
        dataset.setValue(val4, "", "THÁNG 4");

        JFreeChart oChart = ChartFactory.createBarChart("BIỂU ĐỒ DOANH THU NĂM " + "", "Quý 1", "DOANH THU(VND)", dataset, PlotOrientation.VERTICAL, false, false, false);

        ChartPanel oPanel = new ChartPanel(oChart);
        jpan2.setLayout(new java.awt.BorderLayout());
        jpan2.removeAll();
        jpan2.add(oPanel);
        jpan2.validate();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel3 = new javax.swing.JLayeredPane();
        jlbtongtiennam = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jlbThanhcongNam = new javax.swing.JLabel();
        jlbdahuyNam = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        panel1 = new javax.swing.JLayeredPane();
        jlbTongtienngay = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jlbthanhcongNgay = new javax.swing.JLabel();
        jlbDahuyNgay = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        panel2 = new javax.swing.JLayeredPane();
        jlbtongtienthang = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jlbThanhcongThang = new javax.swing.JLabel();
        jlbDahuyThang = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jLayeredPane3 = new javax.swing.JLayeredPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbThongke = new javax.swing.JTable();
        idcNgayBD = new com.toedter.calendar.JDateChooser();
        idcNgayKt = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnLoc = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLayeredPane4 = new javax.swing.JLayeredPane();
        jpan2 = new javax.swing.JLayeredPane();
        cbQUy = new javax.swing.JComboBox<>();
        cbNam2 = new javax.swing.JComboBox<>();
        cbThang = new javax.swing.JComboBox<>();
        cbNam = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        panel3.setBackground(java.awt.Color.blue);
        panel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        panel3.setForeground(new java.awt.Color(102, 51, 255));

        jlbtongtiennam.setText("jLabel1");

        jLabel22.setText("Thành công :");

        jlbThanhcongNam.setText("jLabel4");

        jlbdahuyNam.setText("jLabel5");

        jLabel25.setText("Đã hủy :");

        panel3.setLayer(jlbtongtiennam, javax.swing.JLayeredPane.DEFAULT_LAYER);
        panel3.setLayer(jLabel22, javax.swing.JLayeredPane.DEFAULT_LAYER);
        panel3.setLayer(jlbThanhcongNam, javax.swing.JLayeredPane.DEFAULT_LAYER);
        panel3.setLayer(jlbdahuyNam, javax.swing.JLayeredPane.DEFAULT_LAYER);
        panel3.setLayer(jLabel25, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout panel3Layout = new javax.swing.GroupLayout(panel3);
        panel3.setLayout(panel3Layout);
        panel3Layout.setHorizontalGroup(
            panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlbtongtiennam, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel3Layout.createSequentialGroup()
                        .addGroup(panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlbThanhcongNam, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlbdahuyNam, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(197, Short.MAX_VALUE))
        );
        panel3Layout.setVerticalGroup(
            panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel3Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jlbtongtiennam)
                .addGap(18, 18, 18)
                .addGroup(panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(jlbThanhcongNam))
                .addGap(18, 18, 18)
                .addGroup(panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(jlbdahuyNam))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        panel1.setBackground(java.awt.Color.blue);
        panel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        panel1.setForeground(new java.awt.Color(102, 51, 255));

        jlbTongtienngay.setText("jLabel1");

        jLabel12.setText("Thành công :");

        jlbthanhcongNgay.setText("jLabel4");

        jlbDahuyNgay.setText("jLabel5");

        jLabel13.setText("Đã hủy :");

        panel1.setLayer(jlbTongtienngay, javax.swing.JLayeredPane.DEFAULT_LAYER);
        panel1.setLayer(jLabel12, javax.swing.JLayeredPane.DEFAULT_LAYER);
        panel1.setLayer(jlbthanhcongNgay, javax.swing.JLayeredPane.DEFAULT_LAYER);
        panel1.setLayer(jlbDahuyNgay, javax.swing.JLayeredPane.DEFAULT_LAYER);
        panel1.setLayer(jLabel13, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlbTongtienngay, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlbthanhcongNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlbDahuyNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(197, Short.MAX_VALUE))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jlbTongtienngay)
                .addGap(18, 18, 18)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jlbthanhcongNgay))
                .addGap(18, 18, 18)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jlbDahuyNgay))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        panel2.setBackground(new java.awt.Color(102, 102, 255));
        panel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        panel2.setForeground(new java.awt.Color(102, 51, 255));

        jlbtongtienthang.setText("jLabel1");

        jLabel17.setText("Thành công :");

        jlbThanhcongThang.setText("jLabel4");

        jlbDahuyThang.setText("jLabel5");

        jLabel20.setText("Đã hủy :");

        panel2.setLayer(jlbtongtienthang, javax.swing.JLayeredPane.DEFAULT_LAYER);
        panel2.setLayer(jLabel17, javax.swing.JLayeredPane.DEFAULT_LAYER);
        panel2.setLayer(jlbThanhcongThang, javax.swing.JLayeredPane.DEFAULT_LAYER);
        panel2.setLayer(jlbDahuyThang, javax.swing.JLayeredPane.DEFAULT_LAYER);
        panel2.setLayer(jLabel20, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout panel2Layout = new javax.swing.GroupLayout(panel2);
        panel2.setLayout(panel2Layout);
        panel2Layout.setHorizontalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlbtongtienthang, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlbThanhcongThang, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlbDahuyThang, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(197, Short.MAX_VALUE))
        );
        panel2Layout.setVerticalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jlbtongtienthang)
                .addGap(18, 18, 18)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jlbThanhcongThang))
                .addGap(18, 18, 18)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(jlbDahuyThang))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        tbThongke.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tbThongke);

        idcNgayBD.setDateFormatString("yyyy-MM-dd");

        idcNgayKt.setDateFormatString("yyyy-MM-dd");

        jLabel3.setText("Từ");

        jLabel4.setText("Đến");

        btnLoc.setText("Lọc");
        btnLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocActionPerformed(evt);
            }
        });

        jButton1.setText("Xuất Exel");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLayeredPane3.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(idcNgayBD, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(idcNgayKt, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(btnLoc, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(jButton1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane3Layout = new javax.swing.GroupLayout(jLayeredPane3);
        jLayeredPane3.setLayout(jLayeredPane3Layout);
        jLayeredPane3Layout.setHorizontalGroup(
            jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1220, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jLayeredPane3Layout.createSequentialGroup()
                .addGap(177, 177, 177)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(idcNgayBD, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(idcNgayKt, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnLoc, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(166, 166, 166)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jLayeredPane3Layout.setVerticalGroup(
            jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane3Layout.createSequentialGroup()
                .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane3Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(idcNgayBD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(idcNgayKt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addComponent(btnLoc)))
                    .addGroup(jLayeredPane3Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jButton1)))
                .addGap(37, 37, 37)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE))
        );

        jTabbedPane4.addTab("Bảng", jLayeredPane3);

        javax.swing.GroupLayout jpan2Layout = new javax.swing.GroupLayout(jpan2);
        jpan2.setLayout(jpan2Layout);
        jpan2Layout.setHorizontalGroup(
            jpan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jpan2Layout.setVerticalGroup(
            jpan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 305, Short.MAX_VALUE)
        );

        cbQUy.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Quý 1", "Quý 2", "Quý 3" }));
        cbQUy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbQUyMouseClicked(evt);
            }
        });
        cbQUy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbQUyActionPerformed(evt);
            }
        });

        jLayeredPane4.setLayer(jpan2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane4.setLayer(cbQUy, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane4.setLayer(cbNam2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane4Layout = new javax.swing.GroupLayout(jLayeredPane4);
        jLayeredPane4.setLayout(jLayeredPane4Layout);
        jLayeredPane4Layout.setHorizontalGroup(
            jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane4Layout.createSequentialGroup()
                .addComponent(jpan2)
                .addContainerGap())
            .addGroup(jLayeredPane4Layout.createSequentialGroup()
                .addGap(280, 280, 280)
                .addComponent(cbQUy, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(cbNam2, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(678, Short.MAX_VALUE))
        );
        jLayeredPane4Layout.setVerticalGroup(
            jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane4Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbQUy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbNam2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addComponent(jpan2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane4.addTab("Biểu đồ", jLayeredPane4);

        cbThang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        cbThang.setSelectedItem(12);
        cbThang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbThangActionPerformed(evt);
            }
        });

        cbNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbNamActionPerformed(evt);
            }
        });

        jLabel1.setText("Năm");

        jLabel2.setText("Tháng");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(62, 62, 62)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cbThang, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbNam, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(246, 246, 246))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(panel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(cbNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbThang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane4))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cbThangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbThangActionPerformed
        String thang = cbThang.getSelectedItem().toString();
        String yearr = year;

        List<BigDecimal> lists = thongKeService.getListDtThang(yearr, thang);
        if (lists.isEmpty()) {
            jlbtongtienthang.setText(String.valueOf(0));
        } else {

            jlbtongtienthang.setText(lists + "VND");
        }

        List<Integer> ListHDThanhcong = thongKeService.getListHDTAHNHCONGTHANg(yearr, thang);
        List<Integer> ListHDhuy = thongKeService.getListHDDahuyThang(year, thang);

        jlbDahuyThang.setText(String.valueOf(ListHDhuy));
        jlbThanhcongThang.setText(String.valueOf(ListHDThanhcong));

    }//GEN-LAST:event_cbThangActionPerformed

    private void cbNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbNamActionPerformed
        year = cbNam.getSelectedItem().toString();
        List<BigDecimal> lists = thongKeService.getListDtNam(year);
        if (lists.isEmpty()) {
            jlbtongtiennam.setText(String.valueOf(0));
        } else {

            jlbtongtiennam.setText(String.valueOf(lists) + "VND");
        }
        List<Integer> ListHDThanhcong = thongKeService.getListHDthanhcongNam(year);
        List<Integer> ListHDhuy = thongKeService.getListHDDAHUYnam(year);

        jlbdahuyNam.setText(String.valueOf(ListHDhuy));
        jlbThanhcongNam.setText(String.valueOf(ListHDThanhcong));
    }//GEN-LAST:event_cbNamActionPerformed
    private void loadQUy2() {
        List<BigDecimal> listThang1 = thongKeService.getListDtThang(year2, "05");
        List<BigDecimal> listThang2 = thongKeService.getListDtThang(year2, "06");
        List<BigDecimal> listThang3 = thongKeService.getListDtThang(year2, "07");
        List<BigDecimal> listThang4 = thongKeService.getListDtThang(year2, "08");

        BigDecimal val1 = new BigDecimal(0);
        BigDecimal val2 = new BigDecimal(0);
        BigDecimal val3 = new BigDecimal(0);
        BigDecimal val4 = new BigDecimal(0);
        for (BigDecimal bigDecimal : listThang1) {

        }

        for (BigDecimal bigDecimal : listThang2) {
            val2 = bigDecimal;
        }

        for (BigDecimal bigDecimal : listThang3) {
            val3 = bigDecimal;
        }

        for (BigDecimal bigDecimal : listThang4) {
            val4 = bigDecimal;
        }

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.setValue(val1, "", "THÁNG 5");
        dataset.setValue(val2, "", "THÁNG 6");
        dataset.setValue(val3, "", "THÁNG 7");
        dataset.setValue(val4, "", "THÁNG 8");

        JFreeChart oChart = ChartFactory.createBarChart("BIỂU ĐỒ DOANH THU NĂM " + "", "Quý 1", "DOANH THU(VND)", dataset, PlotOrientation.VERTICAL, false, false, false);

        ChartPanel oPanel = new ChartPanel(oChart);
        jpan2.setLayout(new java.awt.BorderLayout());
        jpan2.removeAll();
        jpan2.add(oPanel);
        jpan2.validate();
    }

    private void laodquye3() {
        List<BigDecimal> listThang9 = thongKeService.getListDtThang(year2, "09");
        List<BigDecimal> listThang10 = thongKeService.getListDtThang(year2, "10");
        List<BigDecimal> listThang11 = thongKeService.getListDtThang(year2, "11");
        List<BigDecimal> listThang12 = thongKeService.getListDtThang(year2, "12");

        BigDecimal val1 = (new BigDecimal(0));
        BigDecimal val2 = new BigDecimal(0);
        BigDecimal val3 = (new BigDecimal(0));
        BigDecimal val4 = new BigDecimal(0);

        for (BigDecimal bigDecimal : listThang9) {
            val1 = bigDecimal;
        }
        for (BigDecimal bigDecimal : listThang10) {
            val2 = bigDecimal;
        }
        for (BigDecimal bigDecimal : listThang11) {
            val3 = bigDecimal;
        }
        for (BigDecimal bigDecimal : listThang12) {
            val4 = bigDecimal;
        }
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.setValue(val1, "", "THÁNG 9");
        dataset.setValue(val2, "", "THÁNG 10");
        dataset.setValue(val3, "", "THÁNG 11");
        dataset.setValue(val4, "", "THÁNG 12");

        JFreeChart oChart = ChartFactory.createBarChart("BIỂU ĐỒ DOANH THU NĂM " + "", "Quý 1", "DOANH THU(VND)", dataset, PlotOrientation.VERTICAL, false, false, false);

        ChartPanel oPanel = new ChartPanel(oChart);
        jpan2.setLayout(new java.awt.BorderLayout());
        jpan2.removeAll();
        jpan2.add(oPanel);
        jpan2.validate();

    }
    private void cbQUyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbQUyActionPerformed
        if (cbQUy.getSelectedItem().equals("Quý 1")) {
            load();
        } else if (cbQUy.getSelectedItem().equals("Quý 2")) {
            loadQUy2();
        } else if (cbQUy.getSelectedItem().equals("Quý 3")) {
            laodquye3();
        }
    }//GEN-LAST:event_cbQUyActionPerformed

    private void cbQUyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbQUyMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cbQUyMouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Thongke");
            XSSFRow row = null;
            Cell cell = null;
            row = sheet.createRow(3);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("Stt");

            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("Thời gian");

            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("Số HD");

            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("Số Lượng");

            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue("Doanh thu");

            for (int i = 0; i < tbThongke.getRowCount(); i++) {

                row = sheet.createRow(5 + i);
                cell = row.createCell(0, CellType.NUMERIC);
                cell.setCellValue(i + 1);

                cell = row.createCell(1, CellType.NUMERIC);
                cell.setCellValue(tbThongke.getValueAt(i, 0).toString());

                cell = row.createCell(2, CellType.NUMERIC);
                cell.setCellValue(tbThongke.getValueAt(i, 1).toString());

                cell = row.createCell(3, CellType.NUMERIC);
                cell.setCellValue(tbThongke.getValueAt(i, 2).toString());

                cell = row.createCell(4, CellType.NUMERIC);
                cell.setCellValue(tbThongke.getValueAt(i, 3).toString());

            }

            File file = new File("D://Thongke.xlsx");
            try {
                FileOutputStream fis = new FileOutputStream(file);
                workbook.write(fis);
                fis.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            JOptionPane.showMessageDialog(this, "Xuat thanh cong");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Xuat that bai");
        }
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocActionPerformed
        Date bd = idcNgayBD.getDate();
        Date kt = idcNgayKt.getDate();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String bd1 = df.format(bd);
        String kt1 = df.format(kt);
        List<HaoDonChitietdomain> list = haodon.getlistTehongay(bd1, kt1);
        loadtable(list);
    }//GEN-LAST:event_btnLocActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLoc;
    private javax.swing.JComboBox<String> cbNam;
    private javax.swing.JComboBox<String> cbNam2;
    private javax.swing.JComboBox<String> cbQUy;
    private javax.swing.JComboBox<String> cbThang;
    private com.toedter.calendar.JDateChooser idcNgayBD;
    private com.toedter.calendar.JDateChooser idcNgayKt;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLayeredPane jLayeredPane3;
    private javax.swing.JLayeredPane jLayeredPane4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JLabel jlbDahuyNgay;
    private javax.swing.JLabel jlbDahuyThang;
    private javax.swing.JLabel jlbThanhcongNam;
    private javax.swing.JLabel jlbThanhcongThang;
    private javax.swing.JLabel jlbTongtienngay;
    private javax.swing.JLabel jlbdahuyNam;
    private javax.swing.JLabel jlbthanhcongNgay;
    private javax.swing.JLabel jlbtongtiennam;
    private javax.swing.JLabel jlbtongtienthang;
    private javax.swing.JLayeredPane jpan2;
    private javax.swing.JLayeredPane panel1;
    private javax.swing.JLayeredPane panel2;
    private javax.swing.JLayeredPane panel3;
    private javax.swing.JTable tbThongke;
    // End of variables declaration//GEN-END:variables
}

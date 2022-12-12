/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.poly.it17326.group6.view;

import com.poly.it17326.group6.domainmodel.ChiTietSP;
import com.poly.it17326.group6.domainmodel.ChitietKhuyenMai;
import com.poly.it17326.group6.domainmodel.Voucher;
import com.poly.it17326.group6.domainmodel.khuyenmai;
import com.poly.it17326.group6.response.ChiTietSpResponse;
import com.poly.it17326.group6.response.ChiTietSpResponse_2;
import com.poly.it17326.group6.response.KhuyenMaiResponse;
import com.poly.it17326.group6.response.VocherReponse;
import com.poly.it17326.group6.service.ChiTietSPService;
import com.poly.it17326.group6.service.VoucherService;
import com.poly.it17326.group6.service.impl.ChiTietSPServiceImpl;
import com.poly.it17326.group6.service.impl.VoucherServiceIplm;
import com.poly.it17326.group6.service.impl.khuyenmaiServiceIplm;
import com.poly.it17326.group6.service.khuyenmaiService;
import java.awt.JobAttributes;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author 123
 */
public class FormKhuyenMai extends javax.swing.JPanel {

    private VoucherService voucherService = new VoucherServiceIplm();
    private DefaultTableModel model;
    private DefaultComboBoxModel defaultComboBoxModel;
    private ChiTietSPService chiTietSPService = new ChiTietSPServiceImpl();
    private khuyenmaiService kmService = new khuyenmaiServiceIplm();

    public FormKhuyenMai() {
        initComponents();
        //      LoadTable(voucherService.getAll());
        LoadTableSPKM(chiTietSPService.getAll());
        LoadTableKM(kmService.getALL());
        loadCBBTT2();
        auto();
        lbAnhKM1.setIcon(new ImageIcon("D:\\Nhom6_PRO1041\\Anh\\Sale.png"));
    }

    private void update(int a) {
        long d = a * 1000;
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {

                String ma = txtMaKM1.getText();
                if (ma.isEmpty()) {
                    return;
                }
                List<khuyenmai> km = kmService.FindKM(ma);
                if (km.isEmpty()) {
                    return;
                }
                int id = km.get(0).getId();
                kmService.UpdateTT(id);
                JOptionPane.showMessageDialog(lbtb, ma + " đã ngừng hoạt động");
                for (khuyenmai x : kmService.getALL()) {
                    if (txtMaKM1.getText().equals(x.getMa())) {
                        kmService.DeleteCtKm(x);
                    }
                }
                LoadTableKM(kmService.FindTT(0));

            }

        };

        Timer timer = new Timer();
        timer.schedule(timerTask, d);

    }

    private String doiNgay(Date d) {
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("yyyy-MM-dd");
        String ngayTao = format.format(d);
        return ngayTao;
    }

    private void loadCBBTT2() {
        List<String> listTT = new ArrayList<>();
        listTT.add("Đang hoạt động");
        listTT.add("Ngừng hoạt động");

        for (String string : listTT) {
            cbTrangThai1.addItem(string);
        }

    }

    private boolean check() {

        if (txtMaKM1.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Khong duoc de trong ma khuyen mai");
            return false;
        }
        if (txtTenKM1.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Khong duoc de trong ten khuyen mai");
            return false;
        }

        try {
            if (txtPhantram1.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Khong duoc de trong gia tri khuyen mai");
                return false;

            } else if (Float.parseFloat(txtPhantram1.getText()) <= 0) {
                JOptionPane.showMessageDialog(this, "gia tri phai >0");
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "gia tri phai la chu so");
            return false;
        }

        if (idcNgayBD2.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Khong duoc de trong ngay bat dau");
            return false;
        }
        if (idcNgayKT2.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Khong duoc de trong ngay ket thuc");
            return false;
        }
        if (idcNgayKT2.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Khong duoc de trong ngay ket thuc");
            return false;
        }
        Date current = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        if (sdf.format(current).equals(sdf.format(idcNgayKT2.getDate()))) {
            JOptionPane.showMessageDialog(this, "Ngày kết thúc phải lớn > ngày hiện tại");
            return false;
        } else if (idcNgayKT2.getDate().compareTo(current) < 0) { // ngay truoc
            JOptionPane.showMessageDialog(this, "Ngày kết thúc phải lớn > ngày hiện tại");
            return false;
        }
        if (sdf.format(current).equals(sdf.format(idcNgayBD2.getDate()))) {
            return true;
        }
        if (idcNgayBD2.getDate().compareTo(current) < 0) { // ngay truoc
            JOptionPane.showMessageDialog(this, "Ngày bắt đầu phải lớn >= ngày hiện tại");
            return false;
        } else if ((idcNgayKT2.getDate())
                .compareTo(idcNgayBD2.getDate()) < 0) {
            JOptionPane.showMessageDialog(this, "Ngày bắt đầu phải < ngày kết thúc");
            return false;
        }
        return true;
    }

    private void TextfieldKM(int i) {
        String ma = tbKhuyenMai2.getValueAt(i, 0).toString();
        List<khuyenmai> listKM = kmService.FindKM(ma);
        Date ngaybd = listKM.get(0).getNgay_bat_dau();
        Date ngaykt = listKM.get(0).getNgay_ket_thuc();
        txtMaKM1.setText(ma);
        txtPhantram1.setText(tbKhuyenMai2.getValueAt(i, 3).toString());
        txtTenKM1.setText(tbKhuyenMai2.getValueAt(i, 1).toString());
        cbTrangThai1.setSelectedItem(tbKhuyenMai2.getValueAt(i, 4).toString());
        idcNgayBD2.setDate(ngaybd);
        idcNgayKT2.setDate(ngaykt);

        int idKm = listKM.get(0).getId();
        List<KhuyenMaiResponse> listspkm = kmService.FindCTKM(idKm);

        if (listspkm.isEmpty()) {
            List<KhuyenMaiResponse> listspk = new ArrayList<>();
            LoadTableSPKM2(listspk);
            return;
        } else {
            LoadTableSPKM2(listspkm);
            return;
        }
    }

    private void LoadTableSPKM2(List<KhuyenMaiResponse> list) {
        model = (DefaultTableModel) tbSanPhamKm.getModel();
        model.setRowCount(0);
        model.setColumnIdentifiers(new String[]{"Mã SP", "Tên SP", "Giá cũ", "Giá Mới"});

        for (KhuyenMaiResponse km : list) {
            model.addRow(new Object[]{
                km.getMaSp(),
                km.getTenSp(), km.getGiacu(), km.getGiaMoi()});

        }
    }

    boolean cheked = false;

    private void LoadTableSPKM(List<ChiTietSpResponse> list) {
        model = (DefaultTableModel) tbSanPhamKm.getModel();
        model.setRowCount(0);
        model.setColumnIdentifiers(new String[]{"Mã SP", "Tên SP", "Giá cũ", "Giá mới", ""});

        for (ChiTietSpResponse vocherReponse : list) {
            model.addRow(new Object[]{
                vocherReponse.getMa(),
                vocherReponse.getTen(), vocherReponse.getDonGia(), "", cheked});

        }
    }

    private void auto() {
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    Date date = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    List<khuyenmai> listKM = kmService.getALL();

                    for (khuyenmai x : listKM) {
                        if (sdf.format(x.getNgay_ket_thuc()).equals(sdf.equals(date))) {
                            int idkm = x.getId();
                            kmService.UpdateTT(idkm);
                            LoadTableKM(kmService.getALL());
                        }
                    }

                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException ex) {
                    }
                }
            }

        }.start();
    }

    private void LoadTableKM(List<khuyenmai> list) {
        model = (DefaultTableModel) tbKhuyenMai2.getModel();
        model.setRowCount(0);
        model.setColumnIdentifiers(new String[]{"Mã KM", "Tên KM", "Loại KM", "Giá trị", "Trạng thái"});

        for (khuyenmai khuyenmai : list) {
            model.addRow(new Object[]{
                khuyenmai.getMa(),
                khuyenmai.getTen(), khuyenmai.getLoaiKhuyenMai(), khuyenmai.getGiaTri(), khuyenmai.trangthai()});

        }
    }

//    private void LoadTable(List<VocherReponse> list) {
//        model = (DefaultTableModel) tbKhuyenMai.getModel();
//        model.setRowCount(0);
//        model.setColumnIdentifiers(new String[]{"Ma", "Ten", "NgayBD", "NgayKT",
//            "Phan Tram", "So Luong", "Trang Thai"});
//
//        for (VocherReponse vocherReponse : list) {
//            model.addRow(new Object[]{
//                vocherReponse.getMaVCh(),
//                vocherReponse.getTenVCh(),
//                doiNgay(vocherReponse.getNgayBatdau()),
//                doiNgay(vocherReponse.getNgayKetThuc()),
//                vocherReponse.getPhanTram(),
//                vocherReponse.getSoLuong(),
//                vocherReponse.trangThai()});
//        }
//
//    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jLayeredPane4 = new javax.swing.JLayeredPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtMaKM1 = new javax.swing.JTextField();
        txtTenKM1 = new javax.swing.JTextField();
        idcNgayBD2 = new com.toedter.calendar.JDateChooser();
        cbTrangThai1 = new javax.swing.JComboBox<>();
        btnThem1 = new javax.swing.JButton();
        lbAnhKM1 = new javax.swing.JLabel();
        btnCapNhat2 = new javax.swing.JButton();
        txtPhantram1 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        idcNgayKT2 = new com.toedter.calendar.JDateChooser();
        jLabel17 = new javax.swing.JLabel();
        cbbLKM = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lbtb = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        txtTimKiem1 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbKhuyenMai2 = new javax.swing.JTable();
        jLabel19 = new javax.swing.JLabel();
        cbTimKiemTT1 = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        btnlocvocher1 = new javax.swing.JButton();
        btntim1 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbSanPhamKm = new javax.swing.JTable();
        txtTimSP = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        btntim2 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel24 = new javax.swing.JLabel();
        chkCheck = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();

        jPanel3.setBackground(new java.awt.Color(226, 215, 214));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 102, 153));
        jLabel4.setText("Mã khuyến mãi:");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 102, 153));
        jLabel5.setText("Tên khuyến mãi:");

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 102, 153));
        jLabel14.setText("Giá trị:");

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 102, 153));
        jLabel15.setText("Ngày bắt đầu:");

        jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 102, 153));
        jLabel16.setText("Trạng thái:");

        txtMaKM1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaKM1ActionPerformed(evt);
            }
        });

        idcNgayBD2.setDateFormatString("yyyy-MM-dd");

        btnThem1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnThem1.setForeground(new java.awt.Color(0, 102, 153));
        btnThem1.setText("Thêm");
        btnThem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem1ActionPerformed(evt);
            }
        });

        btnCapNhat2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnCapNhat2.setForeground(new java.awt.Color(0, 102, 153));
        btnCapNhat2.setText("Cập nhật");
        btnCapNhat2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhat2ActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 102, 153));
        jLabel18.setText("Ngày kết thúc:");

        idcNgayKT2.setDateFormatString("yyyy-MM-dd");

        jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 102, 153));
        jLabel17.setText("Loại KM");

        cbbLKM.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "%", "tiền" }));

        jButton2.setText("jButton2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnThem1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(59, 59, 59)
                        .addComponent(btnCapNhat2, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtMaKM1)
                            .addComponent(txtPhantram1)
                            .addComponent(idcNgayBD2, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                            .addComponent(cbTrangThai1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(idcNgayKT2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTenKM1)
                            .addComponent(cbbLKM, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                        .addComponent(lbtb, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(jLabel1)
                        .addGap(25, 25, 25))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbAnhKM1, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(164, 164, 164))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(lbAnhKM1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(99, 99, 99)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaKM1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(lbtb, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenKM1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(cbbLKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPhantram1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idcNgayBD2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idcNgayKT2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addGap(33, 33, 33)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(cbTrangThai1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCapNhat2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(182, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(226, 215, 214));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));

        tbKhuyenMai2.setModel(new javax.swing.table.DefaultTableModel(
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
        tbKhuyenMai2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbKhuyenMai2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbKhuyenMai2);

        jLabel19.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 102, 153));
        jLabel19.setText("Tìm khuyến mãi");

        cbTimKiemTT1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Đang hoạt động", "Ngừng hoạt động", " ", " ", " " }));
        cbTimKiemTT1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTimKiemTT1ActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 102, 153));
        jLabel20.setText("Trạng thái");

        btnlocvocher1.setText("Lọc");
        btnlocvocher1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlocvocher1ActionPerformed(evt);
            }
        });

        btntim1.setText("Tìm");
        btntim1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntim1ActionPerformed(evt);
            }
        });

        tbSanPhamKm.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 4", "null", "null"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tbSanPhamKm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbSanPhamKmMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbSanPhamKm);

        jLabel23.setText("Loại sản phẩm");

        btntim2.setBackground(new java.awt.Color(255, 255, 255));
        btntim2.setText("Tìm");
        btntim2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntim2ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel24.setText("Tìm sản phẩm");

        chkCheck.setText("Tất cả");
        chkCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkCheckActionPerformed(evt);
            }
        });

        jButton1.setText("hủy");
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

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTimKiem1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btntim1)
                        .addGap(36, 36, 36)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbTimKiemTT1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 729, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 740, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(140, 140, 140)
                        .addComponent(btnlocvocher1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(txtTimSP, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btntim2)
                        .addGap(42, 42, 42)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButton1)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(chkCheck, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(52, 52, 52)
                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(800, 800, 800)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiem1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btntim1)
                    .addComponent(cbTimKiemTT1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(183, 183, 183)
                        .addComponent(btnlocvocher1))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btntim2)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkCheck))
                .addGap(47, 47, 47)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(175, 175, 175))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(412, 412, 412)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(432, Short.MAX_VALUE)))
        );

        jLayeredPane4.setLayer(jPanel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane4.setLayer(jPanel4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane4Layout = new javax.swing.GroupLayout(jLayeredPane4);
        jLayeredPane4.setLayout(jLayeredPane4Layout);
        jLayeredPane4Layout.setHorizontalGroup(
            jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 764, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jLayeredPane4Layout.setVerticalGroup(
            jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Thiét lập khuyến mãi", jLayeredPane4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        String ma = txtMaKM1.getText();
        if (ma.isEmpty()) {
            JOptionPane.showMessageDialog(this, "vui lòng chọn khuyến mại");
            return;
        }
        List<khuyenmai> listKM = kmService.FindKM(ma);
        int idkm = listKM.get(0).getId();
        int chon = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn hủy", "Update", JOptionPane.YES_NO_OPTION);
        if (chon == JOptionPane.YES_OPTION) {
            if (kmService.UpdateTT(idkm)) {
                JOptionPane.showMessageDialog(this, "Hủy thành công");
                LoadTableKM(kmService.FindTT(0));
            } else {
                JOptionPane.showMessageDialog(this, "Hủy thất bại ");
            };
        } else {
            return;
        }
    }//GEN-LAST:event_jButton1MouseClicked

    private void chkCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkCheckActionPerformed
        if (chkCheck.isSelected()) {
            cheked = true;
            LoadTableSPKM(chiTietSPService.getAll());
        } else {
            cheked = false;
            LoadTableSPKM(chiTietSPService.getAll());
        }
    }//GEN-LAST:event_chkCheckActionPerformed

    private void btntim2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntim2ActionPerformed

        if (txtTimSP.getText().isEmpty()) {
            LoadTableSPKM(chiTietSPService.getAll());
            return;
        } else {
            ArrayList<ChiTietSpResponse> list = new ArrayList<>();
            List<ChiTietSpResponse> listTimKiem = chiTietSPService.getTimKiem2(txtTimSP.getText());

            if (listTimKiem.isEmpty()) {

                LoadTableSPKM(list);
                return;
            }
            LoadTableSPKM(listTimKiem);

        }
    }//GEN-LAST:event_btntim2ActionPerformed

    private void tbSanPhamKmMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbSanPhamKmMouseClicked

    }//GEN-LAST:event_tbSanPhamKmMouseClicked

    private void btntim1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntim1ActionPerformed
        if (txtTimKiem1.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã");
            return;
        }
        List<khuyenmai> list = kmService.FindKM(txtTimKiem1.getText());
        LoadTableKM(list);
    }//GEN-LAST:event_btntim1ActionPerformed

    private void btnlocvocher1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlocvocher1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnlocvocher1ActionPerformed

    private void cbTimKiemTT1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTimKiemTT1ActionPerformed
        if (cbTimKiemTT1.getSelectedItem().toString().equals("Đang hoạt động")) {
            int tt = 1;
            List<khuyenmai> listkm = kmService.FindTT(tt);
            LoadTableKM(listkm);
            return;
        } else if (cbTimKiemTT1.getSelectedItem().toString().equals("Ngừng hoạt động")) {
            int tt = 0;
            List<khuyenmai> listkm = kmService.FindTT(tt);
            LoadTableKM(listkm);
            return;
        } else {
            LoadTableKM(kmService.getALL());
        }
    }//GEN-LAST:event_cbTimKiemTT1ActionPerformed

    private void tbKhuyenMai2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbKhuyenMai2MouseClicked
        int index = tbKhuyenMai2.getSelectedRow();
        TextfieldKM(index);
    }//GEN-LAST:event_tbKhuyenMai2MouseClicked

    private void btnCapNhat2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhat2ActionPerformed

        String ma = txtMaKM1.getText();
        int chon = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn cập nhât", "Update", JOptionPane.YES_NO_OPTION);
        if (chon == JOptionPane.YES_OPTION) {
            List<khuyenmai> listKM = kmService.FindKM(ma);

            List<ChiTietSpResponse> listTimKiem;
            List<KhuyenMaiResponse> listspkm;
            int idkm = listKM.get(0).getId();
            listspkm = kmService.FindCTKM(idkm);
            if (!listspkm.isEmpty()) {
                for (int i = 0; i < tbSanPhamKm.getModel().getRowCount(); i++) {
                    if ((Boolean) tbSanPhamKm.getModel().getValueAt(i, 4)) {
                        String mssp = listspkm.get(i).getMaSp();
                        if (mssp.equals(tbSanPhamKm.getValueAt(i, 0).toString()) == false) {
                            JOptionPane.showMessageDialog(this, "San pham da ton tai");
                            return;
                        }

                    }
                }
                return;
            }
            for (int i = 0; i < tbSanPhamKm.getModel().getRowCount(); i++) {
                if ((Boolean) tbSanPhamKm.getModel().getValueAt(i, 4)) {
                    ChitietKhuyenMai ctkm = new ChitietKhuyenMai();
                    ctkm.setMaSP(tbSanPhamKm.getValueAt(i, 0).toString());
                    ctkm.setTenSP(tbSanPhamKm.getValueAt(i, 1).toString());
                    ctkm.setDonGia(new BigDecimal(tbSanPhamKm.getValueAt(i, 2).toString()));
                    BigDecimal giacu = new BigDecimal(tbSanPhamKm.getValueAt(i, 2).toString());
                    if (cbbLKM.getSelectedItem().equals("%")) {
                        BigDecimal giamoi = giacu.subtract(giacu.multiply(new BigDecimal(txtPhantram1.getText()).divide(new BigDecimal(100))));
                        ctkm.setDonGiaConLai(giamoi);
                    } else if (cbbLKM.getSelectedItem().equals("tiền")) {
                        BigDecimal giamoi = giacu.subtract(new BigDecimal(txtPhantram1.getText()));
                        ctkm.setDonGiaConLai(giamoi);
                    }
                    khuyenmai km = new khuyenmai();
                    km.setId(idkm);
                    ctkm.setKhuyenmai(km);
                    listTimKiem = chiTietSPService.getTimKiem2(tbSanPhamKm.getValueAt(i, 0).toString());
                    int idctsp = listTimKiem.get(0).getIdCTSP();
                    ChiTietSP ctsp = new ChiTietSP();
                    ctsp.setId(idctsp);
                    ctkm.setChiTietSP(ctsp);

                    kmService.AddCTKM(ctkm);

                }
            }
            JOptionPane.showMessageDialog(this, "Cập nhật thành công");

        }

    }//GEN-LAST:event_btnCapNhat2ActionPerformed

    private void btnThem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem1ActionPerformed
        if (check()) {
            String ma = txtMaKM1.getText();
            List<khuyenmai> listTim = kmService.FindKM(ma);

            if (listTim.isEmpty()) {
                khuyenmai km1 = new khuyenmai();
                km1.setMa(ma);
                km1.setGiaTri(Integer.parseInt(txtPhantram1.getText()));
                km1.setNgay_bat_dau(idcNgayBD2.getDate());
                km1.setNgay_ket_thuc(idcNgayKT2.getDate());
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                if (sdf.format(date).equals(sdf.format(idcNgayBD2.getDate()))) {

                    km1.setTrangThai(1);
                } else {
                    km1.setTrangThai(0);
                }
                km1.setTen(txtTenKM1.getText());
                km1.setLoaiKhuyenMai(cbbLKM.getSelectedItem().toString());
                kmService.AddKM(km1);
                LoadTableKM(kmService.getALL());
                JOptionPane.showMessageDialog(this, "them thanh cong");

                List<khuyenmai> listKM = kmService.FindKM(ma);
                List<ChiTietSpResponse> listTimKiem;
                int idkm = listKM.get(0).getId();
                for (int i = 0; i < tbSanPhamKm.getModel().getRowCount(); i++) {
                    if ((Boolean) tbSanPhamKm.getModel().getValueAt(i, 4)) {
                        ChitietKhuyenMai ctkm = new ChitietKhuyenMai();
                        ctkm.setMaSP(tbSanPhamKm.getValueAt(i, 0).toString());
                        ctkm.setTenSP(tbSanPhamKm.getValueAt(i, 1).toString());
                        ctkm.setDonGia(new BigDecimal(tbSanPhamKm.getValueAt(i, 2).toString()));
                        BigDecimal giacu = new BigDecimal(tbSanPhamKm.getValueAt(i, 2).toString());
                        if (cbbLKM.getSelectedItem().equals("%")) {
                            BigDecimal giamoi = giacu.subtract(giacu.multiply(new BigDecimal(txtPhantram1.getText()).divide(new BigDecimal(100))));
                            ctkm.setDonGiaConLai(giamoi);
                        } else if (cbbLKM.getSelectedItem().equals("tiền")) {
                            BigDecimal giamoi = giacu.subtract(new BigDecimal(txtPhantram1.getText()));
                            ctkm.setDonGiaConLai(giamoi);
                        }
                        khuyenmai km = new khuyenmai();
                        km.setId(idkm);
                        ctkm.setKhuyenmai(km);
                        listTimKiem = chiTietSPService.getTimKiem2(tbSanPhamKm.getValueAt(i, 0).toString());
                        int idctsp = listTimKiem.get(0).getIdCTSP();
                        ChiTietSP ctsp = new ChiTietSP();
                        ctsp.setId(idctsp);
                        ctkm.setChiTietSP(ctsp);
                        kmService.AddCTKM(ctkm);

                    }

                }
                return;
            } else {
                JOptionPane.showMessageDialog(this, "Khuyến mãi dã tồn tại?");
                return;
            }
        }
    }//GEN-LAST:event_btnThem1ActionPerformed

    private void txtMaKM1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaKM1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaKM1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String a = (JOptionPane.showInputDialog("Nhập giá trị :"));
        if (a.isEmpty()) {
            return;
        }
        try {
            int Second = Integer.parseInt(a);
            if (Second < 0) {
                JOptionPane.showMessageDialog(this, "Giá trị phải >0");
                return;
            }
            update(Second);
            LoadTableKM(kmService.getALL());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số");
            return;
        }

    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhat2;
    private javax.swing.JButton btnThem1;
    private javax.swing.JButton btnlocvocher1;
    private javax.swing.JButton btntim1;
    private javax.swing.JButton btntim2;
    private javax.swing.JComboBox<String> cbTimKiemTT1;
    private javax.swing.JComboBox<String> cbTrangThai1;
    private javax.swing.JComboBox<String> cbbLKM;
    private javax.swing.JCheckBox chkCheck;
    private com.toedter.calendar.JDateChooser idcNgayBD2;
    private com.toedter.calendar.JDateChooser idcNgayKT2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLayeredPane jLayeredPane4;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbAnhKM1;
    private javax.swing.JLabel lbtb;
    private javax.swing.JTable tbKhuyenMai2;
    private javax.swing.JTable tbSanPhamKm;
    private javax.swing.JTextField txtMaKM1;
    private javax.swing.JTextField txtPhantram1;
    private javax.swing.JTextField txtTenKM1;
    private javax.swing.JTextField txtTimKiem1;
    private javax.swing.JTextField txtTimSP;
    // End of variables declaration//GEN-END:variables
}

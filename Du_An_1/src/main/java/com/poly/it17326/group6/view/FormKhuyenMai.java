/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.poly.it17326.group6.view;

import com.poly.it17326.group6.domainmodel.Voucher;
import com.poly.it17326.group6.response.VocherReponse;
import com.poly.it17326.group6.service.VoucherService;
import com.poly.it17326.group6.service.impl.VoucherServiceIplm;
import java.awt.JobAttributes;
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

    public FormKhuyenMai() {
        initComponents();
        LoadTable(voucherService.getAll());
        loadCBBTT();
        loadCBBTimKiem();
        lbAnhKM.setIcon(new ImageIcon("D:\\Nhom6_PRO1041\\Anh\\sale.png"));
        update();
    }

    private String doiNgay(Date d) {
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("yyyy-MM-dd");
        String ngayTao = format.format(d);
        return ngayTao;
    }

    private void loadCBBTimKiem() {
        List<String> listTT = new ArrayList<>();
        listTT.add("Dang hoat dong");
        listTT.add("Da het han");
        for (String string : listTT) {
            cbTimKiemTT.addItem(string);
        }
    }

    private void loadCBBTT() {
        List<String> listTT = new ArrayList<>();
        listTT.add("Dang hoat dong");
        listTT.add("Da het han");
        for (String string : listTT) {
            cbTrangThai.addItem(string);
        }

    }
    

    private void loadTextField(int i) {
        if (tbKhuyenMai.getRowCount() > 0) {
            txtMaKM.setText(tbKhuyenMai.getValueAt(i, 0).toString());
            txtTenKM.setText(tbKhuyenMai.getValueAt(i, 1).toString());
            txtPhantram.setText(tbKhuyenMai.getValueAt(i, 4).toString());
            cbTrangThai.setSelectedItem(tbKhuyenMai.getValueAt(i, 6).toString());
            txtSoluong.setText(tbKhuyenMai.getValueAt(i, 5).toString());
            try {
                Date ngaBd = new SimpleDateFormat("yyyy-MM-dd").parse(tbKhuyenMai.getValueAt(i, 2).toString());
                Date NgayKT = new SimpleDateFormat("yyyy-MM-dd").parse(tbKhuyenMai.getValueAt(i, 3).toString());

                idcNgayBD.setDate(ngaBd);
//                idcNgayKT.setDate(NgayKT);
            } catch (ParseException ex) {
                Logger.getLogger(FormKhuyenMai.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    private boolean check() {

        if (txtMaKM.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Khong duoc de trong ma khuyen mai");
            return false;
        }
        if (txtTenKM.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Khong duoc de trong ten khuyen mai");
            return false;
        }

        try {
            if (txtPhantram.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Khong duoc de trong gia tri khuyen mai");
                return false;

            } else if (Float.parseFloat(txtPhantram.getText()) <= 0) {
                JOptionPane.showMessageDialog(this, "gia tri phai >0");
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "gia tri phai la chu so");
            return false;
        }
        try {
            if (txtSoluong.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Khong duoc de trong so luong khuyen mai");
                return false;
            } else if (Integer.parseInt(txtSoluong.getText()) <= 0) {
                JOptionPane.showMessageDialog(this, "so luong phai >0");
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "so luong phai la chu so");
            return false;
        }
        if (idcNgayBD.getDate().equals(" ")) {
            JOptionPane.showMessageDialog(this, "Khong duoc de trong ngay bat dau");
            return false;
        }
//        if (idcNgayKT.getDate().equals(" ")) {
//            JOptionPane.showMessageDialog(this, "Khong duoc de trong ngay ket thuc");
//            return false;
//        }
        Date curent = new Date();
        if(idcNgayBD.getDate().before(curent)){
            JOptionPane.showMessageDialog(this,"Ngày bắt đầu phải ở hiện tại hoặc trong tương lai");
            return false;
        }
       
        return true;
    }

    private void LoadTable(List<VocherReponse> list) {
        model = (DefaultTableModel) tbKhuyenMai.getModel();
        model.setRowCount(0);
        model.setColumnIdentifiers(new String[]{"Ma", "Ten", "NgayBD", "NgayKT",
            "Phan Tram", "So Luong", "Trang Thai"});

        for (VocherReponse vocherReponse : list) {
            model.addRow(new Object[]{
                vocherReponse.getMaVCh(),
                vocherReponse.getTenVCh(),
                doiNgay(vocherReponse.getNgayBatdau()),
                doiNgay(vocherReponse.getNgayKetThuc()),
                vocherReponse.getPhanTram(),
                vocherReponse.getSoLuong(),
                vocherReponse.trangThai()});
        }

    }
    
    private void update(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 5);
        calendar.set(Calendar.MINUTE, 04);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Date dateSchedule = calendar.getTime();
       

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                
                String ma = txtMaKM.getText();
                if(ma.isEmpty()){
                   return;
                }
                voucherService.updateTrangThai(ma,0);
            }
        };

        Timer timer = new Timer();
        timer.schedule(timerTask, dateSchedule);
    
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtMaKM = new javax.swing.JTextField();
        txtTenKM = new javax.swing.JTextField();
        idcNgayBD = new com.toedter.calendar.JDateChooser();
        cbTrangThai = new javax.swing.JComboBox<>();
        btnThem = new javax.swing.JButton();
        lbAnhKM = new javax.swing.JLabel();
        btnCapNhat1 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txtSoluong = new javax.swing.JTextField();
        txtPhantram = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jdcNgayKT = new com.toedter.calendar.JDateChooser();
        jPanel2 = new javax.swing.JPanel();
        txtTimKiem = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbKhuyenMai = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        cbTimKiemTT = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        idcNgayKT1 = new com.toedter.calendar.JDateChooser();
        idcNgayBD1 = new com.toedter.calendar.JDateChooser();
        btnlocvocher = new javax.swing.JButton();
        btntim = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(226, 215, 214));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 153));
        jLabel1.setText("Mã khuyến mãi:");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 102, 153));
        jLabel2.setText("Tên khuyến mãi:");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 102, 153));
        jLabel3.setText("Giá trị:");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 102, 153));
        jLabel6.setText("Ngày bắt đầu:");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 102, 153));
        jLabel7.setText("Trạng thái:");

        txtMaKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaKMActionPerformed(evt);
            }
        });

        idcNgayBD.setDateFormatString("yyyy-MM-dd");

        btnThem.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnThem.setForeground(new java.awt.Color(0, 102, 153));
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnCapNhat1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnCapNhat1.setForeground(new java.awt.Color(0, 102, 153));
        btnCapNhat1.setText("Cập nhật");
        btnCapNhat1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhat1ActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 102, 153));
        jLabel9.setText("Số lượng");

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 102, 153));
        jLabel11.setText("Ngày kết thúc:");

        jdcNgayKT.setDateFormatString("yyyy-MM-dd");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(156, 156, 156)
                        .addComponent(lbAnhKM, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSoluong, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMaKM)
                            .addComponent(txtTenKM)
                            .addComponent(txtPhantram)
                            .addComponent(idcNgayBD, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
                            .addComponent(jdcNgayKT, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(151, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(btnCapNhat1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(97, 97, 97))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel1, jLabel2, jLabel3, jLabel6, jLabel7});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cbTrangThai, idcNgayBD, jdcNgayKT, txtMaKM, txtPhantram, txtSoluong, txtTenKM});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(lbAnhKM, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPhantram, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idcNgayBD, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jdcNgayKT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cbTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel9)
                        .addGap(49, 49, 49))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(txtSoluong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCapNhat1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(205, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(226, 215, 214));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));

        tbKhuyenMai.setModel(new javax.swing.table.DefaultTableModel(
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
        tbKhuyenMai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbKhuyenMaiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbKhuyenMai);

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 102, 153));
        jLabel8.setText("Tìm khuyến mãi");

        cbTimKiemTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTimKiemTTActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 102, 153));
        jLabel10.setText("Trạng thái");

        idcNgayKT1.setDateFormatString("yyyy-MM-dd");

        idcNgayBD1.setDateFormatString("yyyy-MM-dd");

        btnlocvocher.setText("Lọc");
        btnlocvocher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlocvocherActionPerformed(evt);
            }
        });

        btntim.setText("Tìm");
        btntim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntimActionPerformed(evt);
            }
        });

        btnXoa.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnXoa.setForeground(new java.awt.Color(0, 102, 153));
        btnXoa.setText("Hủy kích hoạt");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 102, 153));
        jLabel12.setText("Từ");

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 102, 153));
        jLabel13.setText("Đến");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(btntim)
                .addGap(67, 67, 67)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbTimKiemTT, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(idcNgayBD1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(434, 434, 434))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(idcNgayKT1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnlocvocher, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(299, 299, 299))))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 785, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(266, 266, 266)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(221, 221, 221)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbTimKiemTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btntim))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(idcNgayBD1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(idcNgayKT1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(btnlocvocher)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        if (check()) {

            Voucher vc = new Voucher();
            vc.setMa(txtMaKM.getText());
            String ngayBD = doiNgay(idcNgayBD.getDate());
            String ngayKT = doiNgay(jdcNgayKT.getDate());
//            String ngayKT = doiNgay(idcNgayKT.getDate());
            int soLuong = Integer.parseInt(txtSoluong.getText());
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date ngaybd = format.parse(ngayBD);
                Date ngaykt = format.parse(ngayKT);
                vc.setNgayApDung(ngaybd);
//                vc.setNgayKetThuc(ngaykt);
                vc.setPhanTram(Float.valueOf(txtPhantram.getText()));
                if (cbTrangThai.getSelectedItem().toString().equals("Dang hoat dong")) {
                    vc.setTrangThai(1);
                } else {
                    vc.setTrangThai(0);
                };
                vc.setTen(txtTenKM.getText());
                vc.setSoLuong(soLuong);
                if (voucherService.addVCH(vc)) {
                    JOptionPane.showMessageDialog(this, "add thanh cong");
                    LoadTable(voucherService.getAll());
                } else {
                    JOptionPane.showMessageDialog(this, "add that bai");
                }
            } catch (ParseException ex) {
                Logger.getLogger(FormKhuyenMai.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void txtMaKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaKMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaKMActionPerformed

    private void cbTimKiemTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTimKiemTTActionPerformed
        int trangthai = 0;
        if (cbTimKiemTT.getSelectedItem().toString().equals("Dang hoat dong")) {
            trangthai = 1;
            List<VocherReponse> listVCHR = new ArrayList<>();
            List<Voucher> list = voucherService.FindTT(trangthai);
            if (list.isEmpty()) {
                LoadTable(listVCHR);
                return;
            }
            for (Voucher lists : list) {
                VocherReponse vc = new VocherReponse(lists);
                listVCHR.add(vc);
            }

            LoadTable(listVCHR);
        } else {
            List<VocherReponse> listVCHR = new ArrayList<>();
            List<Voucher> list = voucherService.FindTT(trangthai);
            if (list.isEmpty()) {
                LoadTable(listVCHR);
                return;
            }
            for (Voucher lists : list) {
                VocherReponse vc = new VocherReponse(lists);
                listVCHR.add(vc);
            }

            LoadTable(listVCHR);
        }
    }//GEN-LAST:event_cbTimKiemTTActionPerformed

    private void tbKhuyenMaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbKhuyenMaiMouseClicked
        int index = tbKhuyenMai.getSelectedRow();
        loadTextField(index);
    }//GEN-LAST:event_tbKhuyenMaiMouseClicked

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        String ma = txtMaKM.getText();
        if (ma.isEmpty()) {
            JOptionPane.showMessageDialog(this, "vui long chon ma");
            return;
        }
        if (voucherService.XoaVCh(ma)) {
            JOptionPane.showMessageDialog(this, "xoa thanh cong");
            LoadTable(voucherService.getAll());
        } else {
            JOptionPane.showMessageDialog(this, "xoa that bai");
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnCapNhat1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhat1ActionPerformed
        if (check()) {
            Voucher vc = new Voucher();
            String ma = txtMaKM.getText();
            String ngayBD = doiNgay(idcNgayBD.getDate());
//            String ngayKT = doiNgay(idcNgayKT.getDate());
             int soLuong = Integer.parseInt(txtSoluong.getText());
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date ngaybd = format.parse(ngayBD);
//                Date ngaykt = format.parse(ngayKT);
                vc.setNgayApDung(ngaybd);
//                vc.setNgayKetThuc(ngaykt);
                vc.setPhanTram(Float.parseFloat(txtPhantram.getText()));
                vc.setSoLuong(soLuong);
                if (cbTrangThai.getSelectedItem().toString().equals("Dang hoat dong")) {
                    vc.setTrangThai(1);
                } else {
                    vc.setTrangThai(0);
                };
                vc.setTen(txtTenKM.getText());
                if (voucherService.updateVCH(ma, vc)) {
                    JOptionPane.showMessageDialog(this, "udpate thanh cong");
                    LoadTable(voucherService.getAll());
                } else {
                    JOptionPane.showMessageDialog(this, "udpate that bai");
                }
            } catch (ParseException ex) {
                Logger.getLogger(FormKhuyenMai.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnCapNhat1ActionPerformed

    private void btnlocvocherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlocvocherActionPerformed
        String ngayBD = doiNgay(idcNgayBD1.getDate());
        String ngayKT = doiNgay(idcNgayKT1.getDate());
        if (ngayBD.isEmpty() && ngayKT.isEmpty()) {
            LoadTable(voucherService.getAll());
            return;
        } else {
            List<VocherReponse> listVCHR = new ArrayList<>();
            List<Voucher> list = voucherService.FindDate(ngayBD, ngayKT);
            if (list.isEmpty()) {
                LoadTable(listVCHR);
                return;
            }
            for (Voucher lists : list) {
                VocherReponse vc = new VocherReponse(lists);
                listVCHR.add(vc);
            }

            LoadTable(listVCHR);

        }
    }//GEN-LAST:event_btnlocvocherActionPerformed

    private void btntimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntimActionPerformed
        String maOrTen = txtTimKiem.getText();

        if (maOrTen.isEmpty()) {
            LoadTable(voucherService.getAll());
            return;
        } else {
            List<VocherReponse> listVCHR = new ArrayList<>();
            List<Voucher> list = voucherService.Find(maOrTen);
            if (list.isEmpty()) {
                LoadTable(listVCHR);
                return;
            }

            for (Voucher lists : list) {
                VocherReponse vc = new VocherReponse(lists);
                listVCHR.add(vc);
            }

            LoadTable(listVCHR);
        }
    }//GEN-LAST:event_btntimActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhat1;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnlocvocher;
    private javax.swing.JButton btntim;
    private javax.swing.JComboBox<String> cbTimKiemTT;
    private javax.swing.JComboBox<String> cbTrangThai;
    private com.toedter.calendar.JDateChooser idcNgayBD;
    private com.toedter.calendar.JDateChooser idcNgayBD1;
    private com.toedter.calendar.JDateChooser idcNgayKT1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.calendar.JDateChooser jdcNgayKT;
    private javax.swing.JLabel lbAnhKM;
    private javax.swing.JTable tbKhuyenMai;
    private javax.swing.JTextField txtMaKM;
    private javax.swing.JTextField txtPhantram;
    private javax.swing.JTextField txtSoluong;
    private javax.swing.JTextField txtTenKM;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}

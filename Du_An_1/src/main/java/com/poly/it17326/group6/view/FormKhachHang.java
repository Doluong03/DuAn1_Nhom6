/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.poly.it17326.group6.view;

import com.poly.it17326.group6.domainmodel.HoaDon;
import com.poly.it17326.group6.domainmodel.KhachHang;
import com.poly.it17326.group6.response.HoaDonresponse;
import com.poly.it17326.group6.service.HoaDonService;
import com.poly.it17326.group6.service.KhachHangService;
import com.poly.it17326.group6.service.impl.HoaDonServecieIplm;
import com.poly.it17326.group6.service.impl.KhachHangServiceImpl;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author 123
 */
public class FormKhachHang extends javax.swing.JPanel {

    private List<String> listRank = new ArrayList<>();
    private HoaDonService hoaDonService = new HoaDonServecieIplm();
    private KhachHangService khachHangService = new KhachHangServiceImpl();

    /**
     * Creates new form FormKhachHang_2
     */
    public FormKhachHang() {
        initComponents();
        //   setLocationRelativeTo(null);
        loadTable(khachHangService.getList());
        //  loadTable1(khachHangService.getList());
        //  loadTableLS(khachHangService.getListLS());
        loadCB();
        auto();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    private void auto() {
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    updateRankAll();
                    loadTable(khachHangService.getList());
                    if (getIdKHByMa() != -1) {
                        cbRank.setSelectedItem(khachHangService.getOne(getIdKHByMa()).Rank());
                        System.out.println(khachHangService.getOne(getIdKHByMa()).Rank());
                    }
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException ex) {
                        //  Logger.getLogger(KhachHangJpanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

        }.start();
    }

    private int getIdKHByMa() {
        khachHangService = new KhachHangServiceImpl();
        for (KhachHang kh : khachHangService.getList()) {
            if (kh.getMa().equals(txtMaKH2.getText())) {
                return kh.getId();
            }
        }
        return -1;
    }

    public void updateRankAll() {
        khachHangService = new KhachHangServiceImpl();
        for (KhachHang kh : khachHangService.getList()) {
            updateRankOne(kh.getId());
        }

    }

    private void updateRankOne(int id) {
        khachHangService = new KhachHangServiceImpl();
        KhachHang kh = khachHangService.getOne(id);
        if (kh.getMa().equalsIgnoreCase("KH0")) {
            kh.setRank(0);
        } else if (tongTienMua(id) < 3000000) {
            kh.setRank(0);
        } else if (tongTienMua(id) < 5000000) {
            kh.setRank(1);
        } else if (tongTienMua(id) < 1000000) {
            kh.setRank(2);
        } else {
            kh.setRank(3);
        }

        khachHangService.updateKH(kh);
    }

    private int tongTienMua(int id) {
        hoaDonService = new HoaDonServecieIplm();

        int tong = 0;
        for (HoaDon hoaDon : hoaDonService.getALLHD()) {
            if (hoaDon.getKhachHang().getId() == id && hoaDon.getTrangThai() == 1) {
                // tt 1 la  đã thanh toán
                tong += hoaDon.getTongTien().intValue();
            }
        }
        return tong;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    public void loadTable(List<KhachHang> listH) {
        DefaultTableModel model = (DefaultTableModel) tbKH.getModel();
        model.setRowCount(0);
        model.setColumnIdentifiers(new String[]{"Mã", "Họ tên", "SDT", "Ngày sinh", "Giới tính", "Địa chỉ", "Trạng thái", "Rank"});
        int i = 1;
        for (KhachHang kh : listH) {
            model.addRow(new Object[]{kh.getMa(), kh.getTen(), kh.getSdt(), doiNgay(kh.getNgaySinh()), kh.getGioiTinh(), kh.getDiaChi(), kh.trangThai(), kh.Rank()});
        }
    }

    public void loadTableLS(List<HoaDonresponse> listH) {
        DefaultTableModel model = (DefaultTableModel) tblLichSu.getModel();
        model.setRowCount(0);
        model.setColumnIdentifiers(new String[]{"Mã HD", "Mã KH", "Tổng tiền hóa đơn", "Ngày mua"});
        int i = 1;
        for (HoaDonresponse kh : listH) {
            model.addRow(new Object[]{kh.getMaHD(), kh.getMaKH(), kh.getTongTien(), doiNgay(kh.getNgayTao())});
        }
    }

//    public void loadTable1(List<KhachHang> listH) {
//        DefaultTableModel model = (DefaultTableModel) tblThongTin.getModel();
//        model.setRowCount(0);
//        model.setColumnIdentifiers(new String[]{"Mã", "Họ tên", "SDT", "Ngày sinh", "Giới tính", "Địa chỉ", "Trạng thái", "Rank"});
//        int i = 1;
//        for (KhachHang kh : listH) {
//            model.addRow(new Object[]{kh.getMa(), kh.getTen(), kh.getSdt(), doiNgay(kh.getNgaySinh()), kh.getGioiTinh(), kh.getDiaChi(), kh.trangThai(), kh.Rank()});
//
//        }
//    }
    String ma = null;

    public String getKH() {
        return ma;
    }

    private String doiNgay(Date d) {
        String ngayTao = null;
        if (d == null) {
            ngayTao = "";
        } else {
            SimpleDateFormat format = new SimpleDateFormat();
            format.applyPattern("yyyy-MM-dd");
            ngayTao = format.format(d);
        }
        return ngayTao;
    }

    private void loadCB() {
        cbRank.removeAllItems();
        cbRank.addItem("Đồng");
        cbRank.addItem("Bạc");
        cbRank.addItem("Vàng");
        cbRank.addItem("Bạch kim");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        lbRank5 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtMaKH2 = new javax.swing.JTextField();
        txtTenKH2 = new javax.swing.JTextField();
        txtSdt2 = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jdcNgaySinh2 = new com.toedter.calendar.JDateChooser();
        jLabel23 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtaDiaCHi2 = new javax.swing.JTextArea();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        lbRank4 = new javax.swing.JLabel();
        rdNam = new javax.swing.JRadioButton();
        rdNu = new javax.swing.JRadioButton();
        btnThem1 = new javax.swing.JButton();
        btnLamMoi1 = new javax.swing.JButton();
        cbRank = new javax.swing.JComboBox<>();
        jLabel26 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        btnXuat = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbKH = new javax.swing.JTable();
        txtTimKiem = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblLichSu = new javax.swing.JTable();

        jTabbedPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel19.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel19.setText("Mã khách hàng:");

        jLabel20.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel20.setText("Tên khách hàng:");

        jLabel21.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel21.setText("Số điện thoại:");

        jLabel22.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel22.setText("Ngày sinh:");

        jdcNgaySinh2.setDateFormatString("yyyy-MM-dd");

        jLabel23.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel23.setText("Địa chỉ:");

        txtaDiaCHi2.setColumns(20);
        txtaDiaCHi2.setRows(5);
        jScrollPane6.setViewportView(txtaDiaCHi2);

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel24.setText("Rank :");

        buttonGroup1.add(rdNam);
        rdNam.setText("Nam");

        buttonGroup1.add(rdNu);
        rdNu.setText("Nữ");
        rdNu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdNuActionPerformed(evt);
            }
        });

        btnThem1.setText("Thêm");
        btnThem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnLamMoi1.setText("Làm mới");
        btnLamMoi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        cbRank.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel26.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel26.setText("Giới tính:");

        jButton1.setText("Sửa");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout lbRank5Layout = new javax.swing.GroupLayout(lbRank5);
        lbRank5.setLayout(lbRank5Layout);
        lbRank5Layout.setHorizontalGroup(
            lbRank5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lbRank5Layout.createSequentialGroup()
                .addGroup(lbRank5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(lbRank5Layout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addComponent(jLabel12))
                    .addGroup(lbRank5Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(lbRank5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel23)
                            .addComponent(jLabel19)
                            .addComponent(jLabel22))
                        .addGap(26, 26, 26)
                        .addGroup(lbRank5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnThem1)
                            .addComponent(jdcNgaySinh2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtMaKH2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(lbRank5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(lbRank5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(lbRank5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(lbRank5Layout.createSequentialGroup()
                                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(lbRank5Layout.createSequentialGroup()
                                .addComponent(lbRank4, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lbRank5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                        .addGroup(lbRank5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20)
                            .addComponent(jLabel21)
                            .addComponent(jLabel26))
                        .addGap(18, 18, 18)
                        .addGroup(lbRank5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(lbRank5Layout.createSequentialGroup()
                                .addGroup(lbRank5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtSdt2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                                    .addComponent(txtTenKH2))
                                .addGap(43, 43, 43)
                                .addComponent(jLabel24)
                                .addGap(30, 30, 30)
                                .addComponent(cbRank, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(lbRank5Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(rdNam)
                                .addGap(100, 100, 100)
                                .addComponent(rdNu)))
                        .addContainerGap(379, Short.MAX_VALUE))
                    .addGroup(lbRank5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addGap(155, 155, 155)
                        .addComponent(btnLamMoi1)
                        .addContainerGap())))
        );

        lbRank5Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnLamMoi1, btnThem1, jButton1});

        lbRank5Layout.setVerticalGroup(
            lbRank5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lbRank5Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lbRank4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addGap(12, 12, 12)
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(lbRank5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txtMaKH2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(txtTenKH2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24)
                    .addComponent(cbRank, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(lbRank5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(lbRank5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel22)
                        .addGroup(lbRank5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSdt2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21)))
                    .addGroup(lbRank5Layout.createSequentialGroup()
                        .addComponent(jdcNgaySinh2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)))
                .addGroup(lbRank5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(lbRank5Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(lbRank5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdNam)
                            .addComponent(rdNu)
                            .addComponent(jLabel26))
                        .addGap(139, 139, 139)
                        .addComponent(jButton1)
                        .addContainerGap(36, Short.MAX_VALUE))
                    .addGroup(lbRank5Layout.createSequentialGroup()
                        .addGroup(lbRank5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(lbRank5Layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(jLabel23)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(lbRank5Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(jScrollPane6)
                                .addGap(18, 18, 18)))
                        .addGroup(lbRank5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnThem1)
                            .addComponent(btnLamMoi1))
                        .addGap(36, 36, 36))))
        );

        jTabbedPane2.addTab("Thiết lập thông tin", lbRank5);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin khách hàng"));

        btnXuat.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnXuat.setText("Export Excel");
        btnXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatActionPerformed(evt);
            }
        });

        tbKH.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        tbKH.setModel(new javax.swing.table.DefaultTableModel(
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
        tbKH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbKHMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbKH);

        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyPressed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel5.setText("Tìm kiếm:");

        jButton2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton2.setText("Show danh sach");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51)
                        .addComponent(btnXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1316, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(124, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(btnXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(328, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Danh Sách Khách Hàng", jPanel5);

        tblLichSu.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(tblLichSu);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 1434, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(254, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Lịch Sử Giao Dịch", jPanel6);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane3)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 556, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 10, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatActionPerformed
        // TODO add your handling code here:
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("HoaDon");
            XSSFRow row = null;
            Cell cell = null;
            row = sheet.createRow(3);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("STT");

            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("Mã KH");

            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("Họ tên");

            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("SĐT");

            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue("Ngày sinh");

            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue("Địa chỉ");

            cell = row.createCell(6, CellType.STRING);
            cell.setCellValue("trạng thái");

            cell = row.createCell(7, CellType.STRING);
            cell.setCellValue("rank");

            for (int i = 0; i < tbKH.getRowCount(); i++) {

                row = sheet.createRow(5 + i);
                cell = row.createCell(0, CellType.NUMERIC);
                cell.setCellValue(i + 1);

                cell = row.createCell(1, CellType.NUMERIC);
                cell.setCellValue(tbKH.getValueAt(i, 0).toString());

                cell = row.createCell(2, CellType.NUMERIC);
                cell.setCellValue(tbKH.getValueAt(i, 1).toString());

                cell = row.createCell(3, CellType.NUMERIC);
                cell.setCellValue(tbKH.getValueAt(i, 2).toString());

                cell = row.createCell(4, CellType.NUMERIC);
                cell.setCellValue(tbKH.getValueAt(i, 3).toString());

                cell = row.createCell(5, CellType.NUMERIC);
                cell.setCellValue(tbKH.getValueAt(i, 4).toString());

                cell = row.createCell(6, CellType.NUMERIC);
                cell.setCellValue(tbKH.getValueAt(i, 5).toString());

                cell = row.createCell(7, CellType.NUMERIC);
                cell.setCellValue(tbKH.getValueAt(i, 6).toString());

            }

            File file = new File("D:\\Nhom6_PRO1041\\Export_KH\\KhachHang.xlsx");
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

    }//GEN-LAST:event_btnXuatActionPerformed

    private void tbKHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbKHMouseClicked
        // TODO add your handling code here:
        int index = tbKH.getSelectedRow();
        ma = tbKH.getValueAt(index, 0).toString();
        loadTableLS(khachHangService.getListLS(ma));
        //   int index = tblThongTin.getSelectedRow();
        txtMaKH2.setText(tbKH.getValueAt(index, 0).toString());
        txtTenKH2.setText(tbKH.getValueAt(index, 1).toString());
        txtSdt2.setText(tbKH.getValueAt(index, 2).toString());
        //  cbRank.setSelectedItem(tbKH.getValueAt(index, 7).toString());
        if (tbKH.getValueAt(index, 7).toString().equalsIgnoreCase("Bạch kim")) {
            cbRank.setSelectedIndex(3);
        }
        if (tbKH.getValueAt(index, 7).toString().equals("Vàng")) {
            cbRank.setSelectedIndex(2);
        }
        if (tbKH.getValueAt(index, 7).toString().equals("Bạc")) {
            cbRank.setSelectedIndex(1);
        }
        if (tbKH.getValueAt(index, 7).toString().equals("Đồng")) {
            cbRank.setSelectedIndex(0);
        }

        if (tbKH.getValueAt(index, 4).toString().equalsIgnoreCase("Nam")) {
            rdNam.setSelected(true);
        } else if (tbKH.getValueAt(index, 4).toString().equalsIgnoreCase("Nữ")) {
            rdNu.setSelected(true);
        } else {
            buttonGroup1.clearSelection();
        }
        try {
            Date d = new SimpleDateFormat("yyyy-MM-dd").parse(tbKH.getValueAt(index, 3).toString());
            jdcNgaySinh2.setDate(d);
        } catch (ParseException ex) {
            Logger.getLogger(FormKhachHang.class.getName()).log(Level.SEVERE, null, ex);
        }
        txtaDiaCHi2.setText(tbKH.getValueAt(index, 5).toString());
    }//GEN-LAST:event_tbKHMouseClicked

    private void txtTimKiemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            List<KhachHang> list = khachHangService.getCheckKH(txtTimKiem.getText());
            loadTable(list);
        }
    }//GEN-LAST:event_txtTimKiemKeyPressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        loadTable(khachHangService.getList());
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        // TODO add your handling code here:
        txtMaKH2.setText("");
        txtTenKH2.setText("");
        txtSdt2.setText("");
        txtaDiaCHi2.setText("");
        buttonGroup1.clearSelection();
        try {
            Date d = new SimpleDateFormat("yyyy-MM-dd").parse("0000-00-00");
            jdcNgaySinh2.setDate(d);
            // jdcNgaySinh.removeAll();
        } catch (ParseException ex) {
            Logger.getLogger(FormKhachHang.class.getName()).log(Level.SEVERE, null, ex);
        }
        //  buttonGroup1.clearSelection();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        KhachHang kh = new KhachHang();
        kh.setMa(txtMaKH2.getText());
        kh.setTrangThai(true);
        kh.setTen(txtTenKH2.getText());
        kh.setNgaySinh(jdcNgaySinh2.getDate());
        kh.setDiaChi(txtaDiaCHi2.getText());
        kh.setSdt(txtSdt2.getText());
        if (rdNam.isSelected()) {
            kh.setGioiTinh("Nam");
        } else {
            kh.setGioiTinh("Nữ");
        }
        if (khachHangService.addKH(kh)) {
            JOptionPane.showMessageDialog(this, "Thêm thành công");
            loadTable(khachHangService.getList());
        } else {
            JOptionPane.showMessageDialog(this, "Thêm thất bại");
        }
        loadTable(khachHangService.getList());
    }//GEN-LAST:event_btnThemActionPerformed

    private void rdNuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdNuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdNuActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        KhachHang kh = new KhachHang();
        for (KhachHang khachHang : khachHangService.getList()) {
            if (khachHang.getMa().equals(txtMaKH2.getText())) {
                kh.setId(khachHang.getId());
            }
        }
        kh.setTrangThai(true);
        kh.setMa(txtMaKH2.getText());
        kh.setTen(txtTenKH2.getText());
        kh.setNgaySinh(jdcNgaySinh2.getDate());
        kh.setDiaChi(txtaDiaCHi2.getText());
        kh.setSdt(txtSdt2.getText());
        if (rdNam.isSelected()) {
            kh.setGioiTinh("Nam");
        } else {
            kh.setGioiTinh("Nữ");
        }
        if (khachHangService.updateKH(kh)) {
            JOptionPane.showMessageDialog(this, "Sửa thành công");
            loadTable(khachHangService.getList());
        } else {
            JOptionPane.showMessageDialog(this, "Sửa thất bại");
        }
        loadTable(khachHangService.getList());
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLamMoi1;
    private javax.swing.JButton btnThem1;
    private javax.swing.JButton btnXuat;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbRank;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private com.toedter.calendar.JDateChooser jdcNgaySinh2;
    private javax.swing.JLabel lbRank4;
    private javax.swing.JPanel lbRank5;
    private javax.swing.JRadioButton rdNam;
    private javax.swing.JRadioButton rdNu;
    private javax.swing.JTable tbKH;
    private javax.swing.JTable tblLichSu;
    private javax.swing.JTextField txtMaKH2;
    private javax.swing.JTextField txtSdt2;
    private javax.swing.JTextField txtTenKH2;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JTextArea txtaDiaCHi2;
    // End of variables declaration//GEN-END:variables
}

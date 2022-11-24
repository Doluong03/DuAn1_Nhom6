/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.poly.it17326.group6.view;

import com.poly.it17326.group6.domainmodel.HoaDon;

import com.poly.it17326.group6.domainmodel.HoaDonChiTiet;

import com.poly.it17326.group6.response.ChiTietSpResponse;
import com.poly.it17326.group6.response.GioHangresponse;
import com.poly.it17326.group6.response.HoaDonresponse;
import com.poly.it17326.group6.service.ChiTietSPService;

import com.poly.it17326.group6.service.HoaDonChiTietService;

import com.poly.it17326.group6.service.impl.ChiTietSPServiceImpl;
import com.poly.it17326.group6.service.impl.HOaDonChiTietServiceIplm;
import com.poly.it17326.group6.service.impl.HoaDonServecieIplm;
import java.math.MathContext;
import java.util.List;

import com.poly.it17326.group6.service.HoaDonService;
import com.poly.it17326.group6.service.impl.ChiTietSPServiceImpl;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author 123
 */
public class FormBanHang extends javax.swing.JPanel {

    private ChiTietSPService chiTietSPService = new ChiTietSPServiceImpl();
    private HoaDonService hoaDonService = new HoaDonServecieIplm();
    private HoaDonChiTietService hoaDonChiTietService = new HOaDonChiTietServiceIplm();
    ArrayList<HoaDonresponse> listHD = hoaDonService.getListsHD();
    /**
     * Creates new form FormBanHang
     */
    public FormBanHang() {
        initComponents();
        loadSP(chiTietSPService.getAll());

        loadHD(listHD);
        jlbMAHD.setVisible(false);
        jlbTENNV.setVisible(false);
        jlbTenKH.setVisible(false);
        jlbngaytao.setVisible(false);
        jlbtienthua.setVisible(false);
        jlbtongitenhang.setVisible(false);
    }

    // list hoa don
    private void loadHD(ArrayList<HoaDonresponse> lists) {
        DefaultTableModel model = new  DefaultTableModel();
        model.setRowCount(0);
        model.setColumnIdentifiers(new String[]{"id", "maHD", "ngayTao", "tenNV", "tinhtrang", "tenKH", "sdt"});
     
        for (HoaDonresponse hoaDonresponse : lists) {
            model.addRow(new Object[]{
                hoaDonresponse.getId(),
                hoaDonresponse.getMaHD(),
                hoaDonresponse.getNgayTao(),
                hoaDonresponse.getTenNV(),
                hoaDonresponse.trangThai(),
                hoaDonresponse.getTenKH(),
                hoaDonresponse.getSdt()});
        }
             tbHoaDon.setModel(model);
    }

    private void loadSP(ArrayList<ChiTietSpResponse> listS) {
        DefaultTableModel model = (DefaultTableModel) tbSanPham.getModel();
        model.setRowCount(0);
        model.setColumnIdentifiers(new String[]{"ID","Mã SP", "Tên SP", "Số lượng tồn", "Đơn giá"});
        int i = 1;
        for (ChiTietSpResponse chiTietSpResponse : listS) {
            model.addRow(new Object[]{chiTietSpResponse.getIdCTSP(),chiTietSpResponse.getMa(), chiTietSpResponse.getTen(), chiTietSpResponse.getSoLuongTon(), chiTietSpResponse.getDonGia()});

        loadIcon();
        // loadHD(hoaDonService.getListsHD());

    }}

 

    private void loadGH(ArrayList<GioHangresponse> listS) {
        DefaultTableModel model = (DefaultTableModel) tbGioHang.getModel();
        model.setRowCount(0);

        BigDecimal tongtien = BigDecimal.valueOf(0) ;
         
        model.setColumnIdentifiers(new String[]{"ID","Ma SP", "Ten SP", "So luong ton", "Don gia", "Thanh tien"});
        for (GioHangresponse gioHangresponse : listS) {
            model.addRow(new Object[]{gioHangresponse.getIdCTSP(),gioHangresponse.getMaSP(), gioHangresponse.getTenSP(), gioHangresponse.getSoLuong(), gioHangresponse.getDonGia(), gioHangresponse.getThanhTien()});
        
        tongtien = tongtien.add(gioHangresponse.getThanhTien()) ;
          // chua fix dc loi

        
        }
        jlbtongitenhang.setVisible(true);
        jlbtongitenhang.setText(tongtien.toString());
    }
    ArrayList<GioHangresponse> listGh = new ArrayList<>();

    private void getSP(int i) {
        GioHangresponse ghr = new GioHangresponse();

        ghr.setIdCTSP(Integer.parseInt(tbSanPham.getValueAt(i, 0).toString()));
        ghr.setMaSP(tbSanPham.getValueAt(i, 1).toString());
        ghr.setTenSP(tbSanPham.getValueAt(i, 2).toString());
        String sl = JOptionPane.showInputDialog(this, "Nhap so luong");

        ghr.setSoLuong(Integer.parseInt(sl));
        BigDecimal donGia = new BigDecimal(tbSanPham.getValueAt(i, 3).toString());
        ghr.setDonGia(donGia);
        listGh.add(ghr);
        loadGH(listGh);
    }

    
    
    private void showJLBKH(){ // hien thi jlb cua phan thong tin nv va kh
        jlbMAHD.setVisible(true);
        jlbTENNV.setVisible(true);
        jlbTenKH.setVisible(true);
        jlbngaytao.setVisible(true);
       
    }
      private void showTTHD(){ // hien thi jlb tong tien 
        
        jlbtienthua.setVisible(true);
        jlbtongitenhang.setVisible(true);
       
    }
    private void loadTextFiled(int index){
        if(tbHoaDon.getRowCount()>0){
        HoaDonresponse hoaDonresponse = listHD.get(index-1);
        jlbMAHD.setText(hoaDonresponse.getMaHD());
        jlbTENNV.setText(hoaDonresponse.getTenNV());
      //  jlbTenKH.setText(hoaDonresponse.getTenKH());
        jlbngaytao.setText(hoaDonresponse.getNgayTao()+"");
        //txtSDT.setText(hoaDonresponse.getSdt()+"");
            showJLBKH();
    }
    }

    private void loadIcon() {
        btnLamMoiGH.setIcon(new ImageIcon("D:\\Nhom6_PRO1041\\Anh\\reload.png"));
        btnXoaSP.setIcon(new ImageIcon("D:\\Nhom6_PRO1041\\Anh\\dustbin.png"));
        lbTimKiemSP.setIcon(new ImageIcon("D:\\Nhom6_PRO1041\\Anh\\search.png"));
        btntaohoadon.setIcon(new ImageIcon("D:\\Nhom6_PRO1041\\Anh\\add.png"));
        btnthanhtoan.setIcon(new ImageIcon("D:\\Nhom6_PRO1041\\Anh\\pay.png"));
    }

    private String doiNgay(Date d) {
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("yyyy-MM-dd");
        String ngayTao = format.format(d);
        return ngayTao;
    }
//
//    private void loadHD(ArrayList<HoaDonresponse> lists) {
//        DefaultTableModel model = (DefaultTableModel) tbHoaDon.getModel();
//        model.setRowCount(0);
//        model.setColumnIdentifiers(new String[]{"Mã HD", "Ngày tạo", "Nhân viên", "Trạng thái"});
//        int i = 1;
//        for (HoaDonresponse hoaDonresponse : lists) {
//            model.addRow(new Object[]{
//                hoaDonresponse.getMaHD(),
//                doiNgay(hoaDonresponse.getNgayTao()),
//                hoaDonresponse.getTenNV(),
//                hoaDonresponse.trangThai()});
//        }
//
//    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        buttonGroup5 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbSanPham = new javax.swing.JTable();
        txtTimKiemSP = new javax.swing.JTextField();
        lbTimKiemSP = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbGioHang = new javax.swing.JTable();
        btnXoaSP = new javax.swing.JButton();
        btnLamMoiGH = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbHoaDon = new javax.swing.JTable();
        rdAllHoadon = new javax.swing.JRadioButton();
        rdDahuy = new javax.swing.JRadioButton();
        rdChoTT = new javax.swing.JRadioButton();
        rdDATT = new javax.swing.JRadioButton();
        txtsearchhoadon = new javax.swing.JTextField();
        btnsearchhoadon = new javax.swing.JButton();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jlbTENNV = new javax.swing.JLabel();
        jlbMAHD = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        jlbTenKH = new javax.swing.JLabel();
        btnsuasdt = new javax.swing.JButton();
        btnxoasdt = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jlbngaytao = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        btntaohoadon = new javax.swing.JButton();
        btnhuy = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jlbtongitenhang = new javax.swing.JLabel();
        jlbtienthua = new javax.swing.JLabel();
        cbbhtThanhtoan = new javax.swing.JComboBox<>();
        txttienkhachdua = new javax.swing.JTextField();
        btnthanhtoan = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sản Phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tbSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbSanPhamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbSanPham);

        txtTimKiemSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemSPActionPerformed(evt);
            }
        });
        txtTimKiemSP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTimKiemSPKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(txtTimKiemSP, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbTimKiemSP, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 704, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()

                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiemSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbTimKiemSP, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)

                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Giỏ hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tbGioHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbGioHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbGioHangMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbGioHang);

        btnXoaSP.setBackground(new java.awt.Color(255, 255, 255));
        btnXoaSP.setText("Bỏ khỏi giỏ hàng");
        btnXoaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaSPActionPerformed(evt);
            }
        });

        btnLamMoiGH.setBackground(new java.awt.Color(255, 255, 255));
        btnLamMoiGH.setText("Làm mới giỏ hàng");
        btnLamMoiGH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiGHActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(202, 202, 202)
                        .addComponent(btnXoaSP)
                        .addGap(50, 50, 50)
                        .addComponent(btnLamMoiGH))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 720, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXoaSP)
                    .addComponent(btnLamMoiGH))
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tbHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbHoaDonMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbHoaDon);

        rdAllHoadon.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdAllHoadon);
        rdAllHoadon.setText("Tất cả");
        rdAllHoadon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdAllHoadonActionPerformed(evt);
            }
        });

        rdDahuy.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdDahuy);
        rdDahuy.setText("Đã hủy");
        rdDahuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdDahuyActionPerformed(evt);
            }
        });

        rdChoTT.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdChoTT);
        rdChoTT.setText("Chờ thanh toán");
        rdChoTT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdChoTTMouseClicked(evt);
            }
        });

        rdDATT.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdDATT);
        rdDATT.setText("Đã thanh toán");
        rdDATT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdDATTMouseClicked(evt);
            }
        });

        txtsearchhoadon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtsearchhoadonActionPerformed(evt);
            }
        });

        btnsearchhoadon.setBackground(new java.awt.Color(255, 255, 255));
        btnsearchhoadon.setText("Search");
        btnsearchhoadon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsearchhoadonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 708, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(rdAllHoadon)
                        .addGap(37, 37, 37)
                        .addComponent(rdDahuy)
                        .addGap(35, 35, 35)
                        .addComponent(rdChoTT)
                        .addGap(18, 18, 18)
                        .addComponent(rdDATT)
                        .addGap(35, 35, 35)
                        .addComponent(txtsearchhoadon, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnsearchhoadon)))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdAllHoadon)
                    .addComponent(rdDahuy)
                    .addComponent(rdChoTT)
                    .addComponent(rdDATT)
                    .addComponent(txtsearchhoadon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnsearchhoadon))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        jLayeredPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tạo hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel1.setText("Tên NV");

        jLabel2.setText("Mã HĐ");

        jlbTENNV.setText("jLabel3");

        jlbMAHD.setText("jLabel4");

        jLabel3.setText("Tên KH");

        jLabel4.setText("SDT");

        jlbTenKH.setText("jLabel5");

        btnsuasdt.setBackground(new java.awt.Color(255, 255, 255));
        btnsuasdt.setText("Sửa sdt");

        btnxoasdt.setBackground(new java.awt.Color(255, 255, 255));
        btnxoasdt.setText("xóa sdt");

        jLabel5.setText("Ngày tạo");

        jlbngaytao.setText("jLabel6");

        jLabel6.setText("Tổng tiền hàng :");

        jLabel8.setText("Hình thức thanh toán");

        jLabel9.setText("Tiền khách đưa");

        jLabel10.setText("Tiền thừa  :");

        btntaohoadon.setBackground(new java.awt.Color(255, 255, 255));
        btntaohoadon.setText("Tạo hóa đơn");

        btnhuy.setBackground(new java.awt.Color(255, 255, 255));
        btnhuy.setText("Hủy");

        jlbtongitenhang.setText("jLabel11");

        jlbtienthua.setText("jLabel13");
        jlbtienthua.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jlbtienthuaMouseEntered(evt);
            }
        });

        cbbhtThanhtoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbhtThanhtoanActionPerformed(evt);
            }
        });

        txttienkhachdua.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txttienkhachduaMouseEntered(evt);
            }
        });

        btnthanhtoan.setBackground(new java.awt.Color(255, 255, 255));
        btnthanhtoan.setText("Thanh toán");
        btnthanhtoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthanhtoanActionPerformed(evt);
            }
        });

        jLayeredPane1.setLayer(jSeparator1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jlbTENNV, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jlbMAHD, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(txtSDT, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jlbTenKH, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(btnsuasdt, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(btnxoasdt, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jlbngaytao, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel8, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel9, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel10, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(btntaohoadon, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(btnhuy, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jSeparator2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jlbtongitenhang, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jlbtienthua, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(cbbhtThanhtoan, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(txttienkhachdua, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(btnthanhtoan, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jSeparator2)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jlbTENNV, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                                    .addComponent(jlbMAHD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                        .addGap(14, 14, 14)
                                        .addComponent(btnsuasdt)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnxoasdt)))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txttienkhachdua, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                            .addComponent(jlbtienthua, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                            .addComponent(jlbtongitenhang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbbhtThanhtoan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(28, 28, 28)
                                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlbTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlbngaytao, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap(67, Short.MAX_VALUE)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnthanhtoan, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addComponent(btntaohoadon)
                        .addGap(35, 35, 35)
                        .addComponent(btnhuy, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(67, 67, 67))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jlbTENNV))
                .addGap(32, 32, 32)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jlbMAHD))
                .addGap(28, 28, 28)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jlbngaytao))
                .addGap(33, 33, 33)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jlbTenKH))
                .addGap(26, 26, 26)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnsuasdt)
                    .addComponent(btnxoasdt))
                .addGap(5, 5, 5)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel6))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlbtongitenhang)))
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cbbhtThanhtoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txttienkhachdua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jlbtienthua))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnthanhtoan, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btntaohoadon, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnhuy, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );


        btntaohoadon.setText("Tạo hóa đơn");
        btntaohoadon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntaohoadonActionPerformed(evt);
            }
        });

        btnhuy.setText("Hủy");

        btnthanhtoan.setText("Thanh toán");


        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)

                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(btntaohoadon)
                        .addGap(60, 60, 60)
                        .addComponent(btnthanhtoan, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(btnhuy, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(234, Short.MAX_VALUE))

        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()

                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                      .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnthanhtoan, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btntaohoadon, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnhuy, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(56, 144, Short.MAX_VALUE))

        );
    }// </editor-fold>//GEN-END:initComponents

    private void tbGioHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbGioHangMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_tbGioHangMouseClicked

    private void tbSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbSanPhamMouseClicked
        // TODO add your handling code here:

        int index = tbSanPham.getSelectedRow();
        getSP(index);

    }//GEN-LAST:event_tbSanPhamMouseClicked

    private void tbHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHoaDonMouseClicked
        int index = tbHoaDon.getSelectedRow();
      
        loadTextFiled(index);
      
    }//GEN-LAST:event_tbHoaDonMouseClicked

    private void txtTimKiemSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemSPActionPerformed

    private void rdAllHoadonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdAllHoadonActionPerformed
        // TODO add your handling code here:
        loadHD(hoaDonService.getListsHD());
    }//GEN-LAST:event_rdAllHoadonActionPerformed

    private void rdDahuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdDahuyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdDahuyActionPerformed

    private void txtsearchhoadonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsearchhoadonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsearchhoadonActionPerformed


    private void cbbhtThanhtoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbhtThanhtoanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbhtThanhtoanActionPerformed

    private void btnthanhtoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthanhtoanActionPerformed
      ArrayList<HoaDonChiTiet> listHDCT = new ArrayList<>();
        if (listGh.isEmpty()) {
            JOptionPane.showMessageDialog(this, "vui long chon san pham");
            return;
        }
      
        
       ArrayList<HoaDon> listIDHD = hoaDonService.getIDHD(jlbMAHD.getText());
          int idhd = listIDHD.get(0).getId();
        for (GioHangresponse gioHangresponse : listGh) {
            HoaDonChiTiet hdct = new HoaDonChiTiet();
            hdct.setMaHD(jlbMAHD.getText());
            hdct.setDonGia(gioHangresponse.getDonGia());
            hdct.setSoLuong(gioHangresponse.getSoLuong());
            hdct.setTenKH(jlbTenKH.getText());
            hdct.setTongTien(new BigDecimal(jlbtongitenhang.getText()));
            hdct.setIdChiTietSP(gioHangresponse.getIdCTSP());
            hdct.setIdHoaDon(idhd);
            listHDCT.add(hdct);
        }
        
        for (HoaDonChiTiet hoaDonChiTiet : listHDCT) {
             hoaDonChiTietService.saveHDCT(hoaDonChiTiet);
             
        }
//         BigDecimal tongtien = new BigDecimal(jlbtongitenhang.getText());
//         BigDecimal tienkhachdua = new BigDecimal(txttienkhachdua.getText());
//         BigDecimal tienthua = tongtien.subtract(tienkhachdua);
        if(txttienkhachdua.getText().isEmpty()){
          JOptionPane.showMessageDialog(this,"Hoa don da duoc treo");
          hoaDonService.updateHD(jlbMAHD.getText(),new BigDecimal(jlbtongitenhang.getText()),0);
              loadHD(hoaDonService.getListsHD());
        }else {
          JOptionPane.showMessageDialog(this,"Thanh toan thanh cong");
          hoaDonService.updateHD(jlbMAHD.getText(),new BigDecimal(jlbtongitenhang.getText()),1);
            loadHD(hoaDonService.getListsHD());
        }
        
                 listGh.removeAll(listGh);// xoa het gio hang
                 loadGH(listGh);


    }//GEN-LAST:event_btnthanhtoanActionPerformed
     private void lamMoi(){
         jlbMAHD.setText("");
         jlbTENNV.setText("");
         jlbTenKH.setText("");
         jlbngaytao.setText("");
         jlbtongitenhang.setText("");
         jlbtienthua.setText("");
         txtSDT.setText("");
         txttienkhachdua.setText("");
     }
    private void jlbtienthuaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlbtienthuaMouseEntered
       
    }//GEN-LAST:event_jlbtienthuaMouseEntered

    private void txttienkhachduaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txttienkhachduaMouseEntered
         try {
             if(txttienkhachdua.getText().isEmpty()){
                 return;
             }else{
             BigDecimal tongtien = new BigDecimal(jlbtongitenhang.getText());
             BigDecimal tienkhachdua = new BigDecimal(txttienkhachdua.getText());
             BigDecimal tienthua = tongtien.subtract(tienkhachdua);
             jlbtienthua.setVisible(true);
               jlbtienthua.setText(tienthua+"");
             }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Tiền phải là số");
        }
       
    }//GEN-LAST:event_txttienkhachduaMouseEntered

    private void txtTimKiemSPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemSPKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            ArrayList<ChiTietSpResponse> listTimKiem = chiTietSPService.getTimKiem(txtTimKiemSP.getText().toString());
            loadSP(listTimKiem);
        }
    }//GEN-LAST:event_txtTimKiemSPKeyPressed

    private void btnLamMoiGHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiGHActionPerformed
        // TODO add your handling code here:
        listGh.removeAll(listGh);
        loadGH(listGh);
    }//GEN-LAST:event_btnLamMoiGHActionPerformed

    private void btnXoaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaSPActionPerformed
        // TODO add your handling code here:
        int index = tbGioHang.getSelectedRow();
        listGh.remove(index);
        loadGH(listGh);
    }//GEN-LAST:event_btnXoaSPActionPerformed

    private void btntaohoadonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntaohoadonActionPerformed
        // TODO add your handling code here:
        HoaDon HoaDon = null;
        try {
            if (hoaDonService.addHD() == false) {
                JOptionPane.showMessageDialog(this, "Fail");
            } else {
                JOptionPane.showMessageDialog(this, "Success");
            }
        } catch (Exception ex) {
            Logger.getLogger(FormBanHang.class.getName()).log(Level.SEVERE, null, ex);
        }
        loadHD(hoaDonService.getListsHD());
    }//GEN-LAST:event_btntaohoadonActionPerformed

    private void rdChoTTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdChoTTMouseClicked
        // TODO add your handling code here:
//        int tt = 0;
//        loadHD(hoaDonService.timKiemTT(tt));
    }//GEN-LAST:event_rdChoTTMouseClicked

    private void rdDATTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdDATTMouseClicked
//        // TODO add your handling code here:
//        int tt = 1;
//        loadHD(hoaDonService.timKiemTT(tt));
    }//GEN-LAST:event_rdDATTMouseClicked

    private void btnsearchhoadonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsearchhoadonActionPerformed
        // TODO add your handling code here:
//        String maHD=txtsearchhoadon.getText();
//        loadHD(hoaDonService.timKiemHD(maHD));
    }//GEN-LAST:event_btnsearchhoadonActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormBanHang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLamMoiGH;
    private javax.swing.JButton btnXoaSP;
    private javax.swing.JButton btnhuy;
    private javax.swing.JButton btnsearchhoadon;
    private javax.swing.JButton btnsuasdt;
    private javax.swing.JButton btntaohoadon;
    private javax.swing.JButton btnthanhtoan;
    private javax.swing.JButton btnxoasdt;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.JComboBox<String> cbbhtThanhtoan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;

    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel jlbMAHD;
    private javax.swing.JLabel jlbTENNV;
    private javax.swing.JLabel jlbTenKH;
    private javax.swing.JLabel jlbngaytao;
    private javax.swing.JLabel jlbtienthua;
    private javax.swing.JLabel jlbtongitenhang;

    private javax.swing.JLabel lbTimKiemSP;

    private javax.swing.JRadioButton rdAllHoadon;
    private javax.swing.JRadioButton rdChoTT;
    private javax.swing.JRadioButton rdDATT;
    private javax.swing.JRadioButton rdDahuy;
    private javax.swing.JTable tbGioHang;
    private javax.swing.JTable tbHoaDon;
    private javax.swing.JTable tbSanPham;

    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JTextField txtTimKiemSP;

    private javax.swing.JTextField txtsearchhoadon;
    private javax.swing.JTextField txttienkhachdua;
    // End of variables declaration//GEN-END:variables
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.poly.it17326.group6.view;

import com.poly.it17326.group6.repository.HoaDonRepository;
import com.poly.it17326.group6.response.HoaDonCTResponse;
import com.poly.it17326.group6.response.HoaDonresponse;
import com.poly.it17326.group6.service.HoaDonChiTietService;
import com.poly.it17326.group6.service.HoaDonService;
import com.poly.it17326.group6.service.SanPhamService;
import com.poly.it17326.group6.service.impl.HOaDonChiTietServiceIplm;
import com.poly.it17326.group6.service.impl.HoaDonServecieIplm;
import com.poly.it17326.group6.service.impl.SanPhamServiceIplm;
import java.awt.Color;
import java.awt.Cursor;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
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
public class FormHoaDon extends javax.swing.JPanel {

    private HoaDonService hoaDonService = new HoaDonServecieIplm();
    private HoaDonChiTietService hoaDonChiTietService = new HOaDonChiTietServiceIplm();
    private SanPhamService sanPhamService = new SanPhamServiceIplm();
    private List<HoaDonresponse> list = hoaDonService.getListsHD();
    private List<HoaDonCTResponse> listhdct;
    private DefaultTableModel defaultTableModel = new DefaultTableModel();
    private DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel();
    private HoaDonRepository hdr = new HoaDonRepository();

    /**
     * Creates new form FormHoaDon_p2
     */
    public FormHoaDon() {
        initComponents();
        loadData(hoaDonService.getListsHD_HTBH(0));
        loadData_GH(hoaDonService.getListsHD_HTBH(1));
        loadcb();
    }

    private void loadData(List<HoaDonresponse> list) {
        defaultTableModel = (DefaultTableModel) tblHoaDon.getModel();
        defaultTableModel.setRowCount(0);
        defaultTableModel.setColumnIdentifiers(new String[]{"Mã hóa đơn", "Ngày tạo", "SĐT", "Mã khách hàng", "Tên khách hàng", "Tên nhân viên", "Tình trạng"});
        for (HoaDonresponse hoaDonresponse : list) {
            defaultTableModel.addRow(new Object[]{hoaDonresponse.getMaHD(), hoaDonresponse.getNgayTao(), hoaDonresponse.getSdt(), hoaDonresponse.getMaKH(), hoaDonresponse.getTenKH(), hoaDonresponse.getTenNV(), hoaDonresponse.trangThai()});
        }
    }

    public void loadTableHoaDonChiTiet() {
        int row = this.tblHoaDon.getSelectedRow();
        if (row == -1) {
            return;
        }
        String mahd = this.tblHoaDon.getValueAt(row, 0).toString();
        defaultTableModel = (DefaultTableModel) tblhdct.getModel();
        defaultTableModel.setColumnIdentifiers(new String[]{"Mã hóa đơn", "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Đơn giá", "Tổng tiền sản phẩm", "Tổng tiền hóa đơn "});
        defaultTableModel.setRowCount(0);
        listhdct = hoaDonService.getListHDCT(mahd);
        if (listhdct == null) {
            return;
        }
        for (HoaDonCTResponse hdct : listhdct) {
            defaultTableModel.addRow(new Object[]{hdct.getMaHD(), hdct.getMaSP(), hdct.getTen(), hdct.getSoLuong(), hdct.getDonGia(), hdct.getGiaBan(), hdct.getTongTien()});
        }
    }

    public void loadTableHoaDonChiTietGH() {
        int row = this.tblHoaDon1.getSelectedRow();
        if (row == -1) {
            return;
        }
        String mahd = this.tblHoaDon1.getValueAt(row, 0).toString();
        defaultTableModel = (DefaultTableModel) tblhdct1.getModel();
        defaultTableModel.setColumnIdentifiers(new String[]{"Mã hóa đơn", "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Đơn giá", "Tổng tiền sản phẩm", "Tổng tiền hóa đơn "});
        defaultTableModel.setRowCount(0);
        listhdct = hoaDonService.getListHDCT(mahd);
        if (listhdct == null) {
            return;
        }
        for (HoaDonCTResponse hdct : listhdct) {
            defaultTableModel.addRow(new Object[]{hdct.getMaHD(), hdct.getMaSP(), hdct.getTen(), hdct.getSoLuong(), hdct.getDonGia(), hdct.getGiaBan(), hdct.getTongTien()});
        }
    }

    private void loadData_GH(List<HoaDonresponse> list) {
        defaultTableModel = (DefaultTableModel) tblHoaDon1.getModel();
        defaultTableModel.setRowCount(0);
        defaultTableModel.setColumnIdentifiers(new String[]{"Mã hóa đơn", "Ngày tạo", "Tổng tiền", "Tên người nhận", "Địa chỉ", "Tên người ship", "Tình trạng"});
        for (HoaDonresponse hoaDonresponse : list) {
            defaultTableModel.addRow(new Object[]{hoaDonresponse.getMaHD(), hoaDonresponse.getNgayTao(), hoaDonresponse.getTongTien(), hoaDonresponse.getTenNN(), hoaDonresponse.getDiaChi(), hoaDonresponse.getTenNS(), hoaDonresponse.trangThai()});
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        btnSeach = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        txttimkiem = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cbotrangthai = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        btnhuy = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblhdct = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblHoaDon1 = new javax.swing.JTable();
        btnSeach1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        txttimkiem1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cbotrangthai1 = new javax.swing.JComboBox<>();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        btnhuy1 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblhdct1 = new javax.swing.JTable();

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("sansserif", 1, 14))); // NOI18N

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
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
        tblHoaDon.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseMoved(evt);
            }
        });
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblHoaDon);

        btnSeach.setText("Seach");
        btnSeach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeachActionPerformed(evt);
            }
        });

        jLabel1.setText("Tìm kiếm hóa đơn");

        jButton1.setText("Xuất danh sách");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Trạng thái hóa đơn");

        cbotrangthai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đã thanh toán", "Chờ thanh toán", " Đã hủy" }));
        cbotrangthai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbotrangthaiActionPerformed(evt);
            }
        });

        jButton2.setText("<<");

        jButton3.setText("<");

        jButton4.setText(">");

        jButton5.setText(">>");

        btnhuy.setText("Hủy");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addGap(76, 76, 76)
                        .addComponent(jButton3)
                        .addGap(85, 85, 85)
                        .addComponent(jButton4)
                        .addGap(75, 75, 75)
                        .addComponent(jButton5)
                        .addGap(133, 133, 133)
                        .addComponent(btnhuy)
                        .addGap(495, 495, 495))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1475, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txttimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbotrangthai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(33, 33, 33)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton1)
                                    .addComponent(btnSeach))))
                        .addGap(0, 18, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txttimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSeach))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbotrangthai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton3)
                    .addComponent(jButton2)
                    .addComponent(jButton5)
                    .addComponent(btnhuy))
                .addContainerGap())
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa đơn chi tiết", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("sansserif", 1, 14))); // NOI18N

        tblhdct.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(tblhdct);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1473, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Hóa đơn tại quầy", jPanel3);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("sansserif", 1, 14))); // NOI18N

        tblHoaDon1.setModel(new javax.swing.table.DefaultTableModel(
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
        tblHoaDon1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                tblHoaDon1MouseMoved(evt);
            }
        });
        tblHoaDon1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDon1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblHoaDon1);

        btnSeach1.setText("Seach");
        btnSeach1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeach1ActionPerformed(evt);
            }
        });

        jLabel3.setText("Tìm kiếm hóa đơn");

        jButton6.setText("Xuất danh sách");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel4.setText("Trạng thái hóa đơn");

        cbotrangthai1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đã thanh toán", "Chờ thanh toán", " Đã hủy" }));
        cbotrangthai1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbotrangthai1ItemStateChanged(evt);
            }
        });
        cbotrangthai1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbotrangthai1ActionPerformed(evt);
            }
        });

        jButton7.setText("<<");

        jButton8.setText("<");

        jButton9.setText(">");

        jButton10.setText(">>");

        btnhuy1.setText("Hủy");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton7)
                        .addGap(76, 76, 76)
                        .addComponent(jButton8)
                        .addGap(85, 85, 85)
                        .addComponent(jButton9)
                        .addGap(75, 75, 75)
                        .addComponent(jButton10)
                        .addGap(133, 133, 133)
                        .addComponent(btnhuy1)
                        .addGap(495, 495, 495))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1475, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txttimkiem1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbotrangthai1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(33, 33, 33)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton6)
                                    .addComponent(btnSeach1))))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txttimkiem1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSeach1))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cbotrangthai1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton9)
                    .addComponent(jButton8)
                    .addComponent(jButton7)
                    .addComponent(jButton10)
                    .addComponent(btnhuy1))
                .addContainerGap())
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa đơn chi tiết", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("sansserif", 1, 14))); // NOI18N

        tblhdct1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(tblhdct1);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 1473, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 15, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Hóa đơn đặt hàng", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1524, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblHoaDonMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseMoved
        tblHoaDon.setCursor(new Cursor(Cursor.HAND_CURSOR));
        int row = tblHoaDon.rowAtPoint(evt.getPoint());
        if (row > -1) {
            // easiest way:
            tblHoaDon.clearSelection();
            tblHoaDon.setRowSelectionInterval(row, row);
        } else {
            tblHoaDon.setSelectionBackground(Color.blue);
        }
    }//GEN-LAST:event_tblHoaDonMouseMoved

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        loadTableHoaDonChiTiet();
    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void btnSeachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeachActionPerformed
        String maHD = txttimkiem.getText();
        String sdt = txttimkiem.getText();
        loadData(hoaDonService.timKiemHD(maHD, sdt));
    }//GEN-LAST:event_btnSeachActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        //        JFileChooser chooser = new JFileChooser();
        //        chooser.showOpenDialog(null);
        //        File f = chooser.getSelectedFile();
        //        String filename = f.getAbsolutePath();
        //        HoaDonExport.exportData(list, filename + ".xlsx");
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("HoaDon");
            XSSFRow row = null;
            Cell cell = null;
            row = sheet.createRow(3);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("Stt");

            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("Mã hóa đơn");

            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("Ngày tạo");

            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("SĐT");

            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue("Mã khách hàng");

            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue("Tên khách hàng");

            cell = row.createCell(6, CellType.STRING);
            cell.setCellValue("Tên nhân viên");

            cell = row.createCell(7, CellType.STRING);
            cell.setCellValue("Tình trạng");

            for (int i = 0; i < tblHoaDon.getRowCount(); i++) {

                row = sheet.createRow(5 + i);
                cell = row.createCell(0, CellType.NUMERIC);
                cell.setCellValue(i + 1);

                cell = row.createCell(1, CellType.NUMERIC);
                cell.setCellValue(tblHoaDon.getValueAt(i, 0).toString());

                cell = row.createCell(2, CellType.NUMERIC);
                cell.setCellValue(tblHoaDon.getValueAt(i, 1).toString());

                if (tblHoaDon.getValueAt(i, 2) == null) {
                    cell = row.createCell(3, CellType.NUMERIC);
                    cell.setCellValue("Trống");
                } else {
                    cell = row.createCell(3, CellType.NUMERIC);
                    cell.setCellValue(tblHoaDon.getValueAt(i, 2).toString());
                }
                cell = row.createCell(4, CellType.NUMERIC);
                cell.setCellValue(tblHoaDon.getValueAt(i, 3).toString());

                cell = row.createCell(5, CellType.NUMERIC);
                cell.setCellValue(tblHoaDon.getValueAt(i, 4).toString());

                cell = row.createCell(6, CellType.NUMERIC);
                cell.setCellValue(tblHoaDon.getValueAt(i, 5).toString());

                cell = row.createCell(7, CellType.NUMERIC);
                cell.setCellValue(tblHoaDon.getValueAt(i, 6).toString());

            }

            File file = new File("D:\\Nhom6_PRO1041\\Export_HD\\HoaDon.xlsx");
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
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cbotrangthaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbotrangthaiActionPerformed
        int tt = 1;

        if (cbotrangthai.getSelectedItem().equals("Đã thanh toán")) {
            List<HoaDonresponse> list = hoaDonService.timKiemTT_all(tt);
            loadData(list);
            return;
        } else if (cbotrangthai.getSelectedItem().equals("Chờ thanh toán")) {
            tt = 0;
            List<HoaDonresponse> list = hoaDonService.timKiemTT_all(tt);
            loadData(list);
            return;
        } else if (cbotrangthai.getSelectedItem().equals("Đã hủy")) {
            tt = 2;
            List<HoaDonresponse> list = hoaDonService.timKiemTT_all(tt);
            loadData(list);

        }
    }//GEN-LAST:event_cbotrangthaiActionPerformed

    private void tblHoaDon1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDon1MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_tblHoaDon1MouseMoved

    private void tblHoaDon1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDon1MouseClicked
        // TODO add your handling code here:
        loadTableHoaDonChiTietGH();
    }//GEN-LAST:event_tblHoaDon1MouseClicked

    private void btnSeach1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeach1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSeach1ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void loadcb() {
        cbotrangthai1.removeAllItems();
        cbotrangthai1.addItem("Trạng thái");
        cbotrangthai1.addItem("Đã giao hàng");
        cbotrangthai1.addItem("Chờ giao hàng");
        cbotrangthai1.addItem("Đang giao hàng");
    }
    private void cbotrangthai1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbotrangthai1ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_cbotrangthai1ActionPerformed

    private void cbotrangthai1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbotrangthai1ItemStateChanged
        // TODO add your handling code here:

        if (evt.getStateChange() == 1) {
            if(cbotrangthai1.getSelectedIndex()==0){
                 loadData_GH(hoaDonService.getListsHD_HTBH(1));
            }
            int tt = 5;
            if (cbotrangthai1.getSelectedItem().equals("Đã giao hàng")) {
                List<HoaDonresponse> list = hoaDonService.timKiemTT_all(tt);
                loadData_GH(list);
                return;
            } else if (cbotrangthai1.getSelectedItem().equals("Chờ giao hàng")) {
                tt = 3;
                List<HoaDonresponse> list = hoaDonService.timKiemTT_all(tt);
                loadData_GH(list);
                return;
            } else if (cbotrangthai1.getSelectedItem().equals("Đang giao hàng")) {
                tt = 4;
                List<HoaDonresponse> list = hoaDonService.timKiemTT_all(tt);
                loadData_GH(list);

            }
        }
    }//GEN-LAST:event_cbotrangthai1ItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSeach;
    private javax.swing.JButton btnSeach1;
    private javax.swing.JButton btnhuy;
    private javax.swing.JButton btnhuy1;
    private javax.swing.JComboBox<String> cbotrangthai;
    private javax.swing.JComboBox<String> cbotrangthai1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTable tblHoaDon1;
    private javax.swing.JTable tblhdct;
    private javax.swing.JTable tblhdct1;
    private javax.swing.JTextField txttimkiem;
    private javax.swing.JTextField txttimkiem1;
    // End of variables declaration//GEN-END:variables
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.poly.it17326.group6.view;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.poly.it17326.group6.domainmodel.ChiTietSP;
import com.poly.it17326.group6.domainmodel.HoaDon;
import com.poly.it17326.group6.domainmodel.HoaDonChiTiet;
import com.poly.it17326.group6.domainmodel.KhachHang;
import com.poly.it17326.group6.domainmodel.Voucher;
import com.poly.it17326.group6.domainmodel.SanPham;
import com.poly.it17326.group6.domainmodel.TaiKhoan;
import com.poly.it17326.group6.repository.ChiTietSpRepository;
import com.poly.it17326.group6.repository.HoaDonChiTietResponsitory;
import com.poly.it17326.group6.response.ChiTietSpResponse;
import com.poly.it17326.group6.response.GioHangresponse;
import com.poly.it17326.group6.response.HoaDonCTResponse;
import com.poly.it17326.group6.response.HoaDonresponse;
import com.poly.it17326.group6.response.VocherReponse;
import com.poly.it17326.group6.service.ChiTietSPService;
import com.poly.it17326.group6.service.HoaDonChiTietService;
import com.poly.it17326.group6.service.HoaDonService;
import com.poly.it17326.group6.service.KhachHangService;
import com.poly.it17326.group6.service.VoucherService;
import com.poly.it17326.group6.service.impl.ChiTietSPServiceImpl;
import com.poly.it17326.group6.service.impl.HOaDonChiTietServiceIplm;
import com.poly.it17326.group6.service.impl.HoaDonServecieIplm;
import com.poly.it17326.group6.service.impl.VoucherServiceIplm;

import com.poly.it17326.group6.service.TaiKhoanService;
import com.poly.it17326.group6.service.impl.ChiTietSPServiceImpl;
import com.poly.it17326.group6.service.impl.HOaDonChiTietServiceIplm;
import com.poly.it17326.group6.service.impl.HoaDonServecieIplm;
import com.poly.it17326.group6.service.impl.KhachHangServiceImpl;
import com.poly.it17326.group6.service.impl.TaiKhoanServiceImpl;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Window;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author 123
 */
public class FormBanHang extends javax.swing.JPanel implements Runnable, ThreadFactory {

    private ChiTietSPService chiTietSPService = new ChiTietSPServiceImpl();
    private HoaDonService hoaDonService = new HoaDonServecieIplm();
    private HoaDonChiTietService hoaDonChiTietService = new HOaDonChiTietServiceIplm();
    private TaiKhoanService taiKhoanService = new TaiKhoanServiceImpl();
    List<HoaDonresponse> listHD = hoaDonService.getListsHD();
    private VoucherService voucherService = new VoucherServiceIplm();
    private KhachHangService khachHangService = new KhachHangServiceImpl();

    /**
     * Creates new form FormBanHang
     */
    public FormBanHang() {
        initComponents();
        loadSP(chiTietSPService.getAll());
        loadIcon();
        cbbhtThanhtoan.addItem("Tiền mặt");
        loadWeb();
        setKH("KH0");
        loadHD(listHD);

    }

    private void loadSP(List<ChiTietSpResponse> listS) {
        DefaultTableModel model = (DefaultTableModel) tbSanPham.getModel();
        model.setRowCount(0);
        model.setColumnIdentifiers(new String[]{"Mã SP", "Tên SP", "Số lượng tồn", "Đơn giá"});
        int i = 1;
        for (ChiTietSpResponse chiTietSpResponse : listS) {
            model.addRow(new Object[]{chiTietSpResponse.getMa(), chiTietSpResponse.getTen(), chiTietSpResponse.getSoLuongTon(), chiTietSpResponse.getDonGia()});
        }
    }

    private void loadGH(ArrayList<GioHangresponse> listS) {
        DefaultTableModel model = (DefaultTableModel) tbGioHang.getModel();
        model.setRowCount(0);
        BigDecimal tongtien = BigDecimal.valueOf(0);
        model.setColumnIdentifiers(new String[]{"Mã SP", "Tên SP", "Số lượng ", "Đơn giá", "Thành tiền"});
        for (GioHangresponse gioHangresponse : listS) {
            model.addRow(new Object[]{gioHangresponse.getMaSP(), gioHangresponse.getTenSP(), gioHangresponse.getSoLuong(), gioHangresponse.getDonGia(), gioHangresponse.getThanhTien()});
            tongtien = tongtien.add(gioHangresponse.getThanhTien());

        }
        if (jlbGIAMGIA.getText().isEmpty()) {
            jlbtongitenhang.setVisible(true);
            jlbtongitenhang.setText(tongtien.toString());
        } else {
            BigDecimal tiengiam = BigDecimal.valueOf(0);
            tongtien = tongtien.subtract(tongtien.multiply(new BigDecimal(jlbGIAMGIA.getText()).divide(new BigDecimal(100))));
            tiengiam = tongtien.multiply(new BigDecimal(jlbGIAMGIA.getText()).divide(new BigDecimal(100)));

            jlbTiengiam.setText(tiengiam.toString());
            jlbtongitenhang.setVisible(true);
            jlbtongitenhang.setText(tongtien.toString());
        }

    }

    ArrayList<GioHangresponse> listGh = new ArrayList<>();

    private void getSP(int i) {
        int check = 0;
        GioHangresponse ghr = new GioHangresponse();
        for (ChiTietSpResponse ctr : chiTietSPService.getAll()) {
            if (ctr.getMa().equals(tbSanPham.getValueAt(i, 0).toString())) {
                ghr.setIdCTSP(ctr.getIdCTSP());
            }
        }
        ghr.setMaSP(tbSanPham.getValueAt(i, 0).toString());
        ghr.setTenSP(tbSanPham.getValueAt(i, 1).toString());
        BigDecimal donGia = new BigDecimal(tbSanPham.getValueAt(i, 3).toString());
        ghr.setDonGia(donGia);
        for (GioHangresponse x : listGh) {
            if (x.getMaSP().equals(tbSanPham.getValueAt(i, 0).toString())) {
                JOptionPane.showMessageDialog(this, "Sản phẩm đã có trong giỏ hàng!");
                check = JOptionPane.showConfirmDialog(this, "Bạn có muốn cập nhật lại số lượng không?", "Error", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION);
                if (check == JOptionPane.YES_OPTION) {
                    String sl2 = JOptionPane.showInputDialog(this, "Nhập số lượng");
                    x.setSoLuong(Integer.parseInt(sl2));
                    loadGH(listGh);
                    return;
                } else {
                    return;
                }
            } else {
                continue;
            }
        }
        String sl = JOptionPane.showInputDialog(this, "Nhập số lượng");
        ghr.setSoLuong(Integer.parseInt(sl));
        listGh.add(ghr);
        loadGH(listGh);
    }

    private void loadIcon() {
        btnLamMoiGH.setIcon(new ImageIcon("D:\\Nhom6_PRO1041\\Anh\\SanPham\\reload.png"));
        btnXoaSP.setIcon(new ImageIcon("D:\\Nhom6_PRO1041\\Anh\\SanPham\\dustbin.png"));
        lbTimKiemSP.setIcon(new ImageIcon("D:\\Nhom6_PRO1041\\Anh\\SanPham\\search.png"));
        btntaohoadon.setIcon(new ImageIcon("D:\\Nhom6_PRO1041\\Anh\\SanPham\\add.png"));
        btnThanhToan.setIcon(new ImageIcon("D:\\Nhom6_PRO1041\\Anh\\SanPham\\pay.png"));

    }

    private void loadDataHdct(ArrayList<HoaDonCTResponse> listGh) {
        DefaultTableModel model = (DefaultTableModel) tbGioHang.getModel();
        model.setColumnIdentifiers(new String[]{"Mã Sp", "Tên Sp", "Số Lượng", "Đơn giá", "Thành tiền"});
        model.setRowCount(0);
        for (HoaDonCTResponse ghr : listGh) {
            model.addRow(new Object[]{ghr.getMaSP(), ghr.getTen(), ghr.getSoLuong(), ghr.getDonGia(), ghr.getThanhTien()});
        }
    }

    private String doiNgay(Date d) {
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("yyyy-MM-dd");
        String ngayTao = format.format(d);
        return ngayTao;
    }

//  
    private void loadHD(List<HoaDonresponse> lists) {
        DefaultTableModel model = (DefaultTableModel) tbHoaDon.getModel();
        model.setRowCount(0);
        model.setColumnIdentifiers(new String[]{"Mã HD", "Ngày tạo", "Nhân viên", "Trạng thái"});
        int i = 1;
        for (HoaDonresponse hoaDonresponse : lists) {
            model.addRow(new Object[]{
                hoaDonresponse.getMaHD(),
                doiNgay(hoaDonresponse.getNgayTao()),
                // hoaDonresponse.getNgayTao(),
                hoaDonresponse.getTenNV(),
                hoaDonresponse.trangThai()});
        }

    }

    private void showJLBKH() { // hien thi jlb cua phan thong tin nv va kh
        jlbMAHD.setVisible(true);
        // jlbTENNV.setVisible(true);
        txtTenKH.setVisible(true);

    }

    private void showTTHD() { // hien thi jlb tong tien 

        jlbtienthua.setVisible(true);
        jlbtongitenhang.setVisible(true);

    }

    private void loadTextFiled(int index) {
        int check = 0;
        if (tbHoaDon.getRowCount() > 0) {
            jlbMAHD.setText(tbHoaDon.getValueAt(index, 0).toString());
            //  jlbTENNV.setText(tbHoaDon.getValueAt(index, 2).toString());
            //  jlbngaytao.setText(tbHoaDon.getValueAt(index, 1) + "");
            showJLBKH();
            if (tbHoaDon.getValueAt(index, 3).equals("Chờ thanh toán")) {
                btnThanhToan.setEnabled(true);
            } else {
                btnThanhToan.setEnabled(false);
            }
            for (HoaDonresponse hoaDonresponse : listHD) {
                if (hoaDonresponse.getMaHD().equals(tbHoaDon.getValueAt(index, 0))) {
                    txtTenKH.setText(hoaDonresponse.getTenKH());
                    txtMaKH.setText(hoaDonresponse.getMaKH());
                }
            }

            ArrayList<HoaDonCTResponse> listGhct = new ArrayList<>();
            double sum = 0;
            for (HoaDonCTResponse gh : hoaDonChiTietService.getListHDCT()) {
                if (tbHoaDon.getValueAt(index, 0).equals(gh.getMaHD())) {
                    listGhct.add(gh);
                    sum = sum + (gh.getThanhTien().doubleValue());
                    BigDecimal tt = new BigDecimal(sum);
                    jlbtongitenhang.setText(tt.toString());
                    check = 1;
                }

            }
            if (listGhct.isEmpty()) {
                for (GioHangresponse gioHangresponse : listGh) {
                    loadGH(listGh);
                    sum = sum + (gioHangresponse.getThanhTien().doubleValue());
                    BigDecimal tt = new BigDecimal(sum);
                    jlbtongitenhang.setText(tt.toString());
                    check = 1;
                }
            } else {
                loadDataHdct(listGhct);
            }

            if (tbHoaDon.getValueAt(index, 3).equals("Chờ thanh toán") && check == 1) {
                listGh.removeAll(listGh);
                for (HoaDonCTResponse hoaDonCTResponse : listGhct) {
                    GioHangresponse GHupdate = new GioHangresponse();
                    GHupdate.setMaSP(hoaDonCTResponse.getMaSP());
                    GHupdate.setIdCTSP(hoaDonCTResponse.getIdChiTietSP());
                    GHupdate.setDonGia(hoaDonCTResponse.getDonGia());
                    GHupdate.setTenSP(hoaDonCTResponse.getTen());
                    GHupdate.setSoLuong(hoaDonCTResponse.getSoLuong());
                    listGh.add(GHupdate);
                    loadGH(listGh);
                }
            } else {
                return;
            }
        }
    }

    private void updateSL() {
        for (GioHangresponse gioHangresponse : listGh) {
            int sl = gioHangresponse.getSoLuong();
            chiTietSPService.updateSoLuong(sl, gioHangresponse.getIdCTSP());
        }
        loadSP(chiTietSPService.getAll());
    }
    int idTK;

    public void setTk(String ten) {
        for (TaiKhoan tk : taiKhoanService.getCheckTen(ten)) {
            idTK = tk.getId();
        }
    }
    private WebcamPanel panel = null;
    private Webcam webcam = null;
    private Executor executor = Executors.newSingleThreadExecutor(this);

    private void loadWeb() {
        Dimension sie = WebcamResolution.QVGA.getSize();
        webcam = Webcam.getWebcams().get(0);
        webcam.setViewSize(sie);
        panel = new WebcamPanel(webcam);
        panel.setPreferredSize(sie);
        panel.setFPSDisplayed(true);
        pnBarCode.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 230, 150));
        executor.execute(this);
//           scan.setVisible(true);
    }

    public void run() {
        int check = 0;
        do {
            String maTam = null;
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(sacn_QR.class.getName()).log(Level.SEVERE, null, ex);
            }
            Result result = null;
            BufferedImage image = null;

            if (webcam.isOpen()) {
                if ((image = webcam.getImage()) == null) {
                    continue;
                }
            }

            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
            try {
                result = new MultiFormatReader().decode(bitmap);
            } catch (Exception e) {
            }

            if (result != null) {
//                outer:
//                for (GioHangresponse x : listGh) {
//                    System.out.println(x);
//                    if (x.getMaVach().equals(result.getText())) {
//                        JOptionPane.showMessageDialog(this, "Sản phẩm đã có trong giỏ hàng!");
//                        check = JOptionPane.showConfirmDialog(this, "Bạn có muốn cập nhật lại số lượng không?", "Error", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION);
//                        if (check == JOptionPane.YES_OPTION) {
//                            String sl2 = JOptionPane.showInputDialog(this, "Nhập số lượng");
//                            x.setSoLuong(Integer.parseInt(sl2));
//                            loadGH(listGh);
//                            continue outer;
//                        } else {
//                            continue;
//                        }
//                    } else {
//                        continue;
//                    }
//                }
                for (ChiTietSpResponse chiTietSpResponse : chiTietSPService.getAll()) {
                    if (chiTietSpResponse.getMaVach().equals(result.getText())) {
                        GioHangresponse GHupdate = new GioHangresponse();
                        maTam = chiTietSpResponse.getMa();
                        GHupdate.setMaSP(chiTietSpResponse.getMa());
                        GHupdate.setIdCTSP(chiTietSpResponse.getIdCTSP());
                        GHupdate.setDonGia(chiTietSpResponse.getDonGia());
                        GHupdate.setTenSP(chiTietSpResponse.getTen());
                        String sl = JOptionPane.showInputDialog(this, "Nhập số lượng");
                        GHupdate.setSoLuong(Integer.parseInt(sl));
                        GHupdate.setMaVach(chiTietSpResponse.getMaVach());
                        listGh.add(GHupdate);
                        loadGH(listGh);
                    }
                }
            }
        } while (true);
    }

    public void setKH(String makH) {
        int idTK = 0;
        for (KhachHang tk : khachHangService.getCheckKH(makH)) {
            txtMaKH.setText(tk.getMa());
            txtTenKH.setText(tk.getTen());
            idTK = tk.getId();
        }
    }

    public KhachHang getIdKh() {
        KhachHang id = new KhachHang();
        for (KhachHang khachHang : khachHangService.getList()) {
            if (txtMaKH.getText().equals(khachHang.getMa())) {
                id = khachHang;
                break;
            } else {
                id = null;
            }
        }

        return id;

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jPopupMenu2 = new javax.swing.JPopupMenu();
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
        pnBarCode = new javax.swing.JLayeredPane();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jlbMAHD = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jlbtongitenhang = new javax.swing.JLabel();
        jlbtienthua = new javax.swing.JLabel();
        cbbhtThanhtoan = new javax.swing.JComboBox<>();
        txttienkhachdua = new javax.swing.JTextField();
        btntaohoadon = new javax.swing.JButton();
        btnThanhToan = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jlbGIAMGIA = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtMavch = new javax.swing.JTextField();
        btnsudung = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jlbTiengiam = new javax.swing.JLabel();
        jlbPHANTRAM = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtTenKH = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtMaKH = new javax.swing.JTextField();
        btnChonKH = new javax.swing.JButton();
        btnThayDoi = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel14 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnHuy1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153), 2), "Sản Phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 14))); // NOI18N

        tbSanPham.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
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
                .addComponent(txtTimKiemSP, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(lbTimKiemSP, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 6, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 721, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbTimKiemSP, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(txtTimKiemSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 67, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153), 2), "Giỏ hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 14))); // NOI18N

        tbGioHang.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
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

        btnXoaSP.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnXoaSP.setText("Bỏ khỏi giỏ hàng");
        btnXoaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaSPActionPerformed(evt);
            }
        });

        btnLamMoiGH.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXoaSP)
                    .addComponent(btnLamMoiGH))
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153), 2), "Hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 14))); // NOI18N

        tbHoaDon.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
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

        buttonGroup1.add(rdAllHoadon);
        rdAllHoadon.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        rdAllHoadon.setText("Tất cả");
        rdAllHoadon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdAllHoadonActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdDahuy);
        rdDahuy.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        rdDahuy.setText("Đã hủy");
        rdDahuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdDahuyActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdChoTT);
        rdChoTT.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        rdChoTT.setText("Chờ thanh toán");
        rdChoTT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdChoTTMouseClicked(evt);
            }
        });
        rdChoTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdChoTTActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdDATT);
        rdDATT.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        rdDATT.setText("Đã thanh toán");
        rdDATT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdDATTMouseClicked(evt);
            }
        });
        rdDATT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdDATTActionPerformed(evt);
            }
        });

        txtsearchhoadon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtsearchhoadonActionPerformed(evt);
            }
        });

        btnsearchhoadon.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnsearchhoadon.setText("Search");
        btnsearchhoadon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsearchhoadonActionPerformed(evt);
            }
        });

        pnBarCode.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnBarCode.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(rdAllHoadon)
                        .addGap(37, 37, 37)
                        .addComponent(rdDahuy)
                        .addGap(35, 35, 35)
                        .addComponent(rdChoTT)
                        .addGap(18, 18, 18)
                        .addComponent(rdDATT)
                        .addGap(0, 49, Short.MAX_VALUE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnBarCode, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtsearchhoadon, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnsearchhoadon)))
                .addGap(25, 25, 25))
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
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnBarCode, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );

        jLayeredPane2.setBackground(new java.awt.Color(255, 255, 255));
        jLayeredPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153), 2), "Tạo hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel4.setText("Mã HĐ");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel8.setText("Tổng tiền hàng :");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel9.setText("Hình thức thanh toán");

        jLabel10.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel10.setText("Tiền khách đưa");

        jLabel11.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel11.setText("Tiền thừa  :");

        jlbtongitenhang.setText("0");

        jlbtienthua.setText("0");
        jlbtienthua.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jlbtienthuaMouseEntered(evt);
            }
        });

        cbbhtThanhtoan.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        cbbhtThanhtoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbhtThanhtoanActionPerformed(evt);
            }
        });

        txttienkhachdua.setText("0");
        txttienkhachdua.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txttienkhachduaMouseEntered(evt);
            }
        });
        txttienkhachdua.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txttienkhachduaKeyPressed(evt);
            }
        });

        btntaohoadon.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        btntaohoadon.setText("Tạo hóa đơn");
        btntaohoadon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntaohoadonActionPerformed(evt);
            }
        });

        btnThanhToan.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnThanhToan.setText("Thanh toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        btnHuy.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnHuy.setText("Hủy");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        jLabel1.setText("GIảm giá :");

        jlbGIAMGIA.setText("0");

        jLabel2.setText("Mã Voucher");

        txtMavch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtMavchMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                txtMavchMouseReleased(evt);
            }
        });
        txtMavch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMavchActionPerformed(evt);
            }
        });

        btnsudung.setText("Sử dụng");
        btnsudung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsudungActionPerformed(evt);
            }
        });

        jLabel12.setText("Tiền giảm :");

        jlbTiengiam.setText("0");

        jlbPHANTRAM.setText("%");

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel5.setText("Tên KH");

        jLabel13.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel13.setText("Mã KH:");

        btnChonKH.setText("Chọn");
        btnChonKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonKHActionPerformed(evt);
            }
        });

        btnThayDoi.setText("Thay đổi");
        btnThayDoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThayDoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtMaKH, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtTenKH)))
                .addGap(24, 24, 24)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnThayDoi)
                    .addComponent(btnChonKH))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnChonKH, btnThayDoi});

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(11, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnChonKH))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThayDoi))
                .addGap(21, 21, 21))
        );

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane4.setViewportView(jTextArea1);

        jLabel14.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel14.setText("Ghi chú:");

        jLabel3.setText("VND");

        jLabel6.setText("VND");

        jLabel7.setText("VND");

        btnHuy1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnHuy1.setText("Làm mới");
        btnHuy1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuy1ActionPerformed(evt);
            }
        });

        jLayeredPane2.setLayer(jSeparator2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jlbMAHD, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jLabel8, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jLabel9, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jLabel10, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jLabel11, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jSeparator3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jlbtongitenhang, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jlbtienthua, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(cbbhtThanhtoan, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(txttienkhachdua, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(btntaohoadon, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(btnThanhToan, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(btnHuy, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jlbGIAMGIA, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(txtMavch, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(btnsudung, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jLabel12, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jlbTiengiam, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jlbPHANTRAM, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jPanel4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jScrollPane4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jLabel14, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jLabel6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jLabel7, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(btnHuy1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane2Layout = new javax.swing.GroupLayout(jLayeredPane2);
        jLayeredPane2.setLayout(jLayeredPane2Layout);
        jLayeredPane2Layout.setHorizontalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jSeparator3)
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane2Layout.createSequentialGroup()
                        .addComponent(jScrollPane4)
                        .addGap(23, 23, 23))
                    .addGroup(jLayeredPane2Layout.createSequentialGroup()
                        .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(38, 38, 38)
                                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jLayeredPane2Layout.createSequentialGroup()
                                        .addComponent(jlbGIAMGIA, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jlbPHANTRAM, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jLayeredPane2Layout.createSequentialGroup()
                                        .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jlbtongitenhang, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                                            .addComponent(jlbTiengiam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel3)))))
                            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(59, 59, 59)
                                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtMavch, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                                    .addComponent(jlbMAHD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(42, 42, 42)
                                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btntaohoadon, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnsudung, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jLayeredPane2Layout.createSequentialGroup()
                        .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jlbtienthua, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7))
                            .addComponent(cbbhtThanhtoan, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txttienkhachdua, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addGap(113, 113, 113)
                .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnHuy1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane2Layout.createSequentialGroup()
                .addGap(0, 59, Short.MAX_VALUE)
                .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 49, Short.MAX_VALUE))
        );

        jLayeredPane2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnHuy, btnHuy1});

        jLayeredPane2Layout.setVerticalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(btntaohoadon)
                    .addGroup(jLayeredPane2Layout.createSequentialGroup()
                        .addComponent(jlbMAHD, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)))
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtMavch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnsudung))
                .addGap(28, 28, 28)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jlbtongitenhang, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)))
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlbPHANTRAM, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlbGIAMGIA, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jlbTiengiam, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)))
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(cbbhtThanhtoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txttienkhachdua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel11))
                    .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jlbtienthua)
                        .addComponent(jLabel7)))
                .addGap(33, 33, 33)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addGap(4, 4, 4)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHuy1))
                .addGap(18, 18, 18)
                .addComponent(btnThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLayeredPane2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnHuy, btnHuy1});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(28, 28, 28)
                .addComponent(jLayeredPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(621, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLayeredPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tbGioHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbGioHangMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_tbGioHangMouseClicked

    private void tbSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbSanPhamMouseClicked
        // TODO add your handling code here:
        int index = tbSanPham.getSelectedRow();
        getSP(index);
        List<ChiTietSpResponse> listCT = chiTietSPService.getAll();
        for (GioHangresponse gioHangresponse : listGh) {
            for (ChiTietSpResponse chiTietSpResponse : listCT) {
                if (chiTietSpResponse.getMa().equals(gioHangresponse.getMaSP())) {
                    if (gioHangresponse.getMaSP().equals(tbSanPham.getValueAt(index, 0))) {
                        tbSanPham.setValueAt(chiTietSpResponse.getSoLuongTon() - gioHangresponse.getSoLuong(), index, 2);
                    } else {
                    }
                }
            }
        }
    }//GEN-LAST:event_tbSanPhamMouseClicked

    private void tbHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHoaDonMouseClicked
        // TODO add your handling code here:
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
        int tt = 2;
        loadHD(hoaDonService.timKiemTT(tt));
    }//GEN-LAST:event_rdDahuyActionPerformed

    private void txtsearchhoadonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsearchhoadonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsearchhoadonActionPerformed

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
        if (index < 0) {
            return;
        }
        listGh.remove(index);
        loadGH(listGh);
    }//GEN-LAST:event_btnXoaSPActionPerformed

    private void btntaohoadonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntaohoadonActionPerformed
        // TODO add your handling code here:
        HoaDon HoaDon = null;
        try {
            if (hoaDonService.addHD(idTK) == false) {
                JOptionPane.showMessageDialog(this, "Fail");
            } else {
                JOptionPane.showMessageDialog(this, "Tạo thành công");
            }
        } catch (Exception ex) {
            Logger.getLogger(FormBanHang.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        rdChoTTActionPerformed(evt);
        int x = 0;
        loadTextFiled(x);
        tbHoaDon.setRowSelectionInterval(0, 0);
    }//GEN-LAST:event_btntaohoadonActionPerformed

    private void rdChoTTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdChoTTMouseClicked
        // TODO add your handling code here:
        int tt = 0;
        loadHD(hoaDonService.timKiemTT(tt));
    }//GEN-LAST:event_rdChoTTMouseClicked

    private void rdDATTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdDATTMouseClicked
        // TODO add your handling code here:
        int tt = 1;
        loadHD(hoaDonService.timKiemTT(tt));
    }//GEN-LAST:event_rdDATTMouseClicked

    private void btnsearchhoadonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsearchhoadonActionPerformed
        // TODO add your handling code here:
        String maHD = txtsearchhoadon.getText();
        String sdt = txtsearchhoadon.getText();
        loadHD(hoaDonService.timKiemHD(maHD, sdt));
    }//GEN-LAST:event_btnsearchhoadonActionPerformed

    private void txttienkhachduaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txttienkhachduaMouseEntered
//        try {
//            if (txttienkhachdua.getText().isEmpty()) {
//                return;
//            } else {
//                BigDecimal tongtien = new BigDecimal(jlbtongitenhang.getText());
//                BigDecimal tienkhachdua = new BigDecimal(txttienkhachdua.getText());
//                BigDecimal tienthua = tongtien.subtract(tienkhachdua);
//                jlbtienthua.setVisible(true);
//                jlbtienthua.setText(tienthua + "");
//            }
//
//        } catch (NumberFormatException e) {
//            JOptionPane.showMessageDialog(this, "Tiền phải là số");
//        }
    }//GEN-LAST:event_txttienkhachduaMouseEntered

    private void cbbhtThanhtoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbhtThanhtoanActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_cbbhtThanhtoanActionPerformed

    private void jlbtienthuaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlbtienthuaMouseEntered

    }//GEN-LAST:event_jlbtienthuaMouseEntered

    private void txttienkhachduaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttienkhachduaKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                if (txttienkhachdua.getText().isEmpty()) {
                    return;
                } else {
                    double tongtien = Double.parseDouble(jlbtongitenhang.getText());
                    double tienkhachdua = Double.parseDouble(txttienkhachdua.getText());
                    double tienthua = tienkhachdua - (tongtien);
                    jlbtienthua.setVisible(true);
                    jlbtienthua.setText(tienthua + "");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Tiền phải là số");
            }
        }
    }//GEN-LAST:event_txttienkhachduaKeyPressed

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        // TODO add your handling code here:
        ArrayList<HoaDonChiTiet> listHDCT = new ArrayList<>();
        if (listGh.isEmpty()) {
            JOptionPane.showMessageDialog(this, "vui lòng chọn sản phẩm");
            return;
        }
        ArrayList<HoaDon> listIDHD = hoaDonService.getIDHD(jlbMAHD.getText());
        int idhd = listIDHD.get(0).getId();

        for (GioHangresponse gioHangresponse : listGh) {
            HoaDonChiTiet hdct = new HoaDonChiTiet();
            hdct.setMaHD(jlbMAHD.getText());
            ChiTietSP ctsp = new ChiTietSP();
            ctsp.setId(gioHangresponse.getIdCTSP());
            hdct.setDonGia(gioHangresponse.getDonGia());
            hdct.setSoLuong(gioHangresponse.getSoLuong());
            hdct.setTenKH(txtTenKH.getText());
            double sum = 0;
            sum = sum + (gioHangresponse.getThanhTien().doubleValue());
            BigDecimal tt = new BigDecimal(sum);
            hdct.setTongTien(tt);
            HoaDon hd = new HoaDon();
            hd.setId(idhd);
            hdct.setIdHoaDon(hd);
            hdct.setIdChiTietSP(ctsp);
            listHDCT.add(hdct);
        }
        List<Voucher> listVCh = voucherService.Find(txtMavch.getText());
        int idvch = listVCh.get(0).getId(); // lay id vch

        List<Voucher> list = voucherService.FindTEN(txtMavch.getText());
        int soluongTon = list.get(0).getSoLuong();
//        
        int check = 0;
        HoaDonChiTietResponsitory hdrp = new HoaDonChiTietResponsitory();
        hdrp.deleteSP(jlbMAHD.getText());
        for (HoaDonChiTiet hoaDonChiTiet : listHDCT) {
            hoaDonChiTietService.saveHDCT(hoaDonChiTiet);
            if (txttienkhachdua.getText().equalsIgnoreCase("0") || txttienkhachdua.getText().isEmpty()) {
                System.out.println(getIdKh());
                hoaDonService.updateHD(jlbMAHD.getText(), new BigDecimal(jlbtongitenhang.getText()), 0, getIdKh());//chua fix sdt 
                hoaDonService.updateVCHHD(jlbMAHD.getText(), idvch); // update vocher// co loi
                voucherService.updateSLVCH(txtMavch.getText(), (soluongTon - 1)); // co loi
                loadHD(hoaDonService.getListsHD());
            } else {
                check = 1;
                System.out.println(getIdKh());
                hoaDonService.updateHD(jlbMAHD.getText(), new BigDecimal(jlbtongitenhang.getText()), 1, getIdKh()); //chua fix sdt 
                hoaDonService.updateVCHHD(jlbMAHD.getText(), idvch); // update vocher // co loi
                rdDATTActionPerformed(evt);
            }
        }
        if (check == 0) {
            JOptionPane.showMessageDialog(this, "Hóa đơn đã được treo");
        } else {
            JOptionPane.showMessageDialog(this, "Thanh toán thành công");
            updateSL();
        }
        listGh.removeAll(listGh);// xoa het gio hang
        loadGH(listGh);
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        // TODO add your handling code here:
        hoaDonService.updateHD(jlbMAHD.getText(), new BigDecimal(jlbtongitenhang.getText()), 2, getIdKh()); // chua fix sdt 
        loadHD(hoaDonService.getListsHD());
        listGh.removeAll(listGh);// xoa het gio hang
        loadGH(listGh);
    }//GEN-LAST:event_btnHuyActionPerformed

    private void btnsudungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsudungActionPerformed
        List<Voucher> list = voucherService.FindTEN(txtMavch.getText());
        int soluongTon = list.get(0).getSoLuong();

        if (jlbMAHD.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui long chon hoa don truoc khi su dung ma");
            return;
        }
        if (soluongTon <= 0) {
            JOptionPane.showMessageDialog(this, "Số lượng voucher này đã hết ?Vui lòng thử voucher khác ");
            return;
        }
        if (txtMavch.getText().isEmpty()) {
            jlbGIAMGIA.setText("");
            return;
        }
        if (list.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ma giảm giá không hợp lệ");
            return;
        }

        for (HoaDon hd : hoaDonService.getALLHD()) {
            if (hd.getMaHD().equals(jlbMAHD.getText())) {
                if (hd.getVoucher() == null) {
                    float giaTriGiamGia = list.get(0).getPhanTram();
                    int epkieu = (int) giaTriGiamGia;
                    jlbGIAMGIA.setText(String.valueOf(epkieu));
                    jlbPHANTRAM.setText("%");
                    JOptionPane.showMessageDialog(this, "success");
                    float tienGiam = Float.parseFloat(jlbtongitenhang.getText());
                    float tienGiam2 = (tienGiam * giaTriGiamGia) / 100;
                    jlbTiengiam.setText(String.valueOf(tienGiam2));
                    return;
                } else if (hd.getVoucher().getTen().equals(txtMavch.getText())) {
                    JOptionPane.showMessageDialog(this, "Hoa don da duoc su dung voucher nay r?");
                    int chon = JOptionPane.showConfirmDialog(this, "Ban co muon thay doi voucher?", "Update vch", JOptionPane.YES_NO_OPTION);
                    if (chon == JOptionPane.YES_OPTION) {
                        String mach = JOptionPane.showInputDialog("Ma voucher moi");
                        List<Voucher> listVCHNEW = voucherService.Find(mach);
                        if (listVCHNEW.isEmpty()) {
                            JOptionPane.showMessageDialog(this, "khong tim thay voucher nay");
                            return;
                        }
                        int idvch = listVCHNEW.get(0).getId();
                        hoaDonService.updateVCHHD(jlbMAHD.getText(), idvch);
                        JOptionPane.showMessageDialog(this, "Doi voucher thanh cong");
                        return;
                    } else {
                        return;
                    }
                }
            }
        }


    }//GEN-LAST:event_btnsudungActionPerformed

    private void txtMavchMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtMavchMouseReleased

    }//GEN-LAST:event_txtMavchMouseReleased

    private void txtMavchMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtMavchMousePressed
        if (txtMavch.getText().isEmpty()) {
            jlbGIAMGIA.setText("");
        }
    }//GEN-LAST:event_txtMavchMousePressed

    private void txtMavchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMavchActionPerformed

    }//GEN-LAST:event_txtMavchActionPerformed

    private void btnChonKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonKHActionPerformed
        // TODO add your handling code here:
        Window wd = SwingUtilities.windowForComponent(this);
        DialogKH dialogKh = new DialogKH((Frame) wd, true);
        dialogKh.setVisible(true);
        dialogKh.getKH();
        this.setKH(dialogKh.getKH());
    }//GEN-LAST:event_btnChonKHActionPerformed

    private void btnThayDoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThayDoiActionPerformed
        // TODO add your handling code here:
        btnChonKHActionPerformed(evt);
    }//GEN-LAST:event_btnThayDoiActionPerformed

    private void rdChoTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdChoTTActionPerformed
        // TODO add your handling code here:
        int tt = 0;
        loadHD(hoaDonService.timKiemTT(tt));
    }//GEN-LAST:event_rdChoTTActionPerformed

    private void rdDATTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdDATTActionPerformed
        // TODO add your handling code here:
        int tt = 1;
        loadHD(hoaDonService.timKiemTT(tt));
    }//GEN-LAST:event_rdDATTActionPerformed

    private void btnHuy1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuy1ActionPerformed
        // TODO add your handling code here:
        jlbMAHD.setText("");
        jlbGIAMGIA.setText("0");
        jlbTiengiam.setText("0");
        jlbtienthua.setText("0");
        jlbtongitenhang.setText("0");
        txttienkhachdua.setText("0");
        setKH("KH0");
    }//GEN-LAST:event_btnHuy1ActionPerformed
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
            java.util.logging.Logger.getLogger(FormMain.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormMain.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormMain.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormMain.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
    private javax.swing.JButton btnChonKH;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnHuy1;
    private javax.swing.JButton btnLamMoiGH;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnThayDoi;
    private javax.swing.JButton btnXoaSP;
    private javax.swing.JButton btnsearchhoadon;
    private javax.swing.JButton btnsudung;
    private javax.swing.JButton btntaohoadon;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.JComboBox<String> cbbhtThanhtoan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel jlbGIAMGIA;
    private javax.swing.JLabel jlbMAHD;
    private javax.swing.JLabel jlbPHANTRAM;
    private javax.swing.JLabel jlbTiengiam;
    private javax.swing.JLabel jlbtienthua;
    private javax.swing.JLabel jlbtongitenhang;
    private javax.swing.JLabel lbTimKiemSP;
    private javax.swing.JLayeredPane pnBarCode;
    private javax.swing.JRadioButton rdAllHoadon;
    private javax.swing.JRadioButton rdChoTT;
    private javax.swing.JRadioButton rdDATT;
    private javax.swing.JRadioButton rdDahuy;
    private javax.swing.JTable tbGioHang;
    private javax.swing.JTable tbHoaDon;
    private javax.swing.JTable tbSanPham;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtMavch;
    private javax.swing.JTextField txtTenKH;
    private javax.swing.JTextField txtTimKiemSP;
    private javax.swing.JTextField txtsearchhoadon;
    private javax.swing.JTextField txttienkhachdua;
    // End of variables declaration//GEN-END:variables

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, "My Thread");
        t.setDaemon(true);
        return t;
    }
}

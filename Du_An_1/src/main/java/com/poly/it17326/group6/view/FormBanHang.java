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
import com.poly.it17326.group6.domainmodel.TaiKhoan;
import com.poly.it17326.group6.repository.HoaDonChiTietResponsitory;
import com.poly.it17326.group6.response.ChiTietSpResponse;
import com.poly.it17326.group6.response.GioHangresponse;
import com.poly.it17326.group6.response.HoaDonCTResponse;
import com.poly.it17326.group6.response.HoaDonresponse;
import com.poly.it17326.group6.service.ChiTietSPService;
import com.poly.it17326.group6.service.HoaDonChiTietService;
import com.poly.it17326.group6.service.HoaDonService;
import com.poly.it17326.group6.service.KhachHangService;
import com.poly.it17326.group6.service.VoucherService;
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
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import com.itextpdf.kernel.color.DeviceRgb;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import com.poly.it17326.group6.repository.HoaDonRepository;
import java.io.FileNotFoundException;

/**
 *
 * @author 123
 */
public class FormBanHang extends javax.swing.JPanel implements Runnable, ThreadFactory {

    private ChiTietSPService chiTietSPService = new ChiTietSPServiceImpl();
    private HoaDonService hoaDonService = new HoaDonServecieIplm();
    private HoaDonChiTietService hoaDonChiTietService = new HOaDonChiTietServiceIplm();
    private TaiKhoanService taiKhoanService = new TaiKhoanServiceImpl();
    List<HoaDonresponse> listHD = hoaDonService.getListsHD_day();
    private VoucherService voucherService = new VoucherServiceIplm();
    private KhachHangService khachHangService = new KhachHangServiceImpl();
    private HoaDonRepository hoaDonRepository = new HoaDonRepository();

    /**
     * Creates new form FormBanHang
     */
    public FormBanHang() {
        initComponents();
        loadSP(chiTietSPService.getAll());
        loadIcon();
        loadCBHTTT();
        loadWeb();
        setKH("KH0");
        loadHD(listHD);
        loadCbTTGH();
        loadCbTTTT();
        //  webcam.close();

    }

    private void loadSP(List<ChiTietSpResponse> listS) {
        DefaultTableModel model = (DefaultTableModel) tbSanPham.getModel();
        model.setRowCount(0);
        model.setColumnIdentifiers(new String[]{"Mã SP", "Tên SP", "NSX", "Loại SP", "Thành phần", "Khối lượng", "Đơn vị tính", "Số lượng tồn", "Đơn giá", "Giá bán"});
        int i = 1;
        for (ChiTietSpResponse chiTietSpResponse : listS) {
            model.addRow(new Object[]{chiTietSpResponse.getMa(), chiTietSpResponse.getTen(), chiTietSpResponse.getNsx(), chiTietSpResponse.getLoaiSP(), chiTietSpResponse.getThanhPhan(), chiTietSpResponse.getAnh(), chiTietSpResponse.getDonViTinh(), chiTietSpResponse.getSoLuongTon(), chiTietSpResponse.getDonGia(), chiTietSpResponse.getGiaMoi()});
        }
    }

    private void loadGH(ArrayList<GioHangresponse> listS) {
        DefaultTableModel model = (DefaultTableModel) tbGioHang.getModel();
        model.setRowCount(0);
        BigDecimal tongtien = BigDecimal.valueOf(0);
        model.setColumnIdentifiers(new String[]{"Mã SP", "Tên SP", "Số lượng ", "Đơn giá", "Giá bán", "Thành tiền"});
        for (GioHangresponse gioHangresponse : listS) {
            model.addRow(new Object[]{gioHangresponse.getMaSP(), gioHangresponse.getTenSP(), gioHangresponse.getSoLuong(), gioHangresponse.getDonGia(), gioHangresponse.getGiaBan(), gioHangresponse.getThanhTien()});
            tongtien = tongtien.add(gioHangresponse.getThanhTien());

        }
        if (jlbGIAMGIA.getText().isEmpty()) {
            jlbtongitenhang.setVisible(true);
            jlbtongitenhang.setText(tongtien.toString());
            jlbtongitenhang1.setText(tongtien.toString());
        } else {
            BigDecimal tiengiam = BigDecimal.valueOf(0);
            BigDecimal tongTienSauGiam = BigDecimal.valueOf(0);
            tongTienSauGiam = tongtien.subtract(tongtien.multiply(new BigDecimal(jlbGIAMGIA.getText()).divide(new BigDecimal(100))));
            tiengiam = tongtien.multiply(new BigDecimal(jlbGIAMGIA.getText()).divide(new BigDecimal(100)));
            jlbTiengiam.setText(tongTienSauGiam.toString());
            jlbTiengiam1.setText(tongTienSauGiam.toString());
            jlbtongitenhang.setVisible(true);
            jlbtongitenhang.setText(tongtien.toString());
            jlbtongitenhang1.setText(tongtien.toString());
            //   jlbTiengiam.setText(jlbtongitenhang.getText());
            //  jlbTiengiam1.setText(jlbtongitenhang.getText());
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
        BigDecimal donGia = new BigDecimal(tbSanPham.getValueAt(i, 8).toString());
        BigDecimal giaBan = new BigDecimal(tbSanPham.getValueAt(i, 9).toString());
        ghr.setDonGia(donGia);
        ghr.setGiaBan(giaBan);
        for (GioHangresponse x : listGh) {
            if (x.getMaSP().equals(tbSanPham.getValueAt(i, 0).toString())) {
                String sl2 = JOptionPane.showInputDialog(this, "Nhập số lượng");
                x.setSoLuong(Integer.parseInt(sl2) + x.getSoLuong());
                loadGH(listGh);
                return;

            } else {
                continue;
            }
        }
        String sl = JOptionPane.showInputDialog(this, "Nhập số lượng");
        if (Integer.parseInt(sl) > Integer.parseInt(tbSanPham.getValueAt(i, 7).toString())) {
            JOptionPane.showMessageDialog(this, "Nhập quá số lượng sản phẩm");
            return;
        } else {
            ghr.setSoLuong(Integer.parseInt(sl));
            listGh.add(ghr);
            loadGH(listGh);
        }

    }

    private void loadIcon() {
        btnLamMoiGH.setIcon(new ImageIcon("D:\\Nhom6_PRO1041\\Anh\\reload.png"));
        //    btnXoaSP.setIcon(new ImageIcon("D:\\Nhom6_PRO1041\\Anh\\SanPham\\dustbin.png"));
        lbTimKiemSP.setIcon(new ImageIcon("D:\\Nhom6_PRO1041\\Anh\\search.png"));
        btntaohoadon.setIcon(new ImageIcon("D:\\Nhom6_PRO1041\\Anh\\add.png"));
        btnThanhToan.setIcon(new ImageIcon("D:\\Nhom6_PRO1041\\Anh\\pay.png"));

    }

    private void loadDataHdct(ArrayList<HoaDonCTResponse> listGh) {
        DefaultTableModel model = (DefaultTableModel) tbGioHang.getModel();
        model.setColumnIdentifiers(new String[]{"Mã Sp", "Tên Sp", "Số Lượng", "Đơn giá", "Giá bán", "Thành tiền"});
        model.setRowCount(0);
        for (HoaDonCTResponse ghr : listGh) {
            model.addRow(new Object[]{ghr.getMaSP(), ghr.getTen(), ghr.getSoLuong(), ghr.getDonGia(), ghr.getGiaBan(), ghr.getThanhTien()});
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

    private void LoadKH() {
        txtNgNhan.setText("");
        txtDiaChiNN.setText("");
        txtSdtNN.setText("");
    }

    private void showTTHD() { // hien thi jlb tong tien 

        jlbtienthua.setVisible(true);
        jlbtongitenhang.setVisible(true);

    }

    private void loadTextFiled(int index) {
        int check = 0;
        jlbTienCK.setText("0");
        jlbtienthua.setText("0");
        if (tbHoaDon.getRowCount() > 0) {
            jlbMAHD.setText(tbHoaDon.getValueAt(index, 0).toString());
            jlbMAHD1.setText(jlbMAHD.getText());
            //  jlbTENNV.setText(tbHoaDon.getValueAt(index, 2).toString());
            //  jlbngaytao.setText(tbHoaDon.getValueAt(index, 1) + "");
            showJLBKH();
            if (tbHoaDon.getValueAt(index, 3).equals("Chờ thanh toán")) {
                btnThanhToan.setEnabled(true);
            } else {
                btnThanhToan.setEnabled(false);
            }
            if (tbHoaDon.getValueAt(index, 3).equals("Chờ giao hàng")) {
                //  btnThanhToan.setEnabled(true);
                btnThanhToan1.setEnabled(false);
            } else if (tbHoaDon.getValueAt(index, 3).equals("Đang giao hàng")) {
                btnThanhToan.setEnabled(false);
                btnThanhToan1.setEnabled(false);

            }
            for (HoaDonresponse hoaDonresponse : hoaDonService.getListsHD()) {
                System.out.println(hoaDonresponse.getMaHD());
                if (hoaDonresponse.getMaHD().equals(tbHoaDon.getValueAt(index, 0))) {
                    txtTenKH.setText(hoaDonresponse.getTenKH());
                    txtMaKH.setText(hoaDonresponse.getMaKH());
                    txtNgNhan.setText(hoaDonresponse.getTenNN());
                    txtSdtNN.setText(hoaDonresponse.getSdtNN());
                    txtNgShip.setText(hoaDonresponse.getTenNS());
                    txtSdtNS.setText(hoaDonresponse.getSdtNS());
                    txtDiaChiNN.setText(hoaDonresponse.getDiaChi());
                    if (jlbtienthua1.getText().contains("-")) {
                        cbTrangThaiTT.setSelectedIndex(2);
                    } else {
                        cbTrangThaiTT.setSelectedIndex(1);
                    }
                    if (hoaDonresponse.getRank() == 0) {
                        lbRank.setText("Đồng");
                    }
                    if (hoaDonresponse.getRank() == 1) {
                        lbRank.setText("Bạc");
                    }
                    if (hoaDonresponse.getRank() == 2) {
                        lbRank.setText("Vàng");
                    }
                    if (hoaDonresponse.getRank() == 3) {
                        lbRank.setText("Bạch kim");
                    }

                    if (hoaDonresponse.getTienS() == null || Double.parseDouble(hoaDonresponse.getTienS().toString()) == 0) {
                        txtTienShip.setText("0");
                    } else {
                        txtTienShip.setText(hoaDonresponse.getTienS().toString());
                    }
                    if (hoaDonresponse.getTienKH() == null || Double.parseDouble(hoaDonresponse.getTienKH().toString()) == 0) {
                        txttienkhachdua1.setText("0");
                        txttienkhachdua.setText("0");
                    } else {
                        txttienkhachdua.setText(hoaDonresponse.getTienKH().toString());
                        txttienkhachdua1.setText(hoaDonresponse.getTienKH().toString());
                    }
                    if (hoaDonresponse.getTienCK() == null || Double.parseDouble(hoaDonresponse.getTienCK().toString()) == 0) {
                        jlbTienCK.setText("0");
                        jlbTienCK1.setText("0");
                    } else {
                        jlbTienCK.setText(hoaDonresponse.getTienCK().toString());
                        jlbTienCK1.setText(hoaDonresponse.getTienCK().toString());
                    }
                    if (hoaDonresponse.getTienThua() == null || Double.parseDouble(hoaDonresponse.getTienThua().toString()) == 0) {
                        jlbtienthua.setText("0");
                        jlbtienthua1.setText("0");
                    } else {
                        jlbtienthua.setText(hoaDonresponse.getTienThua().toString());
                        jlbtienthua1.setText(hoaDonresponse.getTienThua().toString());
                    }
                    cbbhtThanhtoan.setSelectedIndex(hoaDonresponse.getHttt());
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
                    jlbtongitenhang1.setText(tt.toString());
                    jlbTiengiam.setText(gh.getTongTien().toString());
                    jlbTiengiam1.setText(gh.getTongTien().toString());
                    check = 1;
                }

            }
            if (listGhct.isEmpty()) {
                for (GioHangresponse gioHangresponse : listGh) {
                    sum = sum + (gioHangresponse.getThanhTien().doubleValue());
                    BigDecimal tt = new BigDecimal(sum);
                    jlbtongitenhang.setText(tt.toString());
                    jlbtongitenhang1.setText(tt.toString());
                    //   giamGiaRank();
                    // check = 1;
                }
            } else {
//                listGh.removeAll(listGh);
//                loadGH(listGh);
                loadDataHdct(listGhct);
            }

            if (tbHoaDon.getValueAt(index, 3).equals("Chờ thanh toán") && check == 1) {
//                listGh.removeAll(listGh);
//                loadGH(listGh);
                for (HoaDonCTResponse hoaDonCTResponse : listGhct) {
                    GioHangresponse GHupdate = new GioHangresponse();
                    GHupdate.setMaSP(hoaDonCTResponse.getMaSP());
                    GHupdate.setIdCTSP(hoaDonCTResponse.getIdChiTietSP());
                    GHupdate.setDonGia(hoaDonCTResponse.getDonGia());
                    GHupdate.setTenSP(hoaDonCTResponse.getTen());
                    GHupdate.setSoLuong(hoaDonCTResponse.getSoLuong());
                    GHupdate.setGiaBan(hoaDonCTResponse.getGiaBan());
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
    private Webcam webcam = Webcam.getDefault();
    private Executor executor = Executors.newSingleThreadExecutor(this);

    private void loadWeb() {
        Dimension sie = WebcamResolution.QVGA.getSize();
        webcam = Webcam.getWebcams().get(0);
        webcam.setViewSize(sie);
        panel = new WebcamPanel(webcam);
        panel.setPreferredSize(sie);
        panel.setFPSDisplayed(true);
        pnBarCode.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 20, 272, 253));
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
                for (ChiTietSpResponse chiTietSpResponse : chiTietSPService.getAll()) {
                    if (chiTietSpResponse.getMaVach().equals(result.getText())) {
                        if (listGh.isEmpty() && jlbMAHD.getText().equals("")) {
                            //  HoaDon HoaDon = null;
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
                            int tt = 0;
                            loadHD(hoaDonService.timKiemTT(tt));
                            int x = 0;
                            loadTextFiled(x);
                            tbHoaDon.setRowSelectionInterval(0, 0);
                        } else {
                            GioHangresponse GHupdate = new GioHangresponse();
                            maTam = chiTietSpResponse.getMa();
                            GHupdate.setMaSP(chiTietSpResponse.getMa());
                            GHupdate.setIdCTSP(chiTietSpResponse.getIdCTSP());
                            GHupdate.setDonGia(chiTietSpResponse.getDonGia());
                            GHupdate.setGiaBan(chiTietSpResponse.getGiaMoi());
                            GHupdate.setTenSP(chiTietSpResponse.getTen());
                            String sl = JOptionPane.showInputDialog(this, "Nhập số lượng");
                            GHupdate.setSoLuong(Integer.parseInt(sl));
                            GHupdate.setMaVach(chiTietSpResponse.getMaVach());
                            listGh.add(GHupdate);
                            loadGH(listGh);
                        }
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
            txtNgNhan.setText(tk.getTen());
            txtSdtNN.setText(tk.getSdt());
            txtDiaChiNN.setText(tk.getDiaChi());
            lbRank.setText(tk.Rank());
            if (tk.getRank() == 0) {
                jlbGIAMGIA.setText("0");
                jlbGIAMGIA1.setText("0");
            }
            if (tk.getRank() == 1) {
                jlbGIAMGIA.setText("3");
                jlbGIAMGIA1.setText("3");
            }
            if (tk.getRank() == 2) {
                jlbGIAMGIA.setText("5");
                jlbGIAMGIA1.setText("5");
            }
            if (tk.getRank() == 3) {
                jlbGIAMGIA.setText("10");
                jlbGIAMGIA1.setText("10");
            }
            idTK = tk.getId();
            loadGH(listGh);
        }
    }

    public void setNV(String makH) {
        int idTK = 0;
        for (TaiKhoan tk : taiKhoanService.getCheck(makH)) {
            txtNgShip.setText(tk.getHoTenNV());
            txtSdtNS.setText(tk.getSdt());
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

    public void close() {
        webcam.close();
    }

    private void print() {
        String path = "D:\\Nhom6_PRO1041\\HD\\ " + jlbMAHD.getText() + ".pdf"; // duowng daan

        float col = 280f;
        float columnWidth[] = {col, col};

        PdfWriter writer;
        try {
            writer = new PdfWriter(path);
            PdfDocument pdfDocument = new PdfDocument(writer);
            com.itextpdf.layout.Document doc = new com.itextpdf.layout.Document(pdfDocument) {
            };
            pdfDocument.setDefaultPageSize(PageSize.A4);
            com.itextpdf.layout.element.Table tb = new com.itextpdf.layout.element.Table(columnWidth);

            tb.addCell(new Cell().add("FMILK")
                    .setTextAlignment(TextAlignment.LEFT)
                    .setVerticalAlignment(VerticalAlignment.MIDDLE)
                    .setMarginTop(30f)
                    .setMarginBottom(30f)
                    .setFontSize(30f)
                    .setBorder(Border.NO_BORDER));
            tb.addCell(new Cell().add("")
                    .setTextAlignment(TextAlignment.RIGHT)
                    .setMarginTop(30f)
                    .setMarginBottom(30f)
                    .setBorder(Border.NO_BORDER)
                    .setMarginRight(10f));
            tb.setBackgroundColor(new DeviceRgb(0, 51, 102))
                    .setFontColor(com.itextpdf.kernel.color.Color.WHITE);
            tb.addCell(new Cell().add("")
                    .setTextAlignment(TextAlignment.RIGHT)
                    .setMarginTop(30f)
                    .setMarginBottom(30f)
                    .setBorder(Border.NO_BORDER)
                    .setMarginRight(10f));
            tb.setBackgroundColor(new DeviceRgb(0, 51, 102))
                    .setFontColor(com.itextpdf.kernel.color.Color.WHITE);
            tb.addCell(new Cell().add("SDT: 0865683753")
                    .setTextAlignment(TextAlignment.RIGHT)
                    .setMarginTop(30f)
                    .setMarginBottom(30f)
                    .setBorder(Border.NO_BORDER)
                    .setMarginRight(10f));
            tb.setBackgroundColor(new DeviceRgb(0, 51, 102))
                    .setFontColor(com.itextpdf.kernel.color.Color.WHITE);

            tb.addCell(new Cell().add("")
                    .setTextAlignment(TextAlignment.RIGHT)
                    .setMarginTop(30f)
                    .setMarginBottom(30f)
                    .setBorder(Border.NO_BORDER)
                    .setMarginRight(10f));
            tb.setBackgroundColor(new DeviceRgb(0, 51, 102))
                    .setFontColor(com.itextpdf.kernel.color.Color.WHITE);
            tb.addCell(new Cell().add("D/C: Trinh Van Po - Nam Tu Liem - Ha Noi")
                    .setTextAlignment(TextAlignment.RIGHT)
                    .setMarginTop(50f)
                    .setMarginBottom(30f)
                    .setBorder(Border.NO_BORDER)
                    .setMarginRight(10f));
            tb.setBackgroundColor(new DeviceRgb(0, 51, 102))
                    .setFontColor(com.itextpdf.kernel.color.Color.WHITE);

            float colwidth[] = {100, 300, 200, 100};
            com.itextpdf.layout.element.Table customInforTbale = new com.itextpdf.layout.element.Table(colwidth);
            customInforTbale.addCell(new Cell(0, 4).add("Thong tin hoa don")
                    .setBorder(Border.NO_BORDER));
            customInforTbale.addCell(new Cell().add("NhanVien:")
                    .setBorder(Border.NO_BORDER));
            customInforTbale.addCell(new Cell().add(tbHoaDon.getValueAt(0, 2).toString())
                    .setBorder(Border.NO_BORDER));
            customInforTbale.addCell(new Cell().add("MaHD:")
                    .setBorder(Border.NO_BORDER)
                    .setTextAlignment(TextAlignment.RIGHT));
            customInforTbale.addCell(new Cell().add(jlbMAHD.getText())
                    .setBorder(Border.NO_BORDER));
            customInforTbale.addCell(new Cell().add("TenKhach :")
                    .setBorder(Border.NO_BORDER));
            customInforTbale.addCell(new Cell().add(txtTenKH.getText())
                    .setBorder(Border.NO_BORDER));
            customInforTbale.addCell(new Cell().add("NgayTao :")
                    .setBorder(Border.NO_BORDER)
                    .setTextAlignment(TextAlignment.RIGHT));
            customInforTbale.addCell(new Cell().add(tbHoaDon.getValueAt(0, 1).toString())
                    .setBorder(Border.NO_BORDER));

            float itemInforWidth[] = {100, 100, 100, 100, 100, 100};
            com.itextpdf.layout.element.Table itemTbale = new com.itextpdf.layout.element.Table(itemInforWidth);

            itemTbale.addCell(new Cell().add("Ma SP")
                    .setBackgroundColor(new DeviceRgb(0, 51, 102))
                    .setFontColor(com.itextpdf.kernel.color.Color.WHITE)
                    .setTextAlignment(TextAlignment.CENTER));
            itemTbale.addCell(new Cell().add("Ten SP")
                    .setBackgroundColor(new DeviceRgb(0, 51, 102))
                    .setFontColor(com.itextpdf.kernel.color.Color.WHITE));
            itemTbale.addCell(new Cell().add("So luong")
                    .setBackgroundColor(new DeviceRgb(0, 51, 102))
                    .setFontColor(com.itextpdf.kernel.color.Color.WHITE)
                    .setTextAlignment(TextAlignment.CENTER));
            itemTbale.addCell(new Cell().add("Don gia")
                    .setBackgroundColor(new DeviceRgb(0, 51, 102))
                    .setFontColor(com.itextpdf.kernel.color.Color.WHITE)
                    .setTextAlignment(TextAlignment.CENTER));
            itemTbale.addCell(new Cell().add("Gia Ban")
                    .setBackgroundColor(new DeviceRgb(0, 51, 102))
                    .setFontColor(com.itextpdf.kernel.color.Color.WHITE)
                    .setTextAlignment(TextAlignment.CENTER));
            itemTbale.addCell(new Cell().add("Thanh tien")
                    .setBackgroundColor(new DeviceRgb(0, 51, 102))
                    .setFontColor(com.itextpdf.kernel.color.Color.WHITE)
                    .setTextAlignment(TextAlignment.CENTER));

            for (int i = 0; i < tbGioHang.getRowCount(); i++) {

                String maSp = tbGioHang.getValueAt(i, 0).toString();
                String tenSp = tbGioHang.getValueAt(i, 1).toString();
                String soluong = tbGioHang.getValueAt(i, 2).toString();
                String dongia = tbGioHang.getValueAt(i, 3).toString() + " VND";
                String giaBan = tbGioHang.getValueAt(i, 4).toString() + " VND";
                String thanhtien = tbGioHang.getValueAt(i, 5).toString() + " VND";
                itemTbale.addCell(maSp).setTextAlignment(TextAlignment.CENTER);
                itemTbale.addCell(tenSp).setTextAlignment(TextAlignment.CENTER);
                itemTbale.addCell(soluong).setTextAlignment(TextAlignment.CENTER);
                itemTbale.addCell(dongia).setTextAlignment(TextAlignment.CENTER);
                itemTbale.addCell(giaBan).setTextAlignment(TextAlignment.CENTER);
                itemTbale.addCell(thanhtien).setTextAlignment(TextAlignment.CENTER);

            }
            itemTbale.addCell(new Cell().add("")
                    .setBackgroundColor(new DeviceRgb(0, 51, 102))
                    .setFontColor(com.itextpdf.kernel.color.Color.WHITE)
                    .setBorder(Border.NO_BORDER));
            itemTbale.addCell(new Cell().add("")
                    .setBackgroundColor(new DeviceRgb(0, 51, 102))
                    .setFontColor(com.itextpdf.kernel.color.Color.WHITE)
                    .setBorder(Border.NO_BORDER));

            itemTbale.addCell(new Cell().add("")
                    .setBackgroundColor(new DeviceRgb(0, 51, 102))
                    .setFontColor(com.itextpdf.kernel.color.Color.WHITE)
                    .setBorder(Border.NO_BORDER));

            itemTbale.addCell(new Cell().add("")
                    .setBackgroundColor(new DeviceRgb(0, 51, 102))
                    .setFontColor(com.itextpdf.kernel.color.Color.WHITE)
                    .setBorder(Border.NO_BORDER));

            itemTbale.addCell(new Cell().add("Tong tien:")
                    .setBackgroundColor(new DeviceRgb(0, 51, 102))
                    .setFontColor(com.itextpdf.kernel.color.Color.WHITE)
                    .setBorder(Border.NO_BORDER)
                    .setTextAlignment(TextAlignment.LEFT));

            itemTbale.addCell(new Cell().add(String.format("%s", jlbTiengiam.getText()) + " VND")
                    .setBackgroundColor(new DeviceRgb(0, 51, 102))
                    .setFontColor(com.itextpdf.kernel.color.Color.WHITE)
                    .setBorder(Border.NO_BORDER)
                    .setTextAlignment(TextAlignment.RIGHT));
            itemTbale.addCell(new Cell().add("")
                    .setBackgroundColor(new DeviceRgb(0, 51, 102))
                    .setFontColor(com.itextpdf.kernel.color.Color.WHITE)
                    .setBorder(Border.NO_BORDER));
            itemTbale.addCell(new Cell().add("")
                    .setBackgroundColor(new DeviceRgb(0, 51, 102))
                    .setFontColor(com.itextpdf.kernel.color.Color.WHITE)
                    .setBorder(Border.NO_BORDER));
            itemTbale.addCell(new Cell().add("")
                    .setBackgroundColor(new DeviceRgb(0, 51, 102))
                    .setFontColor(com.itextpdf.kernel.color.Color.WHITE)
                    .setBorder(Border.NO_BORDER));

            itemTbale.addCell(new Cell().add("")
                    .setBackgroundColor(new DeviceRgb(0, 51, 102))
                    .setFontColor(com.itextpdf.kernel.color.Color.WHITE)
                    .setBorder(Border.NO_BORDER));

            itemTbale.addCell(new Cell().add("Tien khach dua:")
                    .setBackgroundColor(new DeviceRgb(0, 51, 102))
                    .setFontColor(com.itextpdf.kernel.color.Color.WHITE)
                    .setBorder(Border.NO_BORDER)
                    .setTextAlignment(TextAlignment.LEFT));

            itemTbale.addCell(new Cell().add(String.format("%s", txttienkhachdua.getText()) + " VND")
                    .setBackgroundColor(new DeviceRgb(0, 51, 102))
                    .setFontColor(com.itextpdf.kernel.color.Color.WHITE)
                    .setBorder(Border.NO_BORDER)
                    .setTextAlignment(TextAlignment.RIGHT));
            itemTbale.addCell(new Cell().add("")
                    .setBackgroundColor(new DeviceRgb(0, 51, 102))
                    .setFontColor(com.itextpdf.kernel.color.Color.WHITE)
                    .setBorder(Border.NO_BORDER));
            itemTbale.addCell(new Cell().add("")
                    .setBackgroundColor(new DeviceRgb(0, 51, 102))
                    .setFontColor(com.itextpdf.kernel.color.Color.WHITE)
                    .setBorder(Border.NO_BORDER));
            itemTbale.addCell(new Cell().add("")
                    .setBackgroundColor(new DeviceRgb(0, 51, 102))
                    .setFontColor(com.itextpdf.kernel.color.Color.WHITE)
                    .setBorder(Border.NO_BORDER));
            itemTbale.addCell(new Cell().add("")
                    .setBackgroundColor(new DeviceRgb(0, 51, 102))
                    .setFontColor(com.itextpdf.kernel.color.Color.WHITE)
                    .setBorder(Border.NO_BORDER));

            itemTbale.addCell(new Cell().add("Tien chuyen khoan:")
                    .setBackgroundColor(new DeviceRgb(0, 51, 102))
                    .setFontColor(com.itextpdf.kernel.color.Color.WHITE)
                    .setBorder(Border.NO_BORDER)
                    .setTextAlignment(TextAlignment.LEFT));

            itemTbale.addCell(new Cell().add(String.format("%s", jlbTienCK.getText()) + " VND")
                    .setBackgroundColor(new DeviceRgb(0, 51, 102))
                    .setFontColor(com.itextpdf.kernel.color.Color.WHITE)
                    .setBorder(Border.NO_BORDER)
                    .setTextAlignment(TextAlignment.RIGHT));
            itemTbale.addCell(new Cell().add("")
                    .setBackgroundColor(new DeviceRgb(0, 51, 102))
                    .setFontColor(com.itextpdf.kernel.color.Color.WHITE)
                    .setBorder(Border.NO_BORDER));
            itemTbale.addCell(new Cell().add("")
                    .setBackgroundColor(new DeviceRgb(0, 51, 102))
                    .setFontColor(com.itextpdf.kernel.color.Color.WHITE)
                    .setBorder(Border.NO_BORDER));
            itemTbale.addCell(new Cell().add("")
                    .setBackgroundColor(new DeviceRgb(0, 51, 102))
                    .setFontColor(com.itextpdf.kernel.color.Color.WHITE)
                    .setBorder(Border.NO_BORDER));
            itemTbale.addCell(new Cell().add("")
                    .setBackgroundColor(new DeviceRgb(0, 51, 102))
                    .setFontColor(com.itextpdf.kernel.color.Color.WHITE)
                    .setBorder(Border.NO_BORDER));
            itemTbale.addCell(new Cell().add("")
                    .setBackgroundColor(new DeviceRgb(0, 51, 102))
                    .setFontColor(com.itextpdf.kernel.color.Color.WHITE)
                    .setBorder(Border.NO_BORDER));

            itemTbale.addCell(new Cell().add("")
                    .setBackgroundColor(new DeviceRgb(0, 51, 102))
                    .setFontColor(com.itextpdf.kernel.color.Color.WHITE)
                    .setBorder(Border.NO_BORDER));
            itemTbale.addCell(new Cell().add("")
                    .setBackgroundColor(new DeviceRgb(0, 51, 102))
                    .setFontColor(com.itextpdf.kernel.color.Color.WHITE)
                    .setBorder(Border.NO_BORDER));
            itemTbale.addCell(new Cell().add("")
                    .setBackgroundColor(new DeviceRgb(0, 51, 102))
                    .setFontColor(com.itextpdf.kernel.color.Color.WHITE)
                    .setBorder(Border.NO_BORDER));
            itemTbale.addCell(new Cell().add("")
                    .setBackgroundColor(new DeviceRgb(0, 51, 102))
                    .setFontColor(com.itextpdf.kernel.color.Color.WHITE)
                    .setBorder(Border.NO_BORDER));
            itemTbale.addCell(new Cell().add("")
                    .setBackgroundColor(new DeviceRgb(0, 51, 102))
                    .setFontColor(com.itextpdf.kernel.color.Color.WHITE)
                    .setBorder(Border.NO_BORDER));
            itemTbale.addCell(new Cell().add("Tien thua:")
                    .setBackgroundColor(new DeviceRgb(0, 51, 102))
                    .setFontColor(com.itextpdf.kernel.color.Color.WHITE)
                    .setBorder(Border.NO_BORDER)
                    .setTextAlignment(TextAlignment.LEFT));

            itemTbale.addCell(new Cell().add(String.format("%s", jlbtienthua.getText()) + " VND")
                    .setBackgroundColor(new DeviceRgb(0, 51, 102))
                    .setFontColor(com.itextpdf.kernel.color.Color.WHITE)
                    .setBorder(Border.NO_BORDER)
                    .setTextAlignment(TextAlignment.RIGHT));

//              itemTbale.addCell(new Cell().add("")
//                    .setBackgroundColor(new DeviceRgb(63, 169, 219))
//                    .setFontColor(com.itextpdf.kernel.color.Color.WHITE)
//                    .setBorder(Border.NO_BORDER));
//            
//            itemTbale.addCell(new Cell().add("")
//                    .setBackgroundColor(new DeviceRgb(63, 169, 219))
//                    .setFontColor(com.itextpdf.kernel.color.Color.WHITE)
//                    .setBorder(Border.NO_BORDER));
//            
//            itemTbale.addCell(new Cell().add("")
//                    .setBackgroundColor(new DeviceRgb(63, 169, 219))
//                    .setFontColor(com.itextpdf.kernel.color.Color.WHITE)
//                    .setBorder(Border.NO_BORDER));
//            
//            itemTbale.addCell(new Cell().add("Tien thua:")
//                    .setBackgroundColor(new DeviceRgb(63, 169, 219))
//                    .setFontColor(com.itextpdf.kernel.color.Color.WHITE)
//                    .setBorder(Border.NO_BORDER)
//                    .setTextAlignment(TextAlignment.RIGHT));
//            itemTbale.addCell(new Cell().add(jlbtienthua.getText())
//                    .setBackgroundColor(new DeviceRgb(63, 169, 219))
//                    .setFontColor(com.itextpdf.kernel.color.Color.WHITE)
//                    .setBorder(Border.NO_BORDER)
//                    .setTextAlignment(TextAlignment.RIGHT));
            doc.add(tb);
            doc.add(new Paragraph("\n"));
            doc.add(customInforTbale);
            doc.add(new Paragraph("\n"));
            doc.add(itemTbale);
            doc.close();
            JOptionPane.showMessageDialog(this, "In thành công");
        } catch (FileNotFoundException ex) {

        }

    }

    private void doiTien(BigDecimal c) {
        String pattern = "###,###.00";
        DecimalFormat df = new DecimalFormat(pattern);
        df.format(c);
    }

    private void loadCBHTTT() {
        cbbhtThanhtoan1.addItem("Tiền mặt");
        cbbhtThanhtoan1.addItem("Chuyển khoản");
        cbbhtThanhtoan1.addItem("Kết hợp");
        cbbhtThanhtoan.addItem("Tiền mặt");
        cbbhtThanhtoan.addItem("Chuyển khoản");
        cbbhtThanhtoan.addItem("Kết hợp");

    }

    private void loadCbTTGH() {
        cbTTGH.removeAllItems();
        cbTTGH.addItem("Giao hàng");
        cbTTGH.addItem("Chờ giao hàng");
        cbTTGH.addItem("Đang giao hàng");
        cbTTGH.addItem("Đã giao hàng");
    }

    private void loadCbTTTT() {
        cbTrangThaiTT.removeAllItems();
        cbTrangThaiTT.addItem("Trạng thái");
        cbTrangThaiTT.addItem("Thanh toán trước");
        cbTrangThaiTT.addItem("Thanh toán khi nhận hàng");
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
        jPanel5 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbSanPham = new javax.swing.JTable();
        txtTimKiemSP = new javax.swing.JTextField();
        lbTimKiemSP = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbGioHang = new javax.swing.JTable();
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
        cbTTGH = new javax.swing.JComboBox<>();
        pnBarCode = new javax.swing.JLayeredPane();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtTenKH = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtMaKH = new javax.swing.JTextField();
        btnChonKH = new javax.swing.JButton();
        lbRank = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jlbtongitenhang = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jlbGIAMGIA = new javax.swing.JLabel();
        jlbPHANTRAM = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jlbTiengiam = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cbbhtThanhtoan = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        txttienkhachdua = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jlbTienCK = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jlbtienthua = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        btnHuy = new javax.swing.JButton();
        btnLamMoiHD = new javax.swing.JButton();
        btnThanhToan = new javax.swing.JButton();
        jlbMAHD = new javax.swing.JLabel();
        btntaohoadon = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtNgNhan = new javax.swing.JTextField();
        txtSdtNN = new javax.swing.JTextField();
        txtDiaChiNN = new javax.swing.JTextField();
        txtNgShip = new javax.swing.JTextField();
        btnChonKH1 = new javax.swing.JButton();
        btnChonNv = new javax.swing.JButton();
        jLabel36 = new javax.swing.JLabel();
        txtSdtNS = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jlbMAHD1 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jlbtongitenhang1 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jlbTiengiam1 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jlbGIAMGIA1 = new javax.swing.JLabel();
        jlbPHANTRAM1 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        cbbhtThanhtoan1 = new javax.swing.JComboBox<>();
        jLabel30 = new javax.swing.JLabel();
        txttienkhachdua1 = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jlbTienCK1 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jlbtienthua1 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        txtTienShip = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        cbTrangThaiTT = new javax.swing.JComboBox<>();
        btnThanhToan1 = new javax.swing.JButton();
        btnDangGiao = new javax.swing.JButton();
        btnDaGiao = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setBackground(new java.awt.Color(153, 173, 157));

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
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtTimKiemSP, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lbTimKiemSP, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbTimKiemSP, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTimKiemSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 22, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153), 2), "Giỏ hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 14))); // NOI18N

        tbGioHang.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        tbGioHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
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
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(405, 405, 405)
                .addComponent(btnLamMoiGH)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(btnLamMoiGH))
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

        cbTTGH.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbTTGH.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbTTGHItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 662, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(rdAllHoadon)
                                .addGap(37, 37, 37)
                                .addComponent(rdDahuy)
                                .addGap(35, 35, 35)
                                .addComponent(rdChoTT)
                                .addGap(18, 18, 18)
                                .addComponent(rdDATT)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtsearchhoadon, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnsearchhoadon)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbTTGH, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(87, 87, 87))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtsearchhoadon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnsearchhoadon))
                    .addComponent(cbTTGH, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdAllHoadon)
                    .addComponent(rdDahuy)
                    .addComponent(rdChoTT)
                    .addComponent(rdDATT))
                .addContainerGap())
        );

        pnBarCode.setBorder(javax.swing.BorderFactory.createTitledBorder("Quét mã vạch sản phẩm"));
        pnBarCode.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane3.setBackground(new java.awt.Color(153, 153, 255));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153), 2));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel5.setText("Tên KH");

        jLabel13.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel13.setText("Mã KH:");

        btnChonKH.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnChonKH.setText("Chọn");
        btnChonKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonKHActionPerformed(evt);
            }
        });

        lbRank.setText("Rank");

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnChonKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbRank, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(36, 36, 36))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13)
                        .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbRank, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnChonKH))
                .addGap(21, 21, 21))
        );

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel4.setText("Mã HĐ");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel8.setText("Tổng tiền hàng :");

        jlbtongitenhang.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jlbtongitenhang.setText("0");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel3.setText("VND");

        jlbGIAMGIA.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jlbGIAMGIA.setText("0");

        jlbPHANTRAM.setText("%");

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel1.setText("Giảm giá :");

        jLabel12.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel12.setText("Tiền thanh toán:");

        jlbTiengiam.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jlbTiengiam.setText("0");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel6.setText("VND");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel9.setText("Hình thức thanh toán");

        cbbhtThanhtoan.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cbbhtThanhtoan.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbhtThanhtoanItemStateChanged(evt);
            }
        });
        cbbhtThanhtoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbhtThanhtoanActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel10.setText("Tiền khách đưa");

        txttienkhachdua.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txttienkhachdua.setText("0");
        txttienkhachdua.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txttienkhachduaCaretUpdate(evt);
            }
        });
        txttienkhachdua.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txttienkhachduaMouseEntered(evt);
            }
        });
        txttienkhachdua.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                txttienkhachduaInputMethodTextChanged(evt);
            }
        });
        txttienkhachdua.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txttienkhachduaKeyPressed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel15.setText("Tiền CK:");

        jlbTienCK.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jlbTienCK.setText("0");
        jlbTienCK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jlbTienCKMouseEntered(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel16.setText("VND");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel7.setText("VND");

        jlbtienthua.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jlbtienthua.setText("0");
        jlbtienthua.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jlbtienthuaMouseEntered(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel11.setText("Tiền thừa  :");

        jLabel14.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel14.setText("Ghi chú:");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane4.setViewportView(jTextArea1);

        btnHuy.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnHuy.setText("Hủy");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        btnLamMoiHD.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnLamMoiHD.setText("Làm mới");
        btnLamMoiHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiHDActionPerformed(evt);
            }
        });

        btnThanhToan.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnThanhToan.setText("Thanh toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        btntaohoadon.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btntaohoadon.setToolTipText("Tạo hóa đơn");
        btntaohoadon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntaohoadonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addComponent(jScrollPane4)
                                .addGap(29, 29, 29))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(28, 28, 28)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel6Layout.createSequentialGroup()
                                                .addComponent(jlbGIAMGIA, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jlbPHANTRAM, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel6Layout.createSequentialGroup()
                                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(jlbtongitenhang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jlbTiengiam, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel6)
                                                    .addComponent(jLabel3)))))
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(232, Short.MAX_VALUE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(113, 113, 113)
                                .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnLamMoiHD, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(53, 53, 53)
                                .addComponent(jlbtienthua, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jSeparator3)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel10)
                                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cbbhtThanhtoan, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txttienkhachdua, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel6Layout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addComponent(jlbTienCK, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel16)))))
                                .addContainerGap())
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(88, 88, 88)
                                .addComponent(jlbMAHD, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btntaohoadon, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(63, 63, 63))))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(20, 20, 20))))
        );

        jPanel6Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnHuy, btnLamMoiHD});

        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jlbMAHD, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btntaohoadon, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jlbtongitenhang, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlbPHANTRAM, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlbGIAMGIA, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jlbTiengiam, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(cbbhtThanhtoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txttienkhachdua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jlbTienCK)
                    .addComponent(jLabel16))
                .addGap(15, 15, 15)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel11))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jlbtienthua)
                        .addComponent(jLabel7)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addGap(4, 4, 4)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLamMoiHD))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jPanel6Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnHuy, btnLamMoiHD});

        jTabbedPane3.addTab("Tại quầy", jPanel6);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin người nhận"));

        jLabel17.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel17.setText("Người nhận:");

        jLabel18.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel18.setText("SDT:");

        jLabel19.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel19.setText("Địa chỉ:");

        jLabel20.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel20.setText("Người Ship:");

        btnChonKH1.setText("Chọn");
        btnChonKH1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonKH1ActionPerformed(evt);
            }
        });

        btnChonNv.setText("Chọn");
        btnChonNv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonNvActionPerformed(evt);
            }
        });

        jLabel36.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel36.setText("SDT:");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18)
                    .addComponent(jLabel19)
                    .addComponent(jLabel20)
                    .addComponent(jLabel36))
                .addGap(33, 33, 33)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(txtSdtNS, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(txtNgNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                                .addComponent(btnChonKH1))
                            .addComponent(txtDiaChiNN, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSdtNN, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(txtNgShip, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnChonNv))))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(txtNgNhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnChonKH1))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel18))
                    .addComponent(txtSdtNN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txtDiaChiNN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txtNgShip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnChonNv))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(txtSdtNS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin hóa đơn"));

        jLabel21.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel21.setText("Mã HĐ");

        jLabel22.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel22.setText("Tổng tiền hàng :");

        jlbtongitenhang1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jlbtongitenhang1.setText("0");

        jLabel23.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel23.setText("VND");

        jLabel24.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel24.setText("Tiền Ship:");

        jLabel25.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel25.setText("VND");

        jLabel26.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel26.setText("Tiền thanh toán:");

        jlbTiengiam1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jlbTiengiam1.setText("0");

        jLabel27.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel27.setText("VND");

        jLabel28.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel28.setText("GIảm giá :");

        jlbGIAMGIA1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jlbGIAMGIA1.setText("0");

        jlbPHANTRAM1.setText("%");

        jLabel29.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel29.setText("Hình thức thanh toán:");

        cbbhtThanhtoan1.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        cbbhtThanhtoan1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbhtThanhtoan1ItemStateChanged(evt);
            }
        });
        cbbhtThanhtoan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbhtThanhtoan1ActionPerformed(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel30.setText("Tiền khách đưa:");

        txttienkhachdua1.setText("0");
        txttienkhachdua1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txttienkhachdua1CaretUpdate(evt);
            }
        });
        txttienkhachdua1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txttienkhachdua1MouseEntered(evt);
            }
        });
        txttienkhachdua1.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                txttienkhachdua1InputMethodTextChanged(evt);
            }
        });
        txttienkhachdua1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txttienkhachdua1KeyPressed(evt);
            }
        });

        jLabel31.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel31.setText("Tiền CK:");

        jlbTienCK1.setText("0");
        jlbTienCK1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jlbTienCK1MouseEntered(evt);
            }
        });

        jLabel32.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel32.setText("VND");

        jLabel33.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel33.setText("Tiền thừa  :");

        jlbtienthua1.setText("0");
        jlbtienthua1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jlbtienthua1MouseEntered(evt);
            }
        });

        jLabel34.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel34.setText("VND");

        txtTienShip.setText("0");
        txtTienShip.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTienShipCaretUpdate(evt);
            }
        });
        txtTienShip.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtTienShipMouseEntered(evt);
            }
        });
        txtTienShip.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                txtTienShipInputMethodTextChanged(evt);
            }
        });
        txtTienShip.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTienShipKeyPressed(evt);
            }
        });

        jLabel35.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel35.setText("Trạng thái thanh toán:");

        cbTrangThaiTT.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        cbTrangThaiTT.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbTrangThaiTTItemStateChanged(evt);
            }
        });
        cbTrangThaiTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTrangThaiTTActionPerformed(evt);
            }
        });

        btnThanhToan1.setText("Thanh toán");
        btnThanhToan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToan1ActionPerformed(evt);
            }
        });

        btnDangGiao.setText("Đang giao");
        btnDangGiao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangGiaoActionPerformed(evt);
            }
        });

        btnDaGiao.setText("Đã giao");
        btnDaGiao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDaGiaoActionPerformed(evt);
            }
        });

        jButton1.setText("Hủy");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel30)
                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel35)
                            .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)))
                        .addGap(4, 4, 4)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jlbMAHD1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txttienkhachdua1)
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addComponent(jlbtongitenhang1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel23))
                                    .addComponent(cbTrangThaiTT, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbbhtThanhtoan1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addComponent(jlbGIAMGIA1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(29, 29, 29)
                                        .addComponent(jlbPHANTRAM1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel9Layout.createSequentialGroup()
                                            .addComponent(txtTienShip, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel9Layout.createSequentialGroup()
                                            .addComponent(jlbTiengiam1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jlbtienthua1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jlbTienCK1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                                            .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton1)))
                                .addContainerGap())))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(btnThanhToan1)
                                .addGap(32, 32, 32)
                                .addComponent(btnDangGiao)
                                .addGap(39, 39, 39)
                                .addComponent(btnDaGiao)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel21)
                    .addComponent(jlbMAHD1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlbtongitenhang1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23))
                .addGap(20, 20, 20)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jlbPHANTRAM1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jlbGIAMGIA1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel28))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jlbTiengiam1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel27)))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25)
                    .addComponent(txtTienShip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(cbTrangThaiTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(cbbhtThanhtoan1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(txttienkhachdua1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(jlbTienCK1)
                    .addComponent(jLabel32))
                .addGap(15, 15, 15)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel33))
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jlbtienthua1)
                        .addComponent(jLabel34)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThanhToan1)
                    .addComponent(btnDangGiao)
                    .addComponent(btnDaGiao)
                    .addComponent(jButton1))
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane3.addTab("Giao hàng", jPanel7);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(pnBarCode, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(73, 73, 73)
                .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 488, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(257, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pnBarCode, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 864, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(98, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tbGioHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbGioHangMouseClicked
        // TODO add your handling code here:
        int i = tbGioHang.getSelectedRow();
        int check = 0;
        for (GioHangresponse x : listGh) {
            if (x.getMaSP().equals(tbGioHang.getValueAt(i, 0).toString())) {
                int checl3 = JOptionPane.showOptionDialog(this, "Vui lòng chọn ", "Option", 0, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Xoá sản phẩm", "Giảm số lượng"}, "asd");
                if (checl3 == 0) {
                    listGh.remove(x);
                    List<ChiTietSpResponse> listCT = chiTietSPService.getAll();
                    for (ChiTietSpResponse chiTietSpResponse : listCT) {
                        if (chiTietSpResponse.getMa().equals(x.getMaSP())) {
                            if (x.getMaSP().equals(tbSanPham.getValueAt(i, 0))) {
                                tbSanPham.setValueAt(chiTietSpResponse.getSoLuongTon(), i, 7);
                            } else {
                            }
                        }
                    }
                } else {
                    String sl2 = JOptionPane.showInputDialog(this, "Nhập số lượng");
                    if (Integer.parseInt(sl2) >= x.getSoLuong()) {
                        List<ChiTietSpResponse> listCT = chiTietSPService.getAll();
                        for (ChiTietSpResponse chiTietSpResponse : listCT) {
                            if (chiTietSpResponse.getMa().equals(x.getMaSP())) {
                                if (x.getMaSP().equals(tbSanPham.getValueAt(i, 0))) {
                                    tbSanPham.setValueAt(chiTietSpResponse.getSoLuongTon(), i, 7);
                                } else {
                                }
                            }
                        }
                        listGh.remove(x);
                    } else {
                        x.setSoLuong(x.getSoLuong() - Integer.parseInt(sl2));
                        List<ChiTietSpResponse> listCT = chiTietSPService.getAll();
                        for (ChiTietSpResponse chiTietSpResponse : listCT) {
                            if (chiTietSpResponse.getMa().equals(x.getMaSP())) {
                                if (x.getMaSP().equals(tbSanPham.getValueAt(i, 0))) {
                                    tbSanPham.setValueAt(chiTietSpResponse.getSoLuongTon() - x.getSoLuong(), i, 7);
                                } else {
                                }
                            }
                        }
                    }
                }
                loadGH(listGh);
                return;
            } else {
                continue;
            }
        }
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
                        tbSanPham.setValueAt(chiTietSpResponse.getSoLuongTon() - gioHangresponse.getSoLuong(), index, 7);
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
        int x = JOptionPane.showConfirmDialog(this, "Bạn muốn xóa tất cả sản phẩm ? ", "Notification", JOptionPane.YES_NO_OPTION);
        if (x == JOptionPane.YES_OPTION) {
            listGh.removeAll(listGh);
            loadGH(listGh);
            loadSP(chiTietSPService.getAll());
        } else {
            return;
        }
    }//GEN-LAST:event_btnLamMoiGHActionPerformed

    private void btntaohoadonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntaohoadonActionPerformed
        // TODO add your handling code here:     
        if (jlbMAHD.getText().isEmpty() || jlbMAHD.getText().equals("")) {
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
        } else {
            btnThanhToanActionPerformed(evt);
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
        }
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
                    jlbtienthua.setText(tienthua + "0");
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
            hdct.setGiaBan(gioHangresponse.getGiaBan());
            double sum = 0;
            sum = sum + (gioHangresponse.getThanhTien().doubleValue());
            BigDecimal tt = new BigDecimal(sum);
            BigDecimal newTien = BigDecimal.valueOf(Double.parseDouble(jlbTiengiam.getText()));
            hdct.setTongTien(newTien);
            HoaDon hd = new HoaDon();
            hd.setId(idhd);
            hdct.setIdHoaDon(hd);
            hdct.setIdChiTietSP(ctsp);
            listHDCT.add(hdct);
        }

        int check = 0;
        HoaDonChiTietResponsitory hdrp = new HoaDonChiTietResponsitory();
        hdrp.deleteSP(jlbMAHD.getText());
        for (HoaDonChiTiet hoaDonChiTiet : listHDCT) {
            hoaDonChiTietService.saveHDCT(hoaDonChiTiet);
            if (txttienkhachdua.getText().equalsIgnoreCase("0") && (jlbTienCK.getText().equalsIgnoreCase("0")) || txttienkhachdua.getText().isEmpty()) {
                //   System.out.println(getIdKh());
                hoaDonService.updateHD(jlbMAHD.getText(), new BigDecimal(jlbTiengiam.getText()), 0, getIdKh(), new BigDecimal(txttienkhachdua.getText()), new BigDecimal(jlbTienCK.getText()), new BigDecimal(jlbtienthua.getText()));
                // update vocher// co loi
                // co loi
                loadHD(hoaDonService.getListsHD_day());
            } else {
                check = 1;
                hoaDonService.updateHD(jlbMAHD.getText(), new BigDecimal(jlbTiengiam.getText()), 1, getIdKh(), new BigDecimal(txttienkhachdua.getText()), new BigDecimal(jlbTienCK.getText()), new BigDecimal(jlbtienthua.getText())); //chua fix sdt 

                if (cbbhtThanhtoan.getSelectedIndex() == 1) {
                    hoaDonRepository.updateHTTT(jlbMAHD.getText(), 1);
                } else if (cbbhtThanhtoan.getSelectedIndex() == 0) {
                    hoaDonRepository.updateHTTT(jlbMAHD.getText(), 0);
                } else {
                    hoaDonRepository.updateHTTT(jlbMAHD.getText(), 2);
                }
                hoaDonRepository.updateHTBH(jlbMAHD.getText(), 0);
                // update vocher // co loi
                rdDATTActionPerformed(evt);
            }
        }
        if (check == 0) {
            JOptionPane.showMessageDialog(this, "Hóa đơn đã được treo");
            updateSL();
            rdChoTTActionPerformed(evt);
            btnLamMoiHDActionPerformed(evt);
        } else {
            int x = JOptionPane.showConfirmDialog(this, "Bạn có muốn in hóa đơn không ?", "Notification!", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                print();
                JOptionPane.showMessageDialog(this, "Thanh toán thành công");
                updateSL();
                btnLamMoiHDActionPerformed(evt);
            } else {
                JOptionPane.showMessageDialog(this, "Thanh toán thành công");
                updateSL();
                btnLamMoiHDActionPerformed(evt);
            }

        }
        listGh.removeAll(listGh);// xoa het gio hang
        loadGH(listGh);
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        // TODO add your handling code here:
        String check = JOptionPane.showInputDialog(this, "Lý do hủy:");
        hoaDonService.updateTTHD(jlbMAHD.getText(), 2, check);
        loadHD(hoaDonService.getListsHD());
        listGh.removeAll(listGh);// xoa het gio hang
        loadGH(listGh);
    }//GEN-LAST:event_btnHuyActionPerformed

    private void btnChonKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonKHActionPerformed
        // TODO add your handling code here:
        Window wd = SwingUtilities.windowForComponent(this);
        DialogKH dialogKh = new DialogKH((Frame) wd, true);
        dialogKh.setVisible(true);
        dialogKh.getKH();

        this.setKH(dialogKh.getKH());
    }//GEN-LAST:event_btnChonKHActionPerformed

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

    private void btnLamMoiHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiHDActionPerformed
        // TODO add your handling code here:
        jlbMAHD.setText("");
        jlbGIAMGIA.setText("0");
        jlbTiengiam.setText("0");
        jlbtienthua.setText("0");
        jlbtongitenhang.setText("0");
        txttienkhachdua.setText("0");
        cbbhtThanhtoan.setSelectedIndex(0);

        listGh.removeAll(listGh);
        loadGH(listGh);
        setKH("KH0");
    }//GEN-LAST:event_btnLamMoiHDActionPerformed

    private void jlbTienCKMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlbTienCKMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jlbTienCKMouseEntered

    private void cbbhtThanhtoanItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbhtThanhtoanItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == 1) {
            if (cbbhtThanhtoan.getSelectedIndex() == 0) {
                jlbTienCK.setText("0");
                txttienkhachdua.setText("0");
            }
            if (cbbhtThanhtoan.getSelectedIndex() == 1) {
                jlbTienCK.setText(jlbTiengiam.getText());
                jlbtienthua.setText("0");
                txttienkhachdua.setText("0");
            }
            if (cbbhtThanhtoan.getSelectedIndex() == 2) {
                txttienkhachdua.setText("0");
                jlbtienthua.setText("0");
                double tong = Double.parseDouble(jlbTiengiam.getText());
                double tkh = Double.parseDouble(txttienkhachdua.getText());
                jlbTienCK.setText(String.valueOf(tong - tkh));
            }

        }
    }//GEN-LAST:event_cbbhtThanhtoanItemStateChanged

    private void txttienkhachduaInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txttienkhachduaInputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_txttienkhachduaInputMethodTextChanged

    private void txttienkhachduaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txttienkhachduaCaretUpdate
        // TODO add your handling code here:
        if (jlbTiengiam.getText().equals("0")) {
        } else {
            if (cbbhtThanhtoan.getSelectedIndex() == 2) {
                jlbtienthua.setText("0");
                double tong = Double.parseDouble(jlbTiengiam.getText());
                double tkh = Double.parseDouble(txttienkhachdua.getText());
                double tienThua = tong - tkh;
                jlbTienCK.setText(String.format("%s", tienThua));
            }
            if (cbbhtThanhtoan.getSelectedIndex() == 0) {
                double tong = Double.parseDouble(jlbTiengiam.getText());
                double tkh = Double.parseDouble(txttienkhachdua.getText());
                double tienThua = tkh - tong;
                jlbtienthua.setText(String.format("%s", tienThua));
            }
        }
    }//GEN-LAST:event_txttienkhachduaCaretUpdate

    private void btnChonKH1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonKH1ActionPerformed
        // TODO add your handling code here:
        Window wd = SwingUtilities.windowForComponent(this);
        DialogKH dialogKh = new DialogKH((Frame) wd, true);
        dialogKh.setVisible(true);
        dialogKh.getKH();
        this.setKH(dialogKh.getKH());
    }//GEN-LAST:event_btnChonKH1ActionPerformed

    private void btnChonNvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonNvActionPerformed
        // TODO add your handling code here:
        Window wd = SwingUtilities.windowForComponent(this);
        DialogNV dialogNV = new DialogNV((Frame) wd, true);
        dialogNV.setVisible(true);
        dialogNV.getNV();
        this.setNV(dialogNV.getNV());
    }//GEN-LAST:event_btnChonNvActionPerformed

    private void cbbhtThanhtoan1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbhtThanhtoan1ItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == 1) {
            if (cbbhtThanhtoan1.getSelectedIndex() == 0) {
                jlbTienCK1.setText("0");
                txttienkhachdua1.setText("0");
            }
            if (cbbhtThanhtoan1.getSelectedIndex() == 1) {
                jlbTienCK1.setText(jlbTiengiam1.getText());
                jlbtienthua1.setText("0");
                txttienkhachdua1.setText("0");
            }
            if (cbbhtThanhtoan1.getSelectedIndex() == 2) {
                txttienkhachdua1.setText("0");
                jlbtienthua1.setText("0");
                double tong = Double.parseDouble(jlbTiengiam1.getText());
                double tkh = Double.parseDouble(txttienkhachdua1.getText());
                jlbTienCK1.setText(String.valueOf(tong - tkh));
            }

        }
    }//GEN-LAST:event_cbbhtThanhtoan1ItemStateChanged

    private void cbbhtThanhtoan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbhtThanhtoan1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbhtThanhtoan1ActionPerformed

    private void txttienkhachdua1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txttienkhachdua1CaretUpdate
        // TODO add your handling code here:
        if (jlbTiengiam1.getText().equals("0")) {
        } else {
            if (cbbhtThanhtoan1.getSelectedIndex() == 2) {
                jlbtienthua1.setText("0");
                double tong = Double.parseDouble(jlbTiengiam1.getText());
                double tkh = Double.parseDouble(txttienkhachdua1.getText());
                double tienThua = tong - tkh;
                jlbTienCK1.setText(String.format("%s", tienThua));
            }
            if (cbbhtThanhtoan1.getSelectedIndex() == 0) {
                double tong = Double.parseDouble(jlbTiengiam1.getText());
                double tkh = Double.parseDouble(txttienkhachdua1.getText());
                double tienThua = tkh - tong;
                jlbtienthua1.setText(String.format("%s", tienThua));
            }
        }
    }//GEN-LAST:event_txttienkhachdua1CaretUpdate

    private void txttienkhachdua1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txttienkhachdua1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_txttienkhachdua1MouseEntered

    private void txttienkhachdua1InputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txttienkhachdua1InputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_txttienkhachdua1InputMethodTextChanged

    private void txttienkhachdua1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttienkhachdua1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttienkhachdua1KeyPressed

    private void jlbTienCK1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlbTienCK1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jlbTienCK1MouseEntered

    private void jlbtienthua1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlbtienthua1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jlbtienthua1MouseEntered

    private void txtTienShipCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTienShipCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTienShipCaretUpdate

    private void txtTienShipMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTienShipMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTienShipMouseEntered

    private void txtTienShipInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txtTienShipInputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTienShipInputMethodTextChanged

    private void txtTienShipKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienShipKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTienShipKeyPressed

    private void cbTrangThaiTTItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbTrangThaiTTItemStateChanged
//        // TODO add your handling code here:
//        if (evt.getStateChange() == 1) {
//            if (cbTrangThaiTT.getSelectedIndex() == 2) {
//                double tienTT = Double.parseDouble(jlbTiengiam1.getText());
//                String tienCoc = new BigDecimal(tienTT / 2).toString();
//                txttienkhachdua1.setText(tienCoc);
//                jlbTienCK1.setText("0");
//            }
//            if (cbTrangThaiTT.getSelectedIndex() == 1) {
//                txttienkhachdua1.setText("0");
//            }
//
//        }
    }//GEN-LAST:event_cbTrangThaiTTItemStateChanged

    private void cbTrangThaiTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTrangThaiTTActionPerformed
        // TODO add your handling code here:
        if (cbTrangThaiTT.getSelectedIndex() == 2) {
            double tienTT = Double.parseDouble(jlbTiengiam1.getText());
            String tienCoc = new BigDecimal(tienTT / 2).toString();
            txttienkhachdua1.setText(tienCoc);
            jlbTienCK1.setText("0");
        }
        if (cbTrangThaiTT.getSelectedIndex() == 1) {
            txttienkhachdua1.setText("0");
        }
    }//GEN-LAST:event_cbTrangThaiTTActionPerformed

    private void btnThanhToan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToan1ActionPerformed
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
            BigDecimal newTien = BigDecimal.valueOf(Double.parseDouble(jlbTiengiam1.getText()));
            hdct.setGiaBan(gioHangresponse.getGiaBan());
            hdct.setTongTien(newTien);
            HoaDon hd = new HoaDon();
            hd.setId(idhd);
            hdct.setIdHoaDon(hd);
            hdct.setIdChiTietSP(ctsp);
            listHDCT.add(hdct);
        }

//        
        int check = 0;
        HoaDonChiTietResponsitory hdrp = new HoaDonChiTietResponsitory();
        hdrp.deleteSP(jlbMAHD.getText());
        for (HoaDonChiTiet hoaDonChiTiet : listHDCT) {
            hoaDonChiTietService.saveHDCT(hoaDonChiTiet);
            if (txttienkhachdua1.getText().equalsIgnoreCase("0") && (jlbTienCK1.getText().equalsIgnoreCase("0")) || txttienkhachdua1.getText().isEmpty()) {
                System.out.println(getIdKh());
                hoaDonService.updateHD(jlbMAHD.getText(), new BigDecimal(jlbTiengiam1.getText()), 0, getIdKh(), new BigDecimal(txttienkhachdua1.getText()), new BigDecimal(jlbTienCK1.getText()), new BigDecimal(jlbtienthua1.getText()));
                // update vocher// co loi
                // co loi
                loadHD(hoaDonService.getListsHD());
            } else {
                check = 1;
                if (cbbhtThanhtoan1.getSelectedIndex() == 1) {
                    hoaDonRepository.updateHTTT(jlbMAHD.getText(), 1);
                } else if (cbbhtThanhtoan1.getSelectedIndex() == 0) {
                    hoaDonRepository.updateHTTT(jlbMAHD.getText(), 0);
                } else {
                    hoaDonRepository.updateHTTT(jlbMAHD.getText(), 2);
                }
                hoaDonRepository.updateHTBH(jlbMAHD.getText(), 1);
                hoaDonService.updateHD(jlbMAHD.getText(), new BigDecimal(jlbTiengiam1.getText()), 3, getIdKh(), new BigDecimal(txttienkhachdua1.getText()), new BigDecimal(jlbTienCK1.getText()), new BigDecimal(jlbtienthua1.getText())); //chua fix sdt 
                hoaDonRepository.updateGH(jlbMAHD.getText(), txtNgNhan.getText(), txtSdtNN.getText(), txtNgShip.getText(), txtSdtNS.getText(), new BigDecimal(txtTienShip.getText()), txtDiaChiNN.getText());
                // update vocher // co loi
                loadHD(hoaDonService.timKiemTT(3));
            }
        }
        if (check == 0) {
            JOptionPane.showMessageDialog(this, "Hóa đơn đã được treo");
            rdChoTTActionPerformed(evt);
            btnLamMoiHDActionPerformed(evt);
        } else {
            int x = JOptionPane.showConfirmDialog(this, "Bạn có muốn in hóa đơn không ?", "Notification!", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                print();
                JOptionPane.showMessageDialog(this, "Thanh toán thành công");
                updateSL();
                btnLamMoiHDActionPerformed(evt);
            } else {
                JOptionPane.showMessageDialog(this, "Thanh toán thành công");
                updateSL();
                btnLamMoiHDActionPerformed(evt);
            }

        }
        listGh.removeAll(listGh);// xoa het gio hang
        loadGH(listGh);
    }//GEN-LAST:event_btnThanhToan1ActionPerformed

    private void btnDangGiaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangGiaoActionPerformed
        // TODO add your handling code here:
      //  hoaDonService.updateHD(jlbMAHD.getText(), new BigDecimal(jlbTiengiam1.getText()), 0, getIdKh(), new BigDecimal(txttienkhachdua1.getText()), new BigDecimal(jlbTienCK1.getText()), new BigDecimal(jlbtienthua1.getText()));
        hoaDonRepository.updateGH(jlbMAHD.getText(), txtNgNhan.getText(), txtSdtNN.getText(), txtNgShip.getText(), txtSdtNS.getText(), new BigDecimal(txtTienShip.getText()), txtDiaChiNN.getText());
        hoaDonService.updateTTHD(jlbMAHD.getText(), 4, "N/a");
        loadHD(hoaDonService.getListsHD());
//        listGh.removeAll(listGh);// xoa het gio hang
//        loadGH(listGh);
        loadHD(hoaDonService.timKiemTT(4));
        btnDangGiao.setEnabled(false);
        btnDaGiao.setEnabled(true);
        //   btnChoGH.setEnabled(false);
    }//GEN-LAST:event_btnDangGiaoActionPerformed

    private void btnDaGiaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDaGiaoActionPerformed
        // TODO add your handling code here:

        if (cbTrangThaiTT.getSelectedIndex() == 2) {
            String check = JOptionPane.showInputDialog("Số tiền khách trả ");
            double tongTien = Double.parseDouble(jlbTiengiam1.getText());
            double tienKH = Double.parseDouble(check);
            double tienCoc = Double.parseDouble(txttienkhachdua1.getText());
            if ((tienKH + tienCoc) < tongTien) {
                JOptionPane.showMessageDialog(this, "Chưa đủ số tiền cần thanh toán");
                return;
            } else {
                hoaDonRepository.updateGH(jlbMAHD.getText(), txtNgNhan.getText(), txtSdtNN.getText(), txtNgShip.getText(), txtSdtNS.getText(), new BigDecimal(txtTienShip.getText()), txtDiaChiNN.getText());
                double tienThua = (tienKH + tienCoc) - tongTien;
                jlbtienthua1.setText(String.valueOf(tienThua));
                hoaDonService.updateHD(jlbMAHD.getText(), new BigDecimal(jlbTiengiam1.getText()), 5, getIdKh(), new BigDecimal(String.valueOf((tienCoc + tienKH))), new BigDecimal(jlbTienCK1.getText()), new BigDecimal(jlbtienthua1.getText())); //chua fix sdt 
                loadHD(hoaDonService.getListsHD());
//        listGh.removeAll(listGh);// xoa het gio hang
//        loadGH(listGh);
                loadHD(hoaDonService.timKiemTT(5));
                btnDangGiao.setEnabled(false);
                btnDaGiao.setEnabled(false);

            }
        } else {
            hoaDonService.updateHD(jlbMAHD.getText(), new BigDecimal(jlbTiengiam1.getText()), 5, getIdKh(), new BigDecimal(txttienkhachdua1.getText()), new BigDecimal(jlbTienCK1.getText()), new BigDecimal(jlbtienthua1.getText())); //chua fix sdt 
            loadHD(hoaDonService.getListsHD());
//        listGh.removeAll(listGh);// xoa het gio hang
//        loadGH(listGh);
            loadHD(hoaDonService.timKiemTT(5));
            btnDangGiao.setEnabled(false);
            btnDaGiao.setEnabled(false);

        }

    }//GEN-LAST:event_btnDaGiaoActionPerformed

    private void cbTTGHItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbTTGHItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == 1) {
            if (cbbhtThanhtoan1.getSelectedIndex() == 0) {
                loadHD(listHD);
            }
            if (cbTTGH.getSelectedIndex() == 1) {
                loadHD(hoaDonService.timKiemTT(3));
            }
            if (cbTTGH.getSelectedIndex() == 2) {
                loadHD(hoaDonService.timKiemTT(4));
            }
            if (cbTTGH.getSelectedIndex() == 3) {
                loadHD(hoaDonService.timKiemTT(5));
            }

        }
    }//GEN-LAST:event_cbTTGHItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        btnHuyActionPerformed(evt);
    }//GEN-LAST:event_jButton1ActionPerformed
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
    private javax.swing.JButton btnChonKH1;
    private javax.swing.JButton btnChonNv;
    private javax.swing.JButton btnDaGiao;
    private javax.swing.JButton btnDangGiao;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnLamMoiGH;
    private javax.swing.JButton btnLamMoiHD;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnThanhToan1;
    private javax.swing.JButton btnsearchhoadon;
    private javax.swing.JButton btntaohoadon;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.JComboBox<String> cbTTGH;
    private javax.swing.JComboBox<String> cbTrangThaiTT;
    private javax.swing.JComboBox<String> cbbhtThanhtoan;
    private javax.swing.JComboBox<String> cbbhtThanhtoan1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel jlbGIAMGIA;
    private javax.swing.JLabel jlbGIAMGIA1;
    private javax.swing.JLabel jlbMAHD;
    private javax.swing.JLabel jlbMAHD1;
    private javax.swing.JLabel jlbPHANTRAM;
    private javax.swing.JLabel jlbPHANTRAM1;
    private javax.swing.JLabel jlbTienCK;
    private javax.swing.JLabel jlbTienCK1;
    private javax.swing.JLabel jlbTiengiam;
    private javax.swing.JLabel jlbTiengiam1;
    private javax.swing.JLabel jlbtienthua;
    private javax.swing.JLabel jlbtienthua1;
    private javax.swing.JLabel jlbtongitenhang;
    private javax.swing.JLabel jlbtongitenhang1;
    private javax.swing.JLabel lbRank;
    private javax.swing.JLabel lbTimKiemSP;
    private javax.swing.JLayeredPane pnBarCode;
    private javax.swing.JRadioButton rdAllHoadon;
    private javax.swing.JRadioButton rdChoTT;
    private javax.swing.JRadioButton rdDATT;
    private javax.swing.JRadioButton rdDahuy;
    private javax.swing.JTable tbGioHang;
    private javax.swing.JTable tbHoaDon;
    private javax.swing.JTable tbSanPham;
    private javax.swing.JTextField txtDiaChiNN;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtNgNhan;
    private javax.swing.JTextField txtNgShip;
    private javax.swing.JTextField txtSdtNN;
    private javax.swing.JTextField txtSdtNS;
    private javax.swing.JTextField txtTenKH;
    private javax.swing.JTextField txtTienShip;
    private javax.swing.JTextField txtTimKiemSP;
    private javax.swing.JTextField txtsearchhoadon;
    private javax.swing.JTextField txttienkhachdua;
    private javax.swing.JTextField txttienkhachdua1;
    // End of variables declaration//GEN-END:variables

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, "My Thread");
        t.setDaemon(true);
        return t;
    }
}

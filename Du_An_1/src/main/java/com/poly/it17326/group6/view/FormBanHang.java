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
import com.poly.it17326.group6.view.DialogKH;
import com.poly.it17326.group6.view.FormMain;
import com.poly.it17326.group6.view.sacn_QR;
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
import com.itextpdf.kernel.color.DeviceRgb;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
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
        cbbhtThanhtoan.addItem("Chuyển khoản");
        cbbhtThanhtoan.addItem("Kết hợp");
        loadWeb();
        setKH("KH0");
        loadHD(listHD);
        loadCB();
//        webcam.close();

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
            jlbTiengiam.setText(jlbtongitenhang.getText());
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
                String sl2 = JOptionPane.showInputDialog(this, "Nhập số lượng");
                x.setSoLuong(Integer.parseInt(sl2) + x.getSoLuong());
                loadGH(listGh);
                return;

            } else {
                continue;
            }
        }
        String sl = JOptionPane.showInputDialog(this, "Nhập số lượng");
        if (Integer.parseInt(sl) > Integer.parseInt(tbSanPham.getValueAt(i, 2).toString())) {
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
        jlbTienCK.setText("0");
        jlbtienthua.setText("0");
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
        pnBarCode.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 280, 250));
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

    private void loadCB() {
        cbVoucher.removeAllItems();
        cbVoucher.addItem("Voucher");
        for (VocherReponse vocherReponse : voucherService.getAll()) {
            cbVoucher.addItem(vocherReponse.getTenVCh());
        }
        if (this.isVisible() == false) {
            webcam.close();
        }
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
                    .setBorder(Border.NO_BORDER));
            customInforTbale.addCell(new Cell().add(jlbMAHD.getText())
                    .setBorder(Border.NO_BORDER));
            customInforTbale.addCell(new Cell().add("TenKhach :")
                    .setBorder(Border.NO_BORDER));
            customInforTbale.addCell(new Cell().add(txtTenKH.getText())
                    .setBorder(Border.NO_BORDER));
            customInforTbale.addCell(new Cell().add("NgayTao :")
                    .setBorder(Border.NO_BORDER));
            customInforTbale.addCell(new Cell().add(tbHoaDon.getValueAt(0, 1).toString())
                    .setBorder(Border.NO_BORDER));

            float itemInforWidth[] = {140, 140, 140, 140, 140};
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
            itemTbale.addCell(new Cell().add("Thanh tien")
                    .setBackgroundColor(new DeviceRgb(0, 51, 102))
                    .setFontColor(com.itextpdf.kernel.color.Color.WHITE)
                    .setTextAlignment(TextAlignment.CENTER));

            for (int i = 0; i < tbGioHang.getRowCount(); i++) {

                String maSp = tbGioHang.getValueAt(i, 0).toString();
                String tenSp = tbGioHang.getValueAt(i, 1).toString();
                String soluong = tbGioHang.getValueAt(i, 2).toString();
                String dongia = tbGioHang.getValueAt(i, 3).toString() + " VND";
                String thanhtien = tbGioHang.getValueAt(i, 4).toString() + " VND";
                itemTbale.addCell(maSp).setTextAlignment(TextAlignment.CENTER);
                itemTbale.addCell(tenSp).setTextAlignment(TextAlignment.CENTER);
                itemTbale.addCell(soluong).setTextAlignment(TextAlignment.CENTER);
                itemTbale.addCell(dongia).setTextAlignment(TextAlignment.CENTER);
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

            itemTbale.addCell(new Cell().add("Tong tien:")
                    .setBackgroundColor(new DeviceRgb(0, 51, 102))
                    .setFontColor(com.itextpdf.kernel.color.Color.WHITE)
                    .setBorder(Border.NO_BORDER)
                    .setTextAlignment(TextAlignment.RIGHT));

            itemTbale.addCell(new Cell().add(jlbTiengiam.getText() + " VND")
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
        btnLamMoiHD = new javax.swing.JButton();
        cbVoucher = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        jlbTienCK = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        pnBarCode = new javax.swing.JLayeredPane();

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
                .addGap(17, 17, 17)
                .addComponent(txtTimKiemSP, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
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
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbTimKiemSP, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTimKiemSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 720, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(283, 283, 283)
                        .addComponent(btnLamMoiGH)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addComponent(btnLamMoiGH)
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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtsearchhoadon, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnsearchhoadon)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(rdAllHoadon)
                                .addGap(37, 37, 37)
                                .addComponent(rdDahuy)
                                .addGap(35, 35, 35)
                                .addComponent(rdChoTT)
                                .addGap(18, 18, 18)
                                .addComponent(rdDATT)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(260, 260, 260))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtsearchhoadon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnsearchhoadon))
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

        btnsudung.setText("Sử dụng");
        btnsudung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsudungActionPerformed(evt);
            }
        });

        jLabel12.setText("Tiền thanh toán:");

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

        btnLamMoiHD.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnLamMoiHD.setText("Làm mới");
        btnLamMoiHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiHDActionPerformed(evt);
            }
        });

        cbVoucher.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel15.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel15.setText("Tiền CK:");

        jlbTienCK.setText("0");
        jlbTienCK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jlbTienCKMouseEntered(evt);
            }
        });

        jLabel16.setText("VND");

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
        jLayeredPane2.setLayer(btnLamMoiHD, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(cbVoucher, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jLabel15, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jlbTienCK, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jLabel16, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane2Layout = new javax.swing.GroupLayout(jLayeredPane2);
        jLayeredPane2.setLayout(jLayeredPane2Layout);
        jLayeredPane2Layout.setHorizontalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jSeparator3)
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
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
                                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                                    .addComponent(jlbMAHD, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                                    .addComponent(cbVoucher, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(42, 42, 42)
                                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btntaohoadon, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                                    .addComponent(btnsudung, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 20, Short.MAX_VALUE))
                    .addGroup(jLayeredPane2Layout.createSequentialGroup()
                        .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbbhtThanhtoan, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txttienkhachdua, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jlbTienCK, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel16)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addGap(113, 113, 113)
                .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLamMoiHD, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane2Layout.createSequentialGroup()
                .addGap(0, 48, Short.MAX_VALUE)
                .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 48, Short.MAX_VALUE))
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addComponent(jlbtienthua, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLayeredPane2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnHuy, btnLamMoiHD});

        jLayeredPane2Layout.setVerticalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane2Layout.createSequentialGroup()
                        .addComponent(jlbMAHD, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8))
                    .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(btntaohoadon)))
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(btnsudung))
                    .addComponent(cbVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
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
                .addGap(14, 14, 14)
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jlbTienCK)
                    .addComponent(jLabel16))
                .addGap(15, 15, 15)
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel11))
                    .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jlbtienthua)
                        .addComponent(jLabel7)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addGap(4, 4, 4)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLamMoiHD))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jLayeredPane2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnHuy, btnLamMoiHD});

        pnBarCode.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnBarCode.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(134, 134, 134)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnBarCode, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(105, 105, 105)
                .addComponent(jLayeredPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(409, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLayeredPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pnBarCode, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(97, Short.MAX_VALUE))
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
                                tbSanPham.setValueAt(chiTietSpResponse.getSoLuongTon(), i, 2);
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
                                    tbSanPham.setValueAt(chiTietSpResponse.getSoLuongTon(), i, 2);
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
                                    tbSanPham.setValueAt(chiTietSpResponse.getSoLuongTon() - x.getSoLuong(), i, 2);
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
        loadSP(chiTietSPService.getAll());
    }//GEN-LAST:event_btnLamMoiGHActionPerformed

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
            BigDecimal newTien = BigDecimal.valueOf(Double.parseDouble(jlbTiengiam.getText()));
            hdct.setTongTien(newTien);
            HoaDon hd = new HoaDon();
            hd.setId(idhd);
            hdct.setIdHoaDon(hd);
            hdct.setIdChiTietSP(ctsp);
            listHDCT.add(hdct);
        }
        List<Voucher> listVCh = voucherService.Find(cbVoucher.getSelectedItem().toString());

        int idvch = 0; // lay id vch

        List<Voucher> list = voucherService.FindTEN(cbVoucher.getSelectedItem().toString());
        int soluongTon = 0;
//        
        int check = 0;
        HoaDonChiTietResponsitory hdrp = new HoaDonChiTietResponsitory();
        hdrp.deleteSP(jlbMAHD.getText());
        for (HoaDonChiTiet hoaDonChiTiet : listHDCT) {
            hoaDonChiTietService.saveHDCT(hoaDonChiTiet);
            if (txttienkhachdua.getText().equalsIgnoreCase("0") || txttienkhachdua.getText().isEmpty()) {
                System.out.println(getIdKh());
                hoaDonService.updateHD(jlbMAHD.getText(), new BigDecimal(jlbTiengiam.getText()), 0, getIdKh());
                for (Voucher voucher : listVCh) {
                    if (cbVoucher.getSelectedItem().equals(voucher.getTen())) {
                        idvch = voucher.getId();
                        soluongTon = voucher.getSoLuong();
                        hoaDonService.updateVCHHD(jlbMAHD.getText(), idvch);
                        voucherService.updateSLVCH(cbVoucher.getSelectedItem().toString(), (soluongTon - 1));
                    }
                }
                // update vocher// co loi
                // co loi
                loadHD(hoaDonService.getListsHD());
            } else {
                check = 1;
                hoaDonService.updateHD(jlbMAHD.getText(), new BigDecimal(jlbTiengiam.getText()), 1, getIdKh()); //chua fix sdt 
                for (Voucher voucher : listVCh) {
                    if (cbVoucher.getSelectedItem().equals(voucher.getTen())) {
                        idvch = voucher.getId();
                        hoaDonService.updateVCHHD(jlbMAHD.getText(), idvch);
                    }
                }
                // update vocher // co loi
                rdDATTActionPerformed(evt);
            }
        }
        if (check == 0) {
            JOptionPane.showMessageDialog(this, "Hóa đơn đã được treo");
            rdChoTTActionPerformed(evt);
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
        hoaDonService.updateHD(jlbMAHD.getText(), new BigDecimal(jlbtongitenhang.getText()), 2, getIdKh()); // chua fix sdt 
        loadHD(hoaDonService.getListsHD());
        listGh.removeAll(listGh);// xoa het gio hang
        loadGH(listGh);
    }//GEN-LAST:event_btnHuyActionPerformed

    private void btnsudungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsudungActionPerformed
        List<Voucher> listVCh = voucherService.Find(cbVoucher.getSelectedItem().toString());
        for (Voucher voucher : listVCh) {
            System.out.println(voucher.toString());
        }
        int soluongTon = listVCh.get(0).getSoLuong();

        if (jlbMAHD.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui long chon hoa don truoc khi su dung ma");
            return;
        }
        if (soluongTon <= 0) {
            JOptionPane.showMessageDialog(this, "Số lượng voucher này đã hết ?Vui lòng thử voucher khác ");
            return;
        }
        if (cbVoucher.getSelectedItem().toString().isEmpty()) {
            jlbGIAMGIA.setText("");
            return;
        }
        if (listVCh.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ma giảm giá không hợp lệ");
            return;
        }
        List<Voucher> list = voucherService.FindTEN(cbVoucher.getSelectedItem().toString());
        for (HoaDon hd : hoaDonService.getALLHD()) {
            if (hd.getMaHD().equals(jlbMAHD.getText())) {
                if (hd.getVoucher() == null) {
                    for (Voucher voucher : list) {
                        float giaTriGiamGia = voucher.getPhanTram();
                        int epkieu = (int) giaTriGiamGia;
                        jlbGIAMGIA.setText(String.valueOf(epkieu));
                        jlbPHANTRAM.setText("%");
                        JOptionPane.showMessageDialog(this, "success");
                        float tienGiam1 = Float.parseFloat(jlbTiengiam.getText());
                        float tienGiam2 = (tienGiam1 * giaTriGiamGia) / 100;
                        Float tong = Float.parseFloat(jlbTiengiam.getText());
                         jlbTiengiam.setText(String.valueOf(tong - tienGiam2));
                        return;
                    }
                } else if (hd.getVoucher().getTen().equals(cbVoucher.getSelectedItem().toString())) {
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

    private void btnLamMoiHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiHDActionPerformed
        // TODO add your handling code here:
        jlbMAHD.setText("");
        jlbGIAMGIA.setText("0");
        jlbTiengiam.setText("0");
        jlbtienthua.setText("0");
        jlbtongitenhang.setText("0");
        txttienkhachdua.setText("0");
        cbbhtThanhtoan.setSelectedIndex(0);
        cbVoucher.setSelectedIndex(0);
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
                jlbTienCK.setText(String.format("%,.0f", tienThua));              
            }
            if (cbbhtThanhtoan.getSelectedIndex() == 0) {
                double tong = Double.parseDouble(jlbTiengiam.getText());
                double tkh = Double.parseDouble(txttienkhachdua.getText());
                double tienThua = tkh - tong;
                jlbtienthua.setText(String.format("%,.0f", tienThua));
            }
        }
    }//GEN-LAST:event_txttienkhachduaCaretUpdate
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
    private javax.swing.JButton btnLamMoiGH;
    private javax.swing.JButton btnLamMoiHD;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnThayDoi;
    private javax.swing.JButton btnsearchhoadon;
    private javax.swing.JButton btnsudung;
    private javax.swing.JButton btntaohoadon;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.JComboBox<String> cbVoucher;
    private javax.swing.JComboBox<String> cbbhtThanhtoan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
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
    private javax.swing.JLabel jlbTienCK;
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

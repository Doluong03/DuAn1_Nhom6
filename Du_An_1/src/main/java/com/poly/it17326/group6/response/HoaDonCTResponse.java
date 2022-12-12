/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group6.response;

import com.poly.it17326.group6.domainmodel.ChiTietSP;
import com.poly.it17326.group6.domainmodel.HoaDon;
import com.poly.it17326.group6.domainmodel.HoaDonChiTiet;
import com.poly.it17326.group6.domainmodel.SanPham;
import com.poly.it17326.group6.domainmodel.Voucher;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author 123
 */
@Getter
@Setter
@ToString
public class HoaDonCTResponse {

    private String maHD;
    private String ten;
    private int soLuong;
    private BigDecimal donGia;
    private String tenKH;
    private String sdt;
    private int idChiTietSP;
    private String maSP;
    private BigDecimal tongTien;//du dA đi qua nơi này
    private BigDecimal ThanhTien;
    private BigDecimal giaBan;
    private float giaKhuyenMai;
    private String loaiSanPham;

    public HoaDonCTResponse() {
    }

    public HoaDonCTResponse(HoaDonChiTiet hdct) {
        this.maHD = hdct.getMaHD();
        this.ten = hdct.getIdChiTietSP().getSanPham().getTen();
        this.soLuong = hdct.getSoLuong();
        this.donGia = hdct.getDonGia();
        this.tenKH = hdct.getTenKH();
        this.idChiTietSP = hdct.getIdChiTietSP().getId();
        this.maSP = hdct.getIdChiTietSP().getSanPham().getMa();
        this.tongTien = hdct.getIdHoaDon().getTongTien();
        this.ThanhTien = getThanhTien();
        this.giaBan = hdct.getGiaBan();
        if (hdct.getIdHoaDon().getVoucher() == null) {
            this.giaKhuyenMai = giaKhuyenMai;
        } else {
            this.giaKhuyenMai = hdct.getIdHoaDon().getVoucher().getPhanTram();
        }
        this.loaiSanPham = hdct.getIdChiTietSP().getLoaiSP().getTen();
    }

    public BigDecimal getThanhTien() {
        if (this.giaBan == null) {
            BigDecimal sl = new BigDecimal(soLuong);
            return donGia.multiply(sl);
        }else{
            BigDecimal sl = new BigDecimal(soLuong);
            return giaBan.multiply(sl);
        }

    }

}

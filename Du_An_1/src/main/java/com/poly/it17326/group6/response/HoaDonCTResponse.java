/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group6.response;


import com.poly.it17326.group6.domainmodel.ChiTietSP;
import com.poly.it17326.group6.domainmodel.HoaDon;
import com.poly.it17326.group6.domainmodel.HoaDonChiTiet;
import com.poly.it17326.group6.domainmodel.SanPham;
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
    }

   public BigDecimal getThanhTien(){
        BigDecimal sl= new BigDecimal(soLuong);
        return donGia.multiply(sl);
    }


}

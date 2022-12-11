/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group6.response;

import com.poly.it17326.group6.domainmodel.ChitietKhuyenMai;
import java.math.BigDecimal;

/**
 *
 * @author 123
 */
public class chiTietKhuyenMaiResponse {
       private int idCTSP;
    private String anh;
    private String ma;
    private String ten;
    private int soLuongTon;
    private BigDecimal donGia;
    private String maVach;
    private String nsx;
    private String thanhPhan;
    private String loaiSP;
    private BigDecimal giaMoi;

    public chiTietKhuyenMaiResponse() {
    }

    public chiTietKhuyenMaiResponse(ChitietKhuyenMai ct) {
        this.idCTSP = idCTSP;
        this.ma = ct.getMaSP();
        this.ten = ct.getTenSP();
        this.soLuongTon = ct.getChiTietSP().getSoLuongTon();
        this.donGia = ct.getDonGia();
        this.giaMoi = ct.getDonGiaConLai();
        this.nsx = ct.getChiTietSP().getNsx().getTen();
        this.thanhPhan = ct.getChiTietSP().getLoaiSP().getThanhPhan().getTen();
        this.loaiSP = ct.getChiTietSP().getLoaiSP().getTen();
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group6.response;

import com.poly.it17326.group6.domainmodel.ChiTietSP;
import java.io.Serializable;
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

public class ChiTietSpResponse implements Serializable {

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

    public ChiTietSpResponse() {
    }

    public ChiTietSpResponse(ChiTietSP ctsp) {
        if (ctsp.getAnh() == null) {
            this.anh = anh;
        } else {
            this.anh = ctsp.getAnh().getTen();
        }
        this.ma = ctsp.getSanPham().getMa();
        this.ten = ctsp.getSanPham().getTen();
        this.soLuongTon = ctsp.getSoLuongTon();
        this.donGia = ctsp.getDonGia();
        this.idCTSP = ctsp.getId();
        if (ctsp.getMaVach() == null) {
            this.maVach = maVach;
        } else {
            this.maVach = ctsp.getMaVach();
        }
        this.nsx = ctsp.getNsx().getTen();
        if (ctsp.getLoaiSP() == null) {
            this.loaiSP = loaiSP;
        } else {
            this.loaiSP = ctsp.getLoaiSP().getTen();
        }
         if (ctsp.getLoaiSP() == null) {
            this.thanhPhan = thanhPhan;
        } else {
           this.thanhPhan = ctsp.getLoaiSP().getThanhPhan().getTen();
        }
        
      
    }

}

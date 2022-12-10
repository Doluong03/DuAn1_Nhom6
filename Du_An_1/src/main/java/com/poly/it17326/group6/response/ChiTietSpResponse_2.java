/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group6.response;

import com.poly.it17326.group6.domainmodel.ChiTietSP;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
public class ChiTietSpResponse_2 implements Serializable {

    private String anh;
    private String ma;
    private String ten;
    private int soLuongTon;
    private BigDecimal donGia;
    private String loaiSP;
    private Date hsd;
    private String Nsx;
    private int id;

    public ChiTietSpResponse_2() {
    }

    public ChiTietSpResponse_2(ChiTietSP ctsp) {
        if (ctsp.getAnh() == null) {
            this.anh = anh;
        } else {
            this.anh = ctsp.getAnh().getTen();
        }
        if (ctsp.getSanPham() == null) {
            this.ma = ma;
        } else {
            this.ma = ctsp.getSanPham().getMa();
        }
        this.ten = ctsp.getSanPham().getTen();
        this.soLuongTon = ctsp.getSoLuongTon();
        this.donGia = ctsp.getDonGia();
        if (ctsp.getLoaiSP() == null) {
            this.loaiSP = loaiSP;
        } else {
            this.loaiSP = ctsp.getLoaiSP().getTen();
        }
        this.hsd = ctsp.getHsd();
        this.Nsx = ctsp.getNsx().getTen();
        this.id = ctsp.getId();
    }

}

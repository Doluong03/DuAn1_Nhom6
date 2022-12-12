/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group6.response;

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
public class GioHangresponse implements Serializable {

    private int idCTSP;
    private String maSP;
    private String tenSP;
    private int soLuong;
    private BigDecimal donGia;
    private String maVach;
    private BigDecimal giaBan;

    public GioHangresponse() {
    }

    public GioHangresponse(ChiTietSpResponse chiTietSP, int soluong) {
        this.maSP = chiTietSP.getMa();
        this.tenSP = chiTietSP.getTen();
        this.soLuong = soluong;
        this.donGia = chiTietSP.getDonGia();
        this.idCTSP = chiTietSP.getIdCTSP();
        this.maVach = chiTietSP.getMaVach();
        this.giaBan = chiTietSP.getGiaMoi();
    }

    public BigDecimal getThanhTien() {
        if (this.giaBan == null) {
            BigDecimal sl = new BigDecimal(soLuong);
            BigDecimal tt = sl.multiply(donGia);
            return tt;
        } else {
            BigDecimal sl = new BigDecimal(soLuong);
            BigDecimal tt = sl.multiply(giaBan);
            return tt;
        }
    }
}

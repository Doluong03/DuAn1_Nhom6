/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group6.response;

import com.poly.it17326.group6.domainmodel.ChitietKhuyenMai;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
public class KhuyenMaiResponse {

    private String tenSp;
    private String MaSp;
    private BigDecimal giacu;
    private BigDecimal giaMoi;
    public KhuyenMaiResponse() {
    }

    public KhuyenMaiResponse(ChitietKhuyenMai chitietKhuyenMai) {
        this.tenSp = chitietKhuyenMai.getTenSP();
        this.MaSp = chitietKhuyenMai.getMaSP();
        this.giacu = chitietKhuyenMai.getDonGia();
        this.giaMoi = chitietKhuyenMai.getDonGiaConLai();
    }

    
}

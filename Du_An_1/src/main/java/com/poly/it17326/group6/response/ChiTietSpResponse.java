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

   

public class ChiTietSpResponse implements Serializable{
  
    private int idCTSP;
    private String anh;
    private String ma;
    private String ten;
    private int soLuongTon;
    private BigDecimal donGia;

    public ChiTietSpResponse() {
    }

    public ChiTietSpResponse(ChiTietSP ctsp) {
        this.anh = ctsp.getAnh().getTen();
        this.ma =ctsp.getSanPham().getMa();
        this.ten = ctsp.getSanPham().getTen();
        this.soLuongTon = ctsp.getSoLuongTon();
        this.donGia = ctsp.getDonGia();
        this.idCTSP = ctsp.getId();
    }
    
    
}

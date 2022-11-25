/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group6.response;

import com.poly.it17326.group6.domainmodel.SanPham;
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
public class SanPhamResponse {

    private int id;
    private String ma;
    private String ten;
    private Date ngayTao;
    private Date ngaySua;
    private boolean xoa;

    public SanPhamResponse() {
    }

    public SanPhamResponse(SanPham sp) {
        this.id = sp.getId();
        this.ma = sp.getMa();
        this.ten = sp.getTen();
        this.ngayTao = sp.getCreateAt();
        this.ngaySua = sp.getUpdateAt();
        this.xoa = sp.isDelete();
    }

}

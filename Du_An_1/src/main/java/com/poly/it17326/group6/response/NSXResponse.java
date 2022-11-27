/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group6.response;

import com.poly.it17326.group6.domainmodel.NSX;

/**
 *
 * @author bachc
 */
public class NSXResponse {
    private int id;
    private String ma;
    private String ten;
    private String quocGia;

    public NSXResponse() {
    }

    public NSXResponse(NSX nsx) {
        this.id = nsx.getId();
        this.ma = nsx.getMa();
        this.ten = nsx.getTen();
        this.quocGia = nsx.getQuocGia();
    }

    public NSXResponse(NSXResponse nsx) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public Object row(){
    return new Object[]{id,ma,ten,quocGia};
    }
}

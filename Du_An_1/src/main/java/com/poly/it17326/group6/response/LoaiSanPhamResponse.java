/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group6.response;

import com.poly.it17326.group6.domainmodel.LoaiSP;

/**
 *
 * @author bachc
 */
public class LoaiSanPhamResponse {
    private int idLSP;
    private String MaSP;
    private String tenSP;
    private int idTP;

    public LoaiSanPhamResponse() {
    }

    public LoaiSanPhamResponse(LoaiSP lsp) {
        this.idLSP = lsp.getId();
        this.MaSP = lsp.getMa();
        this.tenSP = lsp.getTen();
        this.idTP = lsp.getThanhPhan().getId();
    }
    public Object row(){ 
             return new Object[]{idLSP,MaSP,tenSP,idLSP};
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group6.response;

import com.poly.it17326.group6.domainmodel.ChucVu;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author bachc
 */
@Getter
@Setter
@ToString
public class ChucVuReponse {
    private int id;
    private String ma;
    private String ten;

    public ChucVuReponse() {
    }

    public ChucVuReponse(ChucVu chucVu) {
        this.id = chucVu.getId();
        this.ma = chucVu.getMa();
        this.ten = chucVu.getTen();
    }
    
}


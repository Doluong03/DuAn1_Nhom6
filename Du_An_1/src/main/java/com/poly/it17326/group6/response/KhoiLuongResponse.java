/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group6.response;

import com.poly.it17326.group6.domainmodel.KhoiLuong;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author OS
 */
@Getter
@Setter
@ToString
public class KhoiLuongResponse {

    private int id;
    private String ma;
    private String ten;

    public KhoiLuongResponse() {
    }

    public KhoiLuongResponse(KhoiLuong anh) {
        this.id = anh.getId();
        this.ma = anh.getMa();
        this.ten = anh.getTen();
    }

}

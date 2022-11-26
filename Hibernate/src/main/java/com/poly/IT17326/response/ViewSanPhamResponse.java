/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.IT17326.response;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class ViewSanPhamResponse {

    private String ma;

    private String ten;

    private Date createAt;

    private String updateAt;

    private Long deleted;

    public ViewSanPhamResponse() {
    }

    public ViewSanPhamResponse(String ma, String ten, Date createAt, String updateAt, Long deleted) {
        this.ma = ma;
        this.ten = ten;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.deleted = deleted;
    }

}

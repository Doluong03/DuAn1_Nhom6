/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group6.response;

import com.poly.it17326.group6.domainmodel.ChucVu;
import java.io.Serializable;
import javax.persistence.Entity;
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
public class TaiKhoanResponse implements Serializable{
    
    private String email;
    private String matkhau;
    private ChucVu chucVu;

    public TaiKhoanResponse() {
    }

    public TaiKhoanResponse(String email, String matkhau, ChucVu chucVu) {
        this.email = email;
        this.matkhau = matkhau;
        this.chucVu = chucVu;
    }
    
    
}

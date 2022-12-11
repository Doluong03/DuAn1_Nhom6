/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group6.domainmodel;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "khuyenmai")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class khuyenmai implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    @Column(name = "create_at")
    private Date create_at;
    
    @Column(name = "gia_tri")
    private int giaTri;
    
    @Column(name = "loai_khuyen_mai")
    private String loaiKhuyenMai;
    
    @Column(name = "ma")
    private String ma;
    
    @Column(name = "ngay_bat_dau")
    private Date ngay_bat_dau;
    
    @Column(name = "ngay_ket_thuc")
    private Date ngay_ket_thuc;
    
    @Column(name = "ten")
    private String ten;

    
    @Column(name = "trang_thai")
    private int trangThai;

    
    public String trangthai(){
        if(trangThai==1){
            return "Đang hoạt động";
        }else{
            return "Ngừng hoạt động";
        }
    }
}

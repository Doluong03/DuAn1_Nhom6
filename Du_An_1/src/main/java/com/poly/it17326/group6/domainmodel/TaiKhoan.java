/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group6.domainmodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author OS
 */
@Entity
@Table(name = "TaiKhoan")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TaiKhoan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private String id;
    @Column(name = "Ma")
    private String ma;
    @Column(name = "HoTen")
    private String hoten;
    @Column(name = "GioiTinh")
    private String gioitinh;
    @Column(name = "NgaySinh")
    private String ngaysinh;
    @Column(name = "DiaChi")
    private String diachi;
    @Column(name = "Sdt")
    private String sdt;
    @Column(name = "MatKhau")
    private String matkhau;
    @Column(name = "email")
    private String email;
    @ManyToOne
    @JoinColumn (name = "IdCV", referencedColumnName = "id")
    private ChucVu chucVu;
   
    
}

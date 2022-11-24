/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group6.domainmodel;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "TaiKhoan")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TaiKhoan implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "Ma")
    private String MaNV;
    
    @Column(name = "HoTen")
    private String HoTenNV;
    
    @Column(name = "Sdt")
    private String Sdt;

    @Column(name = "GioiTinh")
    private String GioiTinh;

    @Column(name = "DiaChi")
    private String DiaChi;

    @Column(name = "MatKhau")
    private String MatKhau;

    @Column(name = "email")
    private String email;

    @Column(name = "NgaySinh")
    private String NgaySinh;

    @Column(name = "Status")
    private boolean Status;

    @Column(name = "Create_at")
    private Date createAt;
    
    @Column(name = "Update_at")
    private Date updateAt;
    
    @Column(name = "Deleted")
    private boolean delete;

    @OneToOne
    @JoinColumn(name = "IdCV",nullable = false)
    private ChucVu chucVu;
}

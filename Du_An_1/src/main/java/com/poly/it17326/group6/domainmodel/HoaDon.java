/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group6.domainmodel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "HoaDon")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class HoaDon implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "Ma")
    private String MaHD;
    @Column(name = "MaKH")
    private String MaKH;
    @Column(name = "HoTen")
    private String HoTen;
    @Column(name = "Sdt")
    private String Sdt;
    @Column(name = "TongTien")
    private BigDecimal tongTien;
    @Column(name = "Create_at")
    private Date createAt;
    @Column(name = "Update_at")
    private Date updateAt;
    @Column(name = "Deleted")
    private boolean delete;

    // khoa ngoai voi banng tai khoan
    @ManyToOne
    @JoinColumn(name = "IdTK")
    private TaiKhoan taiKhoan;
   // khoa ngoai voi bang tinh trang
    @OneToOne
    @JoinColumn(name = "IdTT")
    private TinhTrang tinhTrang;
    
    
    
    // chua maping voi vocher

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group6.domainmodel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@IdClass(HoadonChiTietID.class)
@Table(name = "HoaDonChiTiet")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class HoaDonChiTiet implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "IdHoaDon")
    private HoaDon IdHoaDon;
    
    @Id
    @ManyToOne()
    @JoinColumn(name = "IdChiTietSP")
    private ChiTietSP IdChiTietSP;

    @Column(name = "Ma")
    private String MaHD;

    @Column(name = "Ten")
    private String TenKH;

    @Column(name = "TongTienSP")
    private BigDecimal tongTien;

    @Column(name = "SoLuong")
    private int SoLuong;

    @Column(name = "DonGia")
    private BigDecimal DonGia;
    
    @Column(name = "giaBan")
    private BigDecimal giaBan;


  }      
    
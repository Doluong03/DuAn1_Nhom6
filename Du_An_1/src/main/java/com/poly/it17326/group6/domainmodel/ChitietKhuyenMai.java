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
import javax.persistence.FetchType;
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

@Entity
@Table(name = "ctsp_khuyenmai")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ChitietKhuyenMai implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "create_at")
    private Date create_at;

    @Column(name = "don_gia")
    private BigDecimal donGia;

    @Column(name = "don_gia_con_lai")
    private BigDecimal donGiaConLai;
    
     @Column(name = "ma_sp")
    private String maSP;
     
      @Column(name = "ten_sp")
    private String tenSP;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_ctsp")
    private ChiTietSP chiTietSP;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_km")
    private khuyenmai khuyenmai;

}

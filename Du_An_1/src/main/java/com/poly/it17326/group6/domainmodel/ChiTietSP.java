/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group6.domainmodel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author 123
 */
@Entity
@Table(name = "ChiTietSP")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ChiTietSP {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;
    @ManyToOne()
    @JoinColumn(name = "IdSP")
    private SanPham sanPham;
    @ManyToOne()
    @JoinColumn(name = "IdNsx")
    private NSX nsx;
    @ManyToOne()
    @JoinColumn(name = "IdLoaiSP")
    private LoaiSP loaiSP;
    @ManyToOne()
    @JoinColumn(name = "IdKL")
    private KhoiLuong khoiLuong;
    @ManyToOne()
    @JoinColumn(name = "IdDV")
    private DonViTinh donViTinh;
    @Column(name = "HSD")
    private Date hsd;
    @Column(name = "SoLuongTon")
    private int soLuongTon;
    @Column(name = "DonGia")
    private BigDecimal donGia;
    @Column(name = "maVach")
    private String maVach;
    @OneToMany(mappedBy = "chiTietSP", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public List<ChitietKhuyenMai> chitietKhuyenMais;
}

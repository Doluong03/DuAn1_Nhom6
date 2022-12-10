/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group6.domainmodel;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "KhachHang")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class KhachHang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;
    @Column(name = "Ma")
    private String ma;
    @Column(name = "HoTen")
    private String ten;
    @Column(name = "NgaySinh")
    private Date ngaySinh;
    @Column(name = "DiaChi")
    private String diaChi;
    @Column(name = "Sdt")
    private String sdt;
    @Column(name = "trangThai")
    private boolean trangThai;
    @Column(name = "GioiTinh")
    private String gioiTinh;

    @Column(name = "Rank")
    private int Rank;

    public String trangThai() {
        if (trangThai == true) {
            return "hoạt động";
        } else {
            return "Nghỉ";
        }
    }

    public String Rank() {
        if (Rank == 0) {
            return "Đồng";
        } else if (Rank == 1) {
            return "Bạc";
        } else if (Rank == 2) {
            return "Vàng";
        } else {
            return "Bạch Kim";
        }
    }
}

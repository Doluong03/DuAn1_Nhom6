/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group6.domainmodel;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "Voucher")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Voucher implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private String id;
    @Column(name = "Ma")
    private String ma;

    @Column(name = "Ten")
    private String Ten;
    
    @Column(name = "NgayApDung")
    private Date NgayApDung;
    
    @Column(name = "NgayKetThuc")
    private Date NgayKetThuc;
    
    @Column(name = "PhanTram")
    private float PhanTram;
}

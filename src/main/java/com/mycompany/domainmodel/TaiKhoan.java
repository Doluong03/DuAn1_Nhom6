/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.domainmodel;

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
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author TRAN VAN HUY
 */
@Entity
@Table(name= "TaiKhoan")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TaiKhoan {
    
@Id
@GenericGenerator(name = "generator", strategy = "guid", parameters = {})
    @GeneratedValue(generator = "generator")
    @Column(name = "Id", columnDefinition = "uniqueidentifier")
//@GeneratedValue(strategy = GenerationType.IDENTITY)
//@Column(name="id")
private long id;

@Column(name="Ma")
private String Ma;

@Column(name="HoTen")
private String HoTen;

@Column(name="GioiTinh")
private String GioiTinh;

@Column(name="NgaySinh")
private String NgaySinh;

@Column(name="DiaChi")
private String DiaChi;

@Column(name="SDT")
private String SDT;

@Column(name="MatKhau")
private String MatKhau;

@Column(name="Email")
private String Email;

@Column(name="IdCv")
private String IdCv;

@Column(name="Create_at")
private Date Create_at;

@Column(name="update_at")
private Date update_at;

@Column(name="Delete")
private long Delete;

    public void setId(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.poly.it17326.group6.service;

import com.poly.it17326.group6.domainmodel.TaiKhoan;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Tung Anh
 */
public interface ChangePasswordService {
    public List<TaiKhoan> getPassword(String ma,String MatKhau);
    
    public String changePassword(String ma, String MatKhau, Date updateAt);
    
    public TaiKhoan getMa(String ma);
}

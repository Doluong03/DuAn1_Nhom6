/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.poly.it17326.group6.service;

import com.poly.it17326.group6.domainmodel.TaiKhoan;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Tung Anh
 */
public interface ForgotPassword {
    public List<TaiKhoan> getAll();
    
    public String changePassword(String ma,String password, Date updateAt);
    
    public TaiKhoan getPassword(String password);
    
    public TaiKhoan getID(int id);
    
    public TaiKhoan getEmail(String email);
}

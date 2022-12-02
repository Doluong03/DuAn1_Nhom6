/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group6.service.impl;

import com.poly.it17326.group6.domainmodel.TaiKhoan;
import com.poly.it17326.group6.repository.ForgotPasswordRepository;
import com.poly.it17326.group6.service.ForgotPassword;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Tung Anh
 */
public class ForgotPasswordServiceImpl implements ForgotPassword{

    private ForgotPasswordRepository fprp = new ForgotPasswordRepository();

    @Override
    public String changePassword(String ma,String password,Date updateAt) {
        return fprp.changePassword(ma, password,updateAt);
    }

    @Override
    public List<TaiKhoan> getAll() {
        return fprp.getAll();
    }

    @Override
    public TaiKhoan getPassword(String password) {
        return  fprp.getPassword(password);
    }

    @Override
    public TaiKhoan getID(int id) {
        return fprp.getID(id);
    }

    @Override
    public TaiKhoan getEmail(String email) {
        return fprp.getEmail(email);
    }
    
}

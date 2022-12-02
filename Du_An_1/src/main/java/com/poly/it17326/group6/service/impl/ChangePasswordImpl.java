/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group6.service.impl;

import com.poly.it17326.group6.domainmodel.TaiKhoan;
import com.poly.it17326.group6.repository.ChangePasswordRepository;
import com.poly.it17326.group6.service.ChangePasswordService;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Tung Anh
 */
public class ChangePasswordImpl implements ChangePasswordService {

    ChangePasswordRepository change = new ChangePasswordRepository();

    @Override
    public List<TaiKhoan> getPassword(String ma, String MatKhau) {
        //   
        if (change.getPassword(ma, MatKhau).isEmpty()) {
            return null;
        } else {
            return change.getPassword(ma, MatKhau);
        }

    }

    @Override
    public String changePassword(String ma, String MatKhau, Date updateAt) {
        return change.changePassword(ma, MatKhau, updateAt);
    }

    @Override
    public TaiKhoan getMa(String ma) {
        return change.getMa(ma);
    }

}

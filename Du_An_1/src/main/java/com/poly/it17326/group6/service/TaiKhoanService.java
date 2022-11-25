/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.poly.it17326.group6.service;

import com.poly.it17326.group6.domainmodel.TaiKhoan;
import java.util.ArrayList;
import java.util.List;
import com.poly.it17326.group6.response.TaiKhoanResponse;

/**
 *
 * @author OS
 */
public interface TaiKhoanService {

    public ArrayList<TaiKhoan> getCheck(String email);

    public ArrayList<TaiKhoan> getCheck1(String matkhau);

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group6.service.impl;

import com.poly.it17326.group6.domainmodel.TaiKhoan;
import com.poly.it17326.group6.repository.TaiKhoanRepository;
import com.poly.it17326.group6.response.TaiKhoanResponse;
import com.poly.it17326.group6.service.TaiKhoanService;
import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author OS
 */
public class TaiKhoanServiceImpl implements TaiKhoanService {

    private TaiKhoanRepository taiKhoanRepository = new TaiKhoanRepository();

    @Override
    public ArrayList<TaiKhoan> getCheck(String email) {
      return taiKhoanRepository.CheckUser(email);
    }

    @Override
    public ArrayList<TaiKhoan> getCheck1(String matkhau) {
       return taiKhoanRepository.CheckPasswork(matkhau);
    }

    @Override
    public ArrayList<TaiKhoan> getCheckTen(String ten) {
        return taiKhoanRepository.CheckName(ten);
    }

    
  

   
   

}

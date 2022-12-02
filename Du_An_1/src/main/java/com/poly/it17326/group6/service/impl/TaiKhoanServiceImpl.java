/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group6.service.impl;

import com.poly.it17326.group6.domainmodel.ChucVu;
import com.poly.it17326.group6.domainmodel.TaiKhoan;
import com.poly.it17326.group6.repository.TaiKhoanRepository;
import com.poly.it17326.group6.response.TaiKhoanResponse;
import com.poly.it17326.group6.service.TaiKhoanService;
import java.util.ArrayList;
import java.util.List;
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

    @Override
    public List<TaiKhoan> getAll() {
        return taiKhoanRepository.getAll();
    }

    @Override
    public Boolean them(TaiKhoan taiKhoan) {
        return taiKhoanRepository.add(taiKhoan);
    }

    @Override
    public Boolean sua(TaiKhoan taiKhoan) {
        return taiKhoanRepository.update(taiKhoan);
    }

    @Override
    public Boolean xoa(TaiKhoan taiKhoan) {
        return taiKhoanRepository.delete(taiKhoan);
    }

    @Override
    public List<ChucVu> getListCB() {
        return taiKhoanRepository.getCB();
    }

    @Override
    public List<Boolean> getListCBTT() {
        return taiKhoanRepository.getCBTT();
    }

    @Override
    public List<TaiKhoan> findCv(String id) {
        return taiKhoanRepository.findCv(id);
    }

    @Override
    public List<TaiKhoan> findTT(Boolean tt) {
        return taiKhoanRepository.findTT(tt);
    }

}

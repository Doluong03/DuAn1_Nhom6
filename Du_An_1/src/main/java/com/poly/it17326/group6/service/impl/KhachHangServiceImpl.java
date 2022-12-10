/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group6.service.impl;

import com.poly.it17326.group6.domainmodel.KhachHang;
import com.poly.it17326.group6.repository.KhachHangRepository;
import com.poly.it17326.group6.response.HoaDonresponse;
import com.poly.it17326.group6.service.KhachHangService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 123
 */
public class KhachHangServiceImpl implements KhachHangService {

    private KhachHangRepository khr = new KhachHangRepository();

    @Override
    public List<KhachHang> getList() {
        return khr.getList();
    }

    @Override
    public Boolean addKH(KhachHang kh) {
        return khr.addKh(kh);
    }

    @Override
    public ArrayList<KhachHang> getCheckKH(String email) {
        return khr.CheckUser(email);
    }

    @Override
    public List<HoaDonresponse> getListLS(String ma) {
        return khr.getListLS(ma);
    }

    @Override
    public Boolean updateRank(KhachHang kh, BigDecimal tongTien) {
        return khr.updateRank(tongTien, kh);
    }

    @Override
    public KhachHang getOne(int ID) {
        return khr.getOne(ID);
    }

    @Override
    public Boolean updateKH(KhachHang kh) {
        return khr.updateKh(kh);
    }
}

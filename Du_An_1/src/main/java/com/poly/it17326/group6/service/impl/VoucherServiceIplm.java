/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group6.service.impl;

import com.poly.it17326.group6.domainmodel.Voucher;
import com.poly.it17326.group6.repository.VoucherResponsitory;
import com.poly.it17326.group6.response.VocherReponse;
import com.poly.it17326.group6.service.VoucherService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Hp
 */
public class VoucherServiceIplm implements VoucherService {

    VoucherResponsitory VoucherResponsitory = new VoucherResponsitory();

    @Override
    public List<VocherReponse> getAll() {
    List<VocherReponse> listVCHR = new ArrayList<>();
        List<Voucher> listVC = VoucherResponsitory.getAll();
        for (Voucher lists : listVC) {
            VocherReponse vc = new VocherReponse(lists);
            listVCHR.add(vc);
        }
        return listVCHR;
    }

    @Override
    public boolean updateTrangThai(String Ma, int TrangThai) {
        return VoucherResponsitory.updateTrangThai(Ma, TrangThai);
    }

    @Override
    public boolean updateSLVCH(String ten, int SoLuong) {
        return VoucherResponsitory.updateSLVCH(ten, SoLuong);
    }

    @Override
    public boolean updateVCH(String Ma, Voucher voucher) {
        return VoucherResponsitory.updateVCH(Ma, voucher);
    }

    @Override
    public boolean addVCH(Voucher voucher) {
        return VoucherResponsitory.addVCH(voucher);
    }

   

    @Override
    public List<Voucher> getVocher() {
        return VoucherResponsitory.getAll();
    }

    @Override
    public boolean XoaVCh(String Ma) {
        return VoucherResponsitory.XoaVCh(Ma);
    }

    @Override
    public List<Voucher> FindDate(String NgayApDung, String NgayKetThuc) {
                return VoucherResponsitory.FindDate(NgayApDung, NgayKetThuc);
    }

    @Override
    public List<Voucher> Find(String Ma) {
        return VoucherResponsitory.Find(Ma);
    }

    @Override
    public List<Voucher> FindTT(int TrangThai) {
        return VoucherResponsitory.FindTT(TrangThai);
    }

    @Override
    public List<Voucher> FindTEN(String Ten) {
        return VoucherResponsitory.Find(Ten);
    }
    
    
    
    
    

}

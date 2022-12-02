/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.poly.it17326.group6.service;

import com.poly.it17326.group6.domainmodel.Voucher;
import com.poly.it17326.group6.response.VocherReponse;
import java.util.Date;

import java.util.List;

/**
 *
 * @author Hp
 */
public interface VoucherService {

    public List<VocherReponse> getAll();

    public List<Voucher> getVocher();

    public boolean updateTrangThai(String Ma, int TrangThai);

    public boolean updateSLVCH(String ten, int SoLuong);

    public boolean updateVCH(String Ma, Voucher voucher);

    public boolean addVCH(Voucher voucher);

    public List<Voucher> FindDate(String NgayApDung, String NgayKetThuc);

    public boolean XoaVCh(String Ma);

    public List<Voucher> Find(String Ma);

    public List<Voucher> FindTT(int TrangThai);

    public List<Voucher> FindTEN(String Ten);
}

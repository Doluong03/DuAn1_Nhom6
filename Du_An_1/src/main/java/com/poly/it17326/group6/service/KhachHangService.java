/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.poly.it17326.group6.service;

import com.poly.it17326.group6.domainmodel.KhachHang;
import com.poly.it17326.group6.domainmodel.TaiKhoan;
import com.poly.it17326.group6.response.HoaDonresponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 123
 */
public interface KhachHangService {

    public List<KhachHang> getList();

    public Boolean addKH(KhachHang kh);

    public ArrayList<KhachHang> getCheckKH(String email);

    List<HoaDonresponse> getListLS(String ma);
    
    Boolean updateRank(KhachHang kh, BigDecimal tongTien);
    
    KhachHang getOne(int ID);
    
    Boolean updateKH(KhachHang kh);
}

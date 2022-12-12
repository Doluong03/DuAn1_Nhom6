/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.poly.it17326.group6.service;

import com.poly.it17326.group6.domainmodel.KhoiLuong;
import com.poly.it17326.group6.domainmodel.ChiTietSP;
import com.poly.it17326.group6.domainmodel.DonViTinh;
import com.poly.it17326.group6.domainmodel.LoaiSP;
import com.poly.it17326.group6.domainmodel.NSX;
import com.poly.it17326.group6.domainmodel.SanPham;
import com.poly.it17326.group6.response.ChiTietSpResponse;
import com.poly.it17326.group6.response.ChiTietSpResponse_2;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 123
 */
public interface ChiTietSPService {

    public List<ChiTietSpResponse> getAll();

    public ArrayList<ChiTietSpResponse> getTimKiem(String ma);

    public List<ChiTietSpResponse_2> getTimKiemLsp(String ten);

    public ChiTietSpResponse_2 addSP(ChiTietSpResponse_2 ctsp);

    public ChiTietSpResponse_2 updateSP(ChiTietSpResponse_2 ctsp);

    public List<DonViTinh> getListSp();

    public List<NSX> getListNsx();

    public List<LoaiSP> getListLSp();

    public List<KhoiLuong> getListA();

    public List<ChiTietSpResponse_2> getAllFSP();

    public ArrayList<ChiTietSpResponse_2> getTimKiemFSP(String ma);
    
    public boolean updateSoLuong(  int sl,int id);
    
    List<ChiTietSpResponse> getTimKiem2(String ma) ;
  
   
}

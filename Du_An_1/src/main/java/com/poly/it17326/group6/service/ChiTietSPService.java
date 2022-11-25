/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.poly.it17326.group6.service;

import com.poly.it17326.group6.domainmodel.Anh;
import com.poly.it17326.group6.domainmodel.LoaiSP;
import com.poly.it17326.group6.domainmodel.NSX;
import com.poly.it17326.group6.domainmodel.SanPham;
import com.poly.it17326.group6.response.ChiTietSpResponse;
import com.poly.it17326.group6.response.ChiTietSpResponse_2;
import java.util.ArrayList;

/**
 *
 * @author 123
 */
public interface ChiTietSPService {

    public ArrayList<ChiTietSpResponse> getAll();

    public ArrayList<ChiTietSpResponse> getTimKiem(String ma);

    public ArrayList<ChiTietSpResponse_2> getTimKiemLsp(String ten);

    public ChiTietSpResponse_2 addSP(ChiTietSpResponse_2 ctsp);

    public ChiTietSpResponse_2 updateSP(ChiTietSpResponse_2 ctsp);

    public ArrayList<SanPham> getListSp();

    public ArrayList<NSX> getListNsx();

    public ArrayList<LoaiSP> getListLSp();

    public ArrayList<Anh> getListA();

    public ArrayList<ChiTietSpResponse_2> getAllFSP();

    public ArrayList<ChiTietSpResponse_2> getTimKiemFSP(String ma);
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group6.service.impl;

import com.poly.it17326.group6.domainmodel.ChitietKhuyenMai;
import com.poly.it17326.group6.domainmodel.khuyenmai;
import com.poly.it17326.group6.repository.KhuyenMaiResponsitory;
import com.poly.it17326.group6.response.KhuyenMaiResponse;
import com.poly.it17326.group6.service.khuyenmaiService;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Hp
 */
public class khuyenmaiServiceIplm implements khuyenmaiService {

    private KhuyenMaiResponsitory khuyenmaiRP = new KhuyenMaiResponsitory();

    @Override
    public ArrayList<khuyenmai> getALL() {
        return khuyenmaiRP.getALL();
    }

    @Override
    public ArrayList<khuyenmai> FindKM(String ma) {
        return khuyenmaiRP.FindKM(ma);
    }

    @Override
    public ArrayList<KhuyenMaiResponse> getALLSPKM() {
        ArrayList<ChitietKhuyenMai> listKM = khuyenmaiRP.getALLSPKM();
        ArrayList<KhuyenMaiResponse> listKMRespon = new ArrayList<>();
        for (ChitietKhuyenMai km : listKM) {
            KhuyenMaiResponse hdr = new KhuyenMaiResponse(km);
            listKMRespon.add(hdr);
        }
        return listKMRespon;
    }

    @Override
    public ArrayList<khuyenmai> FindTT(int trangthai) {
        return khuyenmaiRP.FindTT(trangthai);
    }

    @Override
    public ArrayList<KhuyenMaiResponse> FindCTKM(int ma) {
       ArrayList<ChitietKhuyenMai> listKM = khuyenmaiRP.FindCTKM(ma);
        ArrayList<KhuyenMaiResponse> listKMRespon = new ArrayList<>();
        for (ChitietKhuyenMai km : listKM) {
            KhuyenMaiResponse hdr = new KhuyenMaiResponse(km);
            listKMRespon.add(hdr);
        }
        return listKMRespon;  
    }

    @Override
    public boolean AddKM(khuyenmai km) {
        return khuyenmaiRP.AddKM(km);
    }

    @Override
    public boolean AddCTKM(ChitietKhuyenMai ctkm) {
          return khuyenmaiRP.AddCTKM(ctkm);
    }

    @Override
    public boolean UpdateTT(int id) {
        return khuyenmaiRP.UpdateTT(id);
    }

    @Override
    public boolean DeleteCtKm(khuyenmai ma) {
        return khuyenmaiRP.DeleteCTKM(ma);
    }

}

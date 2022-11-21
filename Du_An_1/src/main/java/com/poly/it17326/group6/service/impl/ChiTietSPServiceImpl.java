/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group6.service.impl;

import com.poly.it17326.group6.domainmodel.ChiTietSP;
import com.poly.it17326.group6.domainmodel.SanPham;
import com.poly.it17326.group6.repository.ChiTietSpRepository;
import com.poly.it17326.group6.response.ChiTietSpResponse;
import com.poly.it17326.group6.service.ChiTietSPService;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 *
 * @author 123
 */
public class ChiTietSPServiceImpl implements ChiTietSPService{
private ChiTietSpRepository chiTietSpRepository=new ChiTietSpRepository();
    @Override
    public ArrayList<ChiTietSpResponse> getAll() {
        ArrayList<ChiTietSP> listCt=chiTietSpRepository.getAll();
        ArrayList<ChiTietSpResponse> listCTR=new ArrayList<>();
        for (ChiTietSP chiTietSP : listCt) {
            ChiTietSpResponse ctr= new ChiTietSpResponse(chiTietSP);
            listCTR.add(ctr);
        }
        return listCTR;
    }

    @Override
    public ArrayList<ChiTietSpResponse> getTimKiem(String ma) {
        ArrayList<ChiTietSpResponse> list = new ArrayList<>();
        ChiTietSP ct= chiTietSpRepository.getTimKiem(ma);
        ChiTietSpResponse ctr= new ChiTietSpResponse(ct);
         list.add(ctr);
         return list;
    }

   
    
}

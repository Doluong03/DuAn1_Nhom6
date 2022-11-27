/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.poly.it17326.group6.service;

import com.poly.it17326.group6.domainmodel.HoaDonChiTiet;
import com.poly.it17326.group6.response.HoaDonCTResponse;
import java.util.ArrayList;

/**
 *
 * @author Hp
 */
public interface HoaDonChiTietService {

    public boolean saveHDCT(HoaDonChiTiet hdct);
    
    public ArrayList<HoaDonCTResponse> getListHDCT();
    
    public boolean  deleteSP(String ma);
}

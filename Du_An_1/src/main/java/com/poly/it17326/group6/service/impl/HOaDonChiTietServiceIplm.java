/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group6.service.impl;

import com.poly.it17326.group6.domainmodel.HoaDonChiTiet;
import com.poly.it17326.group6.repository.HoaDonChiTietResponsitory;
import com.poly.it17326.group6.service.HoaDonChiTietService;

/**
 *
 * @author Hp
 */
public class HOaDonChiTietServiceIplm implements HoaDonChiTietService{
     HoaDonChiTietResponsitory HoaDonChiTietResponsitory = new HoaDonChiTietResponsitory();
    @Override
    public boolean saveHDCT(HoaDonChiTiet hdct) {
        return HoaDonChiTietResponsitory.saveHDCT(hdct);
    }
    
}

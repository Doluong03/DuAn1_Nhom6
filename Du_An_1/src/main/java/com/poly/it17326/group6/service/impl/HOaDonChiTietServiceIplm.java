/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group6.service.impl;

import com.poly.it17326.group6.domainmodel.HoaDonChiTiet;
import com.poly.it17326.group6.repository.HoaDonChiTietResponsitory;
import com.poly.it17326.group6.response.HoaDonCTResponse;
import com.poly.it17326.group6.service.HoaDonChiTietService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hp
 */
public class HOaDonChiTietServiceIplm implements HoaDonChiTietService {

    HoaDonChiTietResponsitory HoaDonChiTietResponsitory = new HoaDonChiTietResponsitory();

    @Override
    public boolean saveHDCT(HoaDonChiTiet hdct) {
        return HoaDonChiTietResponsitory.saveHDCT(hdct);
    }

    @Override
    public List<HoaDonCTResponse> getListHDCT() {
        List<HoaDonCTResponse> listOut = new ArrayList<>();
        List<HoaDonChiTiet> listHd = HoaDonChiTietResponsitory.getListHDCT();
        for (HoaDonChiTiet hoaDonChiTiet : listHd) {
            HoaDonCTResponse hd = new HoaDonCTResponse(hoaDonChiTiet);
            listOut.add(hd);
        }
        return listOut;
    }

    @Override
    public boolean deleteSP(String ma) {
        return deleteSP(ma);
    }

}

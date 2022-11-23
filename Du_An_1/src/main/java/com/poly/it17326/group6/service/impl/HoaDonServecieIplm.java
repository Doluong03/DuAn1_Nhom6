/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group6.service.impl;

import com.poly.it17326.group6.domainmodel.HoaDon;
import com.poly.it17326.group6.repository.HoaDonRepository;
import com.poly.it17326.group6.response.HoaDonresponse;
import com.poly.it17326.group6.service.HoaDonService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hp
 */
public class HoaDonServecieIplm implements HoaDonService {

    private HoaDonRepository HoaDonRepository = new HoaDonRepository();

    @Override
    public ArrayList<HoaDonresponse> getListsHD() {
        ArrayList<HoaDon> listHD = new HoaDonRepository().getAll();
        ArrayList<HoaDonresponse> listHDRespon = new ArrayList<>();
        for (HoaDon hd : listHD) {
            HoaDonresponse hdr = new HoaDonresponse(hd);
            listHDRespon.add(hdr);
        }
        return listHDRespon;
    }

    @Override
    public ArrayList<HoaDon> getIDHD(String Ma) {
        return HoaDonRepository.getIDHD(Ma);
    }

    @Override
    public boolean updateHD(String Ma, BigDecimal tongtien, int IdTT) {
        return HoaDonRepository.updateHD(Ma, tongtien, IdTT);
    }

    public boolean addHD() {
        return HoaDonRepository.addHD();
    }

    @Override
    public ArrayList<HoaDonresponse> timKiemTT(int tt) {
        ArrayList<HoaDon> listHD = new HoaDonRepository().getSearch(tt);
        ArrayList<HoaDonresponse> listHDRespon = new ArrayList<>();
        for (HoaDon hd : listHD) {
            HoaDonresponse hdr = new HoaDonresponse(hd);
            listHDRespon.add(hdr);
        }
        return listHDRespon;
    }

    @Override
    public ArrayList<HoaDonresponse> getAllHD() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<HoaDonresponse> timKiemHD(String ma) {
        ArrayList<HoaDon> listHD = new HoaDonRepository().timKiemHD(ma);
        ArrayList<HoaDonresponse> listHDRespon = new ArrayList<>();
        for (HoaDon hd : listHD) {
            HoaDonresponse hdr = new HoaDonresponse(hd);
            listHDRespon.add(hdr);
        }
        return listHDRespon;
    }

}

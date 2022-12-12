/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group6.service.impl;

import com.poly.it17326.group6.domainmodel.HoaDon;
import com.poly.it17326.group6.domainmodel.HoaDonChiTiet;
import com.poly.it17326.group6.domainmodel.KhachHang;
import com.poly.it17326.group6.repository.HoaDonRepository;
import com.poly.it17326.group6.response.HoaDonCTResponse;
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
    public List<HoaDonresponse> getListsHD() {
        List<HoaDon> listHD = new HoaDonRepository().getAll();
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

    public boolean updateHD(String Ma, BigDecimal tongtien, int trangthai, KhachHang idKH, BigDecimal tienKH, BigDecimal tienCK, BigDecimal tienThua) {
        return HoaDonRepository.updateHD(Ma, tongtien, trangthai, idKH, tienKH, tienCK, tienThua);
    }

    @Override
    public boolean addHD(int idTK) {
        return HoaDonRepository.addHD(idTK);
    }

    @Override
    public List<HoaDonresponse> timKiemTT(int tt) {
        List<HoaDon> listHD = new HoaDonRepository().getSearch(tt);
        ArrayList<HoaDonresponse> listHDRespon = new ArrayList<>();
        for (HoaDon hd : listHD) {
            HoaDonresponse hdr = new HoaDonresponse(hd);
            listHDRespon.add(hdr);
        }
        return listHDRespon;
    }

    @Override

    public List<HoaDonresponse> timKiemHD(String ma, String sdt) {
        List<HoaDon> listHD = new HoaDonRepository().timKiemHD(ma, sdt);
        ArrayList<HoaDonresponse> listHDRespon = new ArrayList<>();
        for (HoaDon hd : listHD) {
            HoaDonresponse hdr = new HoaDonresponse(hd);
            listHDRespon.add(hdr);
        }
        return listHDRespon;
    }

    @Override
    public List<HoaDon> getALLHD() {
        return HoaDonRepository.getALLHD();
    }

    @Override
    public boolean updateVCHHD(String ma, int IdVC) {
        return HoaDonRepository.updateVCHHD(ma, IdVC);
    }

    @Override
    public boolean updateTTHD(String Ma, int trangthai, String lyDo) {
        return HoaDonRepository.updateTTHD(Ma, trangthai, lyDo);
    }

    @Override
    public List<HoaDonCTResponse> getListHDCT(String mahd) {
        List<HoaDonCTResponse> listOut = new ArrayList<>();
        List<HoaDonChiTiet> listHd = HoaDonRepository.getListHDCT(mahd);
        for (HoaDonChiTiet hoaDonChiTiet : listHd) {
            HoaDonCTResponse hd = new HoaDonCTResponse(hoaDonChiTiet);
            listOut.add(hd);
        }
        return listOut;
    }

    @Override
    public List<HoaDonresponse> getListsHD_day() {
        List<HoaDon> listHD = new HoaDonRepository().getAllDay();
        ArrayList<HoaDonresponse> listHDRespon = new ArrayList<>();
        for (HoaDon hd : listHD) {
            HoaDonresponse hdr = new HoaDonresponse(hd);
            listHDRespon.add(hdr);
        }
        return listHDRespon;
    }

    @Override
    public List<HoaDonresponse> timKiemTT_all(int tt) {
        List<HoaDon> listHD = new HoaDonRepository().getSearch_all(tt);
        ArrayList<HoaDonresponse> listHDRespon = new ArrayList<>();
        for (HoaDon hd : listHD) {
            HoaDonresponse hdr = new HoaDonresponse(hd);
            listHDRespon.add(hdr);
        }
        return listHDRespon;
    }

    @Override
    public List<HoaDonresponse> getListsHD_HTBH(int ht) {
        List<HoaDon> listHD = new HoaDonRepository().getAll_HTBH(ht);
        ArrayList<HoaDonresponse> listHDRespon = new ArrayList<>();
        for (HoaDon hd : listHD) {
            HoaDonresponse hdr = new HoaDonresponse(hd);
            listHDRespon.add(hdr);
        }
        return listHDRespon;
    }
    
    public static void main(String[] args) {
        HoaDonServecieIplm x= new HoaDonServecieIplm();
        for (HoaDonresponse hoaDon : x.getListsHD()) {
            System.out.println(hoaDon);
        }
    }
}

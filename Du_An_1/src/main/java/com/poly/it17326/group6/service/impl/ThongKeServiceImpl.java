/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group6.service.impl;

import com.poly.it17326.group6.repository.ThongKeRepository;
import com.poly.it17326.group6.service.ThongKeService;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author 123
 */
public class ThongKeServiceImpl implements ThongKeService {

    private ThongKeRepository tkr = new ThongKeRepository();

    @Override
    public List<BigDecimal> getListDtThang(String year, String Month) {
        return tkr.getListDtThang(year, Month);
    }

    @Override
    public List<BigDecimal> getListDtNam(String nam) {
        return tkr.getListDtNam(nam);
    }

    @Override
    public List<BigDecimal> getListDtNgay(String createAt) {
        return tkr.getListDtNgay(createAt);
    }

    @Override
    public List<Integer> getListHdThanhcongNgay(String createAt) {
        return tkr.getListHdThanhcongNgay(createAt);
    }

    @Override
    public List<Integer> getListHDDAHUYNgay(String createAt) {
        return tkr.getListHDDAHUYNgay(createAt);
    }

    @Override
    public List<Integer> getListHDDahuyThang(String year, String Montht) {
        return tkr.getListHDDahuyThang(year, Montht);
    }

    @Override
    public List<Integer> getListHDTAHNHCONGTHANg(String year, String Month) {
        return tkr.getListHDTAHNHCONGTHANg(year, Month);
    }

    @Override
    public List<Integer> getListHDDAHUYnam(String nam) {
        return tkr.getListHDDAHUYnam(nam);
    }

    @Override
    public List<Integer> getListHDthanhcongNam(String nam) {
        return tkr.getListHDthanhcongNam(nam);
    }

    @Override
    public List<Double> getListDtThangs(String year, String Month) {
        return tkr.getListDtThangs(year, Month);
    }

   

}

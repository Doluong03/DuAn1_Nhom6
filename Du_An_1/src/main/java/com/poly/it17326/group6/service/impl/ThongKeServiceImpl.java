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
    public List<BigDecimal> doanhThu(Date begin, Date end) {
        return tkr.getListDtNgay(begin, end);
    }

    @Override
    public List<Long> hoaDon(Date begin, Date end) {
        return tkr.getListHdNgay(begin, end);
    }

}

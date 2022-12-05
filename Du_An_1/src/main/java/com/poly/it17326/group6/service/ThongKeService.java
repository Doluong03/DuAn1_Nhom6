/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.poly.it17326.group6.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author 123
 */
public interface ThongKeService {

    public List<BigDecimal> getListDtThang(String year, String Month);

    public List<BigDecimal> getListDtNam(String nam);

    public List<BigDecimal> getListDtNgay(String createAt);

    public List<Integer> getListHdThanhcongNgay(String createAt);

    public List<Integer> getListHDDAHUYNgay(String createAt);

    public List<Integer> getListHDDahuyThang(String year, String Montht);

    public List<Integer> getListHDTAHNHCONGTHANg(String year, String Month);

    public List<Integer> getListHDDAHUYnam(String nam);

    public List<Integer> getListHDthanhcongNam(String nam);

      public List<Double> getListDtThangs(String year, String Month);
}

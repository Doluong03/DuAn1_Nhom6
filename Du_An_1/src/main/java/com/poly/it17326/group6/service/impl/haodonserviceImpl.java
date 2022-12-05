/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group6.service.impl;

import com.poly.it17326.group6.domainmodel.HaoDonChitietdomain;
import com.poly.it17326.group6.repository.HaodonchitietRespontory;
import com.poly.it17326.group6.service.haodonservice;
import java.util.List;

/**
 *
 * @author Hp
 */
public class haodonserviceImpl implements haodonservice{
    HaodonchitietRespontory s = new HaodonchitietRespontory();
    @Override
    public List<HaoDonChitietdomain> getlistTehongay(String bd, String end) {
        return s.getlistTehongay(bd, end);
    }

    @Override
    public List<HaoDonChitietdomain> getlistALLHDCT() {
        return s.getlistALLHDCT();
    }
    
}

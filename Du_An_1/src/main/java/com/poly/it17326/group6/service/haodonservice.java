/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.poly.it17326.group6.service;

import com.poly.it17326.group6.domainmodel.HaoDonChitietdomain;
import java.util.List;

/**
 *
 * @author Hp
 */
public interface haodonservice {

    public List<HaoDonChitietdomain> getlistTehongay(String bd, String end);

    public List<HaoDonChitietdomain> getlistALLHDCT();
}

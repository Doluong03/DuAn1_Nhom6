/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.poly.it17326.group6.service;

import com.poly.it17326.group6.domainmodel.HoaDon;
import com.poly.it17326.group6.response.HoaDonresponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hp
 */
public interface HoaDonService {

    public ArrayList<HoaDonresponse> getListsHD();

    public ArrayList<HoaDon> getIDHD(String Ma);
    
     public boolean  updateHD(String Ma, BigDecimal tongtien,int trangthai);
     

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.poly.it17326.group6.service;

import com.poly.it17326.group6.domainmodel.HoaDon;

import com.poly.it17326.group6.repository.HoaDonRepository;

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

    public boolean updateHD(String Ma, BigDecimal tongtien, int trangthai, String tenKH, String sdt);

    public boolean addHD(int idTk);

    public ArrayList<HoaDonresponse> timKiemTT(int tt);

    public ArrayList<HoaDonresponse> timKiemHD(String ma, String sdt);

    public List<HoaDon> getALLHD();

    public boolean updateVCHHD(String ma, int IdVC);
}

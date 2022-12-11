/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.poly.it17326.group6.service;

import com.poly.it17326.group6.config.HibernateConfig;
import com.poly.it17326.group6.domainmodel.ChitietKhuyenMai;
import com.poly.it17326.group6.domainmodel.khuyenmai;
import com.poly.it17326.group6.response.KhuyenMaiResponse;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.Query;
import org.hibernate.Session;

/**
 *
 * @author Hp
 */
public interface khuyenmaiService {

    public ArrayList<khuyenmai> getALL();

    public ArrayList<khuyenmai> FindKM(String ma);

    public ArrayList<KhuyenMaiResponse> getALLSPKM();

    public ArrayList<khuyenmai> FindTT(int trangthai);

    public ArrayList<KhuyenMaiResponse> FindCTKM(int ma);

    public boolean AddKM(khuyenmai km);

    public boolean AddCTKM(ChitietKhuyenMai ctkm);

    public boolean UpdateTT(int id);

    
}

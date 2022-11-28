/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group6.service.impl;

import com.poly.it17326.group6.domainmodel.NSX;
import com.poly.it17326.group6.repository.NSXRespository;
import com.poly.it17326.group6.service.NSXService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bachc
 */
public class NSXServiceImpl implements NSXService {

    private NSXRespository repo = new NSXRespository();

    @Override
    public ArrayList<NSX> getAll() {
        return repo.getAll();

    }

    @Override
    public Boolean addNSX(NSX nsx) {
        return repo.addNSX(nsx);
    }

    @Override
    public NSX updateNSX(NSX nsx) {
        return repo.updateNSX(nsx);
    }

    @Override
    public Boolean delete(String ma) {
        return repo.deleteNSX(ma);
    }

}

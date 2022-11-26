/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group6.service.impl;

import com.poly.it17326.group6.domainmodel.NSX;
import com.poly.it17326.group6.repository.NSXRespository;
import com.poly.it17326.group6.response.NSXResponse;
import com.poly.it17326.group6.service.NSXService;
import com.poly.it17326.group6.view.DialogNsx;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bachc
 */
public class NSXServiceImpl implements NSXService{
private NSXRespository repo=new NSXRespository();
    @Override
    public List<NSXResponse> getAll() {
        List<NSXResponse> response=new ArrayList<>();
        List<NSX> listNSX=repo.getAll();
        for (NSX nsx : listNSX) {
            NSXResponse NSXRepose=new NSXResponse(nsx);
            response.add(NSXRepose);
        }
        return response;
    }

    @Override
    public Boolean addNSX(NSX nsx) {
       return repo.addNSX(nsx);
    }
    @Override
    public Boolean updateNSX(NSX nsx) {
        return repo.updateNSX(nsx);
    }

    @Override
    public Boolean delete(String ma) {
        return repo.deleteNSX(ma);
    }
   
}

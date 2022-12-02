/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group6.service.impl;

import com.poly.it17326.group6.domainmodel.ChucVu;
import com.poly.it17326.group6.repository.ChucVuReponsitory;
import com.poly.it17326.group6.service.ChucVuService;
import java.util.ArrayList;

/**
 *
 * @author bachc
 */
public class ChucVuServiceImpl implements ChucVuService{
private ChucVuReponsitory repo=new ChucVuReponsitory();
    @Override
    public ArrayList<ChucVu> getAll() {
          return repo.getAll();
    }

    @Override
    public Boolean addCV(ChucVu cv) {
          return repo.addChucVu(cv);
    }

    @Override
    public ChucVu updateCV(ChucVu cv) {
        return repo.updateCV(cv);
    }

    @Override
    public Boolean delete(String ma) {
        return repo.deleteCV(ma);
    }
    
}

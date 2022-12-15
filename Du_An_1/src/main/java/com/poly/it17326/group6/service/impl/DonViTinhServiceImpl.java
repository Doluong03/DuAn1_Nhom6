/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group6.service.impl;

import com.poly.it17326.group6.domainmodel.DonViTinh;
import com.poly.it17326.group6.domainmodel.KhoiLuong;
import com.poly.it17326.group6.repository.AnhRepository;
import com.poly.it17326.group6.repository.DonViTinhRepository;
import com.poly.it17326.group6.response.KhoiLuongResponse;
import com.poly.it17326.group6.service.DonViTinhService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 123
 */
public class DonViTinhServiceImpl implements DonViTinhService{
     private DonViTinhRepository anhRepository = new DonViTinhRepository();

    @Override
    public List<DonViTinh> getAll() {
        return anhRepository.getAll();
    }

    @Override
    public boolean Them(DonViTinh anh) {
        return anhRepository.addAnh(anh);
    }

    @Override
    public boolean xoa(String Ma) {
        return anhRepository.XoaAnh(Ma);
    }

    @Override
    public boolean sua(String Ma, String Ten) {
        return anhRepository.updateAnh(Ma, Ten);
    }
}

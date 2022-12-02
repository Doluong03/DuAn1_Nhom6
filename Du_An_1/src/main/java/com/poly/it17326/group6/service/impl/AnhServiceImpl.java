/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group6.service.impl;

import com.poly.it17326.group6.domainmodel.Anh;
import com.poly.it17326.group6.domainmodel.ChiTietSP;
import com.poly.it17326.group6.response.AnhResponse;
import com.poly.it17326.group6.service.AnhService;
import java.util.List;
import com.poly.it17326.group6.repository.AnhRepository;
import com.poly.it17326.group6.response.ChiTietSpResponse;
import java.util.ArrayList;

/**
 *
 * @author OS
 */
public class AnhServiceImpl implements AnhService {

    private AnhRepository anhRepository = new AnhRepository();

    @Override
    public List<AnhResponse> getAll() {
        List<Anh> listA = anhRepository.getAll();
        List<AnhResponse> listARP = new ArrayList<>();
        for (Anh anh : listA) {
            AnhResponse anhResponse = new AnhResponse(anh);
            listARP.add(anhResponse);
        }
        return listARP;
    }

    @Override
    public boolean Them(Anh anh) {
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group6.service.impl;

import com.poly.it17326.group6.domainmodel.LoaiSP;
import com.poly.it17326.group6.repository.LoaiSanPhamResponsitory;
import com.poly.it17326.group6.response.LoaiSanPhamResponse;
import com.poly.it17326.group6.service.LoaiSanPhamService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bachc
 */
public class LoaiSanPhamServiceImpl implements LoaiSanPhamService{
private LoaiSanPhamResponsitory repo =new LoaiSanPhamResponsitory();
    @Override
    public List<LoaiSanPhamResponse> getAll() {
          List<LoaiSanPhamResponse> response=new ArrayList<>();
        List<LoaiSP> listLSP=repo.getAll();
        for (LoaiSP lsp : listLSP) {
            LoaiSanPhamResponse LSPRepose=new LoaiSanPhamResponse(lsp);
            response.add(LSPRepose);
        }
        return response;
    }

    @Override
    public Boolean addNSX(LoaiSP lsp) {
        return repo.addLSP(lsp);
    }

    @Override
    public Boolean updateNSX(LoaiSP lsp) {
        return repo.updateLSP(lsp);
    }

    @Override
    public Boolean delete(LoaiSP lsp) {
        return repo.deleteLSP(lsp);
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.poly.it17326.group6.service;

import com.poly.it17326.group6.domainmodel.LoaiSP;
import com.poly.it17326.group6.response.LoaiSanPhamResponse;
import java.util.List;

/**
 *
 * @author bachc
 */
public interface LoaiSanPhamService {
     List<LoaiSanPhamResponse> getAll();
    Boolean addNSX(LoaiSP lsp);
    Boolean updateNSX(LoaiSP lsp);
    Boolean delete(LoaiSP lsp);
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group6.service.impl;

import com.poly.it17326.group6.domainmodel.KhoiLuong;
import com.poly.it17326.group6.domainmodel.ChiTietSP;
import com.poly.it17326.group6.domainmodel.LoaiSP;
import com.poly.it17326.group6.domainmodel.NSX;
import com.poly.it17326.group6.domainmodel.SanPham;
import com.poly.it17326.group6.repository.ChiTietSpRepository;
import com.poly.it17326.group6.repository.SanPhamRepository;
import com.poly.it17326.group6.response.ChiTietSpResponse_2;
import com.poly.it17326.group6.service.SanPhamService;
import java.util.List;

/**
 *
 * @author DUC-DU
 */
public class SanPhamServiceIplm implements SanPhamService {

    private SanPhamRepository sanPhamRepository = new SanPhamRepository();
    private ChiTietSpRepository chiTietSpRepository = new ChiTietSpRepository();

    @Override
    public List<SanPham> getAll() {
        return sanPhamRepository.getAll();
    }

    @Override
    public Boolean them(SanPham sanPham) {
        return sanPhamRepository.add(sanPham);
    }

    @Override
    public Boolean sua(SanPham sanPham) {
        return sanPhamRepository.update(sanPham);
    }

    @Override
    public Boolean xoa(SanPham sanPham) {
        return sanPhamRepository.delete(sanPham);
    }

    @Override
    public Boolean save(SanPham sanPham, ChiTietSP ctsp) {
        return sanPhamRepository.save(sanPham, ctsp);
    }

    @Override
    public Boolean save2(SanPham sanPham, ChiTietSpResponse_2 ctsp) {
        ChiTietSP ct = new ChiTietSP();
        for (LoaiSP lsp : chiTietSpRepository.getListLSP()) {
            if (lsp.getTen().equals(ctsp.getLoaiSP())) {
                ct.setLoaiSP(lsp);
            }
        }
        for (NSX nsx : chiTietSpRepository.getListNsx()) {
            if (nsx.getTen().equals(ctsp.getNsx())) {
                ct.setNsx(nsx);
            }
        }
        for (KhoiLuong a : chiTietSpRepository.getListAnh()) {
            if (a.getTen().equals(ctsp.getAnh())) {
                ct.setKhoiLuong(a);
            }
        }
        ct.setHsd(ctsp.getHsd());
        ct.setDonGia(ctsp.getDonGia());
        ct.setSoLuongTon(ctsp.getSoLuongTon());
        ct.setId(ctsp.getId());
        ChiTietSpResponse_2 ctr = new ChiTietSpResponse_2(ct);
        return sanPhamRepository.save(sanPham, ct);
    }

}

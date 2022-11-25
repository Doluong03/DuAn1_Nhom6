/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group6.service.impl;

import com.poly.it17326.group6.domainmodel.Anh;
import com.poly.it17326.group6.domainmodel.ChiTietSP;
import com.poly.it17326.group6.domainmodel.LoaiSP;
import com.poly.it17326.group6.domainmodel.NSX;
import com.poly.it17326.group6.domainmodel.SanPham;
import com.poly.it17326.group6.repository.ChiTietSpRepository;
import com.poly.it17326.group6.response.ChiTietSpResponse;
import com.poly.it17326.group6.response.ChiTietSpResponse_2;
import com.poly.it17326.group6.service.ChiTietSPService;
import java.util.ArrayList;

/**
 *
 * @author 123
 */
public class ChiTietSPServiceImpl implements ChiTietSPService {

    private ChiTietSpRepository chiTietSpRepository = new ChiTietSpRepository();

    @Override
    public ArrayList<ChiTietSpResponse> getAll() {
        ArrayList<ChiTietSP> listCt = chiTietSpRepository.getAll();
        ArrayList<ChiTietSpResponse> listCTR = new ArrayList<>();
        for (ChiTietSP chiTietSP : listCt) {
            ChiTietSpResponse ctr = new ChiTietSpResponse(chiTietSP);
            listCTR.add(ctr);
        }
        return listCTR;
    }

    @Override
    public ArrayList<ChiTietSpResponse> getTimKiem(String ma) {
        ArrayList<ChiTietSpResponse> listCTrespon = new ArrayList<>();
        ChiTietSP c = chiTietSpRepository.getTimKiem(ma);
        ChiTietSpResponse ctr = new ChiTietSpResponse(c);
        listCTrespon.add(ctr);
        return listCTrespon;
    }

    @Override
    public ArrayList<ChiTietSpResponse_2> getTimKiemFSP(String ma) {
        ArrayList<ChiTietSpResponse_2> listCTrespon = new ArrayList<>();
        ChiTietSP c = chiTietSpRepository.getTimKiem(ma);
        ChiTietSpResponse_2 ctr = new ChiTietSpResponse_2(c);
        listCTrespon.add(ctr);
        return listCTrespon;
    }

    @Override
    public ArrayList<ChiTietSpResponse_2> getAllFSP() {
        ArrayList<ChiTietSP> listCt = chiTietSpRepository.getAll();
        ArrayList<ChiTietSpResponse_2> listCTR = new ArrayList<>();
        for (ChiTietSP chiTietSP : listCt) {
            ChiTietSpResponse_2 ctr = new ChiTietSpResponse_2(chiTietSP);
            listCTR.add(ctr);
        }
        return listCTR;
    }

    @Override
    public ArrayList<ChiTietSpResponse_2> getTimKiemLsp(String ten) {
        ArrayList<ChiTietSpResponse_2> listCTrespon = new ArrayList<>();
        ChiTietSP c = chiTietSpRepository.getTimKiem(ten);
        ChiTietSpResponse_2 ctr = new ChiTietSpResponse_2(c);
        listCTrespon.add(ctr);
        return listCTrespon;
    }

    @Override
    public ArrayList<SanPham> getListSp() {
        return chiTietSpRepository.getListSP();
    }

    @Override
    public ChiTietSpResponse_2 addSP(ChiTietSpResponse_2 ctsp) {
        ctsp.setId(0);
        int check = 0;
        ChiTietSP ct = new ChiTietSP();
        for (SanPham sanPham : chiTietSpRepository.getListSP()) {
            if (sanPham.getTen().equals(ctsp.getTen())) {
                ct.setSanPham(sanPham);
            }
        }
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
        for (Anh a : chiTietSpRepository.getListAnh()) {
            if (a.getTen().equals(ctsp.getAnh())) {
                ct.setAnh(a);
            }
        }
        ct.setHsd(ctsp.getHsd());
        ct.setDonGia(ctsp.getDonGia());
        ct.setSoLuongTon(ctsp.getSoLuongTon());
        ct.setId(ctsp.getId());
        ChiTietSpResponse_2 ctr = new ChiTietSpResponse_2(ct);
        chiTietSpRepository.addSP(ct);
        return ctr;
    }

    @Override
    public ArrayList<NSX> getListNsx() {
        return chiTietSpRepository.getListNsx();
    }

    @Override
    public ArrayList<LoaiSP> getListLSp() {
        return chiTietSpRepository.getListLSP();
    }

    @Override
    public ArrayList<Anh> getListA() {
        return chiTietSpRepository.getListAnh();
    }

    @Override
    public ChiTietSpResponse_2 updateSP(ChiTietSpResponse_2 ctsp) {
        ChiTietSP ct = new ChiTietSP();
        for (SanPham sanPham : chiTietSpRepository.getListSP()) {
            if (sanPham.getTen().equals(ctsp.getTen())) {
                ct.setSanPham(sanPham);
            }
        }
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
        for (Anh a : chiTietSpRepository.getListAnh()) {
            if (a.getTen().equals(ctsp.getAnh())) {
                ct.setAnh(a);
            }
        }
        ct.setHsd(ctsp.getHsd());
        ct.setDonGia(ctsp.getDonGia());
        ct.setSoLuongTon(ctsp.getSoLuongTon());
        ct.setId(ctsp.getId());
        ChiTietSpResponse_2 ctr = new ChiTietSpResponse_2(ct);
        chiTietSpRepository.updateSP(ct);
        return ctr;
    }
}

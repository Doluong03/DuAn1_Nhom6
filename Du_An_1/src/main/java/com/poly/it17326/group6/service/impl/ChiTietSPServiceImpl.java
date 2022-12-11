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
import java.util.List;

/**
 *
 * @author 123
 */
public class ChiTietSPServiceImpl implements ChiTietSPService {

    private ChiTietSpRepository chiTietSpRepository = new ChiTietSpRepository();

    @Override
    public List<ChiTietSpResponse> getAll() {
        List<ChiTietSP> listCt = chiTietSpRepository.getAll();
        List<ChiTietSpResponse> listCTR = new ArrayList<>();
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
    public List<ChiTietSpResponse_2> getAllFSP() {
        List<ChiTietSP> listCt = chiTietSpRepository.getAll();
        List<ChiTietSpResponse_2> listCTR = new ArrayList<>();
        for (ChiTietSP chiTietSP : listCt) {
            ChiTietSpResponse_2 ctr = new ChiTietSpResponse_2(chiTietSP);
            listCTR.add(ctr);
        }
        return listCTR;
    }

//    public static void main(String[] args) {
//        ChiTietSPServiceImpl c= new ChiTietSPServiceImpl();
//        for (ChiTietSpResponse_2 chiString : c.getAllFSP()) {
//            System.out.println(chiString);
//        }
//    }
    @Override
    public List<ChiTietSpResponse_2> getTimKiemLsp(String ten) {
        ArrayList<ChiTietSpResponse_2> listCTrespon = new ArrayList<>();
        List<ChiTietSP> c = chiTietSpRepository.getTimKiemLsp(ten);
        for (ChiTietSP chiTietSP : c) {
            ChiTietSpResponse_2 ctr = new ChiTietSpResponse_2(chiTietSP);
            listCTrespon.add(ctr);
        }
        return listCTrespon;
    }

    @Override
    public List<SanPham> getListSp() {
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
    public List<NSX> getListNsx() {
        return chiTietSpRepository.getListNsx();
    }

    @Override
    public List<LoaiSP> getListLSp() {
        return chiTietSpRepository.getListLSP();
    }

    @Override
    public List<Anh> getListA() {
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

    @Override
    public boolean updateSoLuong( int sl,int id) {
        return  chiTietSpRepository.updateSL(sl,id);
    }
    
     @Override
    public List<ChiTietSpResponse> getTimKiem2(String ma) {
        ArrayList<ChiTietSpResponse> listCTrespon = new ArrayList<>();
        List<ChiTietSP> c = chiTietSpRepository.getTimKiem2(ma);
        for (ChiTietSP chiTietSP : c) {
            ChiTietSpResponse ctr = new ChiTietSpResponse(chiTietSP);
            listCTrespon.add(ctr);
        }
        return listCTrespon;
    }
}

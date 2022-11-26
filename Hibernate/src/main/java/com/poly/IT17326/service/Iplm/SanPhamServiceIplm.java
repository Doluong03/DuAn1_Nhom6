/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.IT17326.service.Iplm;

import com.poly.IT17326.domainmodel.SanPham;
import com.poly.IT17326.repository.SanPhamRepository;
import com.poly.IT17326.service.SanPhamService;
import java.util.List;

/**
 *
 * @author DUC-DU
 */
public class SanPhamServiceIplm implements SanPhamService {

    private SanPhamRepository sanPhamRepository = new SanPhamRepository();

    @Override
    public List<SanPham> getAll() {
        return sanPhamRepository.getAll();
    }

    @Override
    public Boolean them(SanPham sanPham) {
        return sanPhamRepository.add(sanPham);
    }

    @Override
    public Boolean sua( SanPham sanPham) {
        return sanPhamRepository.update(sanPham);
    }

    @Override
    public Boolean xoa(SanPham sanPham) {
        return sanPhamRepository.delete(sanPham);
    }

}

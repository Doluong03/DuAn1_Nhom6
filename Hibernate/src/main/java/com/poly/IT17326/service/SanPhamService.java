/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.poly.IT17326.service;

import com.poly.IT17326.domainmodel.SanPham;
import java.util.List;

/**
 *
 * @author DUC-DU
 */
public interface SanPhamService {

    List<SanPham> getAll();

    Boolean them(SanPham sanPham);

    Boolean sua( SanPham sanPham);

    Boolean xoa(SanPham sanPham);
}

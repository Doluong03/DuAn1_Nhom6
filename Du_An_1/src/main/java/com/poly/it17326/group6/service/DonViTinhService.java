/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.poly.it17326.group6.service;

import com.poly.it17326.group6.domainmodel.DonViTinh;
import com.poly.it17326.group6.domainmodel.KhoiLuong;
import com.poly.it17326.group6.response.KhoiLuongResponse;
import java.util.List;

/**
 *
 * @author 123
 */
public interface DonViTinhService {

    public List<DonViTinh> getAll();

    public boolean Them(DonViTinh anh);

    public boolean xoa(String Ma);

    public boolean sua(String Ma, String Ten);
}

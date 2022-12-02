/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.impl;

import com.mycompany.domainmodel.TaiKhoan;
import com.mycompany.repository.TaiKhoanRepository;
import com.mycompany.respone.ViewTaiKhoanReponse;
import com.mycompany.service.ViewTaiKhoanService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author TRAN VAN HUY
 */
public class ViewTaiKhoanServiceImpl implements ViewTaiKhoanService{
    
    private TaiKhoanRepository taiKhoanRepository = new TaiKhoanRepository();

    @Override
    public List<TaiKhoan> getAll() {
        return taiKhoanRepository.getAll();
    }

    @Override
    public Boolean them(TaiKhoan taiKhoan) {
        return taiKhoanRepository.add(taiKhoan);
    }

    @Override
    public Boolean sua(TaiKhoan taiKhoan) {
        return taiKhoanRepository.update(taiKhoan);
    }

    @Override
    public Boolean xoa(TaiKhoan taiKhoan) {
        return taiKhoanRepository.delete(taiKhoan);
    }
  
    
}

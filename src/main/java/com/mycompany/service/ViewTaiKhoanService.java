/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.service;

import com.mycompany.domainmodel.TaiKhoan;
import com.mycompany.respone.ViewTaiKhoanReponse;
import java.util.List;

/**
 *
 * @author TRAN VAN HUY
 */
public interface ViewTaiKhoanService {
    List<TaiKhoan> getAll();
    Boolean them(TaiKhoan taiKhoan);

    Boolean sua( TaiKhoan taiKhoan);

    Boolean xoa(TaiKhoan taiKhoan);
}

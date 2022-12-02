/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.respone;

import com.github.lgooddatepicker.components.DatePicker;
import com.mycompany.domainmodel.TaiKhoan;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author TRAN VAN HUY
 */
@Getter
@Setter
@ToString
public class ViewTaiKhoanReponse {
    private Long ID;
    private String Ma;
    private String HoTen;
    private String GioiTinh;
    private DatePicker NgaySinh;
    private String DiaChi;
    private String SDT;
    private String MatKhau;
    private String Email;
    private String IdCv;
    private Date Create_at;
    private String update_at;
    private long Delete;

    public ViewTaiKhoanReponse() {
    }

    public ViewTaiKhoanReponse(Long ID, String Ma, String HoTen, String GioiTinh, DatePicker NgaySinh, String DiaChi, String SDT, String MatKhau, String Email, String IdCv, Date Create_at, String update_at, long Delete) {
        this.ID = ID;
        this.Ma = Ma;
        this.HoTen = HoTen;
        this.GioiTinh = GioiTinh;
        this.NgaySinh = NgaySinh;
        this.DiaChi = DiaChi;
        this.SDT = SDT;
        this.MatKhau = MatKhau;
        this.Email = Email;
        this.IdCv = IdCv;
        this.Create_at = Create_at;
        this.update_at = update_at;
        this.Delete = Delete;
    }

   
    
    

}

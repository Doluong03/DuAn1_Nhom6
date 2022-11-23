/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group6.response;

import com.poly.it17326.group6.domainmodel.HoaDon;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class HoaDonresponse {
    private String id;
    private String maHD;
    private Date ngayTao;
    private String tenNV;
    private int tinhtrang;
    private String tenKH;
    private String sdt;

    public HoaDonresponse() {
    }

    public HoaDonresponse(HoaDon hd) {
        this.id = hd.getId();
        this.maHD = hd.getMaHD();
        this.ngayTao = hd.getCreateAt();
        this.tenNV = hd.getTaiKhoan().getHoten();
        this.tinhtrang = hd.getTinhTrang().getTrangThai();
        this.tenKH = hd.getHoTenkh();
        this.sdt = hd.getSdt();
    }
    
    public String trangThai(){
        if(tinhtrang==1){
            return "Đã thanh toán";
        }else if(tinhtrang==0){
            return "chờ thanh toán";
        }
        return null;
    }
    
//    
//    public Object[] toAddrow(){
//      return new Object[]{id,maHD,ngayTao,sdt,tenKH,tenNV,trangThai()};
//    }
    
    
}
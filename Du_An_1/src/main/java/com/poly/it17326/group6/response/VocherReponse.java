/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group6.response;

import com.poly.it17326.group6.domainmodel.Voucher;
import java.util.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class VocherReponse {
    private String tenVCh;
    private String maVCh;
    private int soLuong;
    private int trangThai;
    private float phanTram;
    private Date ngayBatdau;
    private Date ngayKetThuc;
   private int idvch;
    public VocherReponse(Voucher voucher) {
        this.tenVCh = voucher.getTen();
        this.soLuong = voucher.getSoLuong();
        this.trangThai = voucher.getTrangThai();
        this.phanTram = voucher.getPhanTram();
        this.ngayBatdau = voucher.getNgayApDung();
        this.ngayKetThuc = voucher.getNgayKetThuc();
        this.maVCh = voucher.getMa();
        this.idvch = voucher.getId();
    }
    
    public String trangThai(){
         if(trangThai==0){
             return "Da het han";
         }else if(trangThai==1){
             return "Dang hoat dong";
         }
         return null;
    }
    
}

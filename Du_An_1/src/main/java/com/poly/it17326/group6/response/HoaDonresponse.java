/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group6.response;

import com.poly.it17326.group6.domainmodel.HoaDon;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class HoaDonresponse {

    private int id;

    private String maHD;
    private Date ngayTao;
    private String tenNV;
    private int tinhtrang;
    private String tenKH;
    private String sdt;
    private String maKH;
    private String tenNN;
    private String sdtNN;
    private String tenNS;
    private String sdtNS;
    private BigDecimal tienS;
    private BigDecimal tienKH;
    private BigDecimal tienCK;
    private BigDecimal tienThua;
    private String diaChi;
    private int httt;
    private int rank;
    private BigDecimal tongTien;

    public HoaDonresponse() {
    }

    public HoaDonresponse(HoaDon hd) {
        this.id = hd.getId();
        this.maHD = hd.getMaHD();
        this.ngayTao = hd.getCreateAt();
        this.tenNV = hd.getTaiKhoan().getHoTenNV();
        this.tinhtrang = hd.getTrangThai();
        this.maKH = hd.getKhachHang().getMa();
        this.tenKH = hd.getKhachHang().getTen();
        this.sdt = hd.getKhachHang().getSdt();
        this.tenNN = hd.getTenNguoiNhan();
        this.tenNS = hd.getTenNguoiShip();
        this.sdtNN = hd.getSdtNguoiNhan();
        this.sdtNS = hd.getSdtNguoiShip();
        this.tienS = hd.getTienShip();
        this.tienKH = hd.getTienKhachDua();
        this.tienCK = hd.getTienChuyenKhoan();
        this.tienThua = hd.getTienThua();
        this.diaChi = hd.getDiaChi();
        this.httt = hd.getHinhThucTT();
        this.rank = hd.getKhachHang().getRank();
        this.tongTien = hd.getTongTien();
    }

    public String trangThai() {
        if (tinhtrang == 1) {
            return "Đã thanh toán";
        } else if (tinhtrang == 0) {
            return "Chờ thanh toán";
        } else if (tinhtrang == 2) {
            return "Đã hủy";
        } else if (tinhtrang == 3) {
            return "Chờ giao hàng";
        } else if (tinhtrang == 4) {
            return "Đang giao hàng";
        } else {
            return "Đã giao hàng";
        }
    }

//    
//    public Object[] toAddrow(){
//      return new Object[]{id,maHD,ngayTao,sdt,tenKH,tenNV,trangThai()};
//    }
}

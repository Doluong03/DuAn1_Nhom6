/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group6.response;

import com.poly.it17326.group6.domainmodel.HoaDon;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.criterion.Projections;
import org.hibernate.query.criteria.internal.expression.function.AggregationFunction;

/**
 *
 * @author 123
 */
@Getter
@Setter
public class DoanhThureponse {
    
    private BigDecimal tongDT;
    private int soHD;

    public DoanhThureponse(HoaDon hd) {
        BigDecimal dt= null;
        dt=dt.add(hd.getTongTien());       
        this.tongDT = dt;
        this.soHD = soHD;
    }

    public DoanhThureponse() {
    }
    
    
}

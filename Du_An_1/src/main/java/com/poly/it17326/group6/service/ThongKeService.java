/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.poly.it17326.group6.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author 123
 */
public interface ThongKeService {
    
    
    public List<BigDecimal> doanhThu(Date begin, Date end);
    
    public List<Long> hoaDon(Date begin, Date end);
}

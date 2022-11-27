/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.poly.it17326.group6.service;

import com.poly.it17326.group6.domainmodel.NSX;
import com.poly.it17326.group6.response.NSXResponse;
import java.util.List;

/**
 *
 * @author bachc
 */
public interface NSXService {
    List<NSXResponse> getAll();
    Boolean addNSX(NSX nsx);
    Boolean updateNSX(NSX nsx);
    Boolean delete(String ma);
    
}

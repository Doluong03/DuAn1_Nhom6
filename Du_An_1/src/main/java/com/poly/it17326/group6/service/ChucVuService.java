/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.poly.it17326.group6.service;

import com.poly.it17326.group6.domainmodel.ChucVu;
import java.util.ArrayList;

/**
 *
 * @author bachc
 */
public interface ChucVuService {
    ArrayList<ChucVu> getAll();
    Boolean addCV(ChucVu cv);
    ChucVu updateCV(ChucVu cv);
    Boolean delete(String ma);
}

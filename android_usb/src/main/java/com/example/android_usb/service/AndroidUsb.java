package com.example.android_usb.service;

import com.example.android_usb.model.AndroidZD;

import java.util.List;

public interface AndroidUsb {

    List<AndroidZD> find(int tag) throws Exception;
    boolean getAvi(int s);
    boolean getImg();
//    boolean pullPhonePhoto(String path,String place);
}

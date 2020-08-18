package com.example.android_usb.controller;

import com.example.android_usb.model.AndroidZD;
import com.example.android_usb.service.AndroidUsb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class AndroidController {

    @Autowired
    AndroidUsb adu;

    @RequestMapping(value = "/findAllAndroid",method = RequestMethod.GET)
    @ResponseBody
    public List<AndroidZD> getAllAndroid(int tag) throws Exception {
        return adu.find(tag);
    }

    @RequestMapping(value = "/getAvi",method = RequestMethod.GET)
    @ResponseBody
    public boolean getAvi(int s) throws Exception {
        return adu.getAvi(s);
    }
    @RequestMapping(value = "/getImg",method = RequestMethod.GET)
    @ResponseBody
    public boolean getImg() throws Exception {
        return adu.getImg();
    }

}

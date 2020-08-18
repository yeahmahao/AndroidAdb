package com.example.android_usb.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AndroidZD {
    private String androidName;
    private String deviceProduct;
    private String deviceModel;
    private String transportId;
    private String xID;
}

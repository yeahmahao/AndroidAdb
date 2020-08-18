package com.example.android_usb.serviceimpl;

import com.example.android_usb.model.AndroidZD;
import com.example.android_usb.service.AndroidUsb;
import lombok.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AndroidUsbImpl implements AndroidUsb {

    private String cmdPath = "cmd /c D:/sdk/platform-tools/adb.exe ";
    @Override
    public List<AndroidZD> find(int tag) throws Exception{
        Process process2 ;
        String out ;
        BufferedReader bfr;
        String line = null;
        ArrayList<String> list = null;
        String device_tpye;
        List<AndroidZD> adList = new ArrayList();
        Runtime runtime = Runtime.getRuntime();

        try {
            list = new ArrayList<String>();
            process2 =  runtime.exec(cmdPath + "devices -l");
            Thread.sleep(1000);
            if(process2 != null){
                bfr = new BufferedReader(new InputStreamReader(process2.getInputStream()));
                while ((line = bfr.readLine()) != null) {
                    System.out.println(line);
                    if (line.length() > 1) {
                        System.out.println(line);
                        list.add(line);
                    }
                }
                if(!list.contains("* daemon started successfully *")){
                    if(list.size() > 1){
                        if(!list.contains("device")){
                            for (int i = 1; i < list.size(); i++) {
                                for (int j = 0; j < list.get(i).split(" ").length; j++) {
//                                    System.out.println("输出："+j+list.get(i).split(" ")[j]);
                                    device_tpye = list.get(i).split(" ")[j];
                                    if(device_tpye.equals("device")){
                                        AndroidZD az = new AndroidZD();
                                        System.out.println("当前设备序列号:"+  list.get(i).split(" ")[0]);
                                        System.out.println("当前设备产品名:"+ list.get(i).split(" ")[16].split(":")[1]);
                                        System.out.println("当前设备型号:"+  list.get(i).split(" ")[17].split(":")[1]);
                                        System.out.println("当前设备名称:"+ list.get(i).split(" ")[18].split(":")[1]);
                                        az.setXID(list.get(i).split(" ")[0]);
                                        az.setAndroidName(list.get(i).split(" ")[16].split(":")[1]);
                                        az.setDeviceModel(list.get(i).split(" ")[17].split(":")[1]);
                                        az.setDeviceProduct(list.get(i).split(" ")[18].split(":")[1]);
                                        az.setTransportId(list.get(i).split(" ")[19].split(":")[1]);
                                        adList.add(az);
                                    }
                                }
                            }
                        }else{
                            System.out.println("当前设备列表中，没有device类型连接设备，请检查！");
                        }
                    }else{
                        System.out.println("当前设备列表没有连接的设备，请检查！");
                    }
                }else {
                    find(tag);
                }
            }else{
                System.out.println("当前执行adb命令异常，请检查adb环境！");
            }
//            process2.destroy();
            System.out.println("完成....");
        } catch (IOException | InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return adList;
    }

    @Override
    public boolean getAvi(int s) {
        Process process ;
        String out ;
        BufferedReader bfr1;
        String line = null;
        ArrayList<String> list = null;
        String device_tpye;
        List<AndroidZD> adList = new ArrayList();
        Runtime runtime = Runtime.getRuntime();
        UUID uuid  =  UUID.randomUUID();
        String uid = uuid.toString();
        try {
            list = new ArrayList<String>();
            process = runtime.exec(cmdPath + "shell screenrecord --bit-rate 2000000 --time-limit "+s+" /sdcard/test/"+uid+".mp4");
            Thread.sleep(s * 1000 +1000);
            process =  runtime.exec(cmdPath + "pull /sdcard/test/"+uid+".mp4 D:/RFC");
            if(process != null) {
                bfr1 = new BufferedReader(new InputStreamReader(process.getInputStream()));
                while ((line = bfr1.readLine()) != null) {
                    System.out.println(line);
                    if (line.length() > 1) {
                        System.out.println(line);
                        list.add(line);
                    }
                }
                if(!list.contains("*adb: error: *")){
                    runtime.exec(cmdPath + "shell rm /sdcard/test/"+uid+".mp4");
                    return true;
                }
            }
        } catch (IOException | InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public boolean getImg() {
        Process process ;
        String out ;
        BufferedReader bfr1;
        String line = null;
        ArrayList<String> list = null;
        List<AndroidZD> adList = new ArrayList();
        Runtime runtime = Runtime.getRuntime();
        UUID uuid  =  UUID.randomUUID();
        String uid = uuid.toString();
        try {
            list = new ArrayList<String>();
            process = runtime.exec(cmdPath + "shell screencap /sdcard/test/"+uid+".png");
            Thread.sleep(1000);
            process =  runtime.exec(cmdPath + "pull /sdcard/test/"+uid+".png D:/RFC");
            if(process != null) {
                bfr1 = new BufferedReader(new InputStreamReader(process.getInputStream()));
                while ((line = bfr1.readLine()) != null) {
                    System.out.println(line);
                    if (line.length() > 1) {
                        System.out.println(line);
                        list.add(line);
                    }
                }
                if(!list.contains("*adb: error: *")){
                    runtime.exec(cmdPath + "shell rm /sdcard/test/"+uid+".png");
                    return true;
                }
            }
        } catch (IOException | InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

}

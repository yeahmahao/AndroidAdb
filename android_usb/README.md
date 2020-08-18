# Android_adb #

通过java使用adb对处在开发者模式下的Android终端进行操作
## 已完成接口 ##


/findAllAndroid   ------获取所有设备信息

/getAvi    -------录屏并将录屏文件下载至指定目录

/getImg  -------截屏并下载至本地目录

## 配置方式 ##
pox.xml
 	<dependency>
       <groupId>org.usb4java</groupId>
       <artifactId>usb4java-javax</artifactId>
       <version>1.2.0</version>
  	</dependency>
  	
需要下载安装:Android Debug Bridge 并且配置环境变量

## 使用方式 ##
	Process process = Runtime.getRuntime().exec("cmd命令");

## 参考 ##
	https://www.cnblogs.com/herenzhiming/articles/5242566.html
	
## 遇到的问题 ##
1. 使用Runtime.getRuntime().exec("adb devices -l")的时候无法获取到设备信息，需要使用adb的安装地址

    例如：本人安装地址为 D:/sdk/platform-tools/adb.exe
    
    故：
    
    
    String adbPath = "D:/sdk/platform-tools/adb.exe ";
    Runtime.getRuntime().exec(adbpath + "devices -l")
    
2. 因为条用cmd命令的时候是使用shell调用的所以需要加上cmd /c


    String adbPath = "cmd /c D:/sdk/platform-tools/adb.exe ";
    Runtime.getRuntime().exec(adbpath + "devices -l")
    
## 联系方式 ##
QQ:1335892353
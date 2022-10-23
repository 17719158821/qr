package test;

import cn.edu.zzuli.qridentify.utils.MD5Utils;
import cn.edu.zzuli.qridentify.utils.QRUtil;

public class TestAndGenerate {

    public static void main(String[] args) {
        String passwd = "admin";
        generatePasswd(passwd);
    }


    //    生成二维码
    public static void generateQR(String code) {
        try {
            QRUtil.generateQRFile("http://localhost:8080/result?certificateCode=" + code, "", "D:\\WorkSpace\\IdeaWorkSpace\\qr\\src\\main\\java\\test\\QR.png");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //    生成用户密码
    public static void generatePasswd(String passwd) {
        String s = MD5Utils.stringToMD5(passwd);
        System.out.println(s);
    }

}

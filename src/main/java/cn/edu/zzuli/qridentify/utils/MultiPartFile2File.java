package cn.edu.zzuli.qridentify.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MultiPartFile2File {
//    接受网络到MultipartFile保存到缓存，并返回缓存地址
    public static String MultiPartFileToFile(MultipartFile file, String cachePath) {
        String savePath = "";
        File toFile = null;
        try {
            if (file == null || file.equals("") || file.getSize() <= 0) {
                return "";
            } else {
                InputStream ins = file.getInputStream();        //获取MultipartFile的输入流
                String ori_fileName = file.getOriginalFilename();
//                获取原始文件名
                String fileName = ori_fileName.substring(ori_fileName.lastIndexOf("/") + 1);
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
                String time = format.format(new Date());
//                文件名增加时间前缀，防止重名出错
                fileName = time + "-"+fileName;
//                保存缓存文件
                savePath = cachePath + fileName;
                toFile = new File(savePath);                            //根据老文件名创建一个File对象
                OutputStream os = new FileOutputStream(toFile);//根据File创建一个输出流
                int bytesRead = 0;
                byte[] buffer = new byte[8192];
                while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                    os.write(buffer, 0, bytesRead);              //将输入流写到输出流中  
                }
                ins.close();
                os.close();
                return savePath;
            }
        } catch (Exception e) {
            //这里进行异常处理  
        }
        return null;
    }
}

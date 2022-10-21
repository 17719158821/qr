package cn.edu.zzuli.qridentify.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class MultiPartFile2File {
    public static File MultiPartFileToFile(MultipartFile file) {
        File toFile = null;
        try {
            if (file == null || file.equals("") || file.getSize() <= 0) {
                file = null;
            } else {
                InputStream ins = file.getInputStream();        //获取MultipartFile的输入流
                String filePath = file.getOriginalFilename();
                filePath = filePath.substring(filePath.lastIndexOf("/") + 1);
                toFile = new File(filePath);                            //根据老文件名创建一个File对象
                OutputStream os = new FileOutputStream(toFile);//根据File创建一个输出流
                int bytesRead = 0;
                byte[] buffer = new byte[8192];
                while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                    os.write(buffer, 0, bytesRead);              //将输入流写到输出流中  
                }
                ins.close();
                os.close();
            }
        } catch (Exception e) {
            //这里进行异常处理  
        }
        return toFile;
    }
}

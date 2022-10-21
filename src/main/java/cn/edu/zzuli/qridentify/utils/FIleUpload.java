package cn.edu.zzuli.qridentify.utils;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.PutObjectRequest;

import java.io.File;

public class FIleUpload {
    public static String upload(String fileName, File upFile) throws Exception {
        String endpoint = "oss-cn-beijing.aliyuncs.com";
        String accessKeyId = "LTAI4GGLWTNTMPZRt1Wh9fpb";
        String accessKeySecret = "doB8g31OFWeS3kypBYWQK9QprVr1O9";
        String bucketName = "newsystem1771915";
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try {
            fileName = "qr/" + fileName;
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileName, upFile);
            ossClient.putObject(putObjectRequest);
            String url = "https://" + bucketName + "." + endpoint + "/" + fileName;
            return url;
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return null;
    }
}

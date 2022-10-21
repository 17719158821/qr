package cn.edu.zzuli.qridentify.service;

import cn.edu.zzuli.qridentify.entity.InfoVo;
import cn.edu.zzuli.qridentify.entity.UserInfo;
import org.springframework.web.multipart.MultipartFile;


public interface QueryService {

    InfoVo getInfo(String identifyCode,String type);
    UserInfo getInfoByQRCode(MultipartFile file);

    InfoVo getInfoFromCertificateCode(String code);

    InfoVo getEnterInfo(String enterpriseName, String type);
}

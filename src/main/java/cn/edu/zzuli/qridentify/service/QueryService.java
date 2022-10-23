package cn.edu.zzuli.qridentify.service;

import cn.edu.zzuli.qridentify.entity.InfoVo;


public interface QueryService {

    InfoVo getInfo(String identifyCode,String type);

    InfoVo getInfoFromCertificateCode(String code);

    InfoVo getEnterInfo(String enterpriseId, String type);
}

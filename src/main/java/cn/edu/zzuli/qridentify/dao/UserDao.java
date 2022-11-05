package cn.edu.zzuli.qridentify.dao;

import cn.edu.zzuli.qridentify.entity.CertificateInfo;
import cn.edu.zzuli.qridentify.entity.Enterprise;
import cn.edu.zzuli.qridentify.entity.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface
UserDao {
    UserInfo getUserInfo(@Param("identifyCode") String identifyCode);
    CertificateInfo getCertificateInfo(@Param("identifyCode")String identifyCode,@Param("type")String type);

    CertificateInfo getCertificateInfoByCertificateCode(@Param("certificateCode")String code);

    CertificateInfo getCertificationByEnterId(@Param("enterpriseId") String enterpriseId, @Param("type")String type);

    Enterprise getEnterInfo(@Param("enterpriseId") String enterpriseName);

    Enterprise getEnterInfoByEnterId(@Param("enterpriseId")String enterpriseId);
}

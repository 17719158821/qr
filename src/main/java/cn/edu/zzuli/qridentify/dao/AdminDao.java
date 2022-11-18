package cn.edu.zzuli.qridentify.dao;

import cn.edu.zzuli.qridentify.entity.Admin;
import cn.edu.zzuli.qridentify.entity.CertificateInfo;
import cn.edu.zzuli.qridentify.entity.Enterprise;
import cn.edu.zzuli.qridentify.entity.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AdminDao {
    public void updateQrPath(@Param("certificateCode") String certificateCode, @Param("path") String path);

    Integer insertUserInfo(UserInfo userInfo);

    Integer insertCertificateInfo(CertificateInfo certificateInfo);

    UserInfo selectUserInfoByIdentify(String identifyCode);

    void removeUserInfo(UserInfo userInfo);

    CertificateInfo selectCertificateInfoByCCode(@Param("certificateCode") String certificateCode);

    void removeCertificateInfo(@Param("certificateCode") String certificateCode);

    int updateUserInfo(UserInfo userInfo);

    int updateCertificateInfo(CertificateInfo certificateInfo);

    Admin selectAmin(@Param("username") String username, @Param("password") String password);

    //    根据证书类型与身份证号查询证书
    CertificateInfo selectCertificateInfoByCTypeAndUserInfo(@Param("identifyCode") String identifyCode, @Param("type") String type);

    Enterprise selectEnterInfoByEnId(String enterpriseId);

    void removeEnterInfo(String enterpriseName);

    //根据证书类型与纳税人识别号查询证书
    CertificateInfo selectCertificateInfoByCTypeAndEnterInfo(@Param("enterpriseId") String enterpriseId, @Param("type") String type);

    int insertEnterInfo(Enterprise enterpriseInfo);

    int updateEnterpriseInfo(Enterprise enterprise);

    int updateEnterCertificateInfo(CertificateInfo certificateInfo);

    List<CertificateInfo> selectAllEnterCertificateInfoList();

    List<CertificateInfo> selectAllUserCertificateInfoList();

}

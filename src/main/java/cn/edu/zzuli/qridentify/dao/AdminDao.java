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

    UserInfo selectUserInfoByIdentify(UserInfo userInfo);

    void removeUserInfo(UserInfo userInfo);

    CertificateInfo selectCertificateInfoByCCode(@Param("certificateCode")String certificateCode);

    List<CertificateInfo> selectCertificateInfoByICode(@Param("identifyCode")String identifyCode);

    void removeCertificateInfo(@Param("certificateCode")String certificateCode);

    int updateUserInfo(UserInfo userInfo);

    int updateCertificateInfo(CertificateInfo certificateInfo);

    List<CertificateInfo> selectAllCertificateInfoList();

    List<UserInfo> selectAllUserList();

    void updatePicPath(@Param("certificateCode")String certificateCode, @Param("path")String path);

    Admin selectAminByUNameAndPasswd(@Param("username") String username, @Param("password") String password);

//    查看同一个人是否拥有多个同一种证书
    CertificateInfo selectCertificateInfoByCTypeAndUserInfo(@Param("identifyCode") String identifyCode, @Param("type") String type);

    Enterprise selectEnterInfoByEnId(String enterpriseId);

    void removeEnterInfo(String enterpriseName);

    CertificateInfo selectCertificateInfoByCTypeAndEnterInfo(@Param("enterpriseId") String enterpriseId, @Param("type") String type);

    int insertEnterInfo(Enterprise enterpriseInfo);

    int updateEnterpriseInfo(Enterprise enterprise);

    int updateEnterCertificateInfo(CertificateInfo certificateInfo);

    List<Enterprise> selectAllEnterList();
}

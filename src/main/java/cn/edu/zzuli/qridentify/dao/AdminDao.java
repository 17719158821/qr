package cn.edu.zzuli.qridentify.dao;

import cn.edu.zzuli.qridentify.entity.Admin;
import cn.edu.zzuli.qridentify.entity.CertificateInfo;
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

    void updatePicPath(@Param("identifyCode")String identifyCode, @Param("path")String path);

    Admin selectAminByUNameAndPasswd(@Param("username") String username, @Param("password") String password);
}
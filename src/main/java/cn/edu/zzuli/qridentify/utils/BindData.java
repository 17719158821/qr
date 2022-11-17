package cn.edu.zzuli.qridentify.utils;

import cn.edu.zzuli.qridentify.entity.CertificateInfo;
import cn.edu.zzuli.qridentify.entity.Enterprise;
import cn.edu.zzuli.qridentify.entity.UserInfo;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.Map;

public class BindData {
    public static void bind(UserInfo userInfo, CertificateInfo certificateInfo, Map<String, Object> map) {
        String date = (String) map.get("certificateDate");

        if (!StringUtils.isEmpty(date)) {
            if (date.length() > 10) {
                date = date.substring(0, 10);
            }
            Date certificateDate = Date.valueOf(date);
            certificateInfo.setCertificateDate(certificateDate);
        }

        Long userId = null;
        String userId_tem = (String) map.get("userId");
        if (!StringUtils.isEmpty(userId_tem)) {
            userId = Long.valueOf(userId_tem);
        }

        String name = (String) map.get("name");
        String identifyCode = (String) map.get("identifyCode");
        String phoneNumber = (String) map.get("phoneNumber");
        String address = (String) map.get("address");
        String gender = (String) map.get("gender");
        String pic = (String) map.get("pic");
        String type = (String) map.get("type");
        String level = (String) map.get("level");
        String certificateDept = (String) map.get("certificateDept");
        String reviewResult = (String) map.get("reviewResult");
        Integer age = (Integer) map.get("age");

        String tGrade = (String) map.get("testGrade");
        String pGrade = (String) map.get("practiceGrade");
        String toGrade = (String) map.get("totalGrade");
        Double testGrade = null;
        Double practiceGrade = null;
        Double totalGrade = null;
        if (!StringUtils.isEmpty(tGrade) && tGrade != null) {
            testGrade = Double.valueOf(tGrade);
        }
        if (!StringUtils.isEmpty(pGrade) && pGrade != null) {
            practiceGrade = Double.valueOf(pGrade);
        }
        if (!StringUtils.isEmpty(toGrade) && toGrade != null) {
            totalGrade = Double.valueOf(toGrade);
        }


        String qrCodePath = (String) map.get("qrCodePath");
        String certificateCode = (String) map.get("certificateCode");
        String certificateId_item = (String) map.get("certificateId");
        Long certificateId = null;
        if (!StringUtils.isEmpty(certificateId_item) && certificateId_item != null) {
            certificateId = Long.valueOf(certificateId_item);
        }


        userInfo.setIdentifyCode(identifyCode).setName(name).setAddress(address).setAge(age).setGender(gender).setPhoneNumber(phoneNumber).setUserId(userId);
        certificateInfo.setPic(pic).setCertificateCode(certificateCode).setIdentifyCode(identifyCode).setCertificateDept(certificateDept).setQrCodePath(qrCodePath).setLevel(level).setPracticeGrade(practiceGrade).setTestGrade(testGrade).setTotalGrade(totalGrade).setType(type).setCertificateId(certificateId).setReviewResult(reviewResult);

    }


    //绑定企业
    public static void bindEnter(Enterprise enterpriseInfo, CertificateInfo certificateInfo, Map<String, Object> map) {
        //  企业id，名称，电话，地址

        String enterpriseId = (String) map.get("enterpriseId");
        String enterpriseName = (String) map.get("enterpriseName");
        String phoneNumber = (String) map.get("phoneNumber");
        String address = (String) map.get("address");
        enterpriseInfo.setEnterpriseId(enterpriseId).setEnterpriseName(enterpriseName).setAddress(address).setPhoneNumber(phoneNumber);
        String pic = (String) map.get("pic");
//        证书类型，认证时间，认证等级，认证部门，复审情况，二维码地址，证书编号，证书id
        String certificateDate_tem = (String) map.get("certificateDate");

        if (!StringUtils.isEmpty(certificateDate_tem) && certificateDate_tem != null) {
            if (certificateDate_tem.length() > 10) {
                certificateDate_tem = certificateDate_tem.substring(0, 10);
            }
            Date date = Date.valueOf(certificateDate_tem);
            certificateInfo.setCertificateDate(date);
        }
        String type = (String) map.get("type");
        String level = (String) map.get("level");
        String certificateDept = (String) map.get("certificateDept");
//        String reviewResult = (String) map.get("reviewResult");
        String certificateCode = (String) map.get("certificateCode");
        String certificateId_item = (String) map.get("certificateId");
        Long certificateId = null;
        if (!StringUtils.isEmpty(certificateId_item) && certificateId_item != null) {
            certificateId = Long.valueOf(certificateId_item);
        }
        certificateInfo.setType(type).setLevel(level).setCertificateDept(certificateDept)
                .setPic(pic)
                .setCertificateCode(certificateCode).setCertificateId(certificateId).setEnterpriseId(enterpriseId);
    }
}

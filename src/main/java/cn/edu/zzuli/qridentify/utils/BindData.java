package cn.edu.zzuli.qridentify.utils;

import cn.edu.zzuli.qridentify.entity.CertificateInfo;
import cn.edu.zzuli.qridentify.entity.Enterprise;
import cn.edu.zzuli.qridentify.entity.UserInfo;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class BindData {
    public static void bind(UserInfo userInfo, CertificateInfo certificateInfo, Map<String, Object> map) {
        String date = (String) map.get("certificateDate");
        Date certificateDate = null;
        if (!StringUtils.isEmpty(date)) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            try {
                certificateDate = dateFormat.parse(date);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
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
        if (!StringUtils.isEmpty(tGrade)) {
            testGrade = Double.valueOf(tGrade);
        }
        if (!StringUtils.isEmpty(pGrade)) {
            practiceGrade = Double.valueOf(pGrade);
        }
        if (!StringUtils.isEmpty(toGrade)) {
            totalGrade = Double.valueOf(toGrade);
        }


        String qrCodePath = (String) map.get("qrCodePath");
        String certificateCode = (String) map.get("certificateCode");
        String certificateId_item = (String) map.get("certificateId");
        Long certificateId = null;
        if (!StringUtils.isEmpty(certificateId_item)) {
            certificateId = Long.valueOf(certificateId_item);
        }


        userInfo.setIdentifyCode(identifyCode).setName(name).setAddress(address).setAge(age).setGender(gender).setPhoneNumber(phoneNumber).setPic(pic).setUserId(userId);
        certificateInfo.setCertificateCode(certificateCode).setIdentifyCode(identifyCode).setCertificateDate(certificateDate).setCertificateDept(certificateDept).setQrCodePath(qrCodePath).setLevel(level).setPracticeGrade(practiceGrade).setTestGrade(testGrade).setTotalGrade(totalGrade).setType(type).setCertificateId(certificateId).setReviewResult(reviewResult);

    }


    //绑定企业
    public static void bindEnter(Enterprise enterpriseInfo, CertificateInfo certificateInfo, Map<String, Object> map) {
        //  企业id，名称，电话，地址

        String enterpriseId = (String) map.get("enterpriseId");
        String enterpriseName = (String) map.get("enterpriseName");
        String phoneNumber = (String) map.get("phoneNumber");
        String address = (String) map.get("address");
        enterpriseInfo.setEnterpriseId(enterpriseId);
        enterpriseInfo.setEnterpriseName(enterpriseName);
        enterpriseInfo.setAddress(address);
        enterpriseInfo.setPhoneNumber(phoneNumber);

//        证书类型，认证时间，认证等级，认证部门，复审情况，二维码地址，证书编号，证书id
        String certificateDate_tem = (String) map.get("certificateDate");
        Date certificateDate = null;
        if (!StringUtils.isEmpty(certificateDate_tem)) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                certificateDate = dateFormat.parse(certificateDate_tem);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        String type = (String) map.get("type");
        String level = (String) map.get("level");
        String certificateDept = (String) map.get("certificateDept");
        String reviewResult = (String) map.get("reviewResult");
        String certificateCode = (String) map.get("certificateCode");
        String certificateId_item = (String) map.get("certificateId");
        Long certificateId = null;
        if (!StringUtils.isEmpty(certificateId_item)) {
            certificateId = Long.valueOf(certificateId_item);
        }
        certificateInfo.setCertificateDate(certificateDate)
                .setType(type).setLevel(level)
                .setCertificateDept(certificateDept)
                .setReviewResult(reviewResult)
                .setCertificateCode(certificateCode)
                .setCertificateId(certificateId)
                .setEnterpriseId(enterpriseId);
    }
}

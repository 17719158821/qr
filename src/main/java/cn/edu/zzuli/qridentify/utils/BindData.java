package cn.edu.zzuli.qridentify.utils;

import cn.edu.zzuli.qridentify.entity.CertificateInfo;
import cn.edu.zzuli.qridentify.entity.UserInfo;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class BindData {
    public static void bind(UserInfo userInfo, CertificateInfo certificateInfo, Map<String, Object> map) {
        String date = (String) map.get("certificateDate");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date certificateDate = null;
        try {
            certificateDate = dateFormat.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
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
        Double testGrade = Double.valueOf(tGrade);
        Double practiceGrade = Double.valueOf(pGrade);
        Double totalGrade = Double.valueOf(toGrade);


        String qrCodePath = (String) map.get("qrCodePath");
        String certificateCode = (String) map.get("certificateCode");
        String certificateId_item = (String) map.get("certificateId");
        Long certificateId = null;
        if(!StringUtils.isEmpty(certificateId_item)){
            certificateId = Long.valueOf(certificateId_item);
        }


        userInfo.setIdentifyCode(identifyCode)
                .setName(name)
                .setAddress(address)
                .setAge(age)
                .setGender(gender)
                .setPhoneNumber(phoneNumber)
                .setPic(pic)
                .setUserId(userId);
        certificateInfo.setCertificateCode(certificateCode)
                .setIdentifyCode(identifyCode)
                .setCertificateDate(certificateDate)
                .setCertificateDept(certificateDept)
                .setQrCodePath(qrCodePath)
                .setLevel(level)
                .setPracticeGrade(practiceGrade)
                .setTestGrade(testGrade)
                .setTotalGrade(totalGrade)
                .setType(type)
                .setCertificateId(certificateId)
                .setReviewResult(reviewResult);

    }
}

package cn.edu.zzuli.qridentify.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@AllArgsConstructor
@Accessors(chain = true)
public class CertificateInfo {
    //    身份证
    private String identifyCode;
    //    证书编号
    private String certificateCode;
    //    证书类别
    private String type;
    //    证书级别
    private String level;
    //    发证机关
    private String certificateDept;
    //    复审情况
    private String reviewResult;
    //    认证时间
    private Date certificateDate;
    //    理论分数
    private Double testGrade;
    //    实操分数
    private Double practiceGrade;
    //    总分
    private Double totalGrade;
    //    二维码路径
    private String qrCodePath;
    private Long certificateId;

    private Long enterpriseId;
    public CertificateInfo() {
    }
}

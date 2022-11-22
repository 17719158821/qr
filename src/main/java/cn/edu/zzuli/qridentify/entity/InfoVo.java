package cn.edu.zzuli.qridentify.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@AllArgsConstructor
@Accessors(chain = true)
public class InfoVo {
    private UserInfo userInfo;
    private CertificateInfo certificateInfo;
    private Enterprise enterprise;
    private Integer pageSize;
    private Integer pageNo;
    public InfoVo() {
    }
}

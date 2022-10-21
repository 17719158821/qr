package cn.edu.zzuli.qridentify.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@Accessors(chain = true)
public class UserInfo {
//    姓名
    private String name;
//    身份证
    private String identifyCode;
    private Integer age;
    private String phoneNumber;
    private String address;
//    性别
    private String gender;
//    证件照路径
    private String pic;
    private Long userId;
    public UserInfo() {
    }
}

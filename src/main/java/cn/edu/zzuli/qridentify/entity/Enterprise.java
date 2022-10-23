package cn.edu.zzuli.qridentify.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@Accessors(chain = true)
public class Enterprise {
    private String enterpriseName;
    private String enterpriseId;
    private String address;
    private String phoneNumber;
    private Long id;
    public Enterprise(){

    }
}

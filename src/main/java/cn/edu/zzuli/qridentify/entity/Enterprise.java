package cn.edu.zzuli.qridentify.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@Accessors(chain = true)
public class Enterprise {
    private String enterpriseName;
    private Long enterpriseId;
    private String address;

    public Enterprise(){

    }
}

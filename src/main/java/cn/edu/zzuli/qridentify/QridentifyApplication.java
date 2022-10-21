package cn.edu.zzuli.qridentify;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("cn.edu.zzuli.qridentify.dao")
@SpringBootApplication
public class QridentifyApplication {

    public static void main(String[] args) {
        SpringApplication.run(QridentifyApplication.class, args);
    }

}

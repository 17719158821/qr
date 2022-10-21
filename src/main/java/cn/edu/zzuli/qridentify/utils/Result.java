package cn.edu.zzuli.qridentify.utils;

import lombok.Data;

@Data
public class Result<T> {
    public static final Integer OK = 0;
    public static final Integer ERR = 1;

    String msg;
    Integer code;
    T data;

    public Result(String msg, Integer code, T data) {
        this.msg = msg;
        this.code = code;
        this.data = data;
    }

    public Result() {
    }

    public Result(String msg, Integer code) {
        this.msg = msg;
        this.code = code;
    }

    public Result(String msg) {
        this.msg = msg;
    }
}

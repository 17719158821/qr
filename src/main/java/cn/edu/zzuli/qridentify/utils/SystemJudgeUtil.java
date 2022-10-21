package cn.edu.zzuli.qridentify.utils;

public class SystemJudgeUtil {
    public static boolean systemJudge(){

        if ( System.getProperty("os.name").toLowerCase().contains("windows")){
            return true;
        }
        return false;
    }

}

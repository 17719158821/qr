package cn.edu.zzuli.qridentify.service;

import cn.edu.zzuli.qridentify.entity.Admin;
import cn.edu.zzuli.qridentify.entity.InfoVo;
import cn.edu.zzuli.qridentify.utils.Result;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface AdminService {


    Result add(Map<String, Object> map);

    Result delete(Map<String, String> map);

    Result update(Map<String, Object> map);

    List<InfoVo> selectList();

    Result uploadQRPic(MultipartFile file, String certificateCode);

    Result uploadUserPic(MultipartFile file, String identifyCode);

    Result selectCerUserInfo(String certificateCode);

    Result login(Admin admin);

    Result addEnterPriseInfo(Map<String, Object> map);

    Result deleteEnter(Map<String, String> map);

    Result updateEnter(Map<String, Object> map);

    List<InfoVo> selectEnterList();

    Result selectCerEnterInfo(String certificateCode);
}

package cn.edu.zzuli.qridentify.service;

import cn.edu.zzuli.qridentify.entity.Admin;
import cn.edu.zzuli.qridentify.entity.InfoVo;
import cn.edu.zzuli.qridentify.utils.Result;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface AdminService {


    Result add(Map<String, Object> map);

    Result delete(Map<String, String> map);

    Result update(Map<String, Object> map);

    PageInfo selectList(int pageNum, int pageSize);

    Result uploadUserPic(MultipartFile file, String certificateCode);

    Result selectCerUserInfo(String certificateCode);

    Result login(Admin admin);

    Result addEnterPriseInfo(Map<String, Object> map);

    Result deleteEnter(Map<String, String> map);

    Result updateEnter(Map<String, Object> map);

    PageInfo selectEnterList(int pageNum, int pageSize);

    Result selectCerEnterInfo(String certificateCode);

    PageInfo fuzzySearch(String key, int pageNum, int pageSize);

    PageInfo fuzzySearchEnter(String key, int pageNum, int pageSize);

    Result updateAdminPWD(Map<String, String> map);
}

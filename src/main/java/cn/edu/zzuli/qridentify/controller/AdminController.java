package cn.edu.zzuli.qridentify.controller;

import cn.edu.zzuli.qridentify.entity.Admin;
import cn.edu.zzuli.qridentify.entity.InfoVo;
import cn.edu.zzuli.qridentify.service.AdminService;
import cn.edu.zzuli.qridentify.utils.Result;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.Generated;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@CrossOrigin
@Controller
@RequestMapping("admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    //    添加个人证书数据
    @PostMapping("add")
    @ResponseBody
    public Result addInfo(@RequestBody Map<String, Object> map) {
        Result result = adminService.add(map);
        return result;
    }

    //    删除个人证书数据
    @PostMapping("delete")
    @ResponseBody
    public Result deleteInfo(@RequestBody Map<String, String> map) {
        Result result = adminService.delete(map);
        return result;
    }

    //    更新个人证书数据
    @PostMapping("update")
    @ResponseBody
    public Result updateInfo(@RequestBody Map<String, Object> map) {
        Result result = adminService.update(map);
        return result;
    }

    //    列出所有个人证书
    @GetMapping("query")
    @ResponseBody
    public Result queryAllList(@RequestParam int pageNum, @RequestParam int pageSize) {

        PageInfo infoVos = adminService.selectList(pageNum, pageSize);

        return new Result("查询成功", Result.OK, infoVos);
    }

    //    上传证书扫描件
    @PostMapping("uploadCerPic")
    @ResponseBody
    public Result uploadUserPic(@RequestParam("file") MultipartFile file, @Param("certificateCode") String certificateCode) {
        Result result = adminService.uploadUserPic(file, certificateCode);
        return result;
    }


    //    查询个人证书数据
    @PostMapping("queryInfo")
    @ResponseBody
    public Result queryInfo(@Param("certificateCode") String certificateCode) {
        Result result = adminService.selectCerUserInfo(certificateCode);
        return result;
    }

    //    管理员登录
    @PostMapping("login")
    @ResponseBody
    public Result login(@RequestBody Admin admin) {
        Result result = adminService.login(admin);
        return result;
    }

    //    添加企业证书数据
    @PostMapping("addEnter")
    @ResponseBody
    public Result addEnterPriseInfo(@RequestBody Map<String, Object> map) {
        Result result = adminService.addEnterPriseInfo(map);
        return result;
    }


    //    删除企业证书数据
    @PostMapping("delEnter")
    @ResponseBody
    public Result deleteEnterInfo(@RequestBody Map<String, String> map) {
        Result result = adminService.deleteEnter(map);
        return result;
    }

    //    更新企业证书数据
    @PostMapping("updateEnter")
    @ResponseBody
    public Result updateEnterInfo(@RequestBody Map<String, Object> map) {
        Result result = adminService.updateEnter(map);
        return result;
    }

    //    列出所有企业证书
    @GetMapping("queryEnter")
    @ResponseBody
    public Result queryAllEnterList(@RequestParam int pageNum, @RequestParam int pageSize) {
        PageInfo infoVos = adminService.selectEnterList(pageNum,pageSize);
        return new Result("查询成功", Result.OK, infoVos);
    }

    //    查询企业证书数据
    @GetMapping("queryEnterInfo")
    @ResponseBody
    public Result queryEnterInfo(@RequestParam("certificateCode") String certificateCode) {
        Result result = adminService.selectCerEnterInfo(certificateCode);
        return result;
    }


}

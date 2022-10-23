package cn.edu.zzuli.qridentify.controller;

import cn.edu.zzuli.qridentify.entity.InfoVo;
import cn.edu.zzuli.qridentify.service.QueryService;
import cn.edu.zzuli.qridentify.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
@CrossOrigin
@Controller
@RequestMapping("query")
public class QueryController {

    @Autowired
    QueryService queryService;

    /**
     * 根据身份证与证书类别查询某一张证书
     */
    @PostMapping("info")
    @ResponseBody
    public Result queryByInfo(@RequestBody Map<String, String> map) {
        String identifyCode = map.get("identifyCode");
        String type = map.get("type");

        InfoVo result = queryService.getInfo(identifyCode, type);
        if (result != null) {
            return new Result("查询成功", Result.OK, result);
        }
        return new Result("查询失败", Result.ERR);
    }

    /**
     * 根据公司企业ID与证书类别查询某一张证书
     */
    @PostMapping("enterinfo")
    @ResponseBody
    public Result queryByEnterInfo(@RequestBody Map<String, String> map) {
        String enterpriseId = map.get("enterpriseId");
        String type = map.get("type");

        InfoVo result = queryService.getEnterInfo(enterpriseId, type);
        if (result != null) {
            return new Result("查询成功", Result.OK, result);
        }
        return new Result("查询失败", Result.ERR);
    }

    /**
     * 根据证书id查询证书信息-对接扫码查询
     */
    @PostMapping("certificateCode")
    @ResponseBody
    public Result queryByCode(@RequestBody Map<String, String> map) {
        String code = map.get("certificateCode");
//        code = EncryptUtil.decrypt(code);
         InfoVo result = queryService.getInfoFromCertificateCode(code);
        if (result != null  ) {
            return new Result("查询成功", Result.OK, result);
        }
        return new Result("查询失败", Result.ERR);
    }




}

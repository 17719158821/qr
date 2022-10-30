package cn.edu.zzuli.qridentify.service.imp;

import cn.edu.zzuli.qridentify.dao.AdminDao;
import cn.edu.zzuli.qridentify.entity.*;
import cn.edu.zzuli.qridentify.service.AdminService;
import cn.edu.zzuli.qridentify.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class AdminServiceIpm implements AdminService {
    @Autowired
    AdminDao adminDao;
    @Value("${qr.path.generate}")
    String web_content;
    @Value("${qr.path.linux}")
    String linux_path;
    @Value("$(qr.path.windows)")
    String windows_path;

    public boolean generateQr(String code) {
//        String content = "http://192.168.1.19:8080/result?certificateCode=";
        String base_path;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String time = format.format(new Date());
        if (SystemJudgeUtil.systemJudge()) {
//            base_path = "D:\\WorkSpace\\IdeaWorkSpace\\qr\\files\\";
            base_path = windows_path;
            base_path = System.getProperty("user.dir") + "\\cache\\";

        } else {
            base_path = linux_path;
            base_path = System.getProperty("user.dir") + "/cache/";

//            base_path = "/JavaDev/qrStatic/";
        }
        QRUtil.mkdirs(base_path);


        if (!StringUtils.isEmpty(code)) {
            String fileName = code + "-" + time + ".png";
//            使用项目当前地址作为缓存路径
            String file_path = base_path + fileName;

            String content = web_content + code;
            try {
                //生成本地二维码
                QRUtil.generateQRFile(content, code, file_path);
                //将文件上传
                String url = FIleUpload.upload(fileName, new File(file_path));
                if (!StringUtils.isEmpty(url)) {
                    adminDao.updateQrPath(code, url);
                    return true;
                } else {
                    return false;
                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            return false;
        }

    }

    @Override
    public Result add(Map<String, Object> map) {
        UserInfo userInfo = new UserInfo();
        CertificateInfo certificateInfo = new CertificateInfo();
        BindData.bind(userInfo, certificateInfo, map);
        //查询这个人存不存在，如果不存在则新增，存在则更新
        UserInfo isExist = adminDao.selectUserInfoByIdentify(userInfo);
        if (isExist != null) {
            adminDao.removeUserInfo(userInfo);
        }
        int i = adminDao.insertUserInfo(userInfo);
        CertificateInfo isCExist = adminDao.selectCertificateInfoByCCode(certificateInfo.getCertificateCode());
        if (isCExist != null) {
            return new Result("新增失败，该证书编号重复", Result.ERR);
        }
        //查看同一个人是否拥有相同的证书
        isCExist = adminDao.selectCertificateInfoByCTypeAndUserInfo(userInfo.getIdentifyCode(), certificateInfo.getType());
        if (isCExist != null) {
            return new Result("新增失败，该用户拥有同类型证书", Result.ERR);
        }
        int j = adminDao.insertCertificateInfo(certificateInfo);
        boolean suss_ = generateQr(certificateInfo.getCertificateCode());
        if (!suss_) {
            return new Result("新增失败", Result.ERR);
        }
        if (i + j > 1) {
            return new Result("新增成功", Result.OK);
        }
        return new Result("新增失败", Result.ERR);
    }

    //    新增企业证书数据
    @Override
    public Result addEnterPriseInfo(Map<String, Object> map) {
        Enterprise enterpriseInfo = new Enterprise();
        CertificateInfo certificateInfo = new CertificateInfo();
        BindData.bindEnter(enterpriseInfo, certificateInfo, map);
        //查询这公司存不存在，如果不存在则新增，存在则更新
        Enterprise isExist = adminDao.selectEnterInfoByEnId(enterpriseInfo.getEnterpriseId());
        if (isExist != null) {
            adminDao.removeEnterInfo(enterpriseInfo.getEnterpriseName());
        }
        int i = adminDao.insertEnterInfo(enterpriseInfo);
        CertificateInfo isCExist = adminDao.selectCertificateInfoByCCode(certificateInfo.getCertificateCode());
        if (isCExist != null) {
            return new Result("新增失败,该证书编号重复", Result.ERR);
        }
        //查看同一个公司是否拥有相同的证书
        isCExist = adminDao.selectCertificateInfoByCTypeAndEnterInfo(enterpriseInfo.getEnterpriseId(), certificateInfo.getType());
        if (isCExist != null) {
            return new Result("新增失败，该单位拥有相同类型证书", Result.ERR);
        }
        int j = adminDao.insertCertificateInfo(certificateInfo);
        boolean suss_ = generateQr(certificateInfo.getCertificateCode());
        if (!suss_) {
            return new Result("新增失败", Result.ERR);
        }
        if (i + j > 1) {
            return new Result("新增成功", Result.OK);
        }
        return new Result("新增失败", Result.ERR);
    }

    //    删除个人用户证书
    @Override
    public Result delete(Map<String, String> map) {
        String certificateCode = map.get("certificateCode");
        CertificateInfo certificateInfo = adminDao.selectCertificateInfoByCCode(certificateCode);
        if (certificateInfo == null) {
            return new Result("删除失败,证书不存在", Result.ERR);
        }

        adminDao.removeCertificateInfo(certificateCode);
        certificateInfo = adminDao.selectCertificateInfoByCCode(certificateCode);
        if (certificateInfo == null) {
            return new Result("删除成功", Result.OK);
        }

        return new Result("删除失败", Result.ERR);
    }

    //    删除企业证书
    @Override
    public Result deleteEnter(Map<String, String> map) {
        String certificateCode = map.get("certificateCode");
        CertificateInfo certificateInfo = adminDao.selectCertificateInfoByCCode(certificateCode);
        if (certificateInfo == null) {
            return new Result("删除失败,证书不存在", Result.ERR);
        }

        adminDao.removeCertificateInfo(certificateCode);

        certificateInfo = adminDao.selectCertificateInfoByCCode(certificateCode);
        if (certificateInfo == null) {
            return new Result("删除成功", Result.OK);
        }

        return new Result("删除失败", Result.ERR);
    }

    @Override
    public Result update(Map<String, Object> map) {
        UserInfo userInfo = new UserInfo();
        CertificateInfo certificateInfo = new CertificateInfo();
        BindData.bind(userInfo, certificateInfo, map);
        int i = adminDao.updateUserInfo(userInfo);
        int j = adminDao.updateCertificateInfo(certificateInfo);
        if (i + j > 0) {
            return new Result("更新成功", Result.OK);
        }
        return new Result("更新成功", Result.ERR);
    }

    @Override
    public List<InfoVo> selectList() {

        List<UserInfo> userInfos = adminDao.selectAllUserList();
        List<CertificateInfo> certificateInfos = adminDao.selectAllCertificateInfoList();

        List<InfoVo> infoVos = new ArrayList<>();
        for (UserInfo u_item : userInfos) {
            for (CertificateInfo c_item : certificateInfos) {
                if (u_item.getIdentifyCode() == c_item.getIdentifyCode() || u_item.getIdentifyCode().equals(c_item.getIdentifyCode())) {
                    InfoVo infoVo = new InfoVo();
                    infoVo.setUserInfo(u_item);
                    infoVo.setCertificateInfo(c_item);
                    infoVos.add(infoVo);
                }

            }
        }

        return infoVos;
    }

    @Override
    public Result uploadQRPic(MultipartFile multipartFile, String certificateCode) {
        //文件上传前的名称
        File file = MultiPartFile2File.MultiPartFileToFile(multipartFile);
        try {
            String url = FIleUpload.upload(multipartFile.getOriginalFilename(), file);
            adminDao.updateQrPath(certificateCode, url);
            return new Result("上传成功", Result.OK, url);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
//        return new Result("",Result.ERR);
    }

    @Override
    public Result uploadUserPic(MultipartFile multipartFile, String identifyCode) {


        String base_path;

        File file = MultiPartFile2File.MultiPartFileToFile(multipartFile);
        if (SystemJudgeUtil.systemJudge()) {
            base_path = windows_path;
            base_path = System.getProperty("user.dir") + "\\cache\\";

        } else {
            base_path = linux_path;
            base_path = System.getProperty("user.dir") + "/cache/";


        }
        try {
            String ori_name = multipartFile.getOriginalFilename();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
            String time = format.format(new Date());
            ori_name = base_path + time + "-" + ori_name;

            String url = FIleUpload.upload(ori_name, file);
            adminDao.updatePicPath(identifyCode, url);
            return new Result("上传成功", Result.OK, url);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Result selectCerUserInfo(String certificateCode) {
        CertificateInfo certificateInfo = adminDao.selectCertificateInfoByCCode(certificateCode);
        UserInfo userInfo = adminDao.selectUserInfoByIdentify(new UserInfo().setIdentifyCode(certificateInfo.getIdentifyCode()));

        InfoVo infoVo = new InfoVo().setUserInfo(userInfo).setCertificateInfo(certificateInfo);

        return new Result("查询成功", Result.OK, infoVo);
    }

    @Override
    public Result login(Admin admin) {
        if (admin == null || StringUtils.isEmpty(admin.getUsername()) || StringUtils.isEmpty(admin.getPassword())) {
            return new Result("登录失败", Result.ERR);
        }
        String password = admin.getPassword();
        String username = admin.getUsername();
        String encoder_pass = MD5Utils.stringToMD5(password);
        Admin res = adminDao.selectAminByUNameAndPasswd(username, encoder_pass);
        if (res != null) {
            return new Result("登录成功", Result.OK);
        }
        return new Result("登录失败", Result.ERR);
    }


    @Override
    public Result updateEnter(Map<String, Object> map) {
        Enterprise enterprise = new Enterprise();
        CertificateInfo certificateInfo = new CertificateInfo();
        BindData.bindEnter(enterprise, certificateInfo, map);
        int i = adminDao.updateEnterpriseInfo(enterprise);
        int j = adminDao.updateEnterCertificateInfo(certificateInfo);
        if (i + j > 0) {
            return new Result("更新成功", Result.OK);
        }
        return new Result("更新失败", Result.ERR);
    }


    @Override
    public List<InfoVo> selectEnterList() {

        List<Enterprise> enterprises = adminDao.selectAllEnterList();
        List<CertificateInfo> certificateInfos = adminDao.selectAllCertificateInfoList();

        List<InfoVo> infoVos = new ArrayList<>();
        for (Enterprise e_item : enterprises) {
            for (CertificateInfo c_item : certificateInfos) {
                if (e_item.getEnterpriseId() == c_item.getEnterpriseId() || e_item.getEnterpriseId().equals(c_item.getEnterpriseId())) {
                    InfoVo infoVo = new InfoVo();
                    infoVo.setEnterprise(e_item);
                    infoVo.setCertificateInfo(c_item);
                    infoVos.add(infoVo);
                }
            }
        }

        return infoVos;
    }

    @Override
    public Result selectCerEnterInfo(String certificateCode) {
        CertificateInfo certificateInfo = adminDao.selectCertificateInfoByCCode(certificateCode);
        if (certificateInfo == null) {
            return new Result("查询失败", Result.ERR);
        }
        Enterprise enterprise = adminDao.selectEnterInfoByEnId(certificateInfo.getEnterpriseId());
        if (enterprise == null) {
            return new Result("查询失败", Result.ERR);
        }
        InfoVo infoVo = new InfoVo().setEnterprise(enterprise).setCertificateInfo(certificateInfo);
        return new Result("查询成功", Result.OK, infoVo);
    }


}

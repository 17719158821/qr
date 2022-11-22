package cn.edu.zzuli.qridentify.service.imp;

import cn.edu.zzuli.qridentify.dao.AdminDao;
import cn.edu.zzuli.qridentify.entity.*;
import cn.edu.zzuli.qridentify.service.AdminService;
import cn.edu.zzuli.qridentify.utils.*;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
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
    @Value("$(qr.path.linux)")
    String linux_path;
    @Value("$(qr.path.windows)")
    String windows_path;

    public boolean generateQr(String code) {
        String base_path;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String time = format.format(new Date());
        if (SystemJudgeUtil.systemJudge()) {
            base_path = System.getProperty("user.dir") + "\\cache\\";
        } else {
            base_path = System.getProperty("user.dir") + "/cache/";
        }
        QRUtil.mkdirs(base_path);
        if (!StringUtils.isEmpty(code)) {
//            生成文件名，code+时间
            String fileName = code + "-" + time + ".png";
//            生成本地缓存路径，
            String file_path = base_path + fileName;
//            生成二维码内容
            String content = web_content + code;
            try {
                //生成二维码，保存到本地缓存
                QRUtil.generateQRFile(content, code, file_path);
                /**
                 * 参数：
                 *  filename：文件原名-code-yyyy-MM-dd-HH-mm-ss.png
                 *  FIle：缓存文件路径 /cache/code-yyyy-MM-dd-HH-mm-ss.png
                 */
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
    public Result uploadUserPic(MultipartFile multipartFile, String certificateCode) {
        String base_path;

        if (SystemJudgeUtil.systemJudge()) {
            base_path = System.getProperty("user.dir") + "\\cache\\";
        } else {
            base_path = System.getProperty("user.dir") + "/cache/";
        }
        String cacheFilePath = MultiPartFile2File.MultiPartFileToFile(multipartFile, base_path);
        try {
//            获取文件原名
            String ori_name = multipartFile.getOriginalFilename();
            String url = FIleUpload.upload(ori_name, new File(cacheFilePath));
            return new Result("上传成功", Result.OK, url);
        } catch (Exception e) {
            return new Result("上传失败", Result.ERR);
        }
    }

    @Override
    public Result add(Map<String, Object> map) {
        UserInfo userInfo = new UserInfo();
        CertificateInfo certificateInfo = new CertificateInfo();
        BindData.bind(userInfo, certificateInfo, map);
        //查询这个人存不存在，如果不存在则新增，存在则更新
        UserInfo isExist = adminDao.selectUserInfoByIdentify(userInfo.getIdentifyCode());
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
    public PageInfo selectList(int pageNum, int pageSize) {
        Page<Object> page = PageHelper.startPage(pageNum, pageSize);
        List<CertificateInfo> certificateInfos = adminDao.selectAllUserCertificateInfoList();
        long total = page.getTotal();
        List<InfoVo> infoVos = new ArrayList<>();
        for (CertificateInfo c_item :
                certificateInfos) {
            UserInfo u_item = adminDao.selectUserInfoByIdentify(c_item.getIdentifyCode());
            InfoVo infoVo = new InfoVo();
            infoVo.setUserInfo(u_item);
            infoVo.setCertificateInfo(c_item);
            infoVos.add(infoVo);
        }
        PageInfo pageInfo = new PageInfo(infoVos);
        pageInfo.setTotal(total);
        return pageInfo;
    }


    @Override
    public Result selectCerUserInfo(String certificateCode) {
        CertificateInfo certificateInfo = adminDao.selectCertificateInfoByCCode(certificateCode);
        UserInfo userInfo = adminDao.selectUserInfoByIdentify(certificateInfo.getIdentifyCode());

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
        Admin res = adminDao.selectAmin(username, encoder_pass);
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
    public PageInfo selectEnterList( int pageNum, int pageSize){
        Page<Object> page = PageHelper.startPage(pageNum, pageSize);
        List<CertificateInfo> certificateInfos = adminDao.selectAllEnterCertificateInfoList();
        long total = page.getTotal();
        List<InfoVo> infoVos = new ArrayList<>();
        for (CertificateInfo c_item :
                certificateInfos) {
            Enterprise e_item = adminDao.selectEnterInfoByEnId(c_item.getEnterpriseId());
            InfoVo infoVo = new InfoVo();
            infoVo.setEnterprise(e_item);
            infoVo.setCertificateInfo(c_item);
            infoVos.add(infoVo);
        }
        PageInfo<InfoVo> pageInfo = new PageInfo(infoVos);
        pageInfo.setTotal(total);
        return pageInfo;
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

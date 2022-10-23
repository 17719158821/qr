package cn.edu.zzuli.qridentify.service.imp;

import cn.edu.zzuli.qridentify.dao.UserDao;
import cn.edu.zzuli.qridentify.entity.CertificateInfo;
import cn.edu.zzuli.qridentify.entity.Enterprise;
import cn.edu.zzuli.qridentify.entity.InfoVo;
import cn.edu.zzuli.qridentify.entity.UserInfo;
import cn.edu.zzuli.qridentify.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class QueryServiceImpl implements QueryService {
    @Autowired
    UserDao userDao;

    @Override
    public InfoVo getInfo(String identifyCode, String type) {
        InfoVo infoVo = null;
        CertificateInfo res_info = userDao.getCertificateInfo(identifyCode, type);
        UserInfo res_user = userDao.getUserInfo(identifyCode);
        if (res_info != null && res_user != null) {
            infoVo = new InfoVo().setCertificateInfo(res_info).setUserInfo(res_user);
        }
        return infoVo;
    }



    //    根据证书编号查询证书信息，如果是公司所属则返回公司信息，否则返回个人用户
    @Override
    public InfoVo getInfoFromCertificateCode(String code) {
        InfoVo infoVo = null;
        CertificateInfo res_Cer_info = userDao.getCertificateInfoByCertificateCode(code);
//        如果查询到信息
        if (res_Cer_info != null) {
//            并且信息没有身份证，存在公司编号
            if (StringUtils.isEmpty(res_Cer_info.getIdentifyCode()) && (!StringUtils.isEmpty(res_Cer_info.getEnterpriseId()))) {
//                那就是公司证书
                Enterprise res_ent = userDao.getEnterInfoByEnterId(res_Cer_info.getEnterpriseId());
                infoVo = new InfoVo().setCertificateInfo(res_Cer_info).setEnterprise(res_ent);
            } else if ((!StringUtils.isEmpty(res_Cer_info.getIdentifyCode())) && (StringUtils.isEmpty(res_Cer_info.getEnterpriseId()))) {
                UserInfo res_user = userDao.getUserInfo(res_Cer_info.getIdentifyCode());
                infoVo = new InfoVo().setCertificateInfo(res_Cer_info).setUserInfo(res_user);
            }
        }
        return infoVo;
    }

    //    根据公司Id与证书类型查询
    @Override
    public InfoVo getEnterInfo(String enterpriseId, String type) {
        InfoVo infoVo = null;
        CertificateInfo res_info = userDao.getCertificationByEnterId(enterpriseId, type);
        Enterprise res_enter = userDao.getEnterInfo(enterpriseId);
        if (res_info != null && res_enter != null) {
            infoVo = new InfoVo().setCertificateInfo(res_info).setEnterprise(res_enter);
        }
        return infoVo;
    }
}

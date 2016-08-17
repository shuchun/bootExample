package com.example.user.extention;

import com.example.base.EncryptUtil;

/**
 * Created by IBM on 2016/8/2.
 * 验证cookie信息是否有效
 */
public class ValidCookieInfo {

    /**
     * 验证
     * @param id        用户id
     * @param secId     加密id
     * @return          验证结果
     */
    public static boolean valid(String id, String secId){
        String signId= EncryptUtil.encryptByDES(authConfig.getEncryKey(),id,null);
        //String currentDomain=UriPathResolver.getDomain(uriInfo);//验证域有效
        return signId.equalsIgnoreCase(secId);
    }
}

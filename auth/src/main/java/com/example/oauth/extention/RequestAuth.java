package com.example.oauth.extention;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by IBM on 2016/9/7.
 * 请求认证服务类
 */
@Component
public class RequestAuth {

    /**
     * 添加无需认证路径
     * @param path
     * @return
     */
    public RequestAuth anonymous(String path){

        AuthPath.anonymousPaths.add(path);
        return this;
    }

    /**
     * 判断指定路径是否无需认证
     * @param path
     * @return
     */
    public boolean isAnonymousPath(String path){
        return AuthPath.anonymousPaths.contains(path);
    }

    /**
     * 认证路径内部类
     * 主要用于存储认证的路径设置
     */
    private static class AuthPath{
        static Set<String> anonymousPaths;//无需认证路径集合

        static {
            anonymousPaths =new HashSet<String>();
            anonymousPaths.add("/error");//异常路径不做拦截
        }
    }
}

package com.example.oauth.controller;

import com.example.oauth.extention.Anonymous;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by IBM on 2016/9/7.
 * 权限认证controller
 */
@RestController
@RequestMapping("/oauth")
public class AuthController {


    /**
     * 未认证返回结果
     * @param request
     * @param response
     * @return
     */
    @Anonymous(value = "/oauth/noAuth")
    @RequestMapping(value = "/noAuth",method = RequestMethod.GET,produces = {"application/json"})
    public String noAuth(HttpServletRequest request, HttpServletResponse response){
        return "noAuth";
    }

    /**
     * 测试path
     * @return
     */
    @RequestMapping(value="/test",method=RequestMethod.GET,produces = {"application/json"})
    public String test(){
        return "test";
    }
}

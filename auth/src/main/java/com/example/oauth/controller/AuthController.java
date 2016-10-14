package com.example.oauth.controller;

import com.example.oauth.extention.Anonymous;
import com.example.resource.service.ResourceService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private ResourceService resourceService;

    /**
     * 未认证返回结果
     * @param request
     * @param response
     * @return
     */
    @Anonymous(value = "/oauth/noAuth")
    @RequestMapping(value = "/noAuth",method = RequestMethod.GET,produces = {"application/json"})
    public String noAuth(HttpServletRequest request, HttpServletResponse response){
        resourceService.saveResource();
        return "noAuth";
    }

    @Anonymous(value = "/oauth/delAuth")
    @RequestMapping(value = "/delAuth",method = RequestMethod.GET,produces = {"application/json"})
    public String delAuth(HttpServletRequest request, HttpServletResponse response){
        resourceService.delResourceFormRole();
        return "delAuth";
    }

    @Anonymous(value = "/oauth/getAuth")
    @RequestMapping(value = "/getAuth",method = RequestMethod.GET,produces = {"application/json"})
    public String getAuth(HttpServletRequest request, HttpServletResponse response){
       int count= resourceService.getResourceFormRole();
        return count+"";
    }

    @Anonymous(value = "/oauth/saveU")
    @RequestMapping(value = "/saveU",method = RequestMethod.GET,produces = {"application/json"})
    public String saveAuth(HttpServletRequest request, HttpServletResponse response){
        resourceService.saveUser();
        return "saveUser over";
    }

    @Anonymous(value = "/oauth/getU")
    @RequestMapping(value = "/getU",method = RequestMethod.GET,produces = {"application/json"})
    public String getUAuth(HttpServletRequest request, HttpServletResponse response){
        resourceService.getUser();
        return "getUser over";
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

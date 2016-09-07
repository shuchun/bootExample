package com.example.user.controller;

import com.example.base.EncryptUtil;
import com.example.base.Tools;
import com.example.oauth.extention.Anonymous;
import com.example.user.entity.ErrorResponse;
import com.example.user.entity.User;
import com.example.user.extention.ErrorCodeTable;
import com.example.user.extention.ValidCookieInfo;
import com.example.user.extention.authConfig;
import com.example.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IBM on 2016/8/2.
 * 用户controller
 */
@RestController
@RequestMapping("/api")
public class UserController {
    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    /**
     * 用户注册
     * @param request       请求
     * @param response      响应
     * @param name          用户名
     * @param password      用户密码
     * @return              用户信息/错误信息
     */
    @Anonymous(value = "/api/auth/sign_up")
    @RequestMapping(value = "/auth/sign_up",method = RequestMethod.POST,produces = {"application/json"})
    public Object signUp(HttpServletRequest request, HttpServletResponse response, String name, String password){
        boolean nameExists=userService.userNameExists(name);
        if(nameExists){
             response.setStatus(400);
            return new ErrorResponse(ErrorCodeTable.UserNameAlreadyExists.getMsg());
        }
        User user=null;
        try {
            user = userService.addUser(name, password);
        }catch (Exception e){
            LOG.error(ErrorCodeTable.ServerException.getMsgCN());
            e.printStackTrace();
        }

        if(Tools.isNotEmpty(user)){
            response=this.addCookieToResponse(user,request,response);
            response.setStatus(200);

            Map<String,Object> backJson=this.decoratorBackJson(user);
            return backJson;
        }

        response.setStatus(500);
        return new ErrorResponse(ErrorCodeTable.ServerException.getMsg());

    }

    /**
     * 用户登录
     * @param request       请求
     * @param response      响应
     * @param name          用户名
     * @param password      用户密码
     * @param id            用户id，cookie记录
     * @param secId         加密用户id，cookie记录
     * @return              用户/错误信息
     */
    @Anonymous(value = "/api/auth/sign_in")
    @RequestMapping(value = "/auth/sign_in",method = RequestMethod.POST,produces = {"application/json"})
    public Object signIn(HttpServletRequest request, HttpServletResponse response,String name,String password,
                       @CookieValue(value = "id",required = false)String id,@CookieValue(value = "secId",required = false)String secId){
        Map<String,Object > backInfo;
        //不安全
        /*if(ValidCookieInfo.valid(id,secId)&&){//cookie有效
            User cookieUser=new User();
            cookieUser.setId(Long.valueOf(id));
            cookieUser.setName(name);


            response=this.addCookieToResponse(cookieUser,request,response);
            response.setStatus(200);

            backInfo=this.decoratorBackJson(cookieUser);

            return backInfo;
        }*/
        boolean nameExists=userService.userNameExists(name);
        if(nameExists) {//用户名存在

            User user=null;
            try{
                user=userService.getUser(name,password);
            }catch (Exception e){
                LOG.info(ErrorCodeTable.AuthFailure.getMsgCN());
            }
            if(Tools.isNotEmpty(user)){//有效

                response=this.addCookieToResponse(user,request,response);
                response.setStatus(200);
                backInfo=this.decoratorBackJson(user);

                return backInfo;
            }else{//用户名、密码不对应
                response.setStatus(400);
                return new ErrorResponse(ErrorCodeTable.AuthFailure.getMsg());
            }
        }
        //用户不存在
        response.setStatus(400);
        return new ErrorResponse(ErrorCodeTable.UserNotExists.getMsg());
    }

    /**
     * 获取用户信息
     * @param request   请求
     * @param response  响应
     * @param id        用户id
     * @param secId     加密id
     * @return          用户信息/错误提示
     */
    @Anonymous(value = "/api/users/info")
    @RequestMapping(value = "/users/info",method = RequestMethod.GET,produces = {"application/json"})
    public Object getUserInfo(HttpServletRequest request, HttpServletResponse response,
                                     @CookieValue(value = "id",required = false)String id, @CookieValue(value = "secId",required = false)String secId){

        if(Tools.isNotEmpty(id)&&Tools.isNotEmpty(secId)){
            if(ValidCookieInfo.valid(id,secId)){
                Long parseId=Long.valueOf(id);
                try {
                    User user = userService.getUser(parseId);

                    if(Tools.isNotEmpty(user)){
                        response = this.addCookieToResponse(user, request, response);
                        response.setStatus(200);

                        Map<String, Object> backJson = Tools.toMap(user, true);
                        backJson.remove("password");

                        return backJson;
                    }else{//没有取到用户信息
                        response.setStatus(400);
                        return new ErrorResponse(ErrorCodeTable.UserNotExists.getMsg());
                    }

                }catch (Exception e){
                    e.printStackTrace();
                    LOG.error(ErrorCodeTable.ServerException.getMsgCN());
                }

            }
        }


        response.setStatus(400);
        return new ErrorResponse(ErrorCodeTable.UserNotLogin.getMsg());
    }

    /**
     * 更新用户信息
     * @param request       请求
     * @param response      响应
     * @param age           年龄
     * @param gender        性别
     * @param id            用户id
     * @param secId         加密id
     * @return              修改后的用户信息/错误提示
     */
    @Anonymous(value = "/api/users/info")
    @RequestMapping(value = "/users/info",method = RequestMethod.PUT,produces = {"application/json"})
    public Object updateUserInfo(HttpServletRequest request,HttpServletResponse response,int age,String gender,
                               @CookieValue(value = "id",required = false)String id, @CookieValue(value = "secId",required = false)String secId){
        if(Tools.isNotEmpty(id)&&Tools.isNotEmpty(secId)){
            if (ValidCookieInfo.valid(id, secId)) {
                Long parseId=Long.valueOf(id);
                User oldUser=null;
                try{
                    oldUser= userService.getUser(parseId);//原来的用户信息
                }catch (Exception e){
                    e.printStackTrace();
                    LOG.info(ErrorCodeTable.UserNotLogin.getMsgCN());
                }
                if(!Tools.isNotEmpty(oldUser)){
                    response.setStatus(400);
                    return new ErrorResponse(ErrorCodeTable.UserNotLogin.getMsg());
                }
                User user=userService.updateUser(parseId,oldUser.getName(),null,age,gender);

                //User user=userService.updateUser(parseId,null,null,age,gender);

                response=this.addCookieToResponse(user,request,response);
                response.setStatus(200);

                Map<String,Object> backJson=Tools.toMap(user,true);
                backJson.remove("password");

                return backJson;
            }
        }


        response.setStatus(400);
        return new ErrorResponse(ErrorCodeTable.UserNotLogin.getMsg());
    }

    /**
     * 登出
     * @param request       请求
     * @param response      响应
     * @param id            用户id
     * @param secId         加密id
     * @return              返回信息
     */
    @Anonymous(value = "/api/auth/sign_out")
    @RequestMapping(value="/auth/sign_out",method = RequestMethod.DELETE,produces = {"application/json"})
    public Object signOut(HttpServletRequest request,HttpServletResponse response,
                        @CookieValue(value = "id",required = false)String id, @CookieValue(value = "secId",required = false)String secId){
        if(Tools.isNotEmpty(id)&&Tools.isNotEmpty(secId)) {
            if (ValidCookieInfo.valid(id, secId)) {
                Cookie[] cookies=request.getCookies();
                for(int i=0;i<cookies.length;i++){
                    Cookie cookie=cookies[i];

                    if(cookie.getName().equalsIgnoreCase("id")||cookie.getName().equalsIgnoreCase("secId")){
                        cookie.setValue("");
                        cookie.setMaxAge(0);
                        cookie.setDomain(request.getContextPath());//域
                        cookie.setPath("/");

                        response.addCookie(cookie);
                    }
                }
                response.setStatus(200);
            }
        }
        Map<String,Object> backJson=new HashMap<>();
        backJson.put("status","SignOutSuccess");
        return backJson;
    }



    /**
     * 组装返回需要的信息
     * @param user      用户实体
     * @return          键值对
     */
    private Map<String,Object> decoratorBackJson(User user){
        Map<String,Object> backJson=new HashMap<>();

        if(Tools.isNotEmpty(user)) {
            backJson.put("id", user.getId());
            backJson.put("name", user.getName());
        }

        return backJson;
    }

    /**
     * 添加cookie信息到响应头
     * @param user          用户
     * @param request       请求
     * @param response      响应
     * @return              响应
     */
    private HttpServletResponse addCookieToResponse(User user, HttpServletRequest request,HttpServletResponse response){
        //设置cookie信息
        Cookie cookieId=new Cookie("id",user.getId().toString());
        Cookie cookieSecId=new Cookie("secId", EncryptUtil.encryptByDES(authConfig.getEncryKey(),user.getId().toString(),null));

        cookieId.setMaxAge(3*60);//3min=3*60sec;//过期时间
        cookieId.setDomain(request.getContextPath());//域
        cookieId.setPath("/");//路径
        cookieSecId.setMaxAge(3*60);
        cookieSecId.setDomain(request.getContextPath());
        cookieSecId.setPath("/");

        response.addCookie(cookieId);
        response.addCookie(cookieSecId);

        return response;
    }

}

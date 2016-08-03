package com.example.user.resources;

import com.example.base.EncryptUtil;
import com.example.base.Tools;
import com.example.user.entity.ErrorResponse;
import com.example.user.entity.User;
import com.example.user.extention.ErrorCodeTable;
import com.example.user.extention.UriPathResolver;
import com.example.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IBM on 2016/8/1.
 * 登录、注册、认证
 */
@Path("/auth")
@Component
public class AuthResource {
    @Autowired
    private UserService userService;

    /**
     * 用户注册
     * @param  userName     用户名
     * @param  userPwd      用户密码
     * @param  uriInfo      uri信息
     * @return              响应信息
     */
    @Path("/sign_up")
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    public Response signUp(@FormParam(value="name") String userName, @FormParam(value="password") String userPwd,
                           @Context UriInfo uriInfo){
        boolean nameExists=userService.userNameExists(userName);
        if(nameExists){
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorResponse(ErrorCodeTable.UserNameAlreadyEXists.getMsg())).build();
        }
        User user=userService.addUser(userName,userPwd);

        Map<String,Object> backJson=this.decoratorBackJson(user);
        String domain=UriPathResolver.getDomain(uriInfo);

        Response.ResponseBuilder response=Response.ok()
                                          .cookie(NewCookie.valueOf("id="+user.getId()))//用户id
                                          .cookie(NewCookie.valueOf("secId="+EncryptUtil.encryptByMD5(user.getId().toString())))//加密
                                          .cookie(NewCookie.valueOf("domain="+domain))//注册域
                                          .entity(backJson);

        return  response.build();
    }

    /**
     * 用户登录
     * @param userName      用户名
     * @param userPwd       用户密码
     * @param id            用户id
     * @param secId         加密id
     * @param domain        cookie注册域
     * @return              响应信息
     */
    @Path("/sign_in")
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    public Response signIn(@FormParam(value="name") String userName,@FormParam(value="password")String userPwd,
                         @CookieParam(value = "id")String id,@CookieParam(value = "secId")String secId,
                         @CookieParam(value = "domain")String domain,@Context UriInfo uriInfo){
        boolean nameExists=userService.userNameExists(userName);
        if(nameExists){//用户名存在
            Map<String,Object > backInfo=new HashMap<>();

            User user=userService.getUser(userName,userPwd);
            if(Tools.isNotEmpty(user)){
                backInfo.put("id",user.getId());
                backInfo.put("name",user.getName());

                Response.ResponseBuilder response= Response.ok()
                                                   .entity(backInfo)//返回主体
                                                   .cookie(NewCookie.valueOf("id="+user.getId()))//用户id
                                                   .cookie(NewCookie.valueOf("secId="+EncryptUtil.encryptByMD5(user.getId().toString())))//加密id
                                                   .cookie(NewCookie.valueOf("domain="+UriPathResolver.getDomain(uriInfo)));//登录域
                return response.build();
            }else{//用户名密码不对应
                return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorResponse(ErrorCodeTable.AuthFailure.getMsg())).build();
            }
        }else{//用户不存在
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorResponse(ErrorCodeTable.UserNotEXists.getMsg())).build();
        }
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
}

package com.example.user.resources;

import com.example.base.EncryptUtil;
import com.example.base.Tools;
import com.example.user.entity.ErrorResponse;
import com.example.user.entity.User;
import com.example.user.extention.ErrorCodeTable;
import com.example.user.extention.UriPathResolver;
import com.example.user.extention.ValidCookieInfo;
import com.example.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.HttpCookie;
import java.net.URI;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IBM on 2016/8/1.
 * 用户信息处理接口
 */
@Path("/users")
@Component
public class UserResource {

    @Autowired
    private UserService userService;

    /**
     * 获取用户信息
     */
    @Path("/info")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getUser(@CookieParam(value = "id")String id, @CookieParam(value = "secId")String secId,
                            @CookieParam(value = "domain")String domain, @Context UriInfo uriInfo,
                            @Context Request request){

        if(Tools.isNotEmpty(id)&&Tools.isNotEmpty(secId)&&Tools.isNotEmpty(domain)){

            if(ValidCookieInfo.valid(id,secId)){//cookie验证有效
                Long parseId=Long.valueOf(id);
                User user=userService.getUser(parseId);

                Map<String,Object> backJson=this.decoratorBackJson(user);

                Response.ResponseBuilder response=Response.ok()
                                                  .entity(backJson);
                return  response.build();
            }

        }
        return Response.status(Response.Status.UNAUTHORIZED).entity(new ErrorResponse(ErrorCodeTable.UserNotLogin.getMsg())).build();

    }

    /**
     * 修改用户的信息
     * @param age
     * @param gender
     */
    @Path("/info")
    @PUT
    @Produces({MediaType.APPLICATION_JSON})
    public Response updateUserInfo(@CookieParam(value="id")String id,@CookieParam(value = "secId")String secId,
                                 @CookieParam(value = "domain")String domain,@Context UriInfo uriInfo,
                                 @CookieParam(value = "name")String name,
                                 @FormParam(value = "age") int age,@FormParam(value = "gender")String gender){
        if(Tools.isNotEmpty(id)&&Tools.isNotEmpty(secId)&&Tools.isNotEmpty(domain)) {

            if (ValidCookieInfo.valid(id,secId)) {//cookie验证有效
                Long parseId=Long.valueOf(id);
                User user=userService.updateUserByIdAndName(parseId,name,age,gender);
                Map<String,Object> backJson=this.decoratorBackJson(user);

                //返回响应信息
                Response.ResponseBuilder response=Response.ok()
                                                  .entity(backJson);
                return  response.build();
            }
        }
        return Response.status(Response.Status.UNAUTHORIZED).entity(new ErrorResponse(ErrorCodeTable.UserNotLogin.getMsg())).build();

    }

    /**
     * 测试方法
     * @return
     */
    @Path("/test")
    @GET
    public String test(){
        return "ok";
    }
    /**
     * 组装返回需要的信息
     * @param user      用户实体
     * @return          键值对
     */
    private Map<String,Object> decoratorBackJson(User user){
        Map<String,Object> backJson=new HashMap<>();

        if(Tools.isNotEmpty(user)){
            backJson.put("id",user.getId());
            backJson.put("name",user.getName());
            backJson.put("age",user.getAge());
            backJson.put("gender",user.getGender());
        }

        return backJson;
    }
}

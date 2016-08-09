package com.example.user.controller;

import com.example.base.EncryptUtil;
import com.example.user.extention.ErrorCodeTable;
import com.example.user.extention.authConfig;
import org.assertj.core.api.Assertions;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.rule.OutputCapture;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.Cookie;

/**
 * Created by IBM on 2016/8/4.
 * controller 层测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserControllerTest {

    @ClassRule
    public static OutputCapture out = new OutputCapture();

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    private String name="testName";
    private String password="testPwd";

    @Before
    public void setUp(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        request = new MockHttpServletRequest();
        request.setCharacterEncoding("UTF-8");
        response = new MockHttpServletResponse();
    }

    @After
    public void tearDown(){

    }

    /**
     * 用户注册测试用例
     */
    @Test
    public void asignUpTest() throws Exception {//添加a字母是为了首先执行注册测试，否则后续依赖用户的测试将失败
        //调用服务
         MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/auth/sign_up",request,response)//请求路径
                .param("name",name)//请求参数
                .param("password",password))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())//判断返回状态
                .andExpect(MockMvcResultMatchers.cookie().exists("id"))//判断cookie信息
                .andExpect(MockMvcResultMatchers.cookie().exists("secId"))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))//判断返回头信息
                .andReturn();
        //判断返回结果
        Assertions.assertThat(result.getResponse().getContentAsString())
                .isNotEmpty().contains(name);

    }

    /**
     * 姓名重复注册失败测试用例
     * @throws Exception
     */
    @Test
    public void nameExistsSignUpTest() throws Exception {

        MvcResult nameExistsResult = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/auth/sign_up",request,response)
                        .param("name",name)
                        .param("password",password))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andReturn();

        Assertions.assertThat(nameExistsResult.getResponse().getContentAsString())
                .isNotEmpty().contains("error").contains(ErrorCodeTable.UserNameAlreadyExists.getMsg());
    }

    /**
     * 用户登录测试用例
     */
    @Test
    public void signInTest() throws Exception {
        MvcResult result=mockMvc.perform(
                MockMvcRequestBuilders.post("/api/auth/sign_in",request,response)
                        .param("name",name)
                        .param("password",password))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andReturn();
        Assertions.assertThat(result.getResponse().getContentAsString())
                .isNotEmpty().contains(name);

    }

    /**
     * 用户不存在登录失败测试用例
     */
    public void userNotExistsSignInTest() throws Exception {
        String errorName="error";
        MvcResult userNotResult=mockMvc.perform(
                MockMvcRequestBuilders.post("/api/auth/sign_in",request,response)
                        .param("name",errorName)
                        .param("password",password))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andReturn();
        Assertions.assertThat(userNotResult.getResponse().getContentAsString())
                .isNotEmpty().contains("error").contains(ErrorCodeTable.UserNotExists.getMsg());
    }

    /**
     * 用户登录信息错误测试用例
     */
    @Test
    public void userSigninInfoErrorTest() throws Exception {
        String errorPwd="error";

        MvcResult loginFailResult=mockMvc.perform(
                MockMvcRequestBuilders.post("/api/auth/sign_in",request,response)
                .param("name",name)
                .param("password",errorPwd))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andReturn();
        Assertions.assertThat(loginFailResult.getResponse().getContentAsString())
                .isNotEmpty().contains("error").contains(ErrorCodeTable.AuthFailure.getMsg());
    }

    /**
     * 用户信息获取测试用例
     */
    @Test
    public void getUserInfoTest() throws Exception {
        String id="1";//用户id
        String secId= EncryptUtil.encryptByDES(authConfig.getEncryKey(),id,null);//加密用户id

        MvcResult userInfo=mockMvc.perform(
                MockMvcRequestBuilders.get("/api/users/info",request,response)//get 请求
                        .cookie(new Cookie("id",id))//设置请求中携带的cookie信息
                        .cookie(new Cookie("secId",secId)))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andReturn();

        Assertions.assertThat(userInfo.getResponse().getContentAsString())
                .isNotEmpty().contains("id").contains(id);
    }

    /**
     * cookie信息不匹配错误测试用例
     */
    public void cookieNotEqualErrorTest() throws Exception {
        String id="1";
        String secId="xxx";

        MvcResult errorInfo =mockMvc.perform(
                MockMvcRequestBuilders.get("/api/users/info",request,response)//get 请求
                        .cookie(new Cookie("id",id))//设置请求中携带的cookie信息
                        .cookie(new Cookie("secId",secId)))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andReturn();

        Assertions.assertThat(errorInfo.getResponse().getContentAsString())
                .isNotEmpty().contains("error").contains(ErrorCodeTable.UserNotLogin.getMsg());
    }

    /**
     * 不存在用户信息错误测试用例
     */
    public void notUserInfoErrorTest() throws Exception {
        String id="-1";
        String secId=EncryptUtil.encryptByDES(authConfig.getEncryKey(),id,null);

        MvcResult errorInfo =mockMvc.perform(
                MockMvcRequestBuilders.get("/api/users/info",request,response)//get 请求
                        .cookie(new Cookie("id",id))//设置请求中携带的cookie信息
                        .cookie(new Cookie("secId",secId)))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andReturn();

        Assertions.assertThat(errorInfo.getResponse().getContentAsString())
                .isNotEmpty().contains("error").contains(ErrorCodeTable.UserNotLogin.getMsg());
    }

    /**
     * 更新用户信息测试用例
     */
    @Test
    public void updateUserInfoTest() throws Exception {
        int age=50;//年龄
        String gender="M";//性别:M(ale)&F(emale)

        String id="1";//用户id
        String secId= EncryptUtil.encryptByDES(authConfig.getEncryKey(),id,null);//加密用户id

        MvcResult newUserInfo =mockMvc.perform(
                MockMvcRequestBuilders.put("/api/users/info",request,response)//get 请求
                        .cookie(new Cookie("id",id))//设置请求中携带的cookie信息
                        .cookie(new Cookie("secId",secId))
                        .param("age",String.valueOf(age))//虽然接收的是int参数，但是在请求时只需传递String类型，age定义为int只是为了提醒接收类型
                        .param("gender",gender))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andReturn();

        Assertions.assertThat(newUserInfo.getResponse().getContentAsString())
                .isNotEmpty().contains("50").contains(gender);
    }

    /**
     * 登出测试用例
     * @throws Exception
     */
    @Test
    public void zSignOutTest() throws Exception {
        String id="1";//用户id
        String secId= EncryptUtil.encryptByDES(authConfig.getEncryKey(),id,null);//加密用户id

        MvcResult signOut =mockMvc.perform(
                MockMvcRequestBuilders.delete("/api/auth/sign_out",request,response)//get 请求
                        .cookie(new Cookie("id",id))//设置请求中携带的cookie信息
                        .cookie(new Cookie("secId",secId)))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.cookie().maxAge("id",0))//cookie不存在
                .andExpect(MockMvcResultMatchers.cookie().maxAge("secId",0))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andReturn();

        Assertions.assertThat(signOut.getResponse().getContentAsString()).contains("status").contains("SignOutSuccess");
    }

}

package com.example.user.service;

import com.example.user.entity.User;
import jdk.Exported;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by IBM on 2016/8/5.
 * UserService 测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    private String name="serviceTestName";
    private String password="serviceTestPwd";

    @Before
    public void setUp(){
        Assertions.assertThat(userService).isNotNull();
    }

    @After
    public void tearDown(){

    }

    /**
     * 用户添加测试用例
     */
    @Test
    public void addUserTest(){
        User user=userService.addUser(name,password);
        Assertions.assertThat(user).isNotNull().hasFieldOrPropertyWithValue("name",name)
                .hasFieldOrProperty("age");
    }

    /**
     * 通过用户名密码获取用户测试用例
     */
    @Test
    public void getUserByNameAndPwdTest(){

        User user=userService.getUser(name,password);//查询用户

        Assertions.assertThat(user).isNotNull().hasFieldOrPropertyWithValue("name",name)
                .hasFieldOrProperty("id").hasFieldOrProperty("age");//判断
    }

    /**
     * 通过用户id获取用户测试用例
     */
    @Test
    public void getUserById(){
        User user1=userService.getUser(name,password);//查询用户
        Assertions.assertThat(user1).isNotNull();
        Long id=user1.getId();
        User user2=userService.getUser(id);

        Assertions.assertThat(user2).isNotNull().hasFieldOrPropertyWithValue("name",name)
                .hasFieldOrPropertyWithValue("id",id).hasFieldOrProperty("age");
        Assertions.assertThat(user1).isEqualToComparingFieldByField(user2);

    }

    /**
     * 获取不存在的用户测试用例
     *
     */
    @Test(expected = NullPointerException.class)//判断会抛出的异常
    public void getNotExistsUserErrorTest(){
        userService.getUser(-1L);
    }

    /**
     * 用户名存在测试用例
     */
    @Test
    public void userNameExistsTest(){
        boolean exists=userService.userNameExists(name);

        Assertions.assertThat(exists).isTrue();
    }

    /**
     * 根据用户id和用户名修改用户信息测试用例
     */
    @Test
    public void updateUserByIdAndNameTest(){
        int age=30;
        String gender="M";
        User user1=userService.getUser(name,password);//查询用户
        Assertions.assertThat(user1).isNotNull();

        User user=userService.updateUserByIdAndName(user1.getId(),name,age,gender);

        Assertions.assertThat(user).isNotNull().hasFieldOrPropertyWithValue("age",age)
                .hasFieldOrPropertyWithValue("gender",gender).hasNoNullFieldsOrProperties();
    }

    /**
     * 修改用户信息失败测试用例
     */
    @Test
    public void updateUserErrorTest(){
        String newName="otherName";
        int age=80;
        String gender="X";
        User user1=userService.getUser(name,password);//查询用户
        Assertions.assertThat(user1).isNotNull();

        User user=userService.updateUserByIdAndName(user1.getId(),newName,age,gender);

        Assertions.assertThat(user).isNotNull();
        Assertions.assertThat(user.getAge()).isNotEqualTo(age);
        Assertions.assertThat(user.getGender()).isNotEqualTo(gender);
    }

    /**
     * 修改用户信息测试
     */
    @Test
    public void updateUserTest(){
        int age=50;
        String gender="F";
        String pwd="newPwd";
        User user1=userService.getUser(name,password);//查询用户
        Assertions.assertThat(user1).isNotNull();

        User user=userService.updateUser(user1.getId(),null,pwd,age,gender);

        Assertions.assertThat(user).isNotNull().hasFieldOrPropertyWithValue("name",name)
                .hasFieldOrPropertyWithValue("password",pwd).hasFieldOrPropertyWithValue("age",age)
                .hasFieldOrPropertyWithValue("gender",gender);
    }


}

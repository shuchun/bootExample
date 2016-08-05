package com.example.user.dao;

import com.example.user.entity.User;
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

import java.util.List;

/**
 * Created by IBM on 2016/8/5.
 * 用户Repository测试类
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private String name="japTestName";
    private String password="jpaTestPwd";
    private int age=10;
    private String gender="M";

    @Before
    public void setUp(){
        Assertions.assertThat(userRepository).isNotNull();

    }

    @After
    public void tearDown(){

    }

    /**
     * 保存测试
     */
    @Test
    public void aSaveTest(){
        User user= userRepository.save(new User(name,password,age,gender));

        Assertions.assertThat(user).isNotNull()
                .hasNoNullFieldsOrProperties().hasFieldOrProperty("id");
    }

    /**
     * 获取测试
     */
    @Test
    public void getTest(){
        User user=userRepository.getUserByNameAndPassword(name,password);
        user=userRepository.getOne(user.getId());

        Assertions.assertThat(user).isNotNull().hasFieldOrPropertyWithValue("name",name);
    }

    /**
     * count 测试
     */
    public void countTest(){
        Long count=userRepository.count();

        Assertions.assertThat(count).isNotNull();
    }

    /**
     * 通过name获取用户测试
     */
    @Test
    public void findUserByNameTest(){
        List<User> users=userRepository.findUserByName(name);

        Assertions.assertThat(users).isNotNull();
    }

    /**
     * 通过用户名，密码获取用户信息测试
     */
    public void getUserByNameAndPasswordTest(){
        User user=userRepository.getUserByNameAndPassword(name,password);

        Assertions.assertThat(user).isNotNull().hasNoNullFieldsOrProperties();

    }

    /**
     * 删除测试
     */
    @Test
    public void zDeleteTest(){
        User user=userRepository.getUserByNameAndPassword(name,password);
        Assertions.assertThat(user).isNotNull();

        userRepository.delete(user);

        user=userRepository.getUserByNameAndPassword(name,password);
        Assertions.assertThat(user).isNull();
    }

}

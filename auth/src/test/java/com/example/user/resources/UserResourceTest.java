package com.example.user.resources;

import org.assertj.core.api.Assertions;
import org.assertj.core.util.Compatibility;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.rule.OutputCapture;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IBM on 2016/8/1.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserResourceTest {

    @Value("${local.server.port}")
    private int port;//测试用例的随机端口
    @Autowired
    private TestRestTemplate restTemplate;//RESTFUL 调用模版
    private String baseUri;

    @Rule
    public OutputCapture capture = new OutputCapture();//测试控制台输出语句

    @Before
    public void setUp(){
        baseUri="http://localhost:" + this.port + "/webapi/";
        System.out.println("BaseUri:"+baseUri);
    }

    @After
    public void tearDown(){

    }

    /**
     * jsersey 测试方法
     */
    @Test
    public void test(){
        String result=restTemplate.getForObject(baseUri+"users/test",String.class,new Object(){});
        Assertions.assertThat(result).isEqualTo("ok");
    }


}

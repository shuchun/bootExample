package com.example.user.dao;

import com.example.user.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    private Long initId=10L;
    private int age=10;
    private String gender="M";
    private int  batchNum=5;


    @Before
    public void setUp(){
        Assertions.assertThat(userRepository).isNotNull();
        Long count=userRepository.count();
        if(userRepository.findUserByName(name).size()==0){
            userRepository.save(new User(initId,name,password,age,gender));//保存一条验证数据
            if(count<=batchNum) {//添加批量数据用于分页查询，排序
                for (int i = 0; i < batchNum; i++) {
                    System.out.println("i:"+i);
                    userRepository.save(new User(Long.valueOf(i),name+i,password+i,i,gender));
                }
            }
        }

    }

    @After
    public void tearDown(){

    }

    /**
     * 保存测试
     */
    @Test
    public void aSaveTest(){

        User user= userRepository.save(new User(initId+1,"testSave","savePwd",10,"M"));

        Assertions.assertThat(user).isNotNull()
                .hasNoNullFieldsOrProperties().hasFieldOrProperty("id");
    }

    /**
     * 获取测试
     */
    @Test
    public void getTest(){
        User user=userRepository.getUserByNameAndPassword(name,password);
        user=userRepository.findOne(user.getId());

        Assertions.assertThat(user).isNotNull().hasFieldOrPropertyWithValue("name",name);
    }

    /**
     * count 测试
     */
    @Test
    public void countTest(){
        Long count=userRepository.count();
        System.out.println("count:"+count);
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

    /**
     * 分页查询测试
     */
    @Test
    public void pageQueryTest(){
        int page=5;//页码,页码是从0开始的
        int rowNum=5;//每页条数
        Page<User> users=userRepository.findAll(new PageRequest(page,rowNum));


        Assertions.assertThat(users.getSize()).isEqualTo(rowNum);//每页条数
        Assertions.assertThat(users.getNumber()).isEqualTo(page);//当前的页码
        Assertions.assertThat(users.getNumberOfElements()).isBetween(0,rowNum);//当前页的条数
        Assertions.assertThat(users.getTotalElements()).isEqualTo(userRepository.count());//总条数
        Assertions.assertThat(users.hasContent()).isEqualTo(users.getNumberOfElements()>0);//是否有内容
        Assertions.assertThat(users.getTotalPages()).isBetween((int)userRepository.count()/rowNum,(int)userRepository.count()/rowNum+1);//总页数

        Assertions.assertThat(users.hasNext()).isEqualTo(!users.isLast());//是否有下一页&是否是末页
        Assertions.assertThat(users.isFirst()).isEqualTo(!users.hasPrevious());//是否是首页&是否有前一页
    }

    /**
     * 排序查询测试用例
     */
    @Test
    public void sortQueryTest(){
        List<User> users=userRepository.findAll(new Sort(Sort.Direction.ASC,"age"));

        Assertions.assertThat(users).isNotNull();
        Assertions.assertThat(users.get(0).getAge()).hasNoNullFieldsOrProperties();
        Assertions.assertThat(users.get(0).getAge()).isEqualTo(0);

    }

    /**
     * like查询并且排序测试用例
     */
    @Test
    public void likeQueryTest(){
        List<User> users=userRepository.findByNameStartingWithIgnoreCase(name,new Sort(Sort.Direction.DESC,"age"));

        Assertions.assertThat(users).isNotNull();
        Assertions.assertThat(users.get(users.size()-1).getName()).isEqualTo(name+0);
    }

}

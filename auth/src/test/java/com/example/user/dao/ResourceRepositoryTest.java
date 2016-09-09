package com.example.user.dao;

import com.example.resource.dao.ResourceRepository;
import com.example.resource.entity.Resource;
import com.example.role.dao.RoleRepository;
import com.example.role.entity.Role;
import com.example.user.entity.User;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by IBM on 2016/9/8.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ResourceRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ResourceRepository appMenuRepository;


    @Test
    public void addTest(){
        User user=new User();
        user.setId(100L);
        user.setName("u100");
        user.setPassword("p100");
        user.setAge(20);

        Resource menu=new Resource();
        menu.setId(102L);
        menu.setResourceName("index");
        menu.setResourceUrl("/aa/bb/test");
        menu.setLevel(1L);

        Role role=new Role();
        role.setId(101L);
        role.setRoleName("admin");
        role.getUser().add(user);
        role.getResources().add(menu);

        userRepository.saveAndFlush(user);
        roleRepository.saveAndFlush(role);
        appMenuRepository.saveAndFlush(menu);

    }
}

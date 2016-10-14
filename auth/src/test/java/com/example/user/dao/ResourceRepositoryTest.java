package com.example.user.dao;

import com.example.resource.dao.ResourceRepository;
import com.example.resource.entity.Resource;
import com.example.role.dao.RoleRepository;
import com.example.role.entity.Role;
import com.example.user.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Set;

/**
 * Created by IBM on 2016/9/8.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ResourceRepositoryTest {

    @Autowired
    private ResourceRepository resourceRepository;
    @Autowired
    private RoleRepository roleRepository;

    private Long id=50L;


    @Test
    public void addTest(){

        Resource resource=new Resource();
        resource.setId(100L);
        resource.setResourceName("index");
        resource.setResourceUrl("/aa/bb/test");
        resource.setLevel(1);
        resource.setResourceOrder(1);

        resourceRepository.save(resource);
    }

    @Test
    public void getTest(){
        Resource resource=new Resource();
        resource.setId(101L);
        resource.setResourceName("index");
        resource.setResourceUrl("/test/index");
        resource.setLevel(1);
        resource.setResourceOrder(1);

        resourceRepository.saveAndFlush(resource);

        resource=resourceRepository.getOne(101L);

        Assertions.assertThat(resource).isNotNull();
        System.out.println(resource.getResourceName());
        //Assertions.assertThat(resource.getResourceName()).isEqualTo("index");
    }

    @Test
    public void findEnableResourceForUserTest(){
        Resource resource=new Resource();
        resource.setId(103L);
        resource.setResourceName("index");
        resource.setResourceUrl("/test/index");
        resource.setLevel(1);
        resource.setResourceOrder(1);

        Resource resource2=new Resource();
        resource.setId(104L);
        resource.setResourceName("home");
        resource.setResourceUrl("/test/home");
        resource.setLevel(1);
        resource.setResourceOrder(1);

        Role role=new Role();
        role.setId(10L);
        role.setRoleName("admin");
        role.setRoleStatus("1");

        role.getResources().add(resource);
        role.getResources().add(resource2);


        resourceRepository.save(resource);
        resourceRepository.save(resource2);
        roleRepository.save(role);

    }
}

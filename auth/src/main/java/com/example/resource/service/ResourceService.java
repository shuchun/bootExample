package com.example.resource.service;

import com.example.resource.dao.ResourceRepository;
import com.example.resource.entity.Resource;
import com.example.role.dao.RoleRepository;
import com.example.role.entity.Role;
import com.example.user.dao.UserRepository;
import com.example.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * Created by IBM on 2016/9/10.
 */
@Service
@Transactional(rollbackFor = {Exception.class})
public class ResourceService {

    @Autowired
    private ResourceRepository resourceRepository;
    @Autowired
    private RoleRepository roleRepository;

    public void saveResource(){

        Resource resource=new Resource();
        //resource.setId(103L);
        resource.setResourceName("index");
        resource.setResourceUrl("/test/index");
        resource.setLevel(1);
        resource.setResourceOrder(1);

        Resource resource2=new Resource();
        //resource.setId(104L);
        resource.setResourceName("home");
        resource.setResourceUrl("/test/home");
        resource.setLevel(1);
        resource.setResourceOrder(1);

        Role role=new Role();
        //role.setId(10L);
        role.setRoleName("admin");
        role.setRoleStatus("1");

        role.getResources().add(resource);
        role.getResources().add(resource2);

        resourceRepository.saveAndFlush(resource);
        resourceRepository.saveAndFlush(resource2);
        roleRepository.saveAndFlush(role);

    }

    public int getResourceFormRole(){
        //获取级联对象方法1：直接获取其中一个对象
        //Role role=roleRepository.findOne(1L);
        //System.out.println("getResourceFromRole:"+role.getResources().size());
        //获取级联对象方法2：通过自定义sql语句
        List<Resource> resourceList=resourceRepository.findEnableResourceForUser(1L);

        return resourceList.size();

    }

    public void delResourceFormRole(){
        Role role=roleRepository.findOne(1L);

        System.out.println(role.getResources().size());
        Set<Resource> resourceList=role.getResources();
       /* for(Resource resource:resourceList){
            System.out.println(resource.getResourceName()+":"+resource.getResourceUrl());
        }*/

        //删除级联对象
        role.setResources(null);

        roleRepository.save(role);
    }

}

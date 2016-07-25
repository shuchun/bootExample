package com.example.service;

import com.example.domain.Person;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IBM on 2016/7/25.
 * 用户service
 */
@Service
public class PersonHystrixService {

    @Autowired
    private PersonService personService;//远程服务调用

    /**
     * 添加用户
     * @param name
     * @return
     */
    @HystrixCommand(fallbackMethod = "fallbackSave")//断路降级方法配置
    public List<Person> save(String name){
        //System.out.println("===========================================================================");
        return personService.save(name);
    }

    /**
     * 断路降级处理方法
     * @param name
     * @return
     */
    public List<Person> fallbackSave(String name){
        List<Person> list=new ArrayList<>();
        Person p=new Person("Person Service 故障"+name);
        list.add(p);
       // System.out.println("personService:fallbackSave----------------------------------------------------------");
        return list;

    }
}

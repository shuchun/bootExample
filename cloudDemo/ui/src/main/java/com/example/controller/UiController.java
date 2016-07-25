package com.example.controller;

import com.example.domain.Person;
import com.example.service.PersonHystrixService;
import com.example.service.SomeHystrixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by IBM on 2016/7/25.
 * 对外网关服务
 */
@RestController
public class UiController {
    @Autowired
    private SomeHystrixService someHystrixService;          //其它服务处理
    @Autowired
    private PersonHystrixService personHystrixService;      //用户服务处理

    /**
     * 用户服务对外网关
     * @param personName
     * @return
     */
    @RequestMapping("/dispatch")
    public List<Person> sendMessage(@RequestBody String personName){
        //System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~111");
        return personHystrixService.save(personName);
    }

    /**
     * 其它服务对外网关
     * @return
     */
    @RequestMapping(value="/getsome",produces = {MediaType.TEXT_PLAIN_VALUE})
    public String getSome(){
        //System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~222");
        return  someHystrixService.getSome();
    }
}

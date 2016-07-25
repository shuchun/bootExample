package com.example.service;

import com.example.domain.Person;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by IBM on 2016/7/25.
 * FeignClient调用外部服务示例
 */
@FeignClient("person")//Feign客户端连接服务中心的person服务
public interface PersonService {

    /**
     * 添加用户
     * @param name
     * @return
     */
    @RequestMapping(value="/save",method= RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE)//具体服务接口的调用声明
    @ResponseBody
    List<Person> save(@RequestBody String name);
}

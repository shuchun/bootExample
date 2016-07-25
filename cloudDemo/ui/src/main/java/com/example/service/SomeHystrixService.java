package com.example.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by IBM on 2016/7/25.
 * Ribbon调用服务service示例
 */
@Service
public class SomeHystrixService {

    @Autowired
    private RestTemplate restTemplate;//restTemplate,Ribbon客户端(已配置负载均衡)

    /**
     * 获取其他服务
     * @return
     */
   @HystrixCommand(fallbackMethod = "fallbackSome")//熔断降级方法
    public String getSome(){
        //System.out.println("------------------------------------------------------------------------");
        return restTemplate.getForObject("http://some/getsome",String.class);//请求服务中心的服务
    }

    /**
     * 降级方法
     * @return
     */
    public String fallbackSome(){
        //System.out.println("++++++++++++++++++++++++++++++++some 故障+++++++++++++++++++++++++++++++++");
        return "some service 模块故障";
    }
}

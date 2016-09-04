package com.example.config;


import org.apache.helix.manager.zk.ZkClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by IBM on 2016/9/3.
 * zookeeper 配置设置
 */
@Configuration
public class ZookeeperConfig {

    @Value("${spring.application.name}")
    private String appName;
    @Value("${server.port}")
    private String appPort;
    @Value("${zookeeper.service.list}")
    private String zookeeperList;

    ExecutorService executor = Executors.newSingleThreadExecutor();

    private static final Logger LOG= LoggerFactory.getLogger(ZookeeperConfig.class);

    //初始化
    @PostConstruct
    private void init(){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                //regist server
                registService();
                try{
                    Thread.sleep(1000*5);
                }catch (Exception e){
                    LOG.error(e.getLocalizedMessage());
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 注册服务
     * @return
     */
    public ZkClient registService(){
        String servicePath="/"+appName;//根节点路径
        ZkClient zkClient=new ZkClient(zookeeperList);

        boolean rootExists=zkClient.exists(servicePath);
        if(!rootExists){
            zkClient.createPersistent(servicePath);
        }

        InetAddress addr=null;
        try {
            addr=InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            LOG.error(e.getLocalizedMessage());
            e.printStackTrace();
        }

        String ip=addr.getHostAddress().toString();
        String serviceInstance=ip+":"+appPort;
        //注册服务
        zkClient.createEphemeral(servicePath+"/"+serviceInstance);
        System.out.println("The Service:"+servicePath+"/"+serviceInstance);
        LOG.info("The Service:"+servicePath+"/"+serviceInstance);
        return zkClient;
    }
}

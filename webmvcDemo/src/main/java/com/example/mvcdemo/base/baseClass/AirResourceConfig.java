package com.example.mvcdemo.base.baseClass;



import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.CommonProperties;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.example.mvcdemo.demo.controller.DemoJersey;

/**
 * <p>AirResourceConfig class.</p>
 *
 * @author hanl
 * @version $Id: $Id
 */
@ApplicationPath("/webapi")
@Component
public class AirResourceConfig extends ResourceConfig {
    /**
     * <p>Constructor for AirResourceConfig.</p>
     */
    public AirResourceConfig() {
    	
    	//全局配置禁用自动探测特征
    	property(CommonProperties.FEATURE_AUTO_DISCOVERY_DISABLE,true);
    	//全局配置禁用自动加载META-INF/services目录下的SPI
    	//property(CommonProperties.METAINF_SERVICES_LOOKUP_DISABLE,true);
    	//全局配置设置响应实体缓存的大小和响应头Content-Length的值，默认是8192byte，现在改成20KB
    	property(CommonProperties.OUTBOUND_CONTENT_LENGTH_BUFFER,20480);
    	
    	//client 配置
    	//设置Grizzly Connector异步线程池大小，默认不设置
    	//property(ClientProperties.ASYNC_THREADPOOL_SIZE,5);
    	//client 连接超时单位毫秒(ms),默认0，永不超时,现在修改5分钟
    	property(ClientProperties.CONNECT_TIMEOUT,300000);
    	//禁用client对META-INF/services下的SPI的自动加载
    	//property(ClientProperties.METAINF_SERVICES_LOOKUP_DISABLE,true);
    	//禁用client的自动探测特性
    	property(ClientProperties.FEATURE_AUTO_DISCOVERY_DISABLE,true);
    	//设置客户端读取超时时间，默认0，单位ms，修改为1分钟
    	property(ClientProperties.READ_TIMEOUT,60000);
    	
    	
        packages("com.lesaas.magnus");
        register(JacksonFeature.class);
        register(DemoJersey.class);
    }   
}
package com.example.mvcdemo;

import java.io.PrintStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.core.env.Environment;

/**
 * 自定义banner方法
 * @author ysc
 *
 */
public class MvcDemoBanner implements Banner {
	
	private static final Logger logger = LoggerFactory.getLogger(MvcDemoBanner.class);


	@Override
	public void printBanner(Environment environment, Class<?> sourceClass,
			PrintStream out) {
		this.show(environment);
	}
	
	/**
	 * 自定义banner
	 * @param environment
	 */
	public void show(Environment environment){
		String port=environment.getProperty("server.port");
		String context=environment.getProperty("server.context-path");
		System.setProperty("profilesName", environment.getProperty("profilesName"));
		
		logger.info(this.getClass().getName()+
				" - Server running at http://localhost:"+ port + context+"/demo");
	}

}

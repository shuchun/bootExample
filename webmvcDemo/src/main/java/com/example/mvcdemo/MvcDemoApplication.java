package com.example.mvcdemo;

import javax.servlet.Filter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;

import com.example.mvcdemo.base.config.SiteMeshConfig;

/**
 * slardar 启动类
 * @author ysc
 *
 */
@SpringBootApplication
//@MapperScan(basePackages = {"com.example.mvcdemo.demo.maps","com.example.mvcdemo.demo.maps.AcountDemoMapper.xml"})  
@ComponentScan(basePackages={"com.example.mvcdemo"})
public class MvcDemoApplication extends SpringBootServletInitializer	{
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(MvcDemoApplication.class);
        
    }
	
	@Bean
    public Filter sitemeshFilter() {
        return new SiteMeshConfig();
    }
	
	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {
		return new EmbeddedServletContainerCustomizer() {
			@Override
			public void customize(ConfigurableEmbeddedServletContainer container) {
				ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED,"/WEB-INF/views/error/401.jsp");
				ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND,"/WEB-INF/views/error/404.jsp");
				ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR,"/WEB-INF/views/error/500.jsp");

				container.addErrorPages(error401Page, error404Page,error500Page);
			}
		};
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//SpringApplication.run(PhoenixQuickStart.class, args);
		 SpringApplication app = new SpringApplication(MvcDemoApplication.class);
		 app.setBanner(new MvcDemoBanner());
		 app.run(args);
		System.out.println("Server at http://localhost:8091/mvcdemo/demo");
		 
	}
}

package com.example;

import com.rometools.rome.feed.synd.SyndEntry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.channel.MessageChannels;
import org.springframework.integration.dsl.core.Pollers;
import org.springframework.integration.dsl.file.Files;
import org.springframework.integration.dsl.mail.Mail;
import org.springframework.integration.feed.inbound.FeedEntryMessageSource;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.integration.scheduling.PollerMetadata;

import java.io.File;
import java.io.IOException;

@SpringBootApplication
public class BootIntegrationApplication {

	@Value("https://spring.io/blog.atom")//资源来源
	private Resource resource;

	public static void main(String[] args) {
		SpringApplication.run(BootIntegrationApplication.class, args);
	}

	////:读取资源
	/**
	 * Pollers配置默认的轮询方式
	 * @return
     */
	@Bean(name=PollerMetadata.DEFAULT_POLLER)
	public PollerMetadata poller(){
		return Pollers.fixedRate(500).get();
	}

	/**
	 * FeedEntryMessageSource实际为feed:inbound-channel-adapter,配置入站通道适配器
	 * @return
	 * @throws IOException
     */
	@Bean
	public FeedEntryMessageSource feedEntryMessageSource() throws IOException {
		FeedEntryMessageSource messageSource=new FeedEntryMessageSource(resource.getURL(),"news");
		return messageSource;
	}

	/**
	 * 数据流
	 * @return
	 * @throws IOException
     */
	@Bean
	public IntegrationFlow myFlow() throws IOException {
		return IntegrationFlows.from(feedEntryMessageSource())//流程起始
				.<SyndEntry,String> route(
						payload->payload.getCategories().get(0).getName(),//路由选择条件：catagories(分类)
						mapping->mapping.channelMapping("releases","releasesChannel")//通过路由后，进行处理通道的映射
						                .channelMapping("engineering","engineeringChannel")
						                .channelMapping("news","newsChannel"))
				.get();//获得IntegrationFlow实体
	}

	////:通道处理流程

	/**
	 * releases通道处理流程
	 * @return
     */
	@Bean
	public IntegrationFlow releasesFlow(){
		return IntegrationFlows.from(MessageChannels.queue("releasesChannel",10))//数据流入通道("releaseChannel")
				.<SyndEntry,String> transform(
						payload->"《"+payload.getTitle()+"》"+payload.getLink()+System.getProperty("line.separator"))//transform 方法进行数据转换
				.handle(Files.outboundAdapter(new File("e:/springblog"))//用handle方法处理file的出站适配器
				.fileExistsMode(FileExistsMode.APPEND)
				.charset("UTF-8")
				.fileNameGenerator(message ->"releases.txt")
				.get())
				.get();
	}

	/**
	 * engineering通道处理流程
	 * @return
     */
	@Bean
	public IntegrationFlow engineeringFlow(){
		return IntegrationFlows.from(MessageChannels.queue("engineeringChannel",10))
				.<SyndEntry,String> transform(
						e -> "《"+e.getTitle()+"》"+e.getLink()+System.getProperty("line.separator"))
				.handle(Files.outboundAdapter(new File("e:/springblog"))
				    .fileExistsMode(FileExistsMode.APPEND)
				    .charset("UTF-8")
				    .fileNameGenerator(message -> "engineering.txt")
				    .get())
				.get();
	}

	/**
	 * news通道处理流程
	 * @return
     */
	@Bean
	public IntegrationFlow newsFlow(){
		return IntegrationFlows.from(MessageChannels.queue("newsChannel",10))
				.<SyndEntry,String> transform(payload -> "《"+payload.getTitle()+"》"+payload.getLink()+System.getProperty("line.separator"))//转换消息体
				.enrichHeaders(//增加消息头信息
						Mail.headers()
						.subject("ysc测试来自spring的新闻")
						.to("***@126.com")
						.from("***@126.com"))
				.handle(//邮件发送
						Mail.outboundAdapter("smtp.126.com")//邮件发送适配器
						.port(25)
						.protocol("smtp")
						.credentials("***@126.com","*****")
						.javaMailProperties(p -> p.put("mail.debug","false")),
						e -> e.id("smtpOut"))
				.get();
	}

}

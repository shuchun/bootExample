# [spring boot Integration](https://github.com/shuchun/bootExample/tree/master/boot-integration)   

### 说明：    

Spring Integration 提供了基于Spring的EIP(Enterprise Integration Patterns,企业集成模式)的实现。   
Sping Integration 主要解决的问题是不同系统之间交互的问题，通过异步消息驱动来达到系统交互时系统之间的松耦合。   
本示例演示读取https://spring.io/blog.atom的新闻聚合文件。
atom是一个xml文件，示例通过获取到的atom的catagories属性将不同分类的新闻转到不同的消息通道处理。   
> releases,engineering分类的消息写入磁盘文件，news分类的消息通过邮件发送。  

### 代码：     
1. 添加依赖  
  * spring-boot-starter-integration
  * spring-integration-feed  
  * spring-integration-mail   
  * spring-integration-java-dsl  
  * spring-integration-file   
  * spring-boot-starter-mail  
2. 编写流程处理   

**依赖**    
```
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-integration</artifactId>
</dependency>
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-mail</artifactId>
</dependency>

<dependency>
	<groupId>org.springframework.integration</groupId>
	<artifactId>spring-integration-feed</artifactId>
	<version>4.3.0.RELEASE</version>
</dependency>

<dependency>
	<groupId>org.springframework.integration</groupId>
	<artifactId>spring-integration-mail</artifactId>
	<version>4.3.0.RELEASE</version>
</dependency>

<dependency>
	<groupId>org.springframework.integration</groupId>
	<artifactId>spring-integration-java-dsl</artifactId>
</dependency>

<dependency>
	<groupId>org.springframework.integration</groupId>
	<artifactId>spring-integration-file</artifactId>
	<version>4.3.0.RELEASE</version>
</dependency>
```   
**处理结果**     
1.releases.txt
> 《Spring Data Release Train Ingalls M1 Released》https://spring.io/blog/2016/07/27/spring-data-release-train-ingalls-m1-released    
《Spring Boot 1.3.7 available now》https://spring.io/blog/2016/07/28/spring-boot-1-3-7-available-now   
《Spring Framework 5.0 M1 released》https://spring.io/blog/2016/07/28/spring-framework-5-0-m1-released   
《Spring Boot 1.4 released》https://spring.io/blog/2016/07/28/spring-boot-1-4-released   
《Spring Cloud Data Flow for Apache YARN 1.0.1 released》https://spring.io/blog/2016/07/29/spring-cloud-data-flow-for-apache-yarn-1-0-1-released   
《Spring IO Platform 2.0.7.RELEASE》https://spring.io/blog/2016/07/29/spring-io-platform-2-0-7-release   
《Spring Cloud Task 1.0.2.RELEASE is now available》https://spring.io/blog/2016/07/29/spring-cloud-task-1-0-2-release-is-now-available    
《Spring Tool Suite 3.8.1 released》https://spring.io/blog/2016/08/03/spring-tool-suite-3-8-1-released   
《Spring for Apache Kafka 1.0.3 available now》https://spring.io/blog/2016/08/12/spring-for-apache-kafka-1-0-3-available-now   
《Spring Security 4.1.2 Released》https://spring.io/blog/2016/08/12/spring-security-4-1-2-released      

2.engineering.txt    
>
《Reactive Programming with Spring 5.0 M1》https://spring.io/blog/2016/07/28/reactive-programming-with-spring-5-0-m1     
《Spring IO Platform Athens RC1》https://spring.io/blog/2016/07/29/spring-io-platform-athens-rc1    
《SpringOne Platform 2016 Recap: Day 1》https://spring.io/blog/2016/08/03/springone-platform-2016-recap-day-1     
《This Week in Spring - SpringOne Platform 2016 edition! - August 2nd, 2016》https://spring.io/blog/2016/08/03/this-week-in-spring-springone-platform-2016-edition-august-2nd-2016    
《SpringOne Platform 2016 Recap: Day 2》https://spring.io/blog/2016/08/03/springone-platform-2016-recap-day-2    
《This Week in Spring - August 9th, 2016》https://spring.io/blog/2016/08/09/this-week-in-spring-august-9th-2016    
《Managing your Database Secrets with Vault》https://spring.io/blog/2016/08/15/managing-your-database-secrets-with-vault    
《This Week in Spring - August 16th, 2016》https://spring.io/blog/2016/08/16/this-week-in-spring-august-16th-2016      
《Spring Cloud Spinnaker 1.0.0.M1》https://spring.io/blog/2016/08/19/spring-cloud-spinnaker-1-0-0-m1     

3.邮件内容   
> 《Webinar Replay: Introducing Spring Cloud Task》https://spring.io/blog/2016/07/29/webinar-replay-introducing-spring-cloud-task      

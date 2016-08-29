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

**Spring Integration Java DSL **   
Spring Integration 提供了IntegrationFlow来定义系统集成流程，而通过IntegrationFlows和IntegrationFlowBuilder来实现使用Fluent API来定义流程。   
在Fluent API里，分别提供了下面的方法来映射Spring Integration的断点(EndPoint):    
```
transform()  -> Transformer
filter()     -> Filter
handle()     -> ServiceActivator、Adapter、Gateway
split()      -> Splitter
aggregator() -> Aggregator
route()      -> Router
bridge()     -> Bridge
```

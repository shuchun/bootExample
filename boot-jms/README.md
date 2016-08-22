# [Spring boot jms](https://github.com/shuchun/bootExample/tree/master/boot-jms)

#### 说明:    
1. 添加依赖  
```
<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-activemq</artifactId>
</dependency>
```  
2. 配置属性  
```
spring.activemq.in-memory=true
spring.activemq.pool.enabled=false
```  
3. 启用jms支持
```
@EnableJms
```
4. 编写消息生产者、消费者  
  * JmsProducer-生产者
  * JmsConsumer-消费者

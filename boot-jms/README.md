# [Spring boot amqp(rabbitMQ)](https://github.com/shuchun/bootExample/tree/feature/boot-jms)

#### 说明:   
**代码：**    
1.添加依赖
```
<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-amqp</artifactId>
</dependency>
```  
2.配置属性
```
#rabbitMQ config
spring.rabbitmq.host=192.168.1.11
spring.rabbitmq.port=5672
spring.rabbitmq.username=guest
spring.rabbitmq.password=guest
```  
3.启用rabbitMQ支持
```
@EnableRabbit
```
4.编写消息生产者、消费者
  * AmqpProducer-生产者
  * AmqpConsumer-消费者   

**部署:**   
1.pull RabbitMQ Docker images   
``` docker pull rabbitmq:3-management ```  
2.run RabbitMQ images  
``` docker run -d -p 5672:5672 -p 15672:15672 rabbitmq:3-management ```  
> 说明：5672是消息代理的端口，15672是web管理页面端口。用户名/密码：guest/guest  

3.查看管理页面 http://192.168.1.11:15672/    
4.配置远程MQ服务器到程序   
#rabbitMQ config
```
spring.rabbitmq.host=192.168.1.11
spring.rabbitmq.port=5672
spring.rabbitmq.username=guest
spring.rabbitmq.password=guest
```  

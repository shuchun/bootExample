# [Spring boot jms](https://github.com/shuchun/bootExample/tree/master/boot-jms)

#### 说明:   
**代码:**   
1.添加依赖
```
<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-activemq</artifactId>
</dependency>
```  
2.配置属性
```
spring.activemq.in-memory=true
spring.activemq.pool.enabled=false
```  
3.启用jms支持
```
@EnableJms
```
4.编写消息生产者、消费者
  * JmsProducer-生产者
  * JmsConsumer-消费者  

**部署:**  
1.pull ActiveMQ Docker images   
``` docker pull cloudesire/activemq ```   
2.run ActiveMQ images   
``` docker run -d -p 61616:61616 -p 8161:8161 cloudesire/activemq ```  
> 说明：61616是消息代理的端口，8161是ActiveMQ的管理界面端口。
默认管理员用户名/密码：admin/admin

3.查看管理页面
[http://192.168.1.11:8161/](http://192.168.1.11:8161/)   
> 注意防火墙问题    

4.配置远程MQ服务器到程序   
```
#auth MQ config
spring.activemq.broker-url=tcp://192.168.1.11:61616
spring.activemq.user=admin
spring.activemq.password=admin
```

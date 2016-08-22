# [Spring boot Demo](https://github.com/shuchun/bootExample)  

[spring boot Hello world：](https://github.com/shuchun/bootExample/tree/master/helloboot) helloDemo    
[用户认证：](https://github.com/shuchun/bootExample/tree/master/securityExample) pring-securityDemo   
[分布式系统：](https://github.com/shuchun/bootExample/tree/master/cloudDemo) cloudDemo     
[mvc示例：](https://github.com/shuchun/bootExample/tree/master/webmvcDemo) webMvcDemo      
[用户系统示例：](https://github.com/shuchun/bootExample/tree/master/auth) auth     
[批量数据处理：](https://github.com/shuchun/bootExample/tree/master/batch) batch    
[异步消息：](https://github.com/shuchun/bootExample/tree/master/boot-jms)boot-jms     



## maven-docker构建docker镜像   
添加了Dockerfile以及docker-maven plugin 用于直接将应用打包为image发布到   
docker服务器中。   
```
开启Docker远程调用接口的方式是修改/etc/sysconfig/docker文件，添加如下参数  
-H tcp://0.0.0.0:2375   
windows需要配置环境变量参数：    
DOCKER_HOME:tcp://xxx.xxx.xxx.xxx:2375   
docker-maven插件会自动使用该参数
```
然后运行命令：
```
mvn package docker:build -DskipTests
```  

## 扩展   

技术：[spring-boot](http://projects.spring.io/spring-boot/)
,[Docker](http://www.oschina.net/translate/tag/docker),[ActiveMQ](http://activemq.apache.org/)    
参考书籍：《spring boot实战》   

----  

# [cloudDemo](https://github.com/shuchun/bootExample/tree/master/cloudDemo)  

模块说明：

  模块名称   |     说明      |  技术
  ---------- |  ------------ |  ----------
  discovery  |    服务注册   |  Eureka Server
  config     |    配置中心   |  spring boot config server,Eureka Client
  person     |    用户添加   |  jpa,Eureka Client
  some       |    模拟其他服务 | DiscoveryClient
  ui         |    对外网关   |  Ribbon,Feign,CircuitBreaker,gangular,bootstrap
  monitor    |    断路器监控 |  hystrix
----
首页：http://localhost    
添加：http://localhost/#/person    
其它：http://localhost/#/some
注册中心：http://localhost:8761    
断路监控：http://localhost:8989/hystrix.stream
----------
项目启动顺序：discovery,config,person,some,ui,monitor
----
技术：spring-boot、spring-cloud、Eureka、Ribbon、Feign、Zuul、Circuit、hibernate、jpa、angular、bootstrap
----  
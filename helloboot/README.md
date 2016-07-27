# [simpleDemo](https://github.com/shuchun/bootExample/tree/master/helloboot)   
简单的spring boot示例    
添加了Dockerfile以及docker-maven plugin 用于直接将应用打包为image发布到   
docker服务器中。   
```
开启Docker远程调用接口的方式是修改/etc/sysconfig/docker文件，添加如下参数  
-H tcp://0.0.0.0:2375   
windows需要配置环境变量参数：    
DOCKER_HOME:tcp://xxx.xxx.xxx.xxx:2375   
docker-maven插件会自动使用该参数
```

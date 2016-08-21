# [Spring boot Batch Demo](https://github.com/shuchun/bootExample/tree/master/batch)

手动触发地址：http://localhost:8080/imp?fileName=people    
自动触发时去除CsvBatchConfig类的``` @Configuration,@EnableBatchProcessing ```  注释以及properties文件的 ``` spring.batch.job.enabled=true ```   注释

properties配置：     
```
#auto batch config
#spring.batch.job.names=job1,job2
#spring.batch.job.enabled=true
#spring.batch.initializer.enabled=true
#spring.batch.schema=
#spring.batch.table-prefix=

#trigger batch config
spring.batch.job.enabled=false
```

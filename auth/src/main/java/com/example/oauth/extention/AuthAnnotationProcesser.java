package com.example.oauth.extention;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

/**
 * Created by IBM on 2016/9/7.
 * bean后处理钩子
 */
@Configuration
public class AuthAnnotationProcesser implements BeanPostProcessor {

    @Autowired
    private RequestAuth auth;//请求认证

    /**
     * bean初始化前调用
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    /**
     * bean初始化后调用
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        //反射查询
        Method[] methods = ReflectionUtils.getAllDeclaredMethods(bean.getClass());
        if (methods != null) {
            for (Method method : methods) {
                //注释了自定义注解
                if(method.isAnnotationPresent(Anonymous.class)){
                    Anonymous annotation = AnnotationUtils.findAnnotation(method, Anonymous.class);
                    auth.anonymous(annotation.value());//将忽略路径添加到认证中
                }

            }
        }
        return bean;
    }
}

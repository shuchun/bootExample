package com.example.oauth.extention;

import java.lang.annotation.*;

/**
 * Created by IBM on 2016/9/7.
 * 自定义无需认证访问路径注解
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Anonymous {
    /**
     * 全路径字串
     * @return
     */
    String value();
    String match() default "";
}

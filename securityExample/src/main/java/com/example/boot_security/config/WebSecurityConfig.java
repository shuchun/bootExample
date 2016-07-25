package com.example.boot_security.config;

import com.example.boot_security.service.CustomUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by IBM on 2016/7/11.
 */
@Configuration
//@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    UserDetailsService customUserService(){
        return new CustomUserService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserService());
        //添加内存用户并指定角色
        /*auth.inMemoryAuthentication()
                .withUser("kaowu").password("password").roles("ROLE_ADMIN")
                .and()
                .withUser("aa").password("password").roles("ROLE_USER");*/
    }

    @Override
    protected void configure(HttpSecurity http)throws Exception{
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .failureUrl("/login?error")
                .permitAll()
                .and()
                .rememberMe().tokenValiditySeconds(1209600)//有效期2周
                .key("myKey")
                .and()
                .logout().permitAll();
    }
}

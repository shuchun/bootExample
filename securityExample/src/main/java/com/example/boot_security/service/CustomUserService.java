package com.example.boot_security.service;

import com.example.boot_security.dao.SysUserRepository;
import com.example.boot_security.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by IBM on 2016/7/11.
 */
public class CustomUserService implements UserDetailsService {

    @Autowired
    SysUserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user=userRepository.findByUsername(username);

        if(user == null){
            throw new UsernameNotFoundException("用户名不存在");
        }

        return user;
    }
}

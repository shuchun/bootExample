package com.example.boot_security.dao;

import com.example.boot_security.domain.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by IBM on 2016/7/11.
 */
public interface SysUserRepository extends JpaRepository<SysUser,Long> {
    SysUser findByUsername(String username);
}

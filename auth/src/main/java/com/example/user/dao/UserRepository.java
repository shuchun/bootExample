package com.example.user.dao;

import com.example.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by IBM on 2016/7/31.
 * 用户jpa操作
 */
public interface UserRepository extends JpaRepository<User,Long> {
}

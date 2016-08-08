package com.example.user.dao;

import com.example.user.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by IBM on 2016/7/31.
 * 用户jpa操作
 */
public interface UserRepository extends JpaRepository<User,Long> {

    //@Query("select user from User user where user.userName = ?1 and user.userPwd = ?2")
    /**
     * 根据用户名和密码查询用户
     * @param name      用户名
     * @param password  密码
     * @return
     */
    User getUserByNameAndPassword(String name, String password);

    /**
     * 根据用户名查询用户
     * @param name  用户名
     * @return
     */
    List<User> findUserByName(String name);

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    User findUserById(Long id);

}

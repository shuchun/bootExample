package com.example.user.dao;

import com.example.user.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by IBM on 2016/7/31.
 * 用户jpa操作
 */
public interface UserRepository extends MongoRepository<User,Long> {

    //@Query("{'age':?0}")
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

    /**
     * 用户名模糊查询并且忽略大小写
     * @param name
     * @return
     */
    List<User> findByNameStartingWithIgnoreCase(String name,Sort sort);

}

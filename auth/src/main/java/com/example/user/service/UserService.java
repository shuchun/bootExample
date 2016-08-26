package com.example.user.service;

import com.example.base.Tools;
import com.example.user.dao.UserRepository;
import com.example.user.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by IBM on 2016/8/1.
 * user Service
 */
@Service
@Transactional
public class UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserRepository userRepository;

    /**
     * 获取用户
     * @param userName      用户名
     * @param userPwd       用户密码
     * @return              返回新用户
     */
    //@Cacheable(value = "user",key = "#root.targetClass+#userName")
    public User getUser(String userName,String userPwd){
        User user;
        user = userRepository.getUserByNameAndPassword(userName,userPwd);
        LOG.info("getUser:"+user.getId());
        return user;
    }

    /**
     * 获取用户
     * @param id    用户id
     * @return      返回用户
     */
    @Cacheable(value="user",key="#root.targetClass+new String(#id)")
    public User getUser(Long id){
        User user=userRepository.findUserById(id);
        LOG.info("getUser:"+(Tools.isNotEmpty(user)?user.getId():""));
        return user;
    }

    /**
     * 修改用户年龄与性别
     * @param id            id
     * @param userName      用户名
     * @param age           年龄
     * @param gender        性别
     * @return              用户
     */
    @Caching(evict = {@CacheEvict(value="user",key = "#root.targetClass+new String(#id)",condition = "#id>0"),
                      @CacheEvict(value="user",key = "#root.targetClass+#userName",condition = "#userName != null")
    })
    public User updateUserByIdAndName(final Long id, final String userName, int age, String gender){

        User user=userRepository.findUserById(id);
        if(Tools.isNotEmpty(user)&&user.getName().equals(userName)){
            user.setAge(age);
            user.setGender(gender);
            return this.updateUser(user);
        }

        return user;
    }

    /**
     * 更新用户信息
     * @param id            id
     * @param userName      用户名
     * @param userPwd       用户密码
     * @param age           年龄
     * @param gender        性别
     * @return              用户
     */
    @Caching(evict = {@CacheEvict(value="user",key = "#root.targetClass+new String(#id)",condition = "#id>0"),
            @CacheEvict(value="user",key = "#root.targetClass+#userName",condition = "#userName != null")
    })
    public User updateUser(final Long id,String userName,String userPwd,int age,String gender){
        User user=userRepository.findUserById(id);
        if(Tools.isNotEmpty(user)){
            if(Tools.isNotEmpty(userName)){
                user.setName(userName);
            }
            if(Tools.isNotEmpty(userPwd)){
                user.setPassword(userPwd);
            }
            if(Tools.isNotEmpty(age)){
                user.setAge(age);
            }
            if(Tools.isNotEmpty(gender)){
                user.setGender(gender);
            }

            user=this.updateUser(user);
        }

        return user;
    }

    /**
     * 修改用户信息
     * @param user      用户
     * @return          修改的用户
     */
    private User updateUser(User user){
        User modUser=null;
        if(Tools.isNotEmpty(user)){
            LOG.info("updateUser:"+user.getId());
            modUser = userRepository.save(user);
        }
        return modUser;
    }

    /**
     * 添加新用户
     * @param userName      用户名
     * @param userPwd       用户密码
     */
    @CachePut(value = "user",key = "#root.targetClass+#userName")
    public User addUser(String userName, String userPwd){
        Long count=userRepository.count();
        User user=new User();
        user.setId(count+1);
        user.setName(userName);
        user.setPassword(userPwd);
        //user.setCreateDate(new Date());
        LOG.info("addUser:"+user.getId()+","+userName);
        return userRepository.save(user);
    }

    /**
     * 用户名是否存在
     * @param userName      用户名
     * @return              是否存在
     */
    public boolean userNameExists(String userName){
        List<User> users=userRepository.findUserByName(userName);
        return Tools.isNotEmpty(users)&&users.size()>0;
    }

    /**
     * 删除用户缓存信息
     */
    @Caching(evict = {@CacheEvict(value="user",key = "#root.targetClass+new String(#id)",condition = "#id>0"),
            @CacheEvict(value="user",key = "#root.targetClass+#userName",condition = "#userName != null")
    })
    public void removeCache(Long id,String userName){
        //删除缓存信息
        LOG.info("用户："+id+","+userName+"登出");
    }


}

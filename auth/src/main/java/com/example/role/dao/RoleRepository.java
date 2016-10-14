package com.example.role.dao;

import com.example.role.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by IBM on 2016/9/7.
 * role repository
 */
public interface RoleRepository extends JpaRepository<Role,Long> {

    Role getRoleById(Long id);

}

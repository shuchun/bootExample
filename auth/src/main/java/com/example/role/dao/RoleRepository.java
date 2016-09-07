package com.example.role.dao;

import com.example.role.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by IBM on 2016/9/7.
 * role repository
 */
public interface RoleRepository extends JpaRepository<Role,Long> {
}

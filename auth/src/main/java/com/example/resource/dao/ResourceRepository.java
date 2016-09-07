package com.example.resource.dao;

import com.example.resource.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by IBM on 2016/9/7.
 * resource repository
 */
public interface ResourceRepository extends JpaRepository<Resource,Long> {
}

package com.example.resource.dao;

import com.example.resource.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

/**
 * Created by IBM on 2016/9/9.
 */
public interface ResourceRepository extends JpaRepository<Resource,Long> {

    @Query("SELECT r FROM Resource r JOIN r.roles ro WHERE ro.id=:rid")
    List<Resource> findEnableResourceForUser(@Param("rid") Long rid);
}

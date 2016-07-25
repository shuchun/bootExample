package com.example.dao;

import com.example.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by IBM on 2016/7/25.
 * 用户 jpa操作
 */
public interface PersonRepository extends JpaRepository<Person,Long> {
}

package com.example.controller;

import com.example.dao.PersonRepository;
import com.example.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by IBM on 2016/7/25.
 */
@RestController
public class PersonController {

    @Autowired
    private PersonRepository personRepository;//jpa操作

    /**
     * 添加用户
     * @param personName    用户名
     * @return
     */
    @RequestMapping(value="/save",method = RequestMethod.POST)
    public List<Person> savePerson(@RequestBody String personName){
        Person p=new Person(personName);
        personRepository.save(p);
        List<Person> pepole=personRepository.findAll(new PageRequest(0,10)).getContent();

        return pepole;
    }
}

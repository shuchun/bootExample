package com.example.bootbatch.batch;

import com.example.bootbatch.domain.Person;
import org.springframework.batch.item.validator.ValidatingItemProcessor;

/**
 * Created by IBM on 2016/8/7.
 * 处理进程
 */
public class CsvItemProcessor extends ValidatingItemProcessor<Person> {

    @Override
    public Person process(Person item){
        super.process(item);

        if(item.getNation().equals("汉族")){
            item.setNation("01");
        }else{
            item.setNation("02");
        }
        System.out.println("Item:"+item.getId()+","+item.getName()+","+item.getAge()+","+item.getNation()+","+item.getAddress());
        return item;
    }
}

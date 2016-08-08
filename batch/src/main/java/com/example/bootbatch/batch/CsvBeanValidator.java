package com.example.bootbatch.batch;

import org.springframework.batch.item.validator.ValidationException;
import org.springframework.batch.item.validator.Validator;
import org.springframework.beans.factory.InitializingBean;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * Created by IBM on 2016/8/7.
 * 校验器
 */
public class CsvBeanValidator<T> implements Validator<T>,InitializingBean {

    private javax.validation.Validator validator;

    @Override
    public void afterPropertiesSet()throws Exception{
        ValidatorFactory validatorFactory= Validation.buildDefaultValidatorFactory();
        validator=validatorFactory.usingContext().getValidator();
    }

    @Override
    public void validate(T value){
        Set<ConstraintViolation<T>> constraintViolations=validator.validate(value);
        if(constraintViolations.size()>0){
            StringBuilder message=new StringBuilder();
            for(ConstraintViolation<T>constraintViolation:constraintViolations){
                message.append(constraintViolation.getMessage()+"\n");
            }
            System.out.println("Valiate:"+message.toString());
            throw new ValidationException(message.toString());
        }
    }
}

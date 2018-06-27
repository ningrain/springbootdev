package com.hh.springbootdev.aop;

import com.hh.springbootdev.annotation.TargetDataSource;
import com.hh.springbootdev.configuration.DynamicDataSourceContextHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Desc:
 * User: jiangningning
 * Date: 2018/6/27
 * Time: 22:21
 */
@Aspect
@Order(-1)
@Component
public class DynamicDataSourceAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(DynamicDataSourceAspect.class);

    @Pointcut("@annotation(com.hh.springbootdev.annotation.TargetDataSource)")
    public void switchDatasource(){}

    @Before("switchDatasource()")
    public void changeDataSource(JoinPoint point){
        MethodSignature ms = (MethodSignature) point.getSignature();
        String targetDataSource = ms.getMethod().getAnnotation(TargetDataSource.class).value().toString();
        LOGGER.info("method：{} 使用数据源 : {}", point.getSignature(), targetDataSource);
        DynamicDataSourceContextHolder.setDataSourceType(targetDataSource);
    }

    @After("switchDatasource()")
    public void restoreDataSource(JoinPoint point){
        MethodSignature ms = (MethodSignature) point.getSignature();
        String targetDataSource = ms.getMethod().getAnnotation(TargetDataSource.class).value().toString();
        LOGGER.info("method：{} 执行完毕，还原数据源 DataSource : {}", point.getSignature(), targetDataSource);
        DynamicDataSourceContextHolder.clearDataSourceType();
    }
}

package com.hh.springbootdev.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.hh.springbootdev.util.RedisUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Desc:
 * User: jiangningning
 * Date: 2018/4/12
 * Time: 13:07
 */
@Configuration
public class CoreConfiguration {

    private static RedisProperties redisProperties = new RedisProperties();

    private static DbProperties dbProperties = new DbProperties();

    @Bean
    public RedisProperties getSupport(){
        return redisProperties;
    }

    @Bean
    public DbProperties getDbProperties(){
        return dbProperties;
    }

    @Bean
    public RedisUtil redisUtil(RedisProperties properties){
        RedisUtil.init(properties);
        return new RedisUtil();
    }

    @Bean
    public DataSource dataSource(DbProperties properties){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(properties.getUrl());
        dataSource.setUsername(properties.getUsername());//用户名
        dataSource.setPassword(properties.getPassword());//密码
        dataSource.setDriverClassName(properties.getDriverClassName());
        dataSource.setInitialSize(2);//初始化时建立物理连接的个数
        dataSource.setMaxActive(20);//最大连接池数量
        dataSource.setMinIdle(0);//最小连接池数量
        dataSource.setMaxWait(60000);//获取连接时最大等待时间，单位毫秒。
        dataSource.setValidationQuery("SELECT 1");//用来检测连接是否有效的sql
        dataSource.setTestOnBorrow(false);//申请连接时执行validationQuery检测连接是否有效
        dataSource.setTestWhileIdle(true);//建议配置为true，不影响性能，并且保证安全性。
        dataSource.setPoolPreparedStatements(false);//是否缓存preparedStatement，也就是PSCache
        return dataSource;
    }

}

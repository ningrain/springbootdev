package com.hh.springbootdev.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.hh.springbootdev.entity.CustomizeDS;
import com.hh.springbootdev.util.RedisUtil;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Desc:
 * User: jiangningning
 * Date: 2018/4/12
 * Time: 13:07
 */
@Configuration
public class CoreConfiguration {

    private static RedisProperties redisProperties = new RedisProperties();

    //private static DbProperties dbProperties = new DbProperties();

    @Bean
    public RedisProperties getSupport() {
        return redisProperties;
    }

    /*@Bean
    public DbProperties getDbProperties() {
        return dbProperties;
    }*/

    @Bean
    public RedisUtil redisUtil(RedisProperties properties) {
        RedisUtil.init(properties);
        return new RedisUtil();
    }

    /*@Bean
    public DataSource dataSource(DbProperties properties) {
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
    }*/

    @Bean(name = "masterDataSource")
    @ConfigurationProperties(prefix = "master.datasource")
    public DataSource masterDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "clusterDataSource")
    @ConfigurationProperties(prefix = "cluster.datasource")
    public DataSource clusterDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "dynamicDataSource")
    public DataSource dynamicDataSource(@Qualifier("masterDataSource") DataSource masterDataSource,
                                        @Qualifier("clusterDataSource") DataSource clusterDataSource) {
        DynamicDataSourceResolver resolver = new DynamicDataSourceResolver();
        Map<Object, Object> datasources = new HashMap<>();
        datasources.put(CustomizeDS.MASTER.toString(), masterDataSource);
        datasources.put(CustomizeDS.CLUSTER.toString(), clusterDataSource);
        resolver.setTargetDataSources(datasources);
        resolver.setDefaultTargetDataSource(masterDataSource);
        resolver.afterPropertiesSet();
        return resolver;
    }

    @Primary
    @Bean(name = "dynamicSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("masterDataSource") DataSource masterDataSource,
                                               @Qualifier("clusterDataSource") DataSource clusterDataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(this.dynamicDataSource(masterDataSource, clusterDataSource));
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(){
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.hh.springbootdev.dao");
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("dynamicSqlSessionFactory");
        return mapperScannerConfigurer;
    }
}

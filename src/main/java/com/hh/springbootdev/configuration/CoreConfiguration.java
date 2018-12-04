package com.hh.springbootdev.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.hh.springbootdev.entity.CustomizeDS;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

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

    @Bean(name = "masterDataSource")
    @ConfigurationProperties(prefix = "master.datasource")
    public DataSource masterDataSource() {
        return buildDatasource();
    }

    @Bean(name = "clusterDataSource")
    @ConfigurationProperties(prefix = "cluster.datasource")
    public DataSource clusterDataSource() {
        return buildDatasource();
    }

    private DataSource buildDatasource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setInitialSize(5);
        dataSource.setMaxActive(20);
        dataSource.setMinIdle(0);
        dataSource.setMaxWait(70000L);
        dataSource.setValidationQuery("SELECT 1");
        dataSource.setTestOnBorrow(false);
        dataSource.setTestWhileIdle(true);
        dataSource.setPoolPreparedStatements(false);
        return dataSource;
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
                                               @Qualifier("clusterDataSource") DataSource clusterDataSource,
                                               MybatisProperties mybatisProperties) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(this.dynamicDataSource(masterDataSource, clusterDataSource));
        sqlSessionFactoryBean.setTypeAliasesPackage(mybatisProperties.getTypeAliasesPackage());
        sqlSessionFactoryBean.setMapperLocations(mybatisProperties.resolveMapperLocations());
        sqlSessionFactoryBean.setTypeHandlersPackage(mybatisProperties.getTypeHandlersPackage());
        sqlSessionFactoryBean.setConfiguration(mybatisProperties.getConfiguration());
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "mapperScannerConfigurer")
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.hh.springbootdev.dao");
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("dynamicSqlSessionFactory");
        return mapperScannerConfigurer;
    }
}

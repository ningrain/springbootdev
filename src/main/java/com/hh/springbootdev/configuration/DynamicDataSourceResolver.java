package com.hh.springbootdev.configuration;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Desc:
 * User: jiangningning
 * Date: 2018/6/27
 * Time: 21:44
 */
public class DynamicDataSourceResolver extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceContextHolder.getDataSourceType();
    }
}

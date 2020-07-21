package com.guyang.spring.boot.core.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author guyang <guyang@ebnew.com>
 * @description
 * @date 2019-11-14 17:23
 */
@Configuration
public class DataSourceConfig{

    public DataSourceConfig() {
        System.out.println("DataSourceConfig 构造方法");
    }

    @Value("${spring-datasource-driver-class-name}")
    private  String datasourceDriverClassName;

    @Value("${spring.datasource.url}")
    private  String datasourceUrl;

    @Value("${spring.datasource.username}")
    private  String datasourceUserName;

    @Value("${spring.datasource.password}")
    private  String datasourcePassword;


    @Bean
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(datasourceDriverClassName);
        dataSource.setUrl(datasourceUrl);
        dataSource.setUsername(datasourceUserName);
        dataSource.setPassword(datasourcePassword);

        return dataSource;
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        //别名扫描的bug:当从jar运行时,无法扫描到jar内的别名，设置vfs来解决这个问题
        sessionFactory.setVfs(SpringBootVFS.class);
        sessionFactory.setTypeAliasesPackage("com.guyang.spring.boot.model");
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        return sessionFactory.getObject();
    }

    public SqlSessionTemplate sqlSessionTemplate() throws Exception {
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
        return sqlSessionTemplate;
    }

    /**
     * 用来配置mapperScan的包扫描路径,如果有多个包,使用逗号或分号分隔
     * 显式配置此Bean，则@MapperScan注解会失效
     *
     * @return
     */
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.guyang.spring.boot.dao");
        return mapperScannerConfigurer;
    }


}

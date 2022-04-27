package com.project.demo.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
@MapperScan(value = {"com.project.demo.dao"})
public class MybatisConfig {
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();

        Resource[] res = new PathMatchingResourcePatternResolver().getResources("classpath:mapper/**/*.xml");
        sessionFactory.setMapperLocations(res);

        sessionFactory.setDataSource(dataSource);
        return sessionFactory.getObject();
    }
}

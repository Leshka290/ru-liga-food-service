package ru.liga.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.liga.batisMapper.BatisOrderMapper;

import javax.sql.DataSource;

@Configuration
public class MyBatisConfig {
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setTypeAliasesPackage("ru.liga.entity");
        return sessionFactory.getObject();
    }

    @Bean
    public MapperFactoryBean<BatisOrderMapper> orderMapper(DataSource dataSource) throws Exception {
        MapperFactoryBean<BatisOrderMapper> factoryBean = new MapperFactoryBean<>(BatisOrderMapper.class);
        factoryBean.setSqlSessionFactory(sqlSessionFactory(dataSource));
        return factoryBean;
    }
}

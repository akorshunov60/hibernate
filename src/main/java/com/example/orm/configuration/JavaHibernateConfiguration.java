package com.example.orm.configuration;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Конфигурация для подключения к проекту Hibernate через Spring-ORM
 */
@Profile("java")
@Configuration
public class JavaHibernateConfiguration {
    @Bean
    public EntityManager entityManager(SessionFactory sessionFactory) {
        return sessionFactory.createEntityManager();
    }

    @Bean
    public DataSource getDataSource() {

        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL("jdbc:mysql://localhost:3306/products?serverTimezone=Europe/Moscow");
        dataSource.setUser("user1");
        dataSource.setPassword("");

        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean localSessionFactoryBean() {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(getDataSource());
        sessionFactoryBean.setPackagesToScan("com.example.orm.model.entity");
        sessionFactoryBean.setHibernateProperties(properties());
        return sessionFactoryBean;
    }

    private Properties properties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "none");
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        hibernateProperties.setProperty("hibernate.show_sql", "true");

        return hibernateProperties;
    }
}

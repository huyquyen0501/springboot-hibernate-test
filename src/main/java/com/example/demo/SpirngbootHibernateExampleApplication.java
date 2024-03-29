package com.example.demo;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

/*Cần dừng việc auto config datasource, datasource transaction và hibernatejpa của JPA
 * 
Tạo biến môi trường của spring core để có thể get thông tin của file application.properties

Config lại datasource,

config sessionFactory, transactionManager*/

@SpringBootApplication
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class })
public class SpirngbootHibernateExampleApplication {
	@Autowired
	private Environment env;

	public static void main(String[] args) {
		SpringApplication.run(SpirngbootHibernateExampleApplication.class, args);
	}

	@Bean(name = "dataSource")
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();

		dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
		dataSource.setUrl(env.getProperty("spring.datasource.url"));
		dataSource.setUsername(env.getProperty("spring.datasource.username"));
		dataSource.setPassword(env.getProperty("spring.datasource.password"));
		return dataSource();
	}

	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory sessionFactory() throws Exception {
		Properties properties = new Properties();

		properties.put("hibernate.dialect", env.getProperty("spring.jpa.properties.hibernate.dialect"));
		properties.put("hibernate.show_sql", env.getProperty("spring.jpa.show-sql"));
		properties.put("current_session_context_class",
				env.getProperty("spring.jpa.properties.hibernate.current_session_context_class"));

		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();

		factoryBean.setPackagesToScan("com.example.demo.entity");
		factoryBean.setDataSource(dataSource());
		factoryBean.setHibernateProperties(properties);

		SessionFactory sessionFactory = factoryBean.getObject();
		return sessionFactory;
	}
	// transaction dam bao su toan ven cho sessionfactory
	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getHibernateTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager= new HibernateTransactionManager(sessionFactory);
		return transactionManager;
	}
}

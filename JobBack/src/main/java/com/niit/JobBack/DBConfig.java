package com.niit.JobBack;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan("com.niit")
@EnableTransactionManagement
public class DBConfig 
{
	@Bean("datasource")
	DataSource dbConnect()
	{
		BasicDataSource d=new BasicDataSource();
		d.setDriverClassName("org.h2.Driver");
		d.setUrl("jdbc:h2:tcp://localhost/~/test1");
		d.setUsername("sa");
		d.setPassword("sa");
		return d;
	}
	
	@Bean("myprop")
	Properties myDBProperties()
	{
		Properties p=new Properties();
		p.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		p.setProperty("hibernate.show_sql", "true");
		p.setProperty("hibernate.hbm2ddl.auto", "update");
		return p;
		
	}
	
	
	@Bean("sessionfactory")
	LocalSessionFactoryBean sessionFactory()
	{
		LocalSessionFactoryBean lsfb=new LocalSessionFactoryBean();
		lsfb.setDataSource(dbConnect());
		lsfb.setHibernateProperties(myDBProperties());
		lsfb.setPackagesToScan("com.niit.JobBack.model");
		return lsfb;
	}
	
	@Autowired
	@Bean
	HibernateTransactionManager manager(SessionFactory sessionfactory)
	{
		HibernateTransactionManager htm=new HibernateTransactionManager();
		htm.setSessionFactory(sessionfactory);
		return htm;
	}

}
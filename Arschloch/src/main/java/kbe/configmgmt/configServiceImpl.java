package kbe.configmgmt;

//import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//import org.springframework.boot.builder.SpringApplicationBuilder;
//import org.springframework.boot.context.web.SpringBootServletInitializer;
//import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import kbe.frontendmgmt.FrontendController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * @authors         Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler
 * Email-Adresse: 	s0564784@htw-berlin.de 	| s0563958@htw-berlin.de| s0557193@htw-berlin.de
 * <p>
 * Eine Klasse, die die Spielinstanz realisiert
 * Hier läuft das Spiel im Großteil ab.
 */
//@ComponentScan(basePackages = {
//		kbe.playermgmt.Player;
//})

////@EntityScan(basePackages = {
////		//model
////})
//@EnableAsync
//@EntityScan("kbe")
@SpringBootApplication
@EnableJpaRepositories("kbe.JpaRepository")
@EnableScheduling
@Configuration
public class configServiceImpl {

//	@Bean
//	public LocalEntityManagerFactoryBean entityManagerFactoryBean() {
//		LocalEntityManagerFactoryBean factory = new LocalEntityManagerFactoryBean();
//		factory.setPersistenceUnitName("example-unit");
//		return factory;
//	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setPackagesToScan(new String[] { "kbe" });

		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(additionalProperties());

		return em;
	}

	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);

		return transactionManager;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
		return new PersistenceExceptionTranslationPostProcessor();
	}

	Properties additionalProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");

		return properties;
	}

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();

		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUsername("SA");
		dataSource.setPassword("");
		dataSource.setUrl("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");

		return dataSource;
	}

	private static ConfigurableApplicationContext container = new AnnotationConfigApplicationContext("kbe");

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(configServiceImpl.class);
		EntityManagerFactory emf = context.getBean(EntityManagerFactory.class);
		EntityManager em = emf.createEntityManager();
		try {
//			nativeQuery(em, "SHOW TABLES");
//			nativeQuery(em, "SHOW COLUMNS from Person");
			FrontendController gui = container.getBean(FrontendController.class);
			gui.init();

		} finally {
			em.close();
			emf.close();
		}
	}

//	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//		return application.sources(configServiceImpl.class);
//	}
//
//	public static void main(String[] args) throws Exception {
//		SpringApplication.run(configServiceImpl.class, args);
//		FrontendController gui = container.getBean(FrontendController.class);
//		gui.init();
//	}
}

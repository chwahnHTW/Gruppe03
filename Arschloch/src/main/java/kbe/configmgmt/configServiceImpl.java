package kbe.configmgmt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import kbe.frontendmgmt.FrontendController;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @authors         Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler
 * Email-Adresse: 	s0564784@htw-berlin.de 	| s0563958@htw-berlin.de| s0557193@htw-berlin.de
 * <p>
 * Eine Klasse, die die Spielinstanz realisiert
 * Hier läuft das Spiel im Großteil ab.
 */
@EnableScheduling
@SpringBootApplication
@EntityScan("kbe")
public class configServiceImpl extends SpringBootServletInitializer {
	private static ConfigurableApplicationContext container = new AnnotationConfigApplicationContext("kbe");

	public static void main(String[] args) {
		SpringApplication.run(configServiceImpl.class, args);
		FrontendController gui = container.getBean(FrontendController.class);
		gui.init();
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

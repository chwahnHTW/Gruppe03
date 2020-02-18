package kbe.configmgmt;

import kbe.frontendmgmt.FrontendService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @authors         Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler
 * Email-Adresse: 	s0564784@htw-berlin.de 	| s0563958@htw-berlin.de| s0557193@htw-berlin.de
 *
 * Eine Klasse, die die Spielinstanz realisiert
 * Hier läuft das Spiel im Großteil ab.
 */
@SpringBootApplication
@Configuration
@ComponentScan(basePackages = {"kbe"})
@EntityScan(basePackageClasses = {
        kbe.cardmgmt.Card.class,
        kbe.playermgmt.Player.class,
        kbe.gamemgmt.GameInstance.class})
@EnableJpaRepositories(basePackageClasses = {
        kbe.repositories.PlayerRepository.class,
        kbe.repositories.CardRepository.class,
        kbe.repositories.GameInstanceRepository.class})
public class ConfigServiceImpl {

    private static ApplicationContext applicationContext;

    public static void main(String[] args) {
        applicationContext = new AnnotationConfigApplicationContext(ConfigServiceImpl.class);

        FrontendService gui = applicationContext.getBean(FrontendService.class);
        gui.init();

    }

}


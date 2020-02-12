package kbe.configmgmt;

import kbe.historymgmt.HistoryServiceImpl;
import kbe.playermgmt.Player;
import kbe.repositories.PlayerRepository;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.*;
import kbe.frontendmgmt.FrontendController;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @authors Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler
 * Email-Adresse: 	s0564784@htw-berlin.de 	| s0563958@htw-berlin.de| s0557193@htw-berlin.de
 * <p>
 * Eine Klasse, die die Spielinstanz realisiert
 * Hier läuft das Spiel im Großteil ab.
 */

@EnableScheduling
@Configuration
//@EnableAutoConfiguration
//@ComponentScan("kbe")
//@EntityScan
@EnableJpaRepositories(basePackageClasses = {PlayerRepository.class})
public class configServiceImpl {

    private static ConfigurableApplicationContext container = new AnnotationConfigApplicationContext("kbe");

    public static void main(String[] args) {

        FrontendController gui = container.getBean(FrontendController.class);
        gui.init();

        HistoryServiceImpl historyService = new HistoryServiceImpl();
        historyService.tueEtwas();
    }

}

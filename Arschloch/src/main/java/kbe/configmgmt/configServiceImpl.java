package kbe.configmgmt;

import kbe.playermgmt.Player;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.*;
import kbe.frontendmgmt.FrontendController;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.sql.DataSource;


/**
 * @authors Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler
 * Email-Adresse: 	s0564784@htw-berlin.de 	| s0563958@htw-berlin.de| s0557193@htw-berlin.de
 * <p>
 * Eine Klasse, die die Spielinstanz realisiert
 * Hier läuft das Spiel im Großteil ab.
 */

@EnableScheduling
@Configuration
public class configServiceImpl {

    private static ConfigurableApplicationContext container = new AnnotationConfigApplicationContext("kbe");

    public static void main(String[] args) {


        EntityManagerFactory factory = Persistence.createEntityManagerFactory("myDb");
        EntityManager entityManager = factory.createEntityManager();

        entityManager.getTransaction().begin();



        Player newUser = new Player();
        newUser.setName("Kaya");
        newUser.setRole(null);

        entityManager.persist(newUser);


        FrontendController gui = container.getBean(FrontendController.class);
        gui.init();

        entityManager.getTransaction().commit();
        entityManager.close();
        factory.close();
//        try {
//			nativeQuery(em, "SHOW TABLES");
//			nativeQuery(em, "SHOW COLUMNS from Person");


//        } catch (Exception e) {
//
//        } finally {
//            em.close();
//            emf.close();
//        }
    }

}

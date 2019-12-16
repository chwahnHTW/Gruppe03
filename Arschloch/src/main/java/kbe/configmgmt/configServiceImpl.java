package kbe.configmgmt;


import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import kbe.frontendmgmt.FrontendController;
/**
 * @authors         Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler
 * Email-Adresse: 	s0564784@htw-berlin.de 	| s0563958@htw-berlin.de| s0557193@htw-berlin.de
 * <p>
 * Eine Klasse, die die Spielinstanz realisiert
 * Hier läuft das Spiel im Großteil ab.
 */

public class configServiceImpl {
	private static ConfigurableApplicationContext container = new AnnotationConfigApplicationContext("kbe");

	public static void main(String[] args) {
		FrontendController gui = container.getBean(FrontendController.class);
		gui.init();
	}
}

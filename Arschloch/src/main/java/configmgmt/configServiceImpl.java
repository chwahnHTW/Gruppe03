package configmgmt;


import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import frontendmgmt.FrontendController;


public class configServiceImpl {
	private static ConfigurableApplicationContext container = new AnnotationConfigApplicationContext("Gruppe03");

	public static void main(String[] args) {
		FrontendController gui = container.getBean(FrontendController.class);
		gui.init();
	}
}

package configmgmt;

import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.MutablePicoContainer;
import org.picocontainer.injectors.AnnotatedMethodInjection;
import org.picocontainer.injectors.SetterInjection;

import cardmgmt.CardServiceImpl;
import frontendmgmt.FrontendServiceImpl;
import gamemgmt.GameInstanceServiceImpl;
import historymgmt.HistoryServiceImpl;
import playermgmt.PlayerServiceImpl;
import rulesmgmt.CardRulesServiceStandardImpl;
import rulesmgmt.PlayerRulesServiceStandardImpl;

public class configServiceImpl {
	private static MutablePicoContainer container  =  new DefaultPicoContainer(new SetterInjection());
//	private static MutablePicoContainer container = new DefaultPicoContainer(new ConstructorInjection());
//	private static MutablePicoContainer container = new DefaultPicoContainer(new AnnotatedFieldInjection());
//	private static MutablePicoContainer container = new DefaultPicoContainer(new AnnotatedMethodInjection());

	public static void main(String[] args) {
		System.out.println("now working");
	    registerComponents();
	    FrontendServiceImpl ui = container.getComponent(FrontendServiceImpl.class);
	    ui.init();
	    
	}

	private static void registerComponents() {
		
		container.addComponent(CardServiceImpl.class);
		container.addComponent(FrontendServiceImpl.class);
		container.addComponent(GameInstanceServiceImpl.class);
		container.addComponent(HistoryServiceImpl.class);
		container.addComponent(PlayerServiceImpl.class);
		container.addComponent(PlayerRulesServiceStandardImpl.class);
		container.addComponent(CardRulesServiceStandardImpl.class);
	}
}

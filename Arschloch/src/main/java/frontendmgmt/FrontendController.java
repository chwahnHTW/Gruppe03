package frontendmgmt;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gamemgmt.GameInstanceServiceImpl;


@Component
public class FrontendController implements FrontendService {
	
@Autowired	
private GameInstanceServiceImpl GISI = new GameInstanceServiceImpl();

@Autowired
private FrontendService frontendService;

@Autowired
private FrontendView frontendView;

	@Override
	public void init() {
        System.out.println("Initializing.......");
        Frontend gui =  new Frontend();
        gui.createFrame(GISI.startGame());   
	}

}

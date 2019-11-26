package frontendmgmt;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import gamemgmt.GameInstance;
import gamemgmt.GameInstanceServiceImpl;


@Controller
public class FrontendController implements FrontendService {
	
@Autowired	
private GameInstanceServiceImpl GISI;

@Autowired
private FrontendView frontendView;

	@Override
	public void init() {
        System.out.println("Initializing.......");
        frontendView.createFrame(GISI.startGame());   
	}

	
    /**
     * Eine Spielinstanz wird erstellt und zurückgegeben.
     * GUI wird mit Spielinstanz bestückt
     *
     * @return : eine Spielinstanz
     */
    private GameInstance startGame() {
		return new GameInstance();}
    
    
    /**
     * Das Spiel wird beendet.
     *
     * @param game: Eine Spielinstanz
     */
   private void endRound(GameInstance game) {};
}

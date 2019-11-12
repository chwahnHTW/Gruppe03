package errormgmt;

import gamemgmt.GameInstanceService;

public interface ErrorService {

	void handleError(GameInstanceService gameService);
	
	
}

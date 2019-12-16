package kbe.historymgmt;

import org.springframework.stereotype.Component;

import kbe.gamemgmt.GameInstance;
import org.springframework.stereotype.Service;

/**
 * @authors         Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler
 * Email-Adresse: 	s0564784@htw-berlin.de	| s0563958@htw-berlin.de| s0557193@htw-berlin.de
 */
@Service
public class HistoryServiceImpl implements HistoryService {

    @Override
    public void persist(GameInstance game) {
    }

    @Override
    public GameInstance getLastPlayedGame() {
        return null;
    }

}

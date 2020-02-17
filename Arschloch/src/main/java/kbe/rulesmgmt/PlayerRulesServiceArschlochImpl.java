package kbe.rulesmgmt;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import kbe.gamemgmt.GameInstance;
import kbe.playermgmt.Player;

/**
 * @authors Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler
 * Email-Adresse: 	s0564784@htw-berlin.de	| s0563958@htw-berlin.de| s0557193@htw-berlin.de
 *
 * Die Implementierung der Regeln, falls das Arschloch beginnen soll.
 */
@Service
@Qualifier("playerRulesServiceArschlochImpl")
public class PlayerRulesServiceArschlochImpl implements PlayerRulesService{

    @Override
	public void determineInitialPlayer(GameInstance gameInstance) {
		for (int i = 0; i < gameInstance.getPlayers().size(); i++) {
            try {
                if (gameInstance.getPlayers().get(i).getRole().equals(Player.Role.ARSCHLOCH1)) {
                    gameInstance.setCurrentPlayer(gameInstance.getPlayers().get(i));
                }
            } catch (Exception e) {

            }
        }
	}

}
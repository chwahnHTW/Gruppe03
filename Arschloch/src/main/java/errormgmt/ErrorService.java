package errormgmt;

import gamemgmt.GameInstance;

/**
 * @authors         Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler
 * Email-Adresse: 	s0564784@htw-berlin.de	| s0563958@htw-berlin.de| s0557193@htw-berlin.de
 */
public interface ErrorService {

    /**
     * Hier werden die Fehler behandelt, die in einer Spielinstanz zustande kommen können
     * @param gameInstance: eine Spielinstanz
     */
    void handleError(GameInstance gameInstance);

}

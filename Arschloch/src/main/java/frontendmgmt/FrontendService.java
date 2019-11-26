package frontendmgmt;

import java.util.List;

import org.springframework.stereotype.Component;

import cardmgmt.Card;
import gamemgmt.GameInstance;

/**
 * @authors         Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler
 * Email-Adresse: 	s0564784@htw-berlin.de	| s0563958@htw-berlin.de| s0557193@htw-berlin.de
 * <p>
 * * Hier wird das Frontend realisiert.
 */

@Component
public interface FrontendService {

    /**
     * Die GUI wird hier initialisiert.
     * es wird keine Spielinstanz erstellt.
     */
    void init();

}

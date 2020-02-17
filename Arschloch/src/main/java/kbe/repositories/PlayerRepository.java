package kbe.repositories;

import kbe.playermgmt.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @authors Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler
 * Email-Adresse: 	s0564784@htw-berlin.de	| s0563958@htw-berlin.de| s0557193@htw-berlin.de
 *
 * Das Repository für die Spieler.
 * Hier werden die Abfragen die für die Spieler aus der Datenbank benötigt werden abgefragt.
 */
@Repository
public interface PlayerRepository extends CrudRepository<Player, Integer> {

    List<Player> findPlayerByName(String name);

    Player findPlayerByPlayerId(Integer playerId);

}

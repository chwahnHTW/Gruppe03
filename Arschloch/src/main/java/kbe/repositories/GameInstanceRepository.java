package kbe.repositories;

import kbe.gamemgmt.GameInstance;
import kbe.playermgmt.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @authors Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler
 * Email-Adresse: 	s0564784@htw-berlin.de	| s0563958@htw-berlin.de| s0557193@htw-berlin.de
 * <p>
 * Das Repository für die Spielinstanz.
 * Hier werden die Abfragen die für die Spielinstanz aus der Datenbank benötigt werden abgefragt.
 */
@Repository
public interface GameInstanceRepository extends CrudRepository<GameInstance, Integer> {

    GameInstance findByGameId(int gameId);

    List<Player> findPlayersByGameId(int gameId);

    GameInstance findByPlayers(List<Player> players);
}
package kbe.repositories;

import kbe.gamemgmt.GameInstance;
import kbe.playermgmt.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameInstanceRepository extends CrudRepository<GameInstance, Integer> {

    GameInstance findByGameId(int gameId);

    List<Player> findPlayersByGameId(int gameId);

    GameInstance findByPlayers(List<Player> players);
}
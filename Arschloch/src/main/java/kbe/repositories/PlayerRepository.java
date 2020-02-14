package kbe.repositories;

import kbe.playermgmt.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Integer>{

    List<Player> findPlayerByName(String name);
//
    Player findPlayerByPlayerId(Integer playerId);

}

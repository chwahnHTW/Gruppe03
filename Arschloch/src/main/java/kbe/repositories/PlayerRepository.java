package kbe.repositories;

import kbe.playermgmt.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long>{

    List<Player> findPlayerByName(String name);
//
//    Player findPlayerByPlayerId(Integer playerId);

    void create(Player player);

}

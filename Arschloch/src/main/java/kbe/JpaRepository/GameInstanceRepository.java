package kbe.JpaRepository;

import kbe.gamemgmt.GameInstance;
import kbe.playermgmt.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameInstanceRepository extends CrudRepository<GameInstance, Integer> {

    List<Player> findByPlayers();

}

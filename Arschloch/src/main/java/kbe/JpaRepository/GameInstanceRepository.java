package kbe.JpaRepository;

import kbe.gamemgmt.GameInstance;
import kbe.playermgmt.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameInstanceRepository extends JpaRepository<GameInstance, Integer> {

    List<Player> findByPlayers();

}

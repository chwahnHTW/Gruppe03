package kbe.historymgmt;


import kbe.gamemgmt.GameInstance;
import kbe.playermgmt.Player;
import kbe.playermgmt.PlayerService;
import kbe.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kbe.jpaConfig.jpaConfiguration;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


/**
 * @authors Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler
 * Email-Adresse: 	s0564784@htw-berlin.de	| s0563958@htw-berlin.de| s0557193@htw-berlin.de
 */
//@EnableJpaRepositories("kbe.JpaRepository")
@Service
@Transactional
public class HistoryServiceImpl implements HistoryService {

    private History history;

    @Autowired
    PlayerRepository playerRepository;

//    @Autowired
//    EntityManager entityManager;

    @Override
    public void tueEtwas() {
//        EntityTransaction trans = entityManager.getTransaction();
//        trans.begin();
        // hier steht die Anwendungslogik

        Player newUser = new Player();
        newUser.setName("Kaya");
        newUser.setRole(null);

        playerRepository.save(newUser);

//        trans.commit();

        System.out.println("Neuer Spieler: " + playerRepository.findPlayerByName("Kaya"));
//        playerRepository.findPlayerByName("Kaya");


    }

    @Override
    public void persist(GameInstance instance) {
        history.setHistory(instance);
    }

    @Override
    public GameInstance getLastPlayedGame() {
//        Integer lastGame = 1;
//        List<Player> players = gameInstanceRepository.findByPlayers();
//        GameInstance gameInstance = gameInstanceRepository.findOne(lastGame);
        return null;
    }

    public void saveCurrentGame(GameInstance gameInstance) {
//        gameInstanceRepository.flush();
//        gameInstanceRepository.save(gameInstance);
    }

    @Override
    public void saveToCSV(GameInstance instance) {

        try {
            FileWriter writer = new FileWriter("/Users/kayaloeher/Desktop/gameHistory.csv");

            List<String> playerNames = new LinkedList<>();
            List<String> playerRole = new LinkedList<>();

            for (Player player : instance.getPlayers()) {
                playerNames.add(player.getName());
                playerRole.add(player.getRole().toString());
            }

            BufferedWriter bw = new BufferedWriter(writer);

            bw.write("Name,Rolle");
            bw.newLine();

            for (int i = 0; i < playerNames.size(); i++) {
                bw.write(playerNames.get(i) + "," + playerRole.get(i));
                bw.newLine();
            }
            bw.close();
            writer.close();

        } catch (IOException e) {

        }
    }
}

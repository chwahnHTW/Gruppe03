package kbe.historymgmt;

import kbe.JpaRepository.CardRepository;
import kbe.JpaRepository.GameInstanceRepository;
import kbe.JpaRepository.PlayerRepository;
import kbe.cardmgmt.Card;
import kbe.cardmgmt.CardService;
import kbe.gamemgmt.GameInstanceService;
import kbe.playermgmt.Player;
import kbe.playermgmt.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kbe.gamemgmt.GameInstance;
import org.springframework.stereotype.Service;


import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.List;

/**
 * @authors Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler
 * Email-Adresse: 	s0564784@htw-berlin.de	| s0563958@htw-berlin.de| s0557193@htw-berlin.de
 */
@Service
public class HistoryServiceImpl implements HistoryService {

    History history = null;


    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    PlayerService playerService;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    CardService cardService;

    @Autowired
    GameInstanceRepository gameInstanceRepository;

    @Autowired
    GameInstanceService gameInstanceService;

    @Override
    public void persist(GameInstance instance) {

        history.setHistory(instance);

    }

    @Override
    @Autowired
    public GameInstance getLastPlayedGame() {

//        List<Player> players = playerRepository.findAll();
//        List<Card> cards = cardRepository.findAll();


        Integer lastGame = 1;
        GameInstance gameInstance =  gameInstanceRepository.findOne(lastGame);


        return gameInstance;
    }


    @Override
    public void saveToCSV(GameInstance instance) {

        try {
            FileWriter writer = new FileWriter("/Users/kayaloeher/Desktop/gameHistory.csv");
//        List<GameInstance> hist = history.getHistory();

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

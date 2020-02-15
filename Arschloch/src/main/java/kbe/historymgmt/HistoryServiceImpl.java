package kbe.historymgmt;

import kbe.cardmgmt.Card;
import kbe.gamemgmt.GameInstance;
import kbe.playermgmt.Player;
import kbe.playermgmt.PlayerService;
import kbe.repositories.CardRepository;
import kbe.repositories.GameInstanceRepository;
import kbe.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Service
@Transactional
public class HistoryServiceImpl implements HistoryService {

    private History history;

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    GameInstanceRepository gameInstanceRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    PlayerService playerService;


    @Override
    public void persist(GameInstance instance) {
        history.setHistory(instance);
    }

    @Override
    public GameInstance getLastPlayedGame(int gameId) {

        GameInstance gameInstance = gameInstanceRepository.findByGameId(gameId);

        return gameInstance;
//        List<Player> players = gameInstanceRepository.findPlayersByGameId(gameId);
//        for (Player player : players) {
//            Player player1 = new Player();
//            player1.setName(player.getName());
//            player1.setRole(player.getRole());
//        }
//
//        List<Card> cards = cardRepository.findAllCards();
//        for (Card card : cards) {
//            Card card1 = new Card();
//            card1.setSymbol(card.getSymbol());
//            card1.setZahl(card.getZahl());
//        }
    }

    @Override
    public void saveCurrentGame(GameInstance instance) {

//        gameInstanceRepository.deleteAll();
//        playerRepository.deleteAll();
//        cardRepository.deleteAll();
//
//        List<Player> players = instance.getPlayers();
//        for (Player player : players) {
//
//            for (Card card : player.getHandCards()) {
//                cardRepository.save(card);
//            }
//            playerRepository.save(player);
//        }
//        if (!instance.getBoardCards().isEmpty()) {
//            for(Card card : instance.getBoardCards()){
//                cardRepository.save(card);
//            }
//        }
        gameInstanceRepository.save(instance);
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

package kbe.historymgmt;

import kbe.playermgmt.Player;
import org.springframework.stereotype.Component;

import kbe.gamemgmt.GameInstance;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
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

    @Override
    public void persist(GameInstance instance) {

        history.setHistory(instance);

    }

    @Override
    public GameInstance getLastPlayedGame() {
        return null;
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


            String collect = playerNames.stream().collect(Collectors.joining(","));
            System.out.println(collect);

            writer.write(collect);
            writer.close();

        } catch (IOException e) {

        }

    }
}

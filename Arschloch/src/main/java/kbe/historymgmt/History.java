package kbe.historymgmt;

import java.util.List;

import kbe.gamemgmt.GameInstance;

/**
 * @authors         Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler
 * Email-Adresse: 	s0564784@htw-berlin.de 	| s0563958@htw-berlin.de| s0557193@htw-berlin.de
 * <p>
 * Nach jedem beendeten Spiel gibt es ein Ergbenis mit der Reihenfolge der Gewinner, den Regeln und den schon gespielten Spielen.
 * Hier wird die Spielhistorie gespeichert.
 */
public class History {

    String fileName = "example.csv";

    List<GameInstance> history;

    /**
     * Generiert eine Spielhistorie
     *
     * @param fileName: Name der Datei, in der die Gesamtspielhistorie gespeichert wird
     * @param history:  Chronologische Liste aller vergangenen Spieldurchläufe
     */
    public History(String fileName, List<GameInstance> history) {
        this.fileName = fileName;
        this.history = history;
    }

    /**
     * Gibt den Namen der Datei zurück.
     *
     * @return :  der Dateiname
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Setzt den Dateinamen
     *
     * @param fileName : der Dateiname
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Gibt eine Spielhistorie zurück
     *
     * @return : die Spielhistorie
     */
    public List<GameInstance> getHistory() {
        return history;
    }

    /**
     * Setzt die Spielhistorie
     *
     * @param instance : die Spielinstanz
     */
    public void setHistory(GameInstance instance) {
        history.add(instance);
    }
}

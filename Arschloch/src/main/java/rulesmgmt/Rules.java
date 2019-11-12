package rulesmgmt;

/**
 * @authors         Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler
 * Email-Adresse: 	s0564784@htw-berlin.de	| s0563958@htw-berlin.de| s0557193@htw-berlin.de
 */
public class Rules {

    String ruleName;

    /**
     * Hier werden die Regeln ( Wertigketien der Karten ) festgelegt.
     *
     * @param name: name der Regel, bezogen auf das Spielblatt udn dessen Wertigkeiten ( values = "standard","french")
     */
    public Rules(String name) {
        this.ruleName = name;
    }

    /**
     * Gibt den Namen der Regel zurück
     *
     * @return : Name der Regel
     */
    public String getRuleName() {
        return ruleName;
    }

    /**
     * Setzt den Namen der Regel
     *
     * @param name : Name der Regel
     */
    public void setRuleName(String name) {
        this.ruleName = name;
    }
}

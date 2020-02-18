package kbe.repositories;

import kbe.cardmgmt.Card;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @authors Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler
 * Email-Adresse: 	s0564784@htw-berlin.de	| s0563958@htw-berlin.de| s0557193@htw-berlin.de
 * <p>
 * Das Repository für die Karten.
 * Hier werden die Abfragen die für die Karten aus der Datenbank benötigt werden abgefragt.
 */
@Repository
public interface CardRepository extends CrudRepository<Card, Integer> {

    @Query(value = "SELECT * FROM Cards", nativeQuery = true)
    List<Card> findAllCards();


}

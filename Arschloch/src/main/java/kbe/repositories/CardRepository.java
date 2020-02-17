package kbe.repositories;

import kbe.cardmgmt.Card;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends CrudRepository<Card, Integer> {

    @Query(value = "SELECT * FROM Cards", nativeQuery = true)
    List<Card> findAllCards();


}

package kbe.JpaRepository;

import kbe.cardmgmt.Card;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface CardRepository extends CrudRepository<Card, Integer> {
}

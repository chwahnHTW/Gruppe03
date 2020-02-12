package kbe.repositories;

import kbe.DataAccessException;
import kbe.playermgmt.Player;
import org.springframework.stereotype.Repository;


import javax.persistence.*;

@Repository
public abstract class PlayerRepositoryImpl implements PlayerRepository {

//    @PersistenceUnit(unitName = "arschloch")
    private EntityManager entityManager;

    @Override
    public void create(Player player) {
        this.entityManager.persist(player);
        try {
            this.entityManager.persist(player);
        } catch (PersistenceException exp) {
            throw new DataAccessException(exp);
        }
    }
}

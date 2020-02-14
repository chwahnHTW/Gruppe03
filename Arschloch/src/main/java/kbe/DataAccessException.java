package kbe;

import javax.persistence.PersistenceException;

public class DataAccessException extends RuntimeException {

    public DataAccessException(PersistenceException exp) {
        super(exp);
    }

}
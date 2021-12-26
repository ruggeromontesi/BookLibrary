package it.ruggero.springboot.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class ExtendedUserRepository implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

}

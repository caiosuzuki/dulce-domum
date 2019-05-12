package com.dulcedomum.base;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class DataBaseUtil {

    @PersistenceContext
    protected EntityManager entityManager;

    @Transactional
    public void clean() {
        entityManager.createNativeQuery("TRUNCATE SCHEMA PUBLIC RESTART IDENTITY AND COMMIT NO CHECK").executeUpdate();
        entityManager.clear();
        entityManager.flush();
    }
}
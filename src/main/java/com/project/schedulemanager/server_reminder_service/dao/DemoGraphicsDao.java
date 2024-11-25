package com.project.schedulemanager.server_reminder_service.dao;


import com.project.schedulemanager.server_reminder_service.entity.Demographics;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

@Repository
public class DemoGraphicsDao {
    EntityManager entityManager;
    DemoGraphicsDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    public Demographics getUserDemographics(String username) {
        TypedQuery<Demographics> categories = entityManager
                .createQuery(
                        "SELECT c FROM Demographics c WHERE c.username = :username", Demographics.class);
        categories.setParameter("username", username);
        return categories.getResultList().get(0);

    }
}

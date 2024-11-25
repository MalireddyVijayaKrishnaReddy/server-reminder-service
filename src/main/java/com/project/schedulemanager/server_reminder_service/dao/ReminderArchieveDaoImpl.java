package com.project.schedulemanager.server_reminder_service.dao;

import com.project.schedulemanager.server_reminder_service.entity.ReminderArchieve;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ReminderArchieveDaoImpl implements ReminderArchieveDao {


    @Autowired
    EntityManager entityManager;
    @Override
    public void archieveReminder(ReminderArchieve reminderArchieve) {
        entityManager.persist(reminderArchieve);
    }
}

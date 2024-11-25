package com.project.schedulemanager.server_reminder_service.dao;


import com.project.schedulemanager.server_reminder_service.entity.Reminder;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ReminderDaoImpl implements ReminderDao {

@Autowired
    EntityManager entityManager;

    @Override
    public void updateReminder(Reminder reminder) {
     entityManager.merge(reminder);
    }

    @Override
    public List<Reminder> getAllReminders(LocalDateTime max_due_date) {
        TypedQuery<Reminder> reminders=  entityManager.createQuery("select r from Reminder r where reminder_sent= :reminder_sent AND reminder_datetime<=: max_due_date", Reminder.class)
                .setParameter("reminder_sent","N")
                .setParameter("max_due_date",max_due_date);

       return reminders.getResultList();
    }

    @Override
    public void removeReminder(Reminder reminder) {
        entityManager.remove(reminder);
    }

    @Override
    public List<Reminder> getExpiredReminders(LocalDateTime max_due_date) {
        TypedQuery<Reminder> reminders=  entityManager.createQuery("select r from Reminder r where reminder_sent= :reminder_sent AND reminder_datetime<=: max_due_date", Reminder.class)
                .setParameter("max_due_date",max_due_date)
                .setParameter("reminder_sent","Y");

        return reminders.getResultList();
    }
}

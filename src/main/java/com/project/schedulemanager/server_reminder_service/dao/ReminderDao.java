package com.project.schedulemanager.server_reminder_service.dao;



import com.project.schedulemanager.server_reminder_service.entity.Reminder;

import java.time.LocalDateTime;
import java.util.List;

public interface ReminderDao {
    public void updateReminder(Reminder reminder);
    public List<Reminder> getAllReminders(LocalDateTime max_due_date);
    public void removeReminder(Reminder reminder);
    public List<Reminder> getExpiredReminders(LocalDateTime max_due_date);
}

package com.project.schedulemanager.server_reminder_service.templates;

import com.project.schedulemanager.server_reminder_service.entity.Reminder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MessageTemplate {
    private String body;
    private String number;
    public MessageTemplate(Reminder reminder) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String dateTime = reminder.getReminder_datetime().format(formatter);

        this.body="Your " + reminder.getTask_name() + " is due on " + dateTime;

    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}

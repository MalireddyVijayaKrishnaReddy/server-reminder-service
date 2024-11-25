package com.project.schedulemanager.server_reminder_service.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "reminders_archieve")
public class ReminderArchieve {


    @Id
    @Column(name = "reminder_id")
    private int reminder_id;

    @Column(name = "category_name")
    private String category_name;

    @Column(name = "reminder_datetime")

    private LocalDateTime reminder_datetime;

    @Column(name = "reminder_medium")
    private String reminder_medium;

    @Column (name="username")
    private String username;

    @Column(name = "task_name")
    private String task_name;

    @Column(name = "note")
    private String note;

    @Column(name="remind_me")
    private String remind_me;

    @Column(name = "reminder_sent")
    private String reminder_sent;

    public int getReminder_id() {
        return reminder_id;
    }

    public void setReminder_id(int reminder_id) {
        this.reminder_id = reminder_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public LocalDateTime getReminder_datetime() {
        return reminder_datetime;
    }

    public void setReminder_datetime(LocalDateTime reminder_datetime) {
        this.reminder_datetime = reminder_datetime;
    }

    public String getReminder_medium() {
        return reminder_medium;
    }

    public void setReminder_medium(String reminder_medium) {
        this.reminder_medium = reminder_medium;
    }


    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNote() {
        return note;

    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getRemind_me() {
        return remind_me;
    }

    public void setRemind_me(String remind_me) {
        this.remind_me = remind_me;
    }

    public String getReminder_sent() {
        return reminder_sent;
    }

    public void setReminder_sent(String reminder_sent) {
        this.reminder_sent = reminder_sent;
    }

    @Override
    public String toString() {
        return "Reminder{" +
                "reminder_id=" + reminder_id +
                ", category_name='" + category_name + '\'' +
                ", reminder_datetime=" + reminder_datetime +
                ", reminder_medium='" + reminder_medium + '\'' +
                ", username='" + username + '\'' +
                ", task_name='" + task_name + '\'' +
                ", note='" + note + '\'' +
                ", remind_me='" + remind_me + '\'' +
                ", reminder_sent='" + reminder_sent + '\'' +
                '}';
    }
}

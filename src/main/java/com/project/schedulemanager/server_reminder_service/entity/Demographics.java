package com.project.schedulemanager.server_reminder_service.entity;

import jakarta.persistence.*;


@Entity
@Table(name="user_demographics")
public class Demographics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private int user_id;
    @Column(name="username")
    private String username;
    @Column(name="firstname")
    private String firstname;

    @Column(name = "last_name")
    private String lastname;
    @Column(name = "phone_number")
    private String phone_number;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }



    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    @Override
    public String toString() {
        return "Demographics{" +
                "user_id=" + user_id +
                ", username='" + username + '\'' +
                ", firstname='" + firstname + '\'' +

                ", lastname='" + lastname + '\'' +
                ", phone_number='" + phone_number + '\'' +
                '}';
    }
}

package com.spaceshooter.entities;

// Java Program to Illustrate Creation of Simple POJO Class

// Importing required classes
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "userTable")

// POJO class
public class UserTable {


    @Id @Column(name = "userName") private String userName;

    @Column(name = "hashedPassword") private String hashedPassword;


    public String getUserName() {
        return userName;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }


    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    @Override
    public String toString() {
        return "UserTable{" +
                "userName='" + userName + '\'' +
                ", hashedPassword='" + hashedPassword + '\'' +
                '}';
    }
}


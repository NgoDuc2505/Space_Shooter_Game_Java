package com.spaceshooter.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "scoreTable")

// POJO class
public class ScoreTable {


    @Id @Column(name = "userName") private String userName;

    @Column(name = "score") private int score;


    public String getUserName() {
        return userName;
    }

    public int getScore() {
        return score;
    }


    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "scoreTable{" +
                "userName='" + userName + '\'' +
                ", score='" + score + '\'' +
                '}';
    }
}
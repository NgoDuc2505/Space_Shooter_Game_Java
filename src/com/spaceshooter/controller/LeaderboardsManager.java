package com.spaceshooter.controller;

import com.spaceshooter.model.LeaderboardData;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

public class LeaderboardsManager {
    final private String leaderboardFilename = "scores.dat";
//    final private String leaderboardFilename = "scores.dat";
    private ArrayList<LeaderboardData> leaderboardsData;
    private DatabaseConnector db = new DatabaseConnector();
    private Statement statement = db.getStatement();
    private ResultSet rs;

    private ArrayList<LeaderboardData> getDataFromDB (){
        final String query = "SELECT * FROM scoreTable;";
        try{
            rs = statement.executeQuery(query);
            leaderboardsData.clear();
            while (rs.next()){
                leaderboardsData.add(new LeaderboardData(rs.getString(1), rs.getInt(2)));
            }
            return leaderboardsData;
        }catch (Exception e){
            System.out.println("LeaderboardsManager error: "+e);
            leaderboardsData = (ArrayList<LeaderboardData>) FileHandler.readObjectFromFile(leaderboardFilename);
            return leaderboardsData;
        }
    }

    public LeaderboardsManager() {
        leaderboardsData = getDataFromDB();

        if (leaderboardsData == null) {
            leaderboardsData = new ArrayList<>();
        }
    }

    public ArrayList<LeaderboardData> getLeaderboardsData() {
        return leaderboardsData;
    }

    public void addUserScore(String username, int score) {
//        leaderboardsData.clear();
        leaderboardsData = getDataFromDB();
        Collections.sort(leaderboardsData);
        System.out.println("Test score: "+username+" // "+score);
        FileHandler.writeObjectToFile(leaderboardsData, leaderboardFilename);
    }
}

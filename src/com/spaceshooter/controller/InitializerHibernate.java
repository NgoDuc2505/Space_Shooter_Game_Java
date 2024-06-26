package com.spaceshooter.controller;



import com.spaceshooter.entities.ScoreTable;
import com.spaceshooter.entities.UserTable;
import org.hibernate.Session;

import java.util.List;


public class InitializerHibernate {
    private static Session sessionGlob;
    public static void main(String[] args) {

        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            session.beginTransaction();
            sessionGlob = session;
            UserTable user = new UserTable();
            Long numberOfUser = session.createQuery("SELECT COUNT(userName) FROM UserTable", Long.class).uniqueResult();
            System.out.println("Number of user in database: " + numberOfUser);
            // Get users
            List<UserTable> users = session.createQuery("FROM UserTable", UserTable.class).list();
            users.forEach(System.out::println);
            UserTable savedUser = session.find(UserTable.class,"duc25");
            System.out.println("savedUser: " + savedUser.toString());
            // Update user
//            savedUser.setUserName("GP Coder");
//            session.update(savedUser);
            UserTable userNew = new UserTable();
            userNew.setUserName("Hiber12");
            userNew.setHashedPassword(SecurityManager.getSha1Hex("123456789"));
            session.save(userNew);
            session.getTransaction().commit();
            session.close();
        }catch (Exception e){
            System.out.println(e+"... Some thing happen here!");
        }
    }

    public static void initScoreUser(String currentUser) throws Exception {
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            session.beginTransaction();
            ScoreTable initSore = new ScoreTable();
            initSore.setUserName(currentUser);
            initSore.setScore(0);
            session.save(initSore);
            session.getTransaction().commit();
            session.close();
        }catch(Exception ex){
            System.out.println(ex + " : " + "Error initScoreUser...");
            throw new Exception("Data too long for username: max at 10!");
        }
    }

    public static void updateUserScore(String currentUser, int playerScore) throws Exception {
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            session.beginTransaction();
            ScoreTable getUser = session.find(ScoreTable.class,currentUser);
            getUser.setScore(playerScore);
            session.update(getUser);
            session.getTransaction().commit();
            session.close();
        }catch(Exception ex){
            System.out.println(ex + " : " + "Error...");
            throw new Exception("Data too long for username: max at 10!");
        }
    }

    public static void register(String currentUser, String pass){
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            session.beginTransaction();
           String hashPass = SecurityManager.getSha1Hex(pass);
           UserTable user = new UserTable();
           user.setUserName(currentUser);
           user.setHashedPassword(hashPass);
           session.save(user);
           session.getTransaction().commit();
           session.close();
        }catch(Exception ex){
            System.out.println(ex + " : " + "Error...");
        }
    }



    public void main2(String[] args) {
//        updateUserScore("duc25",200);
        register("test123","jjjjj22");
//        initScoreUser("test123");
    }

}

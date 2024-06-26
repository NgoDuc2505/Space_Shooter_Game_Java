package com.spaceshooter.controller;

import com.spaceshooter.model.User;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Map;

public class SecurityManager {
    public static String currentUser;
    final static private String usersFilename = "users.dat";
    private static Map<String, User> usersMap;
    private static DatabaseConnector db = new DatabaseConnector();
    private static Statement statement = db.getStatement();
    private static ResultSet rs;
    static {
        usersMap = (Hashtable<String, User>) FileHandler.readObjectFromFile(usersFilename);

        if (usersMap == null) {
            usersMap = new Hashtable<>();
        }
    }

    private static String registerHibernateQuery(String currentUser, String pass){
        String query = "INSERT INTO userTable(username, hashedPassword)\n" +
                "VALUES (\""+ currentUser +"\",\""+ pass +"\");";
        return query;
    }

    private static String initScoreHibernateQuery(String currentUser){
        String queryScore = "INSERT INTO scoreTable(username, score)\n" +
                "VALUES (\""+ currentUser +"\", "+ 0 +");";
        return queryScore;
    }

    public static boolean login(String username, String password) throws Exception {
        return checkLoginInformation(username, password);
    }

    public static void register(String username, String password) throws Exception {
        validateInformation(username, password);
        String hashedPassword = getSha1Hex(password);
        User newUserToRegister = new User(username, hashedPassword);
        System.out.println("Secure: "+username+" : "+hashedPassword);
        if (usersMap.containsKey(username)) {
            throw new Exception("Username already exists");
        }
        final String excution = registerHibernateQuery(username,hashedPassword);
        final String excutionScore = initScoreHibernateQuery(username);
        currentUser = username;
        usersMap.put(username, newUserToRegister);
        System.out.println("query "+excution);
        InitializerHibernate.register(username,password);
        InitializerHibernate.initScoreUser(username);
//        statement.executeUpdate(excution);
//        statement.executeUpdate(excutionScore);
        FileHandler.writeObjectToFile(usersMap, usersFilename);
    }

    public static String getSha1Hex(String clearString) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            messageDigest.update(clearString.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = messageDigest.digest();
            StringBuilder buffer = new StringBuilder();
            for (byte b : bytes) {
                buffer.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
            }
            return buffer.toString();
        } catch (Exception ignored) {
            ignored.printStackTrace();
            return null;
        }
    }

    private static boolean checkLoginInformation(String username, String password) throws Exception {
        validateInformation(username, password);
        String passText = getSha1Hex(password);
        System.out.println("Normal passText "+password);
        System.out.println("passText "+passText);
        final String query = "SELECT * FROM userTable\n" +
                "WHERE username = \""+ username +"\";";
        String usrNameDB = "";
        String hashPassDB = "";
        if (usersMap.containsKey(username)) {
            rs = statement.executeQuery(query);
            while(rs.next()){
                usrNameDB = rs.getString(1);
                hashPassDB = rs.getString(2);
            }
            System.out.println("usrNameDB: "+usrNameDB);
            if (usersMap.get(username).getPassword().equals(passText) && hashPassDB.equals(passText)) {
                currentUser = username;
                return true;
            } else {
                throw new Exception("Incorrect Password");
            }
        } else {
            return false;
        }
    }

    private static void validateInformation(String username, String password) throws Exception {
        boolean isUsernameValid = true;

        if (username.length() < 4) {
            isUsernameValid = false;
        }

        if (isUsernameValid) {
            for (char letter : username.toCharArray()) {
                if (!Character.isLetterOrDigit(letter)) {
                    isUsernameValid = false;
                }
            }
        }

        if (!isUsernameValid) {
            throw new Exception("Username must be alphanumeric and at least 4 characters long");
        }

        // check password
        if (password.isEmpty() || password.length() < 8) {
            throw new Exception("Password must be at least 8 characters long");
        }
    }

    public static boolean deleteUser(String username) {
        User value = usersMap.remove(username);
        FileHandler.writeObjectToFile(usersMap, usersFilename);

        return value != null;
    }

    public static User getUser(String username) {
        return usersMap.get(username);
    }

}

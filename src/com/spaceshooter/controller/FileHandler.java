package com.spaceshooter.controller;

import java.io.*;

public class FileHandler {

    public static void writeObjectToFile(Object objectToWrite, String filename) {
        try {
            FileOutputStream fileOut = new FileOutputStream(filename);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(objectToWrite);
            objectOut.close();

        } catch (FileNotFoundException ex) {
            createNewFile(filename);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static Object readObjectFromFile(String filename) {
        Object objectToRead = null;

        try {
            FileInputStream fileInputStream = new FileInputStream(filename);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            objectToRead = objectInputStream.readObject();

            fileInputStream.close();
            objectInputStream.close();
        } catch (FileNotFoundException fnfe) {
            createNewFile(filename);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return objectToRead;
    }

    private static void createNewFile(String filename) {
        File file = new File(filename);
        try {
            file.createNewFile();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}

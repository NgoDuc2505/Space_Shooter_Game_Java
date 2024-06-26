package com.spaceshooter.controller;


import com.spaceshooter.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SecurityManagerTest {
    private User user;

    @AfterEach
    void tearDown() {
    }

    @Test
    void loginTest() {
        Assertions.assertDoesNotThrow(() ->
                SecurityManager.login("goodusername", "goodpassword"));
    }

    @Test
    void loginTestUsernameBadCharacters() {
        Assertions.assertThrows(Exception.class, () ->
                SecurityManager.login("abc$$$$$$$$$$$$", "zzzzzzzzzzzzzzzz"));
    }

    @Test
    void loginTestEmptyUsernameAndPassword() {
        Assertions.assertThrows(Exception.class, () ->
                SecurityManager.login("", ""));
    }

    @Test
    void loginTestEmptyUsernameGoodPassword() {
        Assertions.assertThrows(Exception.class, () ->
                SecurityManager.login("", "123shfois1"));
    }

    @Test
    void loginTestGoodUsernameEmptyPassword() {
        Assertions.assertThrows(Exception.class, () ->
                SecurityManager.login("yuval", ""));
    }

    @Test
    void registerTest() {
        Assertions.assertDoesNotThrow(() ->
                SecurityManager.register("goodusername", "goodpassword"));

        Assertions.assertTrue(SecurityManager.deleteUser("goodusername"));
    }

    @Test
    void registerTestEmptyUsernameAndPassword() {
        Assertions.assertThrows(Exception.class, () ->
                SecurityManager.register("", ""));

        Assertions.assertNull(SecurityManager.getUser(""));
    }

    @Test
    void registerTestEmptyUsernameGoodPassword() {
        Assertions.assertThrows(Exception.class, () ->
                SecurityManager.register("", "123shfois1"));

        Assertions.assertNull(SecurityManager.getUser(""));
    }

    @Test
    void registerTestGoodUsernameEmptyPassword() {
        Assertions.assertThrows(Exception.class, () ->
                SecurityManager.register("someusername", ""));

        Assertions.assertNull(SecurityManager.getUser("someusername"));
    }

    @Test
    void registerTestUsernameBadCharacters() {
        Assertions.assertThrows(Exception.class, () ->
                SecurityManager.register("abc$$$$$$$$$$$$", "zzzzzzzzzzzzzzzz"));

        Assertions.assertNull(SecurityManager.getUser("abc$$$$$$$$$$$$"));
    }
}
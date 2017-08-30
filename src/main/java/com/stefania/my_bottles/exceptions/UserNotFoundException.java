package com.stefania.my_bottles.exceptions;

public class UserNotFoundException extends Throwable {

    public UserNotFoundException(String username) {
        super("User not found: username=" + username);
    }

}

package com.example.YouOweMeProject.Model;

import java.util.ArrayList;

public class Friend {
    String username;
    Float balance;

    public Friend() {
    }

    public Friend(String username, Float balance) {
        this.username = username;
        this.balance = balance;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }
}

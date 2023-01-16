package com.example.YouOweMeProject.Model;

import java.util.ArrayList;

public class Friends {
    ArrayList<Friend> friends;

    public Friends() {
    }

    public Friends(ArrayList<Friend> friends) {
        this.friends = friends;
    }

    public ArrayList<Friend> getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<Friend> friends) {
        this.friends = friends;
    }
}

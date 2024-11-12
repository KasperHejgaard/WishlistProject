package com.example.wishlist.model;

public class Profile {
    private int profileID;
    private String username;
    private String password;

    public Profile() {

    }

    public Profile(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getProfileID() {
        return profileID;
    }

    public void setProfileID(int profileID) {
        this.profileID = profileID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

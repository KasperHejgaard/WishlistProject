package com.example.wishlist.service;

import com.example.wishlist.model.Profile;

public class ProfileService {

    Profile profile = new Profile();

    public Profile getProfile() {
        return profile;
    }

    public Profile getProfile(String username, String password) {
        return profile;
    }
}

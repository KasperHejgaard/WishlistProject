package service;

import model.Profile;

public class ProfileService {

    Profile profile = new Profile();

    public Profile getProfile() {
        return profile;
    }

    public Profile getProfile(String username, String password) {
        return profile;
    }
}

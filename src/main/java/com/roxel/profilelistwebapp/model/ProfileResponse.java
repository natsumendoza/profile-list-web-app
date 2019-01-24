package com.roxel.profilelistwebapp.model;

import java.util.List;

public class ProfileResponse {

    private String statusCode;
    private List<Profile> profiles;

    public ProfileResponse(String statusCode, List<Profile> profiles) {
        this.statusCode = statusCode;
        this.profiles = profiles;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public List<Profile> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<Profile> profiles) {
        this.profiles = profiles;
    }
}

package com.roxel.profilelistwebapp.service;

import com.roxel.profilelistwebapp.model.ProfileResponse;

public interface ProfileService {
    ProfileResponse getProfiles();
    ProfileResponse getProfileById(String id);
    ProfileResponse getProfilesByName(String name);
}

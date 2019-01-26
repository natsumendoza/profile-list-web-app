package com.roxel.profilelistwebapp.service.impl;

import com.roxel.profilelistwebapp.model.Profile;
import com.roxel.profilelistwebapp.model.ProfileResponse;
import com.roxel.profilelistwebapp.service.ProfileService;
import com.roxel.profilelistwebapp.util.ProfilesUtil;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Profiles;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfileServiceImpl extends ProfilesUtil implements ProfileService {

    private final Logger logger;

    @Autowired
    public ProfileServiceImpl(Logger logger, RestTemplate restTemplate) {
        super(restTemplate);

        this.logger = logger;

    }

    @Override
    public ProfileResponse getProfiles() { return getAllProfilesRestTemplate(); }

    @Override
    public ProfileResponse getProfileById(String id) {

        logger.info("profile id: " + id);

        ProfileResponse profileResponse = getAllProfilesRestTemplate();
        List<Profile> profiles = profileResponse.getProfiles();
        List<Profile> foundProfiles = findProfileById(profiles, id);

        return new ProfileResponse(profileResponse.getStatusCode(), foundProfiles);
    }

    @Override
    public ProfileResponse getProfilesByName(String name) {

        ProfileResponse profileResponse = getAllProfilesRestTemplate();
        List<Profile> profiles = profileResponse.getProfiles();
        List<Profile> foundProfiles = searchProfilesByName(profiles, name);

        return new ProfileResponse(profileResponse.getStatusCode(), foundProfiles);
    }


}

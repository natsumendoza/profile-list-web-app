package com.roxel.profilelistwebapp.util;

import com.roxel.profilelistwebapp.model.Profile;
import com.roxel.profilelistwebapp.model.ProfileResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class ProfilesUtil {

    private RestTemplate restTemplate;

    @Value("${profile.service.url}")
    private String profileServiceUrl;

    public ProfilesUtil() {}

    public ProfilesUtil(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    protected ProfileResponse getAllProfilesRestTemplate() {

        ResponseEntity<List<Profile>> responseEntity = restTemplate.exchange(
                profileServiceUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Profile>>(){});

        List<Profile> profiles = null;
        String statusCode = responseEntity.getStatusCode().toString();

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            if (responseEntity.getBody().size() > 0) {
                profiles = responseEntity.getBody();
            }
        }


        return new ProfileResponse(statusCode, profiles);
    }

    protected List<Profile> findProfileById(List<Profile> profiles, String id) {

        final List<Profile> foundProfiles = new ArrayList<>();

        profiles.forEach(p -> {
            if (p.getId().equalsIgnoreCase(id)) {
                foundProfiles.add(p);
            }
        });

        return foundProfiles;

    }

    protected List<Profile> searchProfilesByName(List<Profile> profiles, String name) {

        final List<Profile> foundProfiles = new ArrayList<>();

        profiles.forEach(profile -> {
            String firstName = profile.getName().getFirst();
            String lastName = profile.getName().getLast();
            String profileName = firstName + " " + lastName;

            if (profileName.toLowerCase().contains(name.toLowerCase())) {
                foundProfiles.add(profile);
            }
        });

        return foundProfiles;

    }
}

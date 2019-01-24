package com.roxel.profilelistwebapp.service.impl;

import com.roxel.profilelistwebapp.model.Profile;
import com.roxel.profilelistwebapp.model.ProfileResponse;
import com.roxel.profilelistwebapp.model.Profiles;
import com.roxel.profilelistwebapp.service.ProfileService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ProfileServiceImpl implements ProfileService {

    private final Logger logger;

    private final RestTemplate restTemplate;

    @Value("${profile.service.url}")
    private String profileServiceUrl;

    @Autowired
    public ProfileServiceImpl(Logger logger, RestTemplate restTemplate) {
        this.logger = logger;
        this.restTemplate = restTemplate;
    }

    @Override
    public ProfileResponse getProfiles() {

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

        logger.info(statusCode);

        return new ProfileResponse(statusCode, profiles);
    }


}

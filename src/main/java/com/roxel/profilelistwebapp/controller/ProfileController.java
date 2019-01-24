package com.roxel.profilelistwebapp.controller;

import com.roxel.profilelistwebapp.model.ProfileResponse;
import com.roxel.profilelistwebapp.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {

    private final ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping()
    public ProfileResponse getProfiles() {
        return profileService.getProfiles();
    }

}

package com.roxel.profilelistwebapp.api;

import com.roxel.profilelistwebapp.model.ProfileResponse;
import com.roxel.profilelistwebapp.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/{id}")
    public ProfileResponse getProfileById(@PathVariable String id) {
        return profileService.getProfileById(id);
    }

    @GetMapping("/search/{name}")
    public ProfileResponse getProfilesByName(@PathVariable String name) {
        return profileService.getProfilesByName(name);
    }

}

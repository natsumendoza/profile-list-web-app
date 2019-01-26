package com.roxel.profilelistwebapp;

import com.roxel.profilelistwebapp.model.Profile;
import com.roxel.profilelistwebapp.model.ProfileResponse;
import com.roxel.profilelistwebapp.service.ProfileService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProfileServiceTest {

    @Autowired
    private ProfileService profileService;

    @Test
    public void testGetProfilesResponse() {
        ProfileResponse profileResponse = profileService.getProfiles();
        Assertions.assertThat(profileResponse
                .getStatusCode()
                .equalsIgnoreCase("200"));
    }

    @Test
    public void testGetProfiles() {
        List<Profile> profiles = profileService.getProfiles().getProfiles();
        Assertions.assertThat(profiles).isNotNull().isNotEmpty();
    }

    @Test
    public void testGetProfileById() {
        String id = "59914e16322c1b042d4fdf2b";
        List<Profile> profiles = profileService.getProfileById(id).getProfiles();
        Assertions.assertThat(profiles).isNotNull().isNotEmpty();
    }

    @Test
    public void testGetProfileByName() {
        String name = "Shepard";
        List<Profile> profiles = profileService.getProfilesByName(name).getProfiles();
        Assertions.assertThat(profiles).isNotNull().isNotEmpty();
    }

}

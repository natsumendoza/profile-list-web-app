package com.roxel.profilelistwebapp;

import com.roxel.profilelistwebapp.api.ProfileController;
import com.roxel.profilelistwebapp.service.ProfileService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProfileControllerTest {

    MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext webApplicationContext;

    @Autowired
    ProfileController profileController;

    @Before
    public void setup() throws Exception {
        this.mockMvc = standaloneSetup(this.profileController).build();
    }

    @Test
    public void testGetProfiles() throws Exception {
       mockMvc.perform(get("/api/profiles")
               .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*.*.id", Matchers.hasItem(Matchers.is("59914e16322c1b042d4fdf2b"))));

    }

    @Test
    public void testGetProfileById() throws Exception {
        String id = "59914e16322c1b042d4fdf2b";
        mockMvc.perform(get("/api/profiles/" + id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*.*.email", Matchers.hasItem(Matchers.is("shepard.potts@stratpoint.us"))));

    }

    @Test
    public void testGetProfilesByName() throws Exception {
        String name = "Shepard";
        mockMvc.perform(get("/api/profiles/search/" + name)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*.*.email", Matchers.hasItem(Matchers.is("shepard.potts@stratpoint.us"))));

    }
}

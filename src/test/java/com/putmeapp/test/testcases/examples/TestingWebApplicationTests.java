package com.putmeapp.test.testcases.examples;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.putmeapp.restful.user.UserDTO;
import com.putmeapp.test.seeds.UserSeeds;
import com.putmeapp.test.utils.UtilsForTesting;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class TestingWebApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserSeeds userSeeds;

    @Autowired
    private UtilsForTesting testUtils;

    @Test
    public void contextLoads() {
    }

    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/api/v1/hello")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello, World")));
    }

    @Test
    public void shouldGetAllUsers() throws Exception {
        String endPoint = "/api/v1/users";
        userSeeds.createOneUser();

        this.mockMvc.perform(get(endPoint)).andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        JSONObject user1 = testUtils.getFirstJSONObjectFromJSONArray(endPoint);
        String name = user1.getString("firstName");
        assertEquals("Mario", name);
    }

    @Test
    public void shouldCreateOneUser() throws Exception {
        userSeeds.createOneUser();

        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("prueba@1.com");
        userDTO.setFirstName("prueba@1.com");
        userDTO.setLastName("prueba@1.com");
        userDTO.setPassword("prueba@1.com");
        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/api/v1/users").content(asJsonString(userDTO))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").exists());

        // MvcResult result = this.mockMvc
        // .perform(MockMvcRequestBuilders.post("/api/v1/users").content(asJsonString(userDTO))
        // .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
        // .andDo(print()).andReturn();

        // String content = result.getResponse().getContentAsString();
        // JSONObject data2 = new JSONObject(content);
        // String name = data2.getString("firstName");
        // assertEquals("prueba@1.com", name);
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

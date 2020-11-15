package com.putmeapp.test.testcases.user;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.putmeapp.restful.user.User;
import com.putmeapp.restful.user.UserDTO;
import com.putmeapp.restful.user.UserMapper;
import com.putmeapp.test.seeds.UserSeeds;
import com.putmeapp.test.utils.UtilsForTesting;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UtilsForTesting testUtils;

    @Autowired
    private UserSeeds userSeeds;

    @Autowired
    private UserMapper userMapper;

    @BeforeAll
    public void initAll() {
        userSeeds.createFourUsers();
    }

    @Test
    @DisplayName("Hello World case")
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/api/v1/hello")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello, World")));
    }

    @Test
    @DisplayName("Get all users")
    public void shouldGetAllUsers() throws Exception {
        String endPoint = "/api/v1/users";
        JSONArray users = testUtils.getJSONArray(endPoint);
        assertEquals(4, users.length());
        JSONObject user1 = testUtils.getFirstJSONObjectFromJSONArray(endPoint);
        String name = user1.getString("firstName");
        assertEquals("Mario", name);
    }

    @Test
    @DisplayName("Get one users")
    public void shouldGetOneUser() throws Exception {
        String endPoint = "/api/v1/users/1";
        JSONObject user1 = testUtils.getJSONObject(endPoint);
        String name = user1.getString("firstName");
        assertEquals("Mario", name);
    }

    @Test
    @DisplayName("Create an user")
    public void shouldCreateOneUser() throws Exception {
        UserDTO userDTO = new UserDTO();
        String email = "prueba@1.com";
        userDTO.setEmail(email);
        userDTO.setFirstName(email);
        userDTO.setLastName(email);
        userDTO.setPassword(email);
        MvcResult result = this.mockMvc
                .perform(MockMvcRequestBuilders.post("/api/v1/users").content(testUtils.asJsonString(userDTO))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andReturn();
        String content = result.getResponse().getContentAsString();
        JSONObject newUser = new JSONObject(content);
        String name = newUser.getString("firstName");
        assertEquals(email, name);
        userSeeds.deleteUserByEmail(email);
    }

    @Test
    @DisplayName("Update an user")
    public void shouldUpdateOneUser() throws Exception {
        String email = "prueba@1.com";
        User user = userSeeds.createUserWithParams(email, email, email, email);
        UserDTO userDTO = userMapper.userToUserDTO(user);
        userDTO.setFirstName("Pablo");
        String endPoint = "/api/v1/users/" + userDTO.getId();
        MvcResult result = this.mockMvc
                .perform(MockMvcRequestBuilders.put(endPoint).content(testUtils.asJsonString(userDTO))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andReturn();
        String content = result.getResponse().getContentAsString();
        JSONObject updateUser = new JSONObject(content);
        String name = updateUser.getString("firstName");
        assertEquals("Pablo", name);
        userSeeds.deleteUserByEmail(email);
    }

}

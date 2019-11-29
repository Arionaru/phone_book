package ru.ariona.demo_phone_book;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.ariona.demo_phone_book.domen.Node;
import ru.ariona.demo_phone_book.domen.User;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    public void getAllTest() throws Exception {
        this.mockMvc.perform(get("/api/user")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getOne() throws Exception {
        this.mockMvc.perform(get("/api/user/100").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", Matchers.is("Иван Иванов")));
    }

    @Test
    public void save() throws Exception {
        this.mockMvc.perform(post("/api/user")
                .content(asJsonString(new User("Test User")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void edit() throws Exception {
        this.mockMvc.perform(put("/api/user/{id}",100)
                .content(asJsonString(new User("Test User")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Test User"));
    }

    @Test
    public void deleteTest() throws Exception {
        this.mockMvc.perform(delete("/api/user/{id}", 103))
                .andExpect(status().isOk());
    }

    @Test
    public void getNodes() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/user/nodes/{id}",101)
        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        List<Node> actual = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<Node>>() {});

        for (Node node : actual) {
            assertEquals(101,node.getUser().getId());
        }

    }

    @Test
    public void getByName() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/api/user").param("name","user"))
                .andExpect(status().isOk())
                .andReturn();

        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        List<User> actual = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<User>>() {});

        for (User user : actual) {
            assertTrue(user.getName().toLowerCase().contains("user".toLowerCase()));
        }

    }
}

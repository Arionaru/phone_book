package ru.ariona.demo_phone_book;

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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.ariona.demo_phone_book.domen.Node;
import ru.ariona.demo_phone_book.domen.User;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class NodeControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    public void getOne() throws Exception {
        this.mockMvc.perform(get("/api/node/1001").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.number", Matchers.is("54677677")));
    }

    @Test
    public void save() throws Exception {
        this.mockMvc.perform(post("/api/node")
                .content(asJsonString(new Node(new User("Test"),"papa","5555555")))
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
        this.mockMvc.perform(put("/api/node/{id}",1000)
                .content(asJsonString(new Node(new User("test"),"test","test")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("test"));
    }

    @Test
    public void deleteTest() throws Exception {
        this.mockMvc.perform(delete("/api/node/{id}", 1001))
                .andExpect(status().isOk());
    }

    @Test
    public void findByNumber() throws Exception {
        this.mockMvc.perform(get("/api/node").param("number","112"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.number", Matchers.is("112")));
    }
}

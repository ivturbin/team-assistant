package com.example.greeting;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class GreetingApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void helloReturnsGreeting() throws Exception {
        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("Привет"));
    }

    @Test
    void helloReturnsGreetingWithName() throws Exception {
        mockMvc.perform(get("/hello").param("name", "Иван"))
                .andExpect(status().isOk())
                .andExpect(content().string("Привет, Иван!"));
    }

    @Test
    void helloShouldReturn406WhenAcceptIsHtml() throws Exception {
        mockMvc.perform(get("/hello").accept(MediaType.TEXT_HTML))
                .andExpect(status().isNotAcceptable());
    }

    @Test
    void helloShouldReturn406WhenAcceptIsJson() throws Exception {
        mockMvc.perform(get("/hello").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotAcceptable());
    }

    @Test
    void helloShouldReturnTextPlainWhenAcceptIsTextPlain() throws Exception {
        mockMvc.perform(get("/hello").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
                .andExpect(content().string("Привет"));
    }

    @Test
    void helloShouldReturnTextPlainWithPlainTextName() throws Exception {
        mockMvc.perform(get("/hello").param("name", "<script>alert(1)</script>").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
                .andExpect(content().string("Привет, <script>alert(1)</script>!"));
    }
}

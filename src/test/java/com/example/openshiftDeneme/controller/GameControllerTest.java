/*package com.example.openshiftDeneme.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.hasSize;

import com.example.openshiftDeneme.OpenshiftDenemeApplication;
import com.example.openshiftDeneme.service.GameService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { OpenshiftDenemeApplication.class })
@WebAppConfiguration
public class GameControllerTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private GameService service;

    private MockMvc mvc;

    @Before
    public void setup() throws Exception {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void createGameTest() throws Exception {
        MvcResult result = mvc.perform(post("/game/create")).andExpect(status().isOk())
        .andReturn();

        MockHttpServletResponse response = result.getResponse();
        int responseLength = response.getContentLength();
        assertThat(responseLength).isGreaterThan(0);
    }

    @Test
    public void getGameTest() throws Exception {
        String id = service.addGame();
        mvc.perform(get("/game/" + id)).andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(id));
    }

    @Test
    public void getAllGameTest() throws Exception {
        service.deleteAllGame();
        String[] gameIdList = {service.addGame(), service.addGame(),
        service.addGame()};
        mvc.perform(get("/game/allgame")).andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(3)))
        .andExpect(jsonPath("$[0]id").value(gameIdList[0]))
        .andExpect(jsonPath("$[1]id").value(gameIdList[1]))
        .andExpect(jsonPath("$[2]id").value(gameIdList[2]));
    }

    @Test
    public void deleteGameTest() throws Exception {
        String id = service.addGame();
        mvc.perform(get("/game/" + id)).andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(id));
        mvc.perform(delete("/game/delete/"+id)).andExpect(status().isOk());
        MvcResult result = mvc.perform(get("/game/"+id)).andExpect(status().isOk())
        .andReturn();

        int responseLength = result.getResponse()
        .getContentLength();
        assertThat(responseLength).isZero();
    }

    @Test
    public void deleteAllGameTest() throws Exception{
        service.addGame();
        service.addGame();
        service.addGame();
        mvc.perform(delete("/game/delete/all")).andExpect(status().isOk());
        MvcResult result =mvc.perform(get("/game/allgame")).andExpect(status().isOk())
        .andReturn();
        
        int responseLength = result.getResponse().getContentLength();
        assertThat(responseLength).isZero();
    }

    @Test
    public void invalidJsonPlayTest() throws Exception{
        String id = service.addGame();
        
        MockHttpServletRequestBuilder builder = 
         MockMvcRequestBuilders.put("/game/play/"+id)
         .contentType(MediaType.APPLICATION_JSON_VALUE)
         .accept(MediaType.APPLICATION_JSON)
         .characterEncoding("UTF-8")
         .content("{\"x\":\"string\", \"y\":\"string\"}");
            mvc.perform(builder).andExpect(status().isBadRequest());
    }

    @Test
    public void playTest() throws Exception{
        String[] expectedValues = {"Oyun Bitti, Kaybettiniz :(",
        "Mayin Yok, Devam Edin !"};
        String id = service.addGame();

        MockHttpServletRequestBuilder builder = 
         MockMvcRequestBuilders.put("/game/play/"+id)
         .contentType(MediaType.APPLICATION_JSON_VALUE)
         .accept(MediaType.APPLICATION_JSON)
         .characterEncoding("UTF-8")
         .content("{\"x\":5, \"y\":3}");

        MvcResult result = mvc.perform(builder).andExpect(status().isOk())
        .andReturn();

        String responseContent = result.getResponse()
        .getContentAsString();
        assertThat(expectedValues).contains(responseContent);
    }
}*/
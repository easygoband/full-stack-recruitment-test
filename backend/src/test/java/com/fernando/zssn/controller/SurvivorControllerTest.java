package com.fernando.zssn.controller;

import static org.hamcrest.Matchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fernando.zssn.controller.SurvivorsController;
import com.fernando.zssn.helpers.JsonPrettier;
import com.fernando.zssn.persistence.entity.Survivor;
import com.fernando.zssn.presentation.JsonFormatHandler;
import com.fernando.zssn.presentation.ViewModel;
import com.fernando.zssn.service.SurvivorService;
import com.fernando.zssn.service.dto.SurvivorRequestDto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import springfox.documentation.spring.web.json.Json;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = SurvivorsController.class)
public class SurvivorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SurvivorService survivorService;

    @Autowired
    ObjectMapper mapper;

    @Test
    public void createNewSurvivor() throws Exception {
        Survivor survivor = new Survivor(1L,"Fernando","Ordaz", 24, (float) -32.1212, (float) 43.214123);
        ViewModel viewModel = new ViewModel(survivor,HttpStatus.CREATED);
        JsonFormatHandler output = new JsonFormatHandler(survivor, HttpStatus.CREATED.value());
        Mockito.when(survivorService.createSurvivor(Mockito.any(SurvivorRequestDto.class))).thenReturn(viewModel);

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/survivors")
                .contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8").content(this.mapper.writeValueAsBytes(survivor));

        MvcResult result = mockMvc.perform(builder).andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.id", is(1)))
                .andExpect(jsonPath("$.code", is(HttpStatus.CREATED.value())))
                .andExpect(MockMvcResultMatchers.content().string(this.mapper.writeValueAsString(output))).andReturn();

        String content = result.getResponse().getContentAsString();

        System.out.println(JsonPrettier.make(content));
    }
}

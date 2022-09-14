package com.fernando.zssn.controller;

import static org.hamcrest.Matchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fernando.zssn.helpers.JsonPrettier;
import com.fernando.zssn.persistence.entity.Survivor;
import com.fernando.zssn.presentation.JsonFormatHandler;
import com.fernando.zssn.presentation.ViewModel;
import com.fernando.zssn.service.SurvivorService;
import com.fernando.zssn.service.dto.LocationRequestDto;
import com.fernando.zssn.service.dto.SurvivorRequestDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

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
    public void post_createNewSurvivor() throws Exception {
        Survivor survivor = new Survivor(1L,"Fernando","Ordaz", 24, (float) -32.1212, (float) 43.214123, 0, null, 0, false);
        ViewModel viewModel = new ViewModel(survivor,HttpStatus.CREATED,"",null);
        JsonFormatHandler output = new JsonFormatHandler(survivor, HttpStatus.CREATED.value(),"",null);
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

    @Test
    public void put_updateSurvivorLocation() throws Exception {
        ViewModel viewModel = new ViewModel(null,HttpStatus.OK,"Survivor location with id (1) updated",null);
        LocationRequestDto locationRequest = new LocationRequestDto(-23.23423F, 4.23423F);
        JsonFormatHandler output = new JsonFormatHandler(null, viewModel.getHttpStatus().value(),viewModel.getMessage(),null);

        Mockito.when(survivorService.updateSurvivorLocation(1L,locationRequest)).thenReturn(viewModel);

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.put("/survivors/1/location")
                .contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8").content(this.mapper.writeValueAsBytes(locationRequest));

        MvcResult result = mockMvc.perform(builder).andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is(viewModel.getMessage())))
                .andExpect(jsonPath("$.code", is(HttpStatus.OK.value())))
                .andExpect(MockMvcResultMatchers.content().string(this.mapper.writeValueAsString(output))).andReturn();

        String content = result.getResponse().getContentAsString();

        System.out.println(JsonPrettier.make(content));
    }

    @Test
    public void put_flagSurvivorInfected() throws Exception {
        ViewModel viewModel = new ViewModel(null,HttpStatus.OK,"Survivor with id (1) was reported as infected",null);

        JsonFormatHandler output = new JsonFormatHandler(null, viewModel.getHttpStatus().value(),viewModel.getMessage(),null);

        Mockito.when(survivorService.updateInfectedReportsBySurvivor(1L)).thenReturn(viewModel);

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.put("/survivors/1/infected")
                .contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8");

        MvcResult result = mockMvc.perform(builder).andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is(viewModel.getMessage())))
                .andExpect(jsonPath("$.code", is(HttpStatus.OK.value())))
                .andExpect(MockMvcResultMatchers.content().string(this.mapper.writeValueAsString(output))).andReturn();

        String content = result.getResponse().getContentAsString();

        System.out.println(JsonPrettier.make(content));
    }
}

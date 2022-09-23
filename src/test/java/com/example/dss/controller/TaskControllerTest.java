package com.example.dss.controller;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.example.dss.dto.BaseResponseDTO;
import com.example.dss.dto.TaskDTO;
import com.example.dss.service.TaskService;
import com.example.dss.util.Constants;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.MvcResult;

@WebMvcTest
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService service;

    private static ObjectMapper mapper = new ObjectMapper();


    @Test
    public void should_get_all_tasks() throws Exception {
        //Given
        TaskDTO taskOne = TaskDTO.builder().name("Cricket").description("Cricket").status(true).id(UUID.randomUUID()).build();
        List<TaskDTO> taskDTOS = new ArrayList<>();
        taskDTOS.add(taskOne);
        BaseResponseDTO responseDTO = BaseResponseDTO.builder().data(taskDTOS).build();
        //When
        Mockito.when(service.processGetAllTasks()).thenReturn(responseDTO);
        //Perform
        mockMvc.perform(get("/api/v1/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", Matchers.hasSize(1)))
                .andExpect(jsonPath("$.data[0].name", Matchers.equalTo("Cricket")));
    }

    @Test
    public void should_get_task_by_id() throws Exception {
        //Given
        UUID uuid = UUID.randomUUID();
        TaskDTO taskOne = TaskDTO.builder().name("Cricket").description("Cricket").status(true).id(uuid).build();
        List<TaskDTO> taskDTOS = new ArrayList<>();
        taskDTOS.add(taskOne);
        BaseResponseDTO responseDTO = BaseResponseDTO.builder().data(taskDTOS).build();
        //When
        Mockito.when(service.processGetTaskById(uuid)).thenReturn(responseDTO);
        //Perform
        mockMvc.perform(get("/api/v1/task/" + uuid))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", Matchers.hasSize(1)))
                .andExpect(jsonPath("$.data[0].name", Matchers.equalTo("Cricket")));
    }

    @Test
    public void should_create_task() throws Exception {
        //Given
        UUID uuid = UUID.randomUUID();
        TaskDTO taskOne = TaskDTO.builder().name("Cricket").description("Cricket").status(true).id(uuid).build();
        BaseResponseDTO responseDTO = BaseResponseDTO.builder().code(HttpStatus.CREATED.value()).status(Constants.SUCCESS).desc(Constants.CREATE_MESSAGE).build();
        //When
        Mockito.when(service.processCreateTask(taskOne)).thenReturn(responseDTO);
        String json = mapper.writeValueAsString(taskOne);
        //Perform
        mockMvc.perform(post("/api/v1/task").contentType(MediaType.APPLICATION_JSON)
                        .content(json).accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.code", Matchers.equalTo(HttpStatus.CREATED.value())))
                        .andExpect(jsonPath("$.status", Matchers.equalTo(Constants.SUCCESS)))
                        .andExpect(jsonPath("$.desc", Matchers.equalTo(Constants.CREATE_MESSAGE)));
    }

    @Test
    public void should_update_task() throws Exception {
       //Given
        UUID uuid = UUID.randomUUID();
        TaskDTO taskOne = TaskDTO.builder().name("Cricket").description("Cricket").status(true).id(uuid).build();
        BaseResponseDTO responseDTO = BaseResponseDTO.builder()
                                                        .code(HttpStatus.OK.value()).status(Constants.SUCCESS).desc(Constants.UPDATE_MESSAGE)
                                                    .build();
        //When
        Mockito.when(service.processUpdateTask(uuid,taskOne)).thenReturn(responseDTO);
        String json = mapper.writeValueAsString(taskOne);
        //Perform
        mockMvc.perform(put("/api/v1/task/" + uuid ).contentType(MediaType.APPLICATION_JSON)
                        .content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", Matchers.equalTo(HttpStatus.OK.value())))
                .andExpect(jsonPath("$.status", Matchers.equalTo(Constants.SUCCESS)))
                .andExpect(jsonPath("$.desc", Matchers.equalTo(Constants.UPDATE_MESSAGE)));
    }

    @Test
    public void should_delete_task_id() throws Exception {
        //Given
        UUID uuid = UUID.randomUUID();
        BaseResponseDTO responseDTO = BaseResponseDTO.builder()
                                                        .code(HttpStatus.OK.value()).status(Constants.SUCCESS).desc(Constants.DELETE_MESSAGE)
                                                    .build();
        //When
        Mockito.when(service.processDeleteTask(uuid)).thenReturn(responseDTO);
        //Perform
        mockMvc.perform(delete("/api/v1/task/" + uuid))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", Matchers.equalTo(HttpStatus.OK.value())))
                .andExpect(jsonPath("$.status", Matchers.equalTo(Constants.SUCCESS)))
                .andExpect(jsonPath("$.desc", Matchers.equalTo(Constants.DELETE_MESSAGE)));
    }

}

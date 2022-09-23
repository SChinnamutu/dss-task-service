package com.example.dss.web;


import com.example.dss.dto.BaseResponseDTO;
import com.example.dss.dto.TaskDTO;
import com.example.dss.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1")
public class TaskRestController {

    @Autowired private TaskService service;

    @GetMapping(value = "/tasks")
    public ResponseEntity<BaseResponseDTO> processGetAllTasks()  {
        return ResponseEntity.ok(service.processGetAllTasks());
    }

    @GetMapping(value = "/task/{id}")
    public ResponseEntity<BaseResponseDTO> processGetTaskById(@PathVariable UUID id)  {
        return ResponseEntity.ok(service.processGetTaskById(id));
    }

    @PostMapping(value = "/task")
    public ResponseEntity<BaseResponseDTO> processCreateTask(@RequestBody TaskDTO req)  {
        return ResponseEntity.ok(service.processCreateTask(req));
    }

    @PutMapping(value = "/task/{id}")
    public ResponseEntity<BaseResponseDTO> processUpdateTask(@PathVariable UUID id, @RequestBody TaskDTO req)  {
        return ResponseEntity.ok(service.processUpdateTask(id,req));
    }

    @DeleteMapping(value = "/task/{id}")
    public ResponseEntity<BaseResponseDTO> processDeleteTask(@PathVariable UUID id) {
        return ResponseEntity.ok(service.processDeleteTask(id));
    }

}

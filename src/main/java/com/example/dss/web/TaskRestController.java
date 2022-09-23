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

    /**
     *  This method is responsible for returning all task from DB.
     *
     * @return BaseResponseDTO
     *              baseResponseDTO
     *
     */
    @GetMapping(value = "/tasks")
    public ResponseEntity<BaseResponseDTO> processGetAllTasks()  {
        return ResponseEntity.ok(service.processGetAllTasks());
    }


    /**
     *  This method is responsible for returning  task by uui from DB.
     *
     * @param id
     *
     * @return BaseResponseDTO
     *              baseResponseDTO
     *
     */
    @GetMapping(value = "/task/{id}")
    public ResponseEntity<BaseResponseDTO> processGetTaskById(@PathVariable UUID id)  {
        return ResponseEntity.ok(service.processGetTaskById(id));
    }


    /**
     *  This method is responsible for creating task record.
     *
     * @param  req
     *
     * @return BaseResponseDTO
     *              baseResponseDTO
     *
     */
    @PostMapping(value = "/task")
    public ResponseEntity<BaseResponseDTO> processCreateTask(@RequestBody TaskDTO req)  {
        return ResponseEntity.ok(service.processCreateTask(req));
    }


    /**
     *  This method is responsible for updating task record.
     *
     * @param id, req
     *
     * @return BaseResponseDTO
     *              baseResponseDTO
     *
     */
    @PutMapping(value = "/task/{id}")
    public ResponseEntity<BaseResponseDTO> processUpdateTask(@PathVariable UUID id, @RequestBody TaskDTO req)  {
        return ResponseEntity.ok(service.processUpdateTask(id,req));
    }


    /**
     *  This method is responsible for deleting task by id.
     *
     * @param  id
     *
     * @return BaseResponseDTO
     *              baseResponseDTO
     *
     */
    @DeleteMapping(value = "/task/{id}")
    public ResponseEntity<BaseResponseDTO> processDeleteTask(@PathVariable UUID id) {
        return ResponseEntity.ok(service.processDeleteTask(id));
    }

}

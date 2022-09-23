package com.example.dss.service;


import com.example.dss.dto.BaseResponseDTO;
import com.example.dss.dto.TaskDTO;
import com.example.dss.entity.TaskEntity;
import com.example.dss.exception.ResourceNotFoundException;
import com.example.dss.repo.TaskRepo;
import com.example.dss.util.Constants;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

/*
This class consists of service logic and its implementations.
 */
@Service
public class TaskService {

    @Autowired TaskRepo repo;

    @Autowired   ModelMapper modelMapper;

    /**
     *  This method is responsible for returning all task from DB.
     *
     * @return BaseResponseDTO
     *              baseResponseDTO
     *
     */
    public BaseResponseDTO processGetAllTasks()  {
        List<TaskEntity> entities = repo.findAll();
        return Optional.ofNullable(entities)
                        .map(this::map)
                        .orElseGet(this::emptyMap);
    }

    /**
     *  This method is responsible for returning  task by uui from DB.
     *
     * @param uuid
     *
     * @return BaseResponseDTO
     *              baseResponseDTO
     *
     */
    public BaseResponseDTO processGetTaskById(UUID uuid)   {
        TaskEntity entity = repo.findById(uuid).orElseThrow( () -> new ResourceNotFoundException(Constants.NO_DATA_FOUND_ID + uuid));
        return Optional.ofNullable(entity)
                        .map(this::map)
                        .orElseGet(this::emptyMap);
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
    public BaseResponseDTO processCreateTask(TaskDTO req) {
       //Checking duplicate same Id
       repo.findById(req.getId()).map(this::map).ifPresent(u -> {throw new ResourceNotFoundException(Constants.ALREAY_EXIST_MESSAGE + req.getId());});
       TaskEntity newEntity = TaskEntity.builder()
                                            .name(req.getName()).description(req.getDescription()).status(req.isStatus()).id(req.getId())
                                       .build();
        repo.save(newEntity);
        return this.buildResponse(HttpStatus.CREATED.value(),Constants.CREATE_MESSAGE);
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
    public BaseResponseDTO processUpdateTask(UUID id, TaskDTO req) throws ResourceNotFoundException {
        TaskEntity entity = repo.findById(id).orElseThrow(()-> new ResourceNotFoundException(Constants.NO_DATA_FOUND_ID + id));
        entity.setDescription(req.getDescription());
        entity.setName(req.getName());
        entity.setStatus(req.isStatus());
        repo.save(entity);
        return this.buildResponse(HttpStatus.OK.value(),Constants.UPDATE_MESSAGE);
    }

    /**
     *  This method is responsible for deleting task by id.
     *
     * @param  uuid
     *
     * @return BaseResponseDTO
     *              baseResponseDTO
     *
     */
    public BaseResponseDTO processDeleteTask(UUID uuid) throws ResourceNotFoundException {
       TaskEntity entity = repo.findById(uuid).orElseThrow( () ->  new ResourceNotFoundException(Constants.NO_DATA_FOUND_ID + uuid ));
       repo.delete(entity);
       return this.buildResponse(HttpStatus.OK.value(),Constants.DELETE_MESSAGE);
    }

    private BaseResponseDTO buildResponse(int code, String message) {
        return BaseResponseDTO.builder()
                                    .code(code)
                                    .status(Constants.SUCCESS)
                                    .desc(message)
                             .build();

    }

    public BaseResponseDTO map(Object data) {
        List<TaskDTO> list = new ArrayList<>();
        if(data instanceof TaskEntity){
            list.add(modelMapper.map(data,TaskDTO.class));
        }else if(data instanceof List){
            list = Arrays.asList(modelMapper.map(data, TaskDTO[].class));
        }
        return BaseResponseDTO.builder()
                                .code(HttpStatus.OK.value())
                                .desc(Constants.FETCH_MESSAGE)
                                .status(Constants.SUCCESS)
                                .data(list)
                          .build();
    }

    public BaseResponseDTO emptyMap() {
        return BaseResponseDTO.builder()
                .code(HttpStatus.OK.value())
                .desc(Constants.NO_DATA_FOUND)
                .status(Constants.SUCCESS)
                .build();
    }

}

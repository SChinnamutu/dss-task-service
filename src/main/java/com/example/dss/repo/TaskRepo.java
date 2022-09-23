package com.example.dss.repo;

import com.example.dss.entity.TaskEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface TaskRepo extends CrudRepository<TaskEntity, UUID> {

   @Override
   List<TaskEntity> findAll();

}

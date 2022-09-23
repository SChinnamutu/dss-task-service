package com.example.dss.service;


import com.example.dss.entity.TaskEntity;
import com.example.dss.repo.TaskRepo;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TaskServiceTest {
    @Mock
    private TaskRepo repo;

    @Test
    public void should_get_all_tasks(){
        //Given
        TaskEntity taskOne = TaskEntity.builder().name("Cricket").description("Cricket").status(true).id(UUID.randomUUID()).build();
        TaskEntity taskTwo = TaskEntity.builder().name("Football").description("Football").status(true).id(UUID.randomUUID()).build();
        List<TaskEntity> list = Arrays.asList(taskOne,taskTwo);
        //When
        when(repo.findAll()).thenReturn(list);
        //Perform
        List<TaskEntity> taskList = repo.findAll();
        //Then
        assertEquals(2, taskList.size());
    }

    @Test
    public void should_get_task_by_id(){
        //Given
        UUID uuid = UUID.randomUUID();
        TaskEntity task = TaskEntity.builder().name("Football").description("Football").status(true).id(uuid).build();
        //When
        when(repo.findById(uuid)).thenReturn(Optional.of(task));
        //Perform
        Optional<TaskEntity> optional = repo.findById(uuid);
        //Then
        assertNotNull(optional.get());
        assertEquals(uuid,optional.get().getId());
        assertEquals(task.getName(),optional.get().getName());
    }

    @Test
    public void should_create_task(){
        //Given
        UUID uuid = UUID.randomUUID();
        TaskEntity taskEntity = TaskEntity.builder().name("Football").description("Football").status(true).id(uuid).build();
        //When
        when(repo.save(taskEntity)).thenReturn(null);
        when(repo.findById(uuid)).thenReturn(Optional.of(taskEntity));
        //Perform
        repo.save(taskEntity);
        //Then
        assertEquals(uuid,repo.findById(uuid).get().getId());
        assertEquals(taskEntity.getName(),repo.findById(uuid).get().getName());
    }


    @Test
    public void should_update_task(){
        /*<------Create--------->*/
        //Given
        UUID uuid = UUID.randomUUID();
        TaskEntity taskEntity = TaskEntity.builder().name("Football").description("Football").status(true).id(uuid).build();
        //When
        when(repo.save(taskEntity)).thenReturn(null);
        when(repo.findById(uuid)).thenReturn(Optional.of(taskEntity));
        //Then
        assertEquals("Football",repo.findById(uuid).get().getName());
        assertEquals("Football",repo.findById(uuid).get().getDescription());
        /*<------Update--------->*/
        //Given
        taskEntity.setName("Cricket");
        taskEntity.setDescription("Cricket");
        taskEntity.setStatus(false);
        //When
        when(repo.findById(uuid)).thenReturn(Optional.of(taskEntity));
        //Perform
        repo.save(taskEntity);
        //Then
        assertEquals("Cricket",repo.findById(uuid).get().getName());
        assertEquals("Cricket",repo.findById(uuid).get().getDescription());
    }

    @Test
    public void should_delete_task_id(){
        /*<---------------Create----------------->*/
        //Given
        UUID uuid1 = UUID.randomUUID();
        TaskEntity taskEntityOne = TaskEntity.builder().name("Football").description("Football").status(true).id(uuid1).build();
        UUID uuid2 = UUID.randomUUID();
        TaskEntity taskEntityTwo = TaskEntity.builder().name("Cricket").description("Cricket").status(true).id(uuid2).build();
        UUID uuid3 = UUID.randomUUID();
        TaskEntity taskEntityThree = TaskEntity.builder().name("Tennis").description("Cricket").status(true).id(uuid3).build();
        List<TaskEntity> entities = Arrays.asList(taskEntityOne,taskEntityTwo,taskEntityThree);
        //When
        when(repo.saveAll(entities)).thenReturn(null);
        when(repo.findAll()).thenReturn(entities);
        //Perform
        repo.saveAll(entities);
        //Then
        assertEquals(3,repo.findAll().size());
        /*<---------------Delete----------------->*/
        //When
        when(repo.findAll()).thenReturn(Arrays.asList(taskEntityOne,taskEntityTwo));
        //Perform
        repo.delete(taskEntityThree);
        //Then
        assertEquals(2,repo.findAll().size());
    }


}

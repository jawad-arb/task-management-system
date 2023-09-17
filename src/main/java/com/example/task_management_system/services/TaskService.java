package com.example.task_management_system.services;

import com.example.task_management_system.dto.CountType;
import com.example.task_management_system.models.Task;
import com.example.task_management_system.repositories.TaskRepo;
import jakarta.annotation.PreDestroy;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskService {
    private TaskRepo taskRepo;

    @Transactional(readOnly = true)
    public List<Task> getTasks(){
        // get the tasks sorted with Date desc
        return taskRepo.getAllTaskDueDateDesc();
    }
    @Transactional
    public Task save(Task task){
        return taskRepo.saveAndFlush(task);
    }

    @Transactional(readOnly = true)
    public boolean existById(Long id){
        // task exist or not
        return taskRepo.existsById(id);
    }

    @Transactional(readOnly = true)
    public Optional<Task> getTaskById(Long id){
        return taskRepo.findById(id);
    }

    public void delete(Long id){
        taskRepo.deleteById(id);
    }

    public List<CountType> getPercentageGroupByType(){
        return taskRepo.getPercentageGroupByType();
    }


}

package com.example.task_management_system.controllers;

import com.example.task_management_system.dto.CountType;
import com.example.task_management_system.models.Task;
import com.example.task_management_system.services.TaskService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/tasks/")
public class TaskController {
    private TaskService taskService;

    @GetMapping("all")
    public List<Task> getTasks(){
        return taskService.getTasks();
    }
    @GetMapping("{taskId}")
    public Optional<Task> getTasks(@PathVariable Long taskId){
        return taskService.getTaskById(taskId);
    }

    @GetMapping("getpourcentage")
    public List<CountType> getPourcentageGroupByType(){
        return taskService.getPercentageGroupByType();
    }

    @PostMapping("addtask")
    public Task saveTask(@RequestBody Task task){
        return taskService.save(task);
    }

    @PutMapping("update/{taskId}")
    public ResponseEntity<?> updateTask(@RequestBody Task taskParam , @PathVariable Long taskId){
        if(taskService.existById(taskId)){
            Task task=taskService.getTaskById(taskId)
                    .orElseThrow(()->new EntityNotFoundException("task not found"));
            task.setTitle(taskParam.getTitle());
            task.setDescription(taskParam.getDescription());
            task.setType(taskParam.getType());
            task.setDueDate(taskParam.getDueDate());
            taskService.save(task);
            return ResponseEntity.ok().body(task);
        }else{
            HashMap<String , String >message =new HashMap<>();
            message.put("message",taskId +"Not Found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }

    @DeleteMapping("delete/{taskId}")
    public ResponseEntity<?> deleetTask(@PathVariable Long taskId){
        if(taskService.existById(taskId)){
            taskService.delete(taskId);
            HashMap<String , String >message =new HashMap<>();
            message.put("message","id = "+taskId +" is deleted");
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }else{
            HashMap<String , String >message =new HashMap<>();
            message.put("message","id = "+taskId +" Not Found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }









}

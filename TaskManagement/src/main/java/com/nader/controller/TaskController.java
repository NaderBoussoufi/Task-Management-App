package com.nader.controller;

import com.nader.dto.CountType;
import com.nader.model.Task;
import com.nader.services.TaskService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
@AllArgsConstructor
public class TaskController {
    private TaskService taskService;

    @GetMapping("/task/vData/percentcounttype")
    public List<CountType> getPercentageGroupByType(){
        return taskService.getPercentageGroupByType();
    }
    @GetMapping("/task")
    public List<Task> getTask(){
        return taskService.getTasks();
    }

    @PostMapping("/task")
    public Task addTask(@RequestBody Task task){
        return taskService.save(task);
    }

    @GetMapping("/task/{id}")
    public Task getById(@PathVariable Long id){
        return taskService.getTaskByID(id).orElseThrow(()->new EntityNotFoundException("Request Task not Found"));
    }

    @PutMapping("/task/{id}")
    public ResponseEntity<?> updateTask(@RequestBody Task taskpara, @PathVariable Long id){
        if (taskService.existById(id)){
            Task task = taskService.getTaskByID(id).orElseThrow(()->new EntityNotFoundException("Request Task not Found"));
            task.setTitle(taskpara.getTitle());
            task.setDueDate(taskpara.getDueDate());
            task.setDescription(taskpara.getDescription());
            task.setType(taskpara.getType());
            taskService.save(task);
            return ResponseEntity.ok().body(task);

        } else {
            HashMap<String,String> message = new HashMap<>();
            message.put("message", id + "task not found or matched");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }

    @DeleteMapping("task/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id){
        if (taskService.existById(id)){
            taskService.delete(id);
            HashMap<String,String> message = new HashMap<>();
            message.put("message", id + " task removed");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);


        } else {
            HashMap<String,String> message = new HashMap<>();
            message.put("message", id + "task not found or matched");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }
}

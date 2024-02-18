package com.maleman.taskm.controller;

import com.maleman.taskm.Services.TaskService;
import com.maleman.taskm.dto.CountType;
import com.maleman.taskm.model.Task;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private TaskService taskService;

    @GetMapping("/task/vData/percentcounttype")
    public List<CountType> getPercentageGroupByType(){
        return taskService.getPercentageGroupByType();
    }

    @GetMapping("/task")
    public List<Task> getTasks(){
        return taskService.getTasks();
    }


    @PostMapping("/task")
    public Task addTask(@RequestBody Task task){
        return taskService.save(task);
    }

    @GetMapping("/task/{id}")
    public Task getById( @PathVariable Long id ){
        return taskService.getTaskById(id).orElseThrow( () -> new EntityNotFoundException("Requsted Task not found"));
    }

    @PutMapping("/task/{id}")
    public ResponseEntity<?> addTask(@RequestBody Task taskPara, @PathVariable Long id){
        if(taskService.existById(id)){
            Task task = taskService.getTaskById(id).orElseThrow( () -> new EntityNotFoundException("Requsted Task not found"));
            task.setTitle(taskPara.getTitle());
            task.setDueDate(taskPara.getDueDate());
            task.setType(taskPara.getType());
            task.setDescription(taskPara.getDescription());

            taskService.save(task);
            return ResponseEntity.ok().body(task);
        }

        HashMap<String, String> message = new HashMap<>();
        message.put("message", id + " task not found or matches");

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    @DeleteMapping("/task/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id){
        if(taskService.existById(id)){
            taskService.deleteTask(id);

            HashMap<String, String> message = new HashMap<>();
            message.put("message", id + " task removed");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }

        HashMap<String, String> message = new HashMap<>();
        message.put("message", id + " task not found or matches");

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }
}

package com.maleman.taskm.Services;

import com.maleman.taskm.dto.CountType;
import com.maleman.taskm.model.Task;
import com.maleman.taskm.repositories.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Transactional(readOnly = true)
    public List<Task> getTasks(){
        return taskRepository.getAllTaskDueDateDesc();
    }

    @Transactional
    public Task save( Task task ){
        return taskRepository.saveAndFlush(task);
    }

    @Transactional(readOnly = true)
    public boolean existById(Long id){
        return taskRepository.existsById(id);
    }

    @Transactional(readOnly = true)
    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public void deleteTask(Long id){
        taskRepository.deleteById(id);
    }

    public List<CountType> getPercentageGroupByType(){
        return taskRepository.getPercentageGroupByType();
    }
}

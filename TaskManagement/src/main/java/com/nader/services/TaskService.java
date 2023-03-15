package com.nader.services;

import com.nader.dto.CountType;
import com.nader.model.Task;
import com.nader.repository.TaskRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskService {
    private TaskRepository taskRepository;
    @Transactional()
    public List<Task> getTasks(){
        return taskRepository.getAllTaskDueDateDesc();
    }

    public Task save(Task task) {
        return taskRepository.saveAndFlush(task);
    }

    public boolean existById(Long id){
        return taskRepository.existsById(id);
    }

    public Optional<Task> getTaskByID(Long id) {
        return taskRepository.findById(id);
    }

    public void delete(Long id) {
        taskRepository.deleteById(id);
    }

    public List<CountType> getPercentageGroupByType(){
        return taskRepository.getPercentageGroupByType();
    }
}


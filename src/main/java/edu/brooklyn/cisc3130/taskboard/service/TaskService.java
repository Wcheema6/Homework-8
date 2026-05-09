package edu.brooklyn.cisc3130.taskboard.service;

import edu.brooklyn.cisc3130.taskboard.dto.TaskRequest;
import edu.brooklyn.cisc3130.taskboard.exception.TaskNotFoundException;
import edu.brooklyn.cisc3130.taskboard.model.Task;
import edu.brooklyn.cisc3130.taskboard.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
    return taskRepository.findByDeletedFalse();
}
    public Page<Task> getAllTasks(Pageable pageable) {
        return taskRepository.findAll(pageable);
    }

    public Task getTaskById(Integer id) {
    return taskRepository.findById(id)
            .orElseThrow(() -> new TaskNotFoundException(id));
}

   public Task createTask(TaskRequest request) {
    Task task = new Task();

    task.setTitle(request.getTitle());
    task.setDescription(request.getDescription());
    task.setCompleted(request.getCompleted() != null ? request.getCompleted() : false);

    if (request.getPriority() == null || request.getPriority().isEmpty()) {
        task.setPriority(Task.Priority.MEDIUM);
    } else {
        task.setPriority(Task.Priority.valueOf(request.getPriority().toUpperCase()));
    }

    return taskRepository.save(task);
}

    public Optional<Task> updateTask(Integer id, Task updatedTask) {
        return taskRepository.findById(id).map(task -> {
            task.setTitle(updatedTask.getTitle());
            task.setDescription(updatedTask.getDescription());
            task.setCompleted(updatedTask.getCompleted());
            task.setPriority(updatedTask.getPriority());
            return taskRepository.save(task);
        });
    }
    public Task updateTask(Integer id, TaskRequest request) {
        Task task = getTaskById(id);
    
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setCompleted(request.getCompleted() != null ? request.getCompleted() : false);
    
        if (request.getPriority() == null || request.getPriority().isEmpty()) {
            task.setPriority(Task.Priority.MEDIUM);
        } else {
            task.setPriority(Task.Priority.valueOf(request.getPriority().toUpperCase()));
        }
    
        return taskRepository.save(task);
    }
   public boolean deleteTask(Integer id) {
    Optional<Task> optionalTask = taskRepository.findById(id);

    if (optionalTask.isPresent()) {
        Task task = optionalTask.get();
        task.setDeleted(true);
        taskRepository.save(task);
        return true;
    }

    return false;
}
public void restoreTask(Integer id) {
    Task task = taskRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Task not found"));

    task.setDeleted(false);
    taskRepository.save(task);
}
    public List<Task> getCompletedTasks() {
        return taskRepository.findByCompletedTrue();
    }

    public List<Task> getIncompleteTasks() {
        return taskRepository.findByCompletedFalse();
    }

    public List<Task> getTasksByPriority(Task.Priority priority) {
        return taskRepository.findByPriority(priority);
    }

    public List<Task> searchTasks(String keyword) {
        return taskRepository.searchTasks(keyword);
    }

    public Page<Task> getCompletedTasks(Pageable pageable) {
        return taskRepository.findByCompletedTrue(pageable);
    }
}
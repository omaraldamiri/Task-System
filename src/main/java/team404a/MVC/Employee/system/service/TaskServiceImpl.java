package team404a.MVC.Employee.system.service;

import org.springframework.stereotype.Service;
import team404a.MVC.Employee.system.entities.Task;
import team404a.MVC.Employee.system.repository.TaskRepository;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task FindTaskById(long id) {
        if(taskRepository.findById(id).isEmpty()) {
            throw new RuntimeException("Task Not Found");
        }
        return taskRepository.findById(id).get();

    }

    @Override
    public List<Task> findAllAvailableTasks() {
        return taskRepository.availableTasks();
    }


    @Override
    public void saveTask(Task task) {
        taskRepository.save(task);
    }

    @Override
    public Task deleteTask(Task task) {
        taskRepository.delete(task);
        return task;
    }

    @Override
    public List<Task> findAllTasks() {
        return taskRepository.findByOrderByCreationDateDesc();
    }
}

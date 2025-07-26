package team404a.MVC.Employee.system.service;



import team404a.MVC.Employee.system.entities.Task;

import java.util.List;

public interface TaskService {

    Task FindTaskById(long id);
    List<Task> findAllAvailableTasks();
    void saveTask(Task task);
    Task deleteTask(Task task);
    List<Task> findAllTasks();


    
}

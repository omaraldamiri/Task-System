package team404a.MVC.Employee.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import team404a.MVC.Employee.system.entities.Task;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
    @Query("SELECT t FROM Task t WHERE t.done = false ORDER BY t.creationDate DESC")
    List<Task> availableTasks();
    List<Task> findByOrderByCreationDateDesc();
}

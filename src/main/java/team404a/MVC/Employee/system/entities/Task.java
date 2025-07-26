package team404a.MVC.Employee.system.entities;

import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name="tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String taskName;
    @Column(name = "description")
    private String taskDescription;
    @Column(name="Priority")
    private String taskPriority;
    @CreationTimestamp
    @Column(name="created_at",updatable = false)
    private LocalDateTime creationDate;
    @Column(name="done")
    private boolean done=true;
    public Task(){}

    public Task(String taskName, String taskDescription, String priority, Date creationDate) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskPriority = priority;
    }

    public long getId() {
        return id;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public String getTaskPriority() {
        return taskPriority;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public boolean isDone() {
        return done;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public void setTaskPriority(String taskPriority) {
        this.taskPriority = taskPriority;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}

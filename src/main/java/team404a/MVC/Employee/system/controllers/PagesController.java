package team404a.MVC.Employee.system.controllers;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import team404a.MVC.Employee.system.entities.Task;
import team404a.MVC.Employee.system.service.TaskService;
import team404a.MVC.Employee.system.service.UserService;

@Controller
public class PagesController {
    private TaskService taskService;
    public PagesController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/taskspage")
    public String returnMain(Model theModel){
        theModel.addAttribute("tasks",taskService.findAllAvailableTasks());
        return "tasks-page";
    }
    @GetMapping("/markasdone")
    public String markAsDone(@RequestParam("id") long id, RedirectAttributes redirectAttributes){
        Task task=taskService.FindTaskById(id);
        task.setDone(true);
        taskService.saveTask(task);
        redirectAttributes.addFlashAttribute("taskname",task.getTaskName());
        return "redirect:/taskspage";
    }

    @GetMapping("/access-denied")
    public String accessDeniedPage(){

        return "access-denied";
    }


}

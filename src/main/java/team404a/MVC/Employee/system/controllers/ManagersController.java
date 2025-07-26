package team404a.MVC.Employee.system.controllers;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import team404a.MVC.Employee.system.entities.Task;
import team404a.MVC.Employee.system.service.TaskService;

@RequestMapping("/managers")
@Controller
public class ManagersController {

    private TaskService taskService;

    public ManagersController(TaskService taskService) {
        this.taskService = taskService;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);}

    @GetMapping("/edittasks")
    public String editTasks(Model theModel){
        theModel.addAttribute("tasks",taskService.findAllTasks());
        return "manager-tasks-page";
    }
    @GetMapping("/showedit")
    public String showEdit(Model theModel, @RequestParam("taskid")long id){
        theModel.addAttribute("task",taskService.FindTaskById(id));
        return "edit-task";
    }

    @PostMapping("/editTask")
    public String processEditTask(@ModelAttribute("task")Task task,RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("edited",true);
        redirectAttributes.addFlashAttribute("editedtaskname",task.getTaskName());
        taskService.saveTask(task);
        return "redirect:/managers/edittasks";
    }

    @GetMapping("/deleteTask")
    public String deleteTask(@RequestParam("taskid") long id, RedirectAttributes redirectAttributes){
        Task task=taskService.FindTaskById(id);
        redirectAttributes.addFlashAttribute("taskname",task.getTaskName());
        taskService.deleteTask(task);
        redirectAttributes.addFlashAttribute("deleted",true);
        return "redirect:/managers/edittasks";
    }
   @GetMapping("/showadd")
    public String addTask(Model theModel){
       theModel.addAttribute("task",new Task());
       return "add-task";
   }
   @PostMapping("/processAddTask")
    public String processAddTask(@ModelAttribute("task") Task task,RedirectAttributes redirectAttributes){
        taskService.saveTask(task);
        redirectAttributes.addFlashAttribute("added",true);
       redirectAttributes.addFlashAttribute("addedtaskname",task.getTaskName());
       return "redirect:/managers/edittasks";
   }
}

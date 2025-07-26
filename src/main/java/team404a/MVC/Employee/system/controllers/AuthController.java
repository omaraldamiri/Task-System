package team404a.MVC.Employee.system.controllers;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import team404a.MVC.Employee.system.entities.Role;
import team404a.MVC.Employee.system.entities.Task;
import team404a.MVC.Employee.system.entities.User;
import team404a.MVC.Employee.system.service.RoleService;
import team404a.MVC.Employee.system.service.TaskService;
import team404a.MVC.Employee.system.service.UserService;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private UserService userService;
    private RoleService roleService;
    private TaskService taskService;
    private PasswordEncoder passwordEncoder;
    public AuthController(UserService userService,RoleService roleService,TaskService taskService,PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.taskService=taskService;
        this.passwordEncoder=passwordEncoder;
    }
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);}

    @GetMapping("/register")
    public String showRegister(Model theModel){
        theModel.addAttribute("user",new User());
        return "register-form";

     }
    @PostMapping("/processRegister")
    public String proccesForm(@Valid @ModelAttribute("user") User theUser, BindingResult bindingResult , Model theModel , HttpSession session ,
    RedirectAttributes redirectAttributes){
            if(bindingResult.hasErrors()) {
                return "register-form";
            }

            if(userService.usernameCheck(theUser.getUsername())){
                theUser.setPassword(passwordEncoder.encode(theUser.getPassword()));
                userService.saveUser(theUser);
                roleService.saveRole(new Role("ROLE_EMPLOYEE",theUser));
                redirectAttributes.addFlashAttribute("register","Registered Successfully");
                return "redirect:/auth/login";
            }

            theModel.addAttribute("valid"," Username Exists Please try another");
            return "register-form";
     }

    @GetMapping("/login")
    public String showForm(HttpServletRequest http){
        return "login-form";
     }





}

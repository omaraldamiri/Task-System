package team404a.MVC.Employee.system.controllers;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import team404a.MVC.Employee.system.entities.Role;
import team404a.MVC.Employee.system.entities.User;
import team404a.MVC.Employee.system.service.RoleService;
import team404a.MVC.Employee.system.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admins")
public class AdminsController {


    private UserService userService;
    private RoleService roleService;
    private PasswordEncoder passwordEncoder;

    public AdminsController(UserService userService, RoleService roleService,PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder=passwordEncoder;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);}

    @GetMapping("/users")
    public String showUsers(Model theModel) {
        theModel.addAttribute("users", userService.findAllUsers());
        return "users-page";
    }

    @GetMapping("/showAddUser")
    public String addUser(Model theModel) {
        theModel.addAttribute("user", new User());
        return "add-user";
    }

    @PostMapping("/addUser")
    public String addUser(@ModelAttribute("user") User user,
                          RedirectAttributes redirectAttributes,
                          @RequestParam(required = false, name = "admin") String admin,
                          @RequestParam(required = false, name = "manager") String manager) {

        List<String> role = new ArrayList<>();

        if (admin != null) {
            role.add(admin);
        }
        if (manager != null) {
            role.add(manager);
        }

        if (userService.usernameCheck(user.getUsername())) {
            redirectAttributes.addFlashAttribute("added", true);
            redirectAttributes.addFlashAttribute("addedusername", user.getUsername());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userService.saveUser(user);
            roleService.saveRole(new Role("ROLE_EMPLOYEE", user));
            if (!role.isEmpty()) {
                roleService.addRolesToUser(role, user);
            }

            return "redirect:/admins/users";
        } else {
            redirectAttributes.addFlashAttribute("valid", "Username exists try another one");
            return "redirect:/admins/showAddUser";
        }
    }

    @GetMapping("/deleteUser")
    public String deleteUser(@RequestParam("userid") long id, RedirectAttributes redirectAttributes) {
        User user = userService.FindUserById(id);
        redirectAttributes.addFlashAttribute("deletedusername", user.getUsername());
        roleService.deleteRoles(user);
        userService.deleteUser(user);
        redirectAttributes.addFlashAttribute("deleted", true);
        return "redirect:/admins/users";
    }

    @GetMapping("/showEditUser")
    public String showEditUser(@RequestParam("userid") long id, Model theModel) {
        theModel.addAttribute("user", userService.FindUserById(id));
        return "edit-user";
    }

    @PostMapping("/editUser")
    public String editUser(@ModelAttribute("user") User user,
                           RedirectAttributes redirectAttributes,
                           @RequestParam(required = false, name = "admin") String admin,
                           @RequestParam(required = false, name = "manager") String manager) {
        List<String> role = new ArrayList<>();

        if (admin != null) {
            role.add(admin);
        }
        if (manager != null) {
            role.add(manager);
        }
            redirectAttributes.addFlashAttribute("edited", true);
            redirectAttributes.addFlashAttribute("editedusername", user.getUsername());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            roleService.deleteRoles(user);
            userService.saveUser(user);
            User tempUser=userService.FindUserById(user.getId());
            roleService.saveRole(new Role("ROLE_EMPLOYEE", tempUser));
            if (!role.isEmpty()) {
                roleService.addRolesToUser(role, tempUser);
            }
            return "redirect:/admins/users";

    }
}

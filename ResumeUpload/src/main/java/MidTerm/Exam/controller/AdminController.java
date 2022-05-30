package MidTerm.Exam.controller;

import MidTerm.Exam.model.UsersModel;
import MidTerm.Exam.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

public class AdminController {

    @Autowired
    UsersService userService;

    @GetMapping("/admin/users")
    public String showUsers(Model model, Principal principal) {
        if (principal.getName().equals("admin")) {
            List<UsersModel> users = userService.findAllUsers();
            model.addAttribute("errorMessage", null);
            model.addAttribute("users", users);
            return "users";
        }

        return "failure";
    }
}

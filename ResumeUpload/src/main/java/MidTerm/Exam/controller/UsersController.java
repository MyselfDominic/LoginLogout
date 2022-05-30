package MidTerm.Exam.controller;


import MidTerm.Exam.model.ErrorModel;
import MidTerm.Exam.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller

public class UsersController {


    @Autowired
    UsersService userService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/register")
    public String register(Model model, ErrorModel errorMessage) {
        model.addAttribute("applicationName", "Resume upload portal");
        model.addAttribute("error", errorMessage.getErrorMessage());

        return "register";
    }


    @GetMapping("/login")
    public String login() {
        if (isAuthenticated()){
            return "redirect:/";
        }
        return "login_page";
    }


    @GetMapping("/success")
    public String success() {
        return "success";
    }

    private boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || AnonymousAuthenticationToken.class
                .isAssignableFrom(authentication.getClass())) {
            return false;
        }
        return authentication.isAuthenticated();
    }

    @GetMapping("/logout")
	public String logout(Model model, HttpSession httpSession) {
        httpSession.invalidate();
        model.addAttribute("applicationName", "Resume upload portal");
        return "redirect:/";
    }





}

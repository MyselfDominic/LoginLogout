package MidTerm.Exam.controller;

import MidTerm.Exam.model.FileModel;
import MidTerm.Exam.model.UsersModel;
import MidTerm.Exam.service.FileService;
import MidTerm.Exam.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.*;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FileController {

    @Autowired
    FileService fileService;


    @GetMapping("/admin_view")
    public String view(  Model model ){
        List<FileModel> files =  fileService.getfile();
        model.addAttribute("files" , files);
        return "admin_view";
    }


    @PostMapping("/submit")
    public String submit(@Valid UsersModel user, BindingResult binding, Model model) {
        if (binding.hasErrors()) {
            List<ObjectError> allErrors = binding.getAllErrors();
            String errorMessage = null;

            for (ObjectError firstError : allErrors) {
                errorMessage = firstError.getDefaultMessage();
                System.out.println(firstError.getDefaultMessage());
            }

            return "redirect:/register?errorMessage=" + errorMessage;
        }
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        UsersService.saveUser(user);
        return "redirect:/";
    }

    @GetMapping("/upload")
    public String upload(Model model, HttpSession httpSession) {
        model.addAttribute("applicationName", "Resume upload portal");
        model.addAttribute("errorMessage", null);
        model.addAttribute("empty", new ArrayList());
        return "upload";
    }

    @PostMapping("/save")
    public String save(@RequestParam("myresume") MultipartFile resume, UsersModel user, Principal principal) throws IOException {

        String fileLocation = System.getProperty("user.dir") + "/src/main/resources/static/";
        String fileName = resume.getOriginalFilename();

        File mySavedFile = new File(fileLocation + fileName);

        InputStream inputStream = resume.getInputStream();

        OutputStream outputStream = new FileOutputStream(mySavedFile);

        int read = 0;
        byte[] bytes = new byte[10240];

        while ((read = inputStream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, read);
        }

        user = UsersService.getUser(principal.getName());
        user.setResume_link("http://localhost:8080/" + fileName);
        UsersService.saveUser(user);

        return "redirect:/success";
    }

}


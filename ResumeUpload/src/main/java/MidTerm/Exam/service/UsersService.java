package MidTerm.Exam.service;


import MidTerm.Exam.model.UsersModel;
import MidTerm.Exam.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class UsersService {

    @Autowired
    UsersRepository userRepo;

    public static void saveUser(UsersModel user) {
        userRepo.save(user);
    }

    public List<UsersModel> findAllUsers() {
        return userRepo.findAll();
    }

    public String verifyUser(String email, String password, HttpSession httpSession) {

        List<UsersModel> users = userRepo.findByEmail(email);
        if (users.size() == 0) {
            return "user not registered";
        }
        if (users.get(0).getPassword().equals(password)) {

            httpSession.setAttribute("userLoggedIn", "yes");
            httpSession.setAttribute("user", users.get(0));
            httpSession.setAttribute("userName", users.get(0).getUsername());
            httpSession.setAttribute("userMobile", users.get(0).getMobile());
            httpSession.setAttribute("userEmail", users.get(0).getEmail());
            return "success";
        } else {
            return "invalid password";
        }
    }

    public static UsersModel getUser(String username) {
        return userRepo.getByUsername(username);
    }


}

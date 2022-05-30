package MidTerm.Exam.repository;

import MidTerm.Exam.model.UsersModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsersRepository extends JpaRepository<UsersModel, Integer> {

    List<UsersModel> findByEmail(String email);
    UsersModel getByUsername(String username);
}




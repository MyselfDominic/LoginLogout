package MidTerm.Exam.repository;

import MidTerm.Exam.model.FileModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileModel, Long> {

}

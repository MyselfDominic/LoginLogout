package MidTerm.Exam.service;

import MidTerm.Exam.model.FileModel;
import MidTerm.Exam.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {

    @Autowired
    FileRepository fileRepository;

    public void save(FileModel fileModel) {

        fileRepository.save(fileModel);
    }

    public List<FileModel> getfile() {
        return fileRepository.findAll();
    }
}
package MidTerm.Exam.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ErrorModel {
    String errorMessage;
}
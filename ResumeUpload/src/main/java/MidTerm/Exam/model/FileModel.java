package MidTerm.Exam.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class FileModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String filename;
}


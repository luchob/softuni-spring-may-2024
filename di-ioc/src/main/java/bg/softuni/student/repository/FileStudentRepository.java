package bg.softuni.student.repository;

import bg.softuni.student.model.Student;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Repository;


@Repository
public class FileStudentRepository implements StudentRepository, BeanNameAware {

    private String name;

    public FileStudentRepository() {
        System.out.println("Instantiating... ");
    }

    @Override
    public List<Student> getAllStudents() {
        return
                new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream("students.csv")))
                        .lines()
                        .map(this::asStudent)
                        .toList();


    }

    private Student asStudent(String s) {
        var line = s.split(",");
        return new Student(line[0].trim(), LocalDate.parse(line[1].trim()));
    }

    @PostConstruct
    public void init() {
        System.out.println(this.name + " manages " + getAllStudents().size() + " students.");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Clean up of file student repo. Bye!");
    }

    @Override
    public void setBeanName(String name) {
        this.name = name;
    }
}

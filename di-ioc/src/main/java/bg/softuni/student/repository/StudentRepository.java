package bg.softuni.student.repository;

import bg.softuni.student.model.Student;

import java.util.List;

public interface StudentRepository {
    List<Student> getAllStudents();
}

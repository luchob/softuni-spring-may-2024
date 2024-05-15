package bg.softuni.student;

import bg.softuni.student.model.Student;

import java.util.Set;

public interface StudentService {

    Set<Student> findYoungestStudents();

}

package bg.softuni.student.repository;

import bg.softuni.student.model.Student;

import java.time.LocalDate;
import java.util.List;

public class InMemoryStudentRepository implements StudentRepository {
    private final List<Student> students = List.of(
            new Student("Nina Bojinova", LocalDate.of(1977, 12, 9)),
            new Student("Lachezar Balev", LocalDate.of(1979, 3, 7)),
            new Student("Lachezar Balev Fake", LocalDate.of(1979, 3, 7))
    );

    @Override
    public List<Student> getAllStudents() {
        return students;
    }
}

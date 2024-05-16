package bg.softuni.student.repository;

import bg.softuni.student.model.Student;
import java.util.ArrayList;
import java.util.List;

public class CompountStudentRepository implements StudentRepository {

  private final List<StudentRepository> allRepos;

  public CompountStudentRepository(List<StudentRepository> allRepos) {
    this.allRepos = allRepos;
  }

  @Override
  public List<Student> getAllStudents() {

    List<Student> allStudents = new ArrayList<>();

    allRepos.forEach(r -> allStudents.addAll(r.getAllStudents()));

    return allStudents;
  }
}
